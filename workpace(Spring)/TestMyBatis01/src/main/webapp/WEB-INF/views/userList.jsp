<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
</head>
<body>
	<div align="center">
		
		
		<c:choose>
			<c:when test="${LoginUser == null}">
				<button type="button" >로그인</button>
			</c:when>
			<c:otherwise>
				<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/user_LogOut.do'">
				<input type="button" value="회원정보수정" onclick="location.href='<%=request.getContextPath()%>/user_LogInfo.do'">
				<input type="button" value="회원탈퇴" onclick="location.href='<%=request.getContextPath() %>/user_delete.do'">
			</c:otherwise>
		</c:choose>
		<table border="1" width="600" cellspacing="0">
			<tr>
				<th>아이디</th><th>이름</th><th>닉네임</th><th>핸드폰번호</th>
			</tr>
			<tr>
			<c:set var="list" value="${UserList }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="u">
					<tr>
						<td>${u.getUserId() }</td>
						<td><a href="userListInfo.do?ListUserId=${u.getUserId() }">
						${u.getUserName() }</a></td>
						<td>${u.getUserNick() }</td>
						<td>${u.getUserPhone() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
		   	<tr>
		   		<td colspan="4" align="center">
		   		<h3>검색된 레코드가 없습니다</h3>
		   		</td>
		   	</tr>
		   </c:if>		   
			</tr>
		</table>
	</div>

</body>
</html>