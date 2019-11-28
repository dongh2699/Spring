<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | MEMBER</title>

<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_detail.css">
   <link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath }/resources/css/bond_member.css">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!--  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.js"></script>
 -->

<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
   <header>
      <%@ include file="particials/header.jsp"%>
   </header>

   <c:set var="bond" value="${Bond }"></c:set>
   <c:set var="member_Count" value="${Member_Count }"></c:set>
   <c:set var="member" value="${Member }"></c:set>
   <c:set var="joinCK" value="${joinCK}"></c:set>
   <input type="hidden" value="${bc_No }" id="bcNo">

   <script type="text/javascript">
      var bcno = document.getElementById("bcNo").value;
      var searchno = 'content' + bcno;
   </script>

   <!-- 전체글 사진첩 멤버 -->
   <nav>
      <ul>
         <li><a
            href="<%=request.getContextPath()%>/bond_Detail?bond_no=${b_No}">전체글</a></li>
         <li><a
            href="<%=request.getContextPath()%>/bond_gallary?bond_no=${b_No}">사진첩</a></li>
         <li><a
            href="<%=request.getContextPath()%>/bond_Member?bond_no=${b_No}">멤버</a></li>
      </ul>
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

            <div class="col-md-6">
               <!-- layout 상속 받아서 main만 변경  -->
               <main>
                  <div class="col-md-12 text-center">
                  <div>
                  	 <span class="blog_Member_Title">BOND 멤버 관리</span>
                  	 <hr>
                     <div class="member_Count"><p>멤버수 : ${member_Count } 명</p></div>
                     <form action="<%=request.getContextPath()%>/searchMember"
                        method="GET">
                        <input type="hidden" value="${b_No}" name="bond_no" id="b_No"> 
                        <input type="text" placeholder="회원 검색하기" name="m_nickname">
       
                     </form>
                  </div>
                  <div id="viewMember">

                     <c:if test="${!empty listDto }">
                        <c:forEach items="${listDto }" var="i" varStatus="status">
                        <div class="col-md-12 ">
                           <div class="m_Picture">
                              <c:choose>
                                 <c:when test="${!empty i.get('M_PICTURE') }">
                                <a data-toggle='modal' href='#myModalProfile${status.count}'>
                                <img src='/BOND/resources/upload/${i.get("M_PICTURE") }' class='member_Picture'>
                        		</a>
                                 </c:when>
                                 <c:otherwise>
                                    <i class="fas fa-user"></i>
                                 </c:otherwise>
                              </c:choose>
                           </div>
                          <div class="m_nickname"><a data-toggle='modal' href='#myModalProfile${status.count}'>${i.get("M_NICKNAME") }</a></div>
                            <div class="m_grade">${i.get("BM_GRADE") }</div>
                              <c:choose>
                                 <c:when test="${ manageCK ne 0}">
                                    <c:choose>
                                       <c:when test="${i.get('BM_GRADE') ne 'admin'}">
                        			   <div class="change_Grade">
                                          <form action="<%=request.getContextPath()%>/changeManage"
                                             method="POST">
                                             <input type="hidden" name="bm_Grade" id="bm_Grade" value="${i.get('BM_GRADE') }">
                                             <input type="hidden" name="m_Email" id="m_Email" value="${i.get('M_EMAIL') }"> 
                                             <input type="hidden" name="b_No" id="b_No" value="${i.get('B_NO') }">
                                             <input type="submit" class="btn btn-outline-secondary" value="권한 변경">
                                          </form>
                                         </div>
                                       </c:when>
                                       <c:otherwise>
                                       </c:otherwise>
                                    </c:choose>
                                 </c:when>
                              </c:choose>
                           </div>
               <div class='modal fade' id='myModalProfile${status.count}' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>
               <div class='modal-dialog' id='profile-dialog'>
                 <div class='modal-content'>
          	     <div class='modal-header' id='profile_Header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>×</span></button>
                    <h4 class='modal-title' id='myModalLabel'>Profile</h4>
                     </div>
                   <div class='profile-body' id=><span class='profile'><img src='/BOND/resources/upload/${i.get("M_PICTURE") }' 
                   class='member_Picture_Profile'></span><span class='profile'><b>${i.get("M_NICKNAME") }</b></span><span class='profile'><b>${i.get('M_EMAIL') }</b></span></div>
                	  <div class='modal-footer'>
                	 <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
                   </div></div></div></div>
                        </c:forEach>
                     </c:if>
                  </div>
               </div>
        
               </main>
            </div>


            <div class="col-md-3" id="right_Aside">
               <!-- 공지사항 ,인기글  -->
               <%@ include file="particials/right_Aside.jsp"%>
            </div>
         </div>
      </div>
   </main>


<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/right_Aside.js"></script>
</body>
</html>