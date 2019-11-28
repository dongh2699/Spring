<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/check_Pw.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<title>BOND | CHECK_PW</title>
</head>
<body>

<header>
	<%@ include file="particials/header.jsp"%>
	</header>

<main>
	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12">개인정보 수정</div>
	<div class="col-md-12"><p>개인정보 수정과 계정 삭제를 위해서는<br>다시 한 번 비밀번호를 입력해야 합니다.</p></div>
			<div class="col-md-12">
		<form action="<%=request.getContextPath() %>/userDetail" method="post">
			<input type="password" name="m_Pw" placeholder="비밀번호" required="required">
			</div>
				<div class="col-md-12">
					<input type="submit" value="확인">
				</div>
		</form>
	</div>
	</div>
</main>

<footer>
	<%@ include file="particials/footer.jsp"%></footer>

</body>
</html>