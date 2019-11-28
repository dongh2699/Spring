<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BOND | DETAIL</title>
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_detail.css">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/loding.css">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/right_Aside.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->

</head>
<body>
   <header>
      <%@ include file="particials/header.jsp"%>
   </header>
 
   <c:set var="bond" value="${Bond }"></c:set>
   <c:set var="member_Count" value="${Member_Count }"></c:set>
   <c:set var="member" value="${Member }"></c:set>
   
   <input type="hidden" value="${bc_No }" id="bcNo">
   <input type="hidden" value="${b_No }" id="b_No">
   <script type="text/javascript">
      var bcno = document.getElementById("bcNo").value;
      var searchno = 'info' + bcno;
   </script>

   <!-- 전체글 사진첩 멤버 -->
      
   <nav>
      <%@ include file="particials/nav.jsp"%>
   </nav>

      <main>
   <div class="container">
      <div class="row">
         <div class="col-md-3">
            <!-- 블로그명, 사진 ,멤버수 ,소개 -->
            <aside id="leftaside">
               <%@ include file="particials/left_Aside.jsp"%>
            </aside>
            <!-- 게시물 -->
         </div>
         <div class="col-md-6">
            <!-- layout 상속 받아서 main만 변경  -->

            <!-- Links -->
            <a data-toggle="modal" data-backdrop="static" href="#myModal">
               <div class="write">새로운 소식을 남겨보세요!</div>
            </a>
            
            <%@ include file="particials/insert_modal.jsp"%>
         </div>
         <div id="boardList">
               <div id="loader">
                 <div id="box"></div>
              <div id="hill"></div>
         </div>
            <!-- 게시물 나오는곳 Ajax처리  -->
         </div>
      </div>
      <div class="col-md-3" id="right_Aside">
         <!-- 공지사항 ,인기글  -->
         <%@ include file="particials/right_Aside.jsp"%>
      </div>
   </div>
   </main>
<script type="text/javascript"
      src="${pageContext.request.contextPath }/resources/js/comment.js"></script>
   <script type="text/javascript"
      src="${pageContext.request.contextPath }/resources/js/board.js"></script>
   <script type="text/javascript"
      src="${pageContext.request.contextPath }/resources/js/fileupload.js"></script>
</body>
</html>