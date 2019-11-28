<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
   
  
  <style type="text/css">
html.open {
  overflow: hidden;
}

.Hamburgerbtn {
  width: 50px;
  height: 50px;
  position: absolute;
  right: 0px;
  top: 0px;
  z-index: 1;
  background-image: url("https://firebasestorage.googleapis.com/v0/b/testsist12341.appspot.com/o/menu.png?alt=media&token=88599640-f1e2-4054-b339-aa0ba38ea5e5");
  background-size: 50%;
  background-repeat: no-repeat;
  background-position: center;
  cursor: pointer;
  display: none;
}

.close {
  width: 50px;
  height: 50px;
  position: absolute;
  right: 0px;
  top: 0px;
  background-image: url("https://firebasestorage.googleapis.com/v0/b/testsist12341.appspot.com/o/close.png?alt=media&token=a03b2ad5-223c-4fed-9c50-3554faf6857a");
  background-size: 50%;
  background-repeat: no-repeat;
  background-position: center;
  cursor: pointer;
}

#menu {
  width: 150px;
  height: 100%;
  position: fixed;
  top: 0px;
  right: -152px;
  z-index: 10;
  border: 1px solid #c9c9c9;
  background-color: white;
  text-align: center;
  transition: All 0.2s ease;
  -webkit-transition: All 0.2s ease;
  -moz-transition: All 0.2s ease;
  -o-transition: All 0.2s ease;
}

#menu.open {
  right: 0px;
}

.page_cover.open {
  display: block;
}

.page_cover {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0px;
  left: 0px;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 4;
  display: none;
}

#menu li {
	list-style: none;
    height: 50px;
    line-height: 50px;
    color: black;
}

#menu li a {
	color: black;
}

@media screen and (max-width: 425px) {
	.Hamburgerbtn {
		display: block;
	}
}
</style>
 
</head>
<body>
   
     
       
       <c:choose>
        <c:when test="${empty login}">
   <div class="container-fluid">
      <div class="row">
           <div class="col-md-10">
          <a href="<%=request.getContextPath()%>"><img src="${pageContext.request.contextPath }/resources/img/logo.jpg" class="logo"></a>
          
          
          <div class="Hamburgerbtn">
</div>
<div onclick="history.back();" class="page_cover"></div>
<div id="menu">
  <div onclick="history.back();" class="close"></div>
	  <li><a href="<%=request.getContextPath()%>">HOME</a></li>
	  <li><a href="<%=request.getContextPath() %>/login">로그인</a></li>
	  <li><a href="<%=request.getContextPath()%>/join">회원가입</a></li>
</div>
<script type="text/javascript">
$(".Hamburgerbtn").click(function() {
	  $("#menu,.page_cover,html").addClass("open");
	  window.location.hash = "#open";
	});

	window.onhashchange = function() {
	  if (location.hash != "#open") {
	    $("#menu,.page_cover,html").removeClass("open");
	  }
	};
</script> 
         
        </div>
        <div class="col-md-2">
            <h6><a href="<%=request.getContextPath()%>/join">회원가입</a></h6>
            <a href="<%=request.getContextPath() %>/login"><img src="${pageContext.request.contextPath }/resources/img/login_button4.jpg" class="btn4"> </a>
        </div>
       
           </div>
      </div>
           </c:when>
           
           <c:when test="${!empty login}">
              <div class="container">
      <div class="row">
              <div class="col-md-3">
          <a href="<%=request.getContextPath()%>/bond"><img src="${pageContext.request.contextPath }/resources/img/logo.jpg" class="logo"></a>
          
           <div class="Hamburgerbtn">
</div>
<div onclick="history.back();" class="page_cover"></div>
<div id="menu">
  <div onclick="history.back();" class="close"></div>
	  <li><a href="<%=request.getContextPath()%>/bond">HOME</a></li>
	  <li><a href="<%=request.getContextPath() %>/log_out">로그아웃</a></li>
	  <li><a href="<%=request.getContextPath() %>/check_Pw">마이페이지</a></li>
</div>
<script type="text/javascript">
$(".Hamburgerbtn").click(function() {
	  $("#menu,.page_cover,html").addClass("open");
	  window.location.hash = "#open";
	});

	window.onhashchange = function() {
	  if (location.hash != "#open") {
	    $("#menu,.page_cover,html").removeClass("open");
	  }
	};
</script> 
          
          
        </div>
          <div class="col-md-7">
          <form action="<%=request.getContextPath() %>/search" method="get">
          <input type="hidden" name="page" value="0">
             <input type="text" placeholder="밴드.카테고리 검색..." name="search_content" required="required">
             <input type="submit" value="검색하기" >
             </form>
        </div>
             <div class="col-md-2">
            <a href="<%=request.getContextPath() %>/check_Pw"><b>마이페이지</b></a> 
            <a href="<%=request.getContextPath() %>/log_out"><b>로그아웃</b></a>
        </div>
        
 </div>
      </div>
      </c:when>
</c:choose>
</body>
</html>