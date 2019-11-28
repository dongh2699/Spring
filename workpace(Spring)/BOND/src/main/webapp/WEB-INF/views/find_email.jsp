<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | FIND_EMAIL</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/find_email.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
</head>
<body>

   <header>
      <%@ include file="/WEB-INF/views/particials/header.jsp"%>

   </header>
   <main>
            <form action="<%=request.getContextPath()%>/find_email_ok" method="post">
      <div class="container-fluid">
         <div class="row">
         
         	<div class="col-md-12">
         		<p>이메일 찾기</p>
         	</div>
               <div class="col-md-12">
                  <span class="name">
                     <input type="text" name="m_Name" placeholder="Name" required="required">
                  </span>
                  </div>
                  
                  <div class="col-md-12">
                  <span class="phone">
                     <input type="text" name="m_Phone" placeholder="(-)없이 입력하세요" required="required">
                  </span>
                  </div>
                  
                  <div class="col-md-12">
                  <span class="submit">
                  <input type="submit" value="이메일찾기">
                  </span>
               </div>
         </div>
      </div>
            </form>

   </main>
   <footer>
      <%@ include file="/WEB-INF/views/particials/footer.jsp"%>
   </footer>

</body>
</html>