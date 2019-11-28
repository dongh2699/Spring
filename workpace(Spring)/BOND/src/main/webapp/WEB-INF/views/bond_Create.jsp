<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | CREATE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/bondcreate.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
</head>
<body>

<header>
	<%@ include file="particials/header.jsp"%>
	</header>
	
	<main>
	<form method="post" action="<%=request.getContextPath()%>/bond_Create_Ok"  enctype="multipart/form-data">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
		<input type="text" name="b_Name" placeholder="BOND NAME" required="required">
		</div>
		
		<div class="col-md-12">
		<textarea rows="10" cols="40" name="b_Intro" required="required"></textarea>
		</div>
		
		<c:set var="categoryList" value="${List }"></c:set>
			<c:if test="${!empty categoryList }">
			<div class="col-md-12">
					<select name="b_category">
			<c:forEach items="${categoryList }" var="i">
						<option value="${i.getCt_no() }">${i.getCt_name()}
				</c:forEach>
					</select>
					</div>
			</c:if>
			
			<div class="col-md-12">
		<input type="file" name="bond_picture"  accept="image/*" required="required">
		</div>
		
		<div class="col-md-12">
	<input type="submit" value="BOND 생성">
	</div>
	</div>
	</div>
	</form>
	</main>
	
	<footer>
		<%@ include file="particials/footer.jsp"%>
	</footer>
	
</body>
</html>