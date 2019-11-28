<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="modal fade" id="myModal" role="dialog">
         <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content" id="modal-content-form">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">❌</button>
                  <h4 class="modal-title" id='modal-title'>글쓰기</h4>
                  
               </div>
               <div class="modal-body" id="model-body-form">
               <c:set var="Manager" value="${Manager}"></c:set>
                  <c:if test="${!empty Manager}">
                  
                     <p id="notic">공지사항으로 등록<input type="checkbox" name="writeType" id="writeType"></p>
                     </c:if>
                  <form action=/BOND/create_board  method="post" id="create_board">
                     <input type="hidden" name="m_Email" value="${member.getM_Email() }"> 
                     <input type="hidden" name="blog_no" value="${bond.getB_No() } " id="blog_no">
                   <textarea cols="77" rows="5" name="bc_Cont"  required="required" id="bc_Cont"></textarea>
                 	 <div>
    					<h4>파일 업로드 </h4>
    					<div class="fileDrop">업로드할 파일을 드래그 해주세요!</div>
    					<!-- 업로드된 파일 목록 -->
    					<div class="uploadedList"></div>
    				</div>
                 
               <div class="modal-footer" id="cu-modal-footer">
                 	 <input type="button" value="작성완료" onClick="fn_insertBoard()" class="btn btn-primary" id="submit_btn">
                  <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
               </div>
                  </form>
            </div><!-- modal-content end -->

         </div><!--modal-dialog end-->
      </div><!--modal fade end -->

</body>
</html>