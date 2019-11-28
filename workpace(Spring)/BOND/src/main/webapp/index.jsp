<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/common.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<title>BOND</title>

</head>
<body>
	<header>
		<%@ include file="/WEB-INF/views/particials/header.jsp"%>

	</header>
	<main>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-3"></div>

			<div class="col-md-3">
				<img
					src="${pageContext.request.contextPath }/resources/img/login_img1.gif"
					class="for_desktop_logo img-fluid">
			</div>

			<div class="col-md-3">
				<h1>
					모임이 쉬워진다!<br>우리끼리 본드
				</h1>
				<h6>당신의 모임도 BOND로 시작하세요</h6>
				<p>
					<img
						src="${pageContext.request.contextPath }/resources/img/bondmain.png"
						class="bondmain img-fluid" id="logo-img"> <img
						src="${pageContext.request.contextPath }/resources/img/bondmain2.png"
						class="bondmain img-fluid" id="logo-img"> <img
						src="${pageContext.request.contextPath }/resources/img/bondmain3.png"
						class="bondmain img-fluid" id="logo-img">
				</p>
				<script type="text/javascript">
					var slideIndex = 0;
					carousel();

					function carousel() {
						var i;
						var x = document.getElementsByClassName("bondmain");

						for (i = 0; i < x.length; i++) {
							x[i].style.display = "none";
						}
						slideIndex++;
						if (slideIndex > x.length) {
							slideIndex = 1
						}
						x[slideIndex - 1].style.display = "block";
						setTimeout(carousel, 2000); // Change image every 2 seconds
					}
				</script>
				<p>
					<a href="<%=request.getContextPath()%>/login"><img
						src="${pageContext.request.contextPath }/resources/img/login_button1.jpg"
						class="img-fluid"></a>

				</p>


				아이디가 없으신가요? <a href="<%=request.getContextPath()%>/join"><b>회원가입하기</b></a>

				<div class="col-md-3"></div>

			</div>
		</div>
	</div>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/particials/footer.jsp"%>
	</footer>


</body>
</html>
