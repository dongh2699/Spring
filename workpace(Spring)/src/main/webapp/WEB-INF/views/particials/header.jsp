<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  
</head>
<body>
   
   <div class="container-fluid">
      <div class="row">
     
       
       <c:choose>
        <c:when test="${empty login}">
           <div class="col-md-10">
          <a href="<%=request.getContextPath()%>"><img src="${pageContext.request.contextPath }/resources/img/logo.jpg" class="logo"></a>
        </div>
        <div class="col-md-2">
            <h6><a href="<%=request.getContextPath()%>/join">회원가입</a></h6>
            <a href="<%=request.getContextPath() %>/login"><img src="${pageContext.request.contextPath }/resources/img/login_button4.jpg" class="btn4"> </a>
        </div>
       
           </c:when>
           <c:when test="${!empty login}">
              <div class="col-md-10">
          <a href="<%=request.getContextPath()%>/bond"><img src="${pageContext.request.contextPath }/resources/img/logo.jpg" class="logo"></a>
        </div>
                 <div class="col-md-2">
            <h6><a href="<%=request.getContextPath() %>/check_Pw"><i class="far fa-address-card"></i></a>
            <a href="<%=request.getContextPath() %>/log_out">로그아웃</a></h6>
        </div>
        
      </c:when>
</c:choose>
 </div>
      </div>
</body>
</html>