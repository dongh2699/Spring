<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | FIND_PWD</title>
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/find_pwd.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/memberPwd.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
   <header>
      <%@ include file="/WEB-INF/views/particials/header.jsp"%>
   </header>
   <main>
               <form action="<%=request.getContextPath()%>/moidfy_pwd"
                     method="POST" onsubmit="return AuthNumber_Chk()">
      <div class="container-fluid">
         <div class="row">
            <div class="col-md-12">
               <input type="hidden" name="AuthNumber" id="AuthNumber">
               </div>            
                  		<div class="col-md-12">
                  		<span class="email">
                        <input type="email" id="m_Email" name="m_Email"   placeholder="E-mail" required="required"> 
                        </span>
                        </div>                      
                        <div class="col-md-12">
                        <span class="btn">
                        <input type="button" value="이메일 전송" id="mailSendBtn" onclick="mail_Chk()">
                        </span>
                        </div>                  
                        <div class="col-md-12" id="AuthChk">
                        <span class="text">
                           <input type="text" name="inputNumber" id="inputNumber" placeholder="checkword" required="required">
                           </span>
                           </div>                   
                           <div class="col-md-12" id="AuthChk2"> 
                           <span class="check">
                           		<input type="submit" value="인증번호확인" >
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