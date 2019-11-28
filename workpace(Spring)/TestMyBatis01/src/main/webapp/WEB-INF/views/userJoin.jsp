<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserJoinPage</title>
<%-- jQuery 라이브러리 링크 --%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>



<%-- 회원 가입에 있어서 데이터를 검증하는 외부 자바스크립트 파일 링크 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user.js"></script>
<script type="text/javascript">
//아이디 유효성 검사(1 = 중복 / 0 != 중복)
$(function() {
	$("#user_id").blur(function() {
		$("#idcheck").hide();  // span 태그 idcheck 영역을 숨기자
		var user_id = $('#user_id').val();
		var id_chk = $('#id_chk').val();
		// 입력 길이 체크
		if($.trim($("#user_id").val()).length < 2) {
			var warningTxt = 
			 '<font color="red">아이디는 2자 이상이어야 합니다.</font>';
			$("#idcheck").text('');  // idcheck 영역 초기화
			$("#idcheck").show();  // span 태그 idcheck 영역을 보이자
			$("#idcheck").append(warningTxt);
			$("#user_id").val('').focus();
			return false;
		};
		
		// 입력 길이 체크
		if($.trim($("#user_id").val()).length > 20) {
			var warningTxt = 
			 '<font color="red">아이디는 20자 미만이어야 합니다.</font>';
			$("#idcheck").text('');  // idcheck 영역 초기화
			$("#idcheck").show();  // span 태그 idcheck 영역을 보이자
			$("#idcheck").append(warningTxt);
			$("#user_id").val('').focus();
			return false;
		};
		$.ajax({
			type : "POST",          // 데이터 전송 방식
			url : "${pageContext.request.contextPath}/user_IdCheck.do",    // 파일 주소와 경로
			data : { "user_id" : $("#user_id").val()

			},	

			success : function(data) {
				console.log("flase = 중복o / true = 중복x : "+ data);							
				
				if (data == "false") {
					var warningTxt = 
						'<font color="red">중복 아이디입니다.</font>';
					$("#idcheck").text('');  // idcheck 영역 초기화
					$("#idcheck").show();  // span 태그 idcheck 영역을 보이자
					$("#idcheck").append(warningTxt);
					
					
					} else { // 아이디가 중복이 되지 않는 경우
						var warningTxt = 
							'<font color="blue">사용 가능한 아이디입니다.</font>';
						$("#idcheck").text('');  // idcheck 영역 초기화
						$("#idcheck").show();  // span 태그 idcheck 영역을 보이자
						$("#idcheck").append(warningTxt);
						$("#user_pass").val('').focus();
						$("#id_chk").val('true');
						return false;
						
					}
				}, 	error : function(e) {  // 비동기통신이 실패한 경우
					alert("data error")
				}
			});
			return false;
		});
});
</script>

</head>
<body>
	<div align="center">
		
			<h3>환영합니다.</h3>
			<form method="post" action="<%=request.getContextPath() %>/user_insert.do"
					onsubmit="return user_check()">
			<input type="hidden" id="id_chk" value="false">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="user_id" required="required">
						<br>
		        
		        <%-- 경고문이 출력되는 위치 --%>
		        <span id="idcheck"></span>				
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="userPwd" id="user_pass" required="required">
					</td>
				</tr>
				<tr>
					<th>비밀먼호확인</th>
					<td>
						<input type="password" name="userPwdChk" id="user_pass2" required="required">
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="userName" id="user_name" required="required">
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input type="text" name="userNick" id="user_nick" required="required">
					</td>
				</tr>
				<tr>
					<th>사진경로</th>
					<td>
						<input type="text" name="userCam">
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" name="userBirth" id="user_birth" required="required">
					</td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td>
						<input type="text" name="userPhone" id="user_phone" required="required">
					</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
				<input type="submit" value="가입" id="reg_submit">
				<input type="reset" value="초기화">				
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					회원이세요?<input type="button" value="로그인하기" onclick="location.href='<%=request.getContextPath()%>/user_login.do'">
					</td>
					</tr>
					<tr>
					<td>
				
					</td>
				</tr>
			</table>
			</form>
		</div>

</body>
</html>