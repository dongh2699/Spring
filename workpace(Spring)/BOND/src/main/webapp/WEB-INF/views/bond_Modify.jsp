<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
			    <c:set var= "ct_no" value="${Ct_no }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BOND | MODIFY</title>

<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_Modify.css">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
  <script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/right_Aside.js"></script>
<script type="text/javascript">
function delete_confirm(b_No) {

	if(confirm("정말로 BOND를 삭제하시겠습니까?")){
		location.href="/BOND/bond_Delete_OK?bond_No="+b_No;
	}
	
}

window.onload=function(){

document.modify_form.b_category.value=${ct_no};

}
</script>

</head>
<body>

<header>
   <%@ include file="particials/header.jsp"%>
   </header>
   
   
   <c:set var="bond" value="${Bond }"></c:set>
   <c:set var="member_Count" value="${Member_Count }"></c:set>
   <c:set var="b_No" value="${Bond.getB_No() }"></c:set>
   <!-- 전체글 사진첩 멤버 -->
   <nav>
		<%@ include file="particials/nav.jsp"%>
	</nav>
   
   <main>
   <div class="container">
      <div class="row">
      <div class="col-md-3">
  
   <aside id="leftaside">
					<%@ include file="particials/left_Aside.jsp"%>
	</aside>
   
   <!-- 게시물 -->
   </div>
   
   <div class="col-md-6">
   <!-- layout 상속 받아서 main만 변경  -->
   

	<form method="post" action="/BOND/bond_Modify_Ok" name="modify_form" enctype="multipart/form-data">
		<input type="hidden" name="bond_No" id="b_No" value="${bond.getB_No()}"> 
		
		<div class="col-md-12">
			<span>BOND 사진</span>
		</div>
		
		<div class="col-md-12">
			  <img src="${pageContext.request.contextPath }/resources/upload/${bond.getB_Picture() }" alt="블로그 이미지" class="current_blog_Img">
		</div>
		
		
		<div class="col-md-12">
				<input type="file" name="blog_Picture" value="${bond.getB_Picture() }" accept="image/*" required="required">
		</div>
		
		<div class="col-md-12">
			<span>BOND 이름</span>
		</div>
		
		<div class="col-md-12">
		<input type="text" name="b_Name" placeholder="BOND NAME" value="${bond.getB_Name() }" required="required">
		</div>
		
		<div class="col-md-12">
			<span>BOND 소개</span>
		</div>
		
		<div class="col-md-12">
		<textarea rows="10" cols="40" name="b_Intro" required="required">${bond.getB_Intro() }</textarea>
		</div>
		
		<div class="col-md-12">
			<span>BOND 카테고리</span>
		</div>
		
		<div class="col-md-12">
			<c:set var="categoryList" value="${List }"></c:set>
				<c:if test="${!empty categoryList }">
					<select required="required" name="b_category">
						<c:forEach items="${categoryList }" var="i">
							<option value="${i.getCt_no() }" > ${i.getCt_name()} 
						</c:forEach>
					</select>
				</c:if>
			</div>
			
		<div class="col-md-12">
			<input type="submit" value="BOND 수정">
			<input type="button" value="BOND 삭제" onclick="delete_confirm(${bond.getB_No() })">
		</div>
	</form>
	
	</div>

   
   <div class="col-md-3">
 
   <%@ include file="particials/right_Aside.jsp"%>
   
   </div>
   </div>
   
   </div>
           </main>
           
</body>
</html>