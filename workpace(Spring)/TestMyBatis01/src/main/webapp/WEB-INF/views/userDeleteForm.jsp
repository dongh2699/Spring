<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
		<%
				session.getAttribute("LoginUser");
		%>
		<div align="center">
			<form method="post" action="<%= request.getContextPath()%>/user_deleteOk.do">
			<c:set var="dto" value="${LoginUser }"></c:set>
			<c:if test="${!empty dto }">
			<input type="hidden" name="userId" value="${dto.getUserId() }">
			<h3>${dto.getUserName() }님 비밀번호를 입력해주세요</h3>
			<table>
				<tr>
					<th>아이디</th>
					<td>${dto.getUserId() }</td>
				</tr>
				<tr>
					<th>비밀번호</th>
				<td>
					<input type="password" name="userPwd">
				</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<input type="submit" value="탈퇴하기">
					<input type="reset" value="초기화">
					<input type="button" value="뒤로가기"
									onclick="location.href='<%=request.getContextPath()%>/user_list.do'">
				</td>
			</table>
			</c:if>
			</form>
		</div>
</body>
</html>