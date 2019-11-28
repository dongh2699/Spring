<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<header>
	<%@ include file="particials/header.jsp"%></header>
	<main>
	<c:set var="list" value="${List }"></c:set>
	<c:if test="${!empty list}">
	<c:forEach items="${list }" var="i">
		<input type="button" name="${i.getB_No() }" value="${i.getB_Name() }" 
		onclick="location.href='<%=request.getContextPath()%>/bond_Detail?bond_no=${i.getB_No() }'">
		
	</c:forEach>
	</c:if>
	</main>
	<input type="button" value="블로그 생성"
		onclick="location.href='<%=request.getContextPath()%>/bond_Create'">
	<footer>
	<%@ include file="particials/footer.jsp"%></footer>
</body>
</html>