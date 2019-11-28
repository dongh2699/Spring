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
      <div class="container-fluid">
         <div class="row">
            <form action="<%=request.getContextPath()%>/find_email_final_ok" method="post">
               <input type="hidden" name="m_Email" value="${m_Email }">
               <input type="hidden" name="dice" value="${dice }">
               인증번호 입력 : <input type="text" name="email_injeung" placeholder="인증번호를 입력하세요.">
               <br> <br>
               <button type="submit" name="submit">인증번호 전송</button>
            </form>

      </div>
      </div>

   </main>
   <footer>
      <%@ include file="/WEB-INF/views/particials/footer.jsp"%>
   </footer>

</body>
</html>