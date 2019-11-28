<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | MODIFY_PWD</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/modify_pwd.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<script type="text/javascript"
   src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
</head>
<body>
   <header>
      <%@ include file="/WEB-INF/views/particials/header.jsp"%>
   </header>
   
   
   <main>
   <div class="container-fluid" align="center">
         <div class="row">
            <div class="col-md-12">
   
      <form method="post" action="<%=request.getContextPath()%>/modify_pwd_ok"
         onsubmit="return member_check()">
         <input type="hidden" name="m_Email" value="${m_Email }">
         
         <span class="title">
               변경할 비밀번호
               </span>
               </div>
               
               <div class="col-md-12">
               
               <input type="password" name="m_Pw" id="m_Pw"
                  required="required">
                  </div>
                  
                  <div class="col-md-12">
                  <span class="title">
            비밀번호 확인
            </span>
            
            </div>
            
            <div class="col-md-12">
            <input type="password" name="PwChk" id="PwChk"
                  required="required">
                  
                  </div>
                  
                  <div class="col-md-12">
                  <input type="submit" value="비밀번호 수정">
                  
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