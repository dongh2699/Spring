<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | USER_DETAIL</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/userDetail.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
  <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member.js"></script>
 
<meta name="viewport" content="width=device-width, initial-scale=1">
 <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

    <script type="text/javascript">
  

</script>
</head>
<body>
	<header>
		<%@ include file="particials/header.jsp"%>

	</header>
	
	<main>
	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12">
			<c:set var="login_member" value="${Login_Member }"></c:set>
			<c:if test="${!empty login_member }">
		<form method="post" action="<%=request.getContextPath()%>/modify_Member" onsubmit="return member_check()" enctype="multipart/form-data">
		<div class="col-md-12">
			<p>회원정보 관리</p>
		</div>
			<%-- 중복확인 유무를 하지않고 submit 했을때 페이지넘기는걸 막기위한 히든값 --%>
			
			<div class="col-md-12">
			
			</div>
			
			<img src="${pageContext.request.contextPath }/resources/upload/${login_member.getM_Picture() }" class="img-fluid">
			<div class="col-md-12">
					<input type="file" name="member_Picture" placeholder="Picture"  accept="image/*" required="required">
					</div>
					
					<div class="col-md-12">
						<input type="text" name="m_Email" value="${login_member.getM_Email() }"
						placeholder="이메일" required="required" id="m_Email" readonly>
				</div>
				
				
				<div class="col-md-12">
				<input type="password" name="m_Pw" id="m_Pw"
						placeholder="Password" required="required">
				</div>
				
				<div class="col-md-12">
						<input type="password" name="PwChk" id="PwChk"
						placeholder="Check-Password" required="required" >
				</div>
				
				<div class="col-md-12">
						<input type="text" name="m_Name"  value="${login_member.getM_Name() }"
						placeholder="Name" required="required">
				</div>
				
				<div class="col-md-12">
						<input type="text" name="m_Nickname" value="${login_member.getM_Nickname() }"
						placeholder="NickName" required="required">
				</div>
					
					<div class="col-md-12">
					<input type="date" name="m_Birth" placeholder="Birthday" 
					value="${login_member.getM_Birth().substring(0,4) }-${login_member.getM_Birth().substring(4,6) }-${login_member.getM_Birth().substring(6,8) }"
						required="required">
					</div>
						
						<div class="col-md-12">
						<input type="text" name="m_Phone" placeholder="Phone" value="${login_member.getM_Phone() }"
						required="required">
						</div>
						
						<div class="col-md-12">
						<input type="submit" value="수정하기">
					<input type="button" value="삭제하기" onclick="delete_confirm()">
					<input type="button" value="뒤돌아가기" onclick="history.back()" >
					</div>
					
		</form>
</c:if>
</div>
	</div>
	</div>
	</main>
	<footer>
		<%@ include file="/WEB-INF/views/particials/footer.jsp"%>
	</footer>
</body>
</html>