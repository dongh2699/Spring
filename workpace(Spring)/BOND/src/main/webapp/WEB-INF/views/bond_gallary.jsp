<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | GALLARY</title>

<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_detail.css">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_gallary.css">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
   <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
   
   
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/gallery_Aside.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/comment.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
var slideIndex = 1;
function slidershow(n) {
	
showDivs(slideIndex,n);
}
function plusDivs(n,b_no) {
  showDivs(slideIndex += n,b_no);
}

function showDivs(n,b_no) {
	
  var i;
  var x = document.getElementsByClassName("uploadFiles_"+b_no);

  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "";  
}


</script>
</head>
<body>

<header>
   <%@ include file="particials/header.jsp"%>
   </header>
   
   <c:set var="bond" value="${Bond }"></c:set>
   <c:set var="member_Count" value="${Member_Count }"></c:set>
   <c:set var="member" value="${Member }"></c:set>
   
   <input type="hidden" value="${bc_No }" id="bcNo">
   
    <input type="hidden" value="${b_No } " id="b_No">

    <script type="text/javascript">
   var bcno = document.getElementById("bcNo").value;
   var searchno ='content'+bcno;
 
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
   </div>   
   <div class="col-md-6" id="gallary_container">
   <div class="gallary_background">
	<h3 class="gallay_title">
		<b>사진첩</b>
	</h3>
	<hr>			
    <c:set var="list" value="${PictureList }"></c:set>
    <c:if test="${!empty list }">       
   <div id="gallary">
       <c:forEach items="${list }" var="p" varStatus="status">
                <c:forTokens var="filename" items="${p.get('FILE_NAME') }" delims=",">
       <div class="file${p.get('BC_NO') }" id="files">
        	<div class="gallay_List">
            <a data-toggle="modal" href="#modal-fullscreen${p.get('BC_NO') }" onclick="getCommentList(${p.get('BC_NO')}); slidershow(${p.get('BC_NO')});">
             <img src="/BOND/upload/displayFile?fileName=${filename }"   	class="gallarythumbnail"> 
             </a>       
          </div>
         </div>
             </c:forTokens>
            
          <div class="modal fade modal-fullscreen" id="modal-fullscreen${p.get('BC_NO') }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
           <div class="modal-dialog">
          <div class="modal-content">
        <!-- Picture Area -->  
                
         <div class="uploadArea">
         
         
	<div class="row">
	
	
  			<a class="col-md-1" onclick="plusDivs(-1,${p.get('BC_NO')})" style="color: white;">&#10094;</a>
  			<div class="col-md-10">
             <c:forTokens var="filename" items="${p.get('FILE_NAME') }" delims=","  varStatus="i">

         <img id="uploadfile"  class='uploadFiles_${p.get("BC_NO") }' src="/BOND/upload/displayFile?fileName=${filename }">

         </c:forTokens>         
         </div>
  			<a class="col-md-1" onclick="plusDivs(1,${p.get('BC_NO')})" style="color: white;">&#10095;</a>
         </div>  
         
         </div>
         <!-- Picture Area end! -->
         <div class="contentRight">    
         <button type="button" class="close" data-dismiss="modal">❌</button>
            <div class="col-md-3"><img src="/BOND/resources/upload/${p.get('M_PICTURE') }" class="member_Picture"></div>
           <div class="col-md-8">
            <div class="who">
            <a data-toggle="modal" href="#myModalProfile${p.get('BC_NO') }">${p.get('M_NICKNAME') }</a>
            </div>                    
            <div class="when">${p.get('UPLOAD_DATE') }</div>    
            </div>         
           
            <div class="col-md-12" id="content_area">
            <div class="content">${p.get('BC_CONT') }</div>     
            </div> 
            <form action=/Bond/create_Comment method='POST' id="board${p.get('BC_NO') }" class="create_Comment_Form">
            <input type='hidden' name='bc_No' value="${p.get('BC_NO') }">
            <div class='input-group'>
            <input type='text' name='cc_Cont' id="txt${p.get('BC_NO') }" class='form-control' placeholder='댓글을 입력해주세요!' aria-label='Recipient's username with two button addons' aria-describedby='button-addon4'>
           <div class='input-group-append' id='button-addon4'>
           <button class='btn btn-outline-secondary' type='button' onclick="create_Comment(${p.get('BC_NO') })">등록</button>
            </div>
            </div>
            </form>      
              
            <div id="comment${p.get('BC_NO') }">
            </div> 
            </div><!-- modal contentRight end -->           
         </div>          
          </div> 
        </div> <!--  modal fullscreen end -->
         <div class='modal fade' id="myModalProfile${p.get('BC_NO') }" tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>
            <div class='modal-dialog'>
           <div class='modal-content'>
           <div class='modal-header'>
           <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>×</span></button>
           <h4 class='modal-title' id='myModalLabel'>Profile</h4>
           </div>      
           <div class='modal-body'><img src="/BOND/resources/upload/${p.get('M_PICTURE') }" class='member_Picture'>
           <hr>${p.get('M_NICKNAME') }<hr>${p.get('M_EMAIL') }</div>
           <div class='modal-footer'>
           <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
           </div></div></div></div>
       </c:forEach> 
  </div> <!-- gallery end -->
    </c:if>
   
    <c:if test="${empty list }">         
               <h3>해당 블로그에 사진이 없습니다.</h3>         
         </c:if>
  </div>
  </div>
  <div class="col-md-3" id="right_Aside">
   <!-- 공지사항 ,인기글  -->
       <%@ include file="particials/right_Aside.jsp"%>
</div>
  </div>
     </main>
        
</body>
</html>