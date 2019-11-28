<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 리스트 상세 정보</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${ListUserInfo }"></c:set>
		<hr width="50%" color="red">
			<h3>${dto.getUserId() }님의 상세정보</h3>
		<hr width="50%" color="red">
	
		<c:if test="${!empty dto }">
			<table border="1" width="450" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td>${dto.getUserId() }</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${dto.getUserName() }</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>${dto.getUserNick() }</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>${dto.getUserBirth() }</td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td>${dto.getUserPhone() }</td>
				</tr>
				<tr>
							<td colspan="2" align="center">
								<input type="button" value="뒤로가기"
									onclick="location.href='<%=request.getContextPath()%>/user_list.do'">
					</tr>
			</table>
		</c:if>
		
	</div>
</body>
</html>