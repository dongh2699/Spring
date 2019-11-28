<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member.js"></script>
 
<meta name="viewport" content="width=device-width, initial-scale=1">
 <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript">
function delete_confirm() {

		if(confirm("정말로 아이디를 삭제하시겠습니까")){
			location.href="/BOND/delete_Member";
		}
		
	}
</script>
    <script type="text/javascript">
$(function(){
   $("#delete_btn").click(function() { // 이메일 입력란에 입력하고 다른 곳으로 넘어갈때 이벤트함수
         var m_Email = $('#m_Pw').val();
         var id_chk = $('#PwChk').val();


         $.ajax({
            type : "POST",          // 데이터 전송 방식
            url : "${pageContext.request.contextPath}/pw_Check",    // 파일 주소와 경로
            data : { "m_Pw" : $("#m_Pw").val()

            },   

            success : function(data) {
               
               if (data == "false") {
                  alert("비밀번호가 틀렸습니다!");
                  $("#m_Pw").text('');  // password 영역 초기화
                  $("#PwChk").text('');  // password check 영역 초기화
                  } else { // 아이디가 중복이 되지 않는 경우
                	  alert("비밀번호가 틀렸습니다!");
                      $("#m_Pw").text('');  // password 영역 초기화
                      $("#PwChk").text('');  // password check 영역 초기화
                     return false;
                     
                  }
               },    error : function(e) {  // 비동기통신이 실패한 경우
                  alert("data error")
               }
            });



   
         return false;
      })
   
}) // 무명함수 end


</script>
</head>
<body>
	<header>
		<%@ include file="particials/header.jsp"%>

	</header>
	<main>
	<div>
		<form method="post" action="<%=request.getContextPath()%>/modify_Member"
			onsubmit="return member_check()">

			<%-- 중복확인 유무를 하지않고 submit 했을때 페이지넘기는걸 막기위한 히든값 --%>
			<c:set var="login_member" value="${Login_Member }"></c:set>
			<c:if test="${!empty login_member }">
			<input type="hidden" name="m_Email" value="${login_member.getM_Email() }">
			<table>
				<tr>
					<th>Picture</th>
					<td><img src="${login_member.getM_Picture() }" class="img-fluid">
					<input type="file" name="m_Picture" placeholder="Picture">
					</td>
				</tr>
				<tr>
					<th>Email</th>
					<td><label>${login_member.getM_Email() }</label>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="m_Pw" id="m_Pw"
						placeholder="Password" required="required"></td>
				</tr>
				<tr>
					<th>Check-Password</th>
					<td><input type="password" name="PwChk" id="PwChk"
						placeholder="Check-Password" required="required" ></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><input type="text" name="m_Name"  value="${login_member.getM_Name() }"
						placeholder="Name" required="required"></td>
				</tr>
				<tr>
					<th>NickName</th>
					<td><input type="text" name="m_Nickname" value="${login_member.getM_Nickname() }"
						placeholder="NickName" required="required"></td>
				</tr>
				<tr>
					<th>Birth</th>
					<td><input type="date" name="m_Birth" placeholder="Birthday" 
					value="${login_member.getM_Birth().substring(0,4) }-${login_member.getM_Birth().substring(4,6) }-${login_member.getM_Birth().substring(6,8) }"
						required="required"></td>
				</tr>
				<tr>
					<th>Phone</th>
					<td><input type="text" name="m_Phone" placeholder="Phone" value="${login_member.getM_Phone() }"
						required="required"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="수정하기">
					<input type="button" value="삭제하기" onclick="delete_confirm()">
					<input type="button" value="뒤돌아가기" onclick="history.back()" >
					</td>
				</tr>
			</table>
</c:if>
		</form>
	</div>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/particials/footer.jsp"%>
	</footer>
</body>
</html>