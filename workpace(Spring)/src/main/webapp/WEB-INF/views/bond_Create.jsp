<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="<%=request.getContextPath()%>/bond_Create_Ok">
		<input type="text" name="b_Name" placeholder="BOND NAME" required="required">
		<textarea rows="10" cols="40" name="b_Intro"></textarea>
		<c:set var="categoryList" value="${List }"></c:set>
			<c:if test="${!empty categoryList }">
			<c:forEach items="${categoryList }" var="i">
					<input type="checkbox" value="${i.getCt_no() }" name="b_category">${i.getCt_name()} 
				</c:forEach>
			</c:if>
		<input type="file" name="b_Picture" >
	<input type="submit" value="BOND 생성">
	</form>
	
</body>
</html>