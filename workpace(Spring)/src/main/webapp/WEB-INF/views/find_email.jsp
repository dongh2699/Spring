<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

   <header>
      <%@ include file="/WEB-INF/views/particials/header.jsp"%>

   </header>
   <main>
      <div class="container-fluid" align="center">
         <div class="row">
            <form action="<%=request.getContextPath()%>/find_email_ok" method="post">
               <div class="col-md-12">
                  <span class="name">
                     <input type="text" name="m_Name" placeholder="Name" required="required">
                  </span>
                  
                  <span class="phone">
                     <input type="text" name="m_Phone" placeholder="(-)없이 입력하세요" required="required">
                  </span>
                  
                  <input type="submit" value="이메일찾기">
               </div>
            </form>
         </div>
      </div>

   </main>
   <footer>
      <%@ include file="/WEB-INF/views/particials/footer.jsp"%>
   </footer>

</body>
</html>