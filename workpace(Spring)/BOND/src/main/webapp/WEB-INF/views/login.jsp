<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | LOG IN</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath }/resources/css/login.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/common.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
</head>
<body>
   <header>
      <%@ include file="particials/header.jsp"%>
   </header>
   <main>
   <div class="container-fluid">
        <div class="content">
       
       <form action="<%=request.getContextPath()%>/login_Ok" method="post">
           <div class="login">
            <span class="login">로그인</span>
            <span class="email">
                <i class="fas fa-user"></i>
                <input type="email" placeholder="이메일" name="m_Email" required="required">
            </span>
                
            <span class="pwd">
                <i class="fas fa-lock"></i>
                <input type="password" placeholder="비밀번호" name="m_Pw" required="required">
            </span>
            
            <span class="subbtn">
                <input type="submit" value="로그인" >
            </span>
        
           </div>
           
           <div class="find">
              <a href="<%=request.getContextPath()%>/find_email">
                  <img src="${pageContext.request.contextPath }/resources/img/find_email.gif" class="find_email"> 
            </a>
            
            <a href="<%=request.getContextPath()%>/find_pwd">
               <img src="${pageContext.request.contextPath }/resources/img/find_pwd.gif" class="find_pwd">
            </a>
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