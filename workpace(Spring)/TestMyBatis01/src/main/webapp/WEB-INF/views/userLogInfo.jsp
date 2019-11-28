<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 개인 정보</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${userLogInfo }"></c:set>
		<hr width="50%" color="red">
			<h3>${dto.getUserId() }님의 개인정보</h3>
		<hr width="50%" color="red">
		<form method="post"
						action="<%=request.getContextPath()%>/userInfo_edit.do">
		<c:if test="${!empty dto }">
			<table border="1" width="450" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input name="userId" value="${dto.getUserId() }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="userPwd" value="${dto.getUserPwd() }" id="user_pass"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="userName" value="${dto.getUserName() }"></td>
				</tr>
				<tr>
					<th>사진경로</th>
					<td><input name="userCam" value="${dto.getUserCam() }"></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input name="userNick" value="${dto.getUserNick() }"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input name="userBirth" value="${dto.getUserBirth() }"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input name="userPhone" value="${dto.getUserPhone() }"></td>
				</tr>
				<tr>
							<td colspan="2" align="center">
								<input type="submit" value="수정">
								<input type="reset" value="취소">
								<input type="button" value="뒤로가기"
									onclick="location.href='<%=request.getContextPath()%>/user_list.do'">
					</tr>
			</table>
		</c:if>
		</form>
	</div>
</body>
</html>