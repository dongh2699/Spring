<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | MAIN</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/bond.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
</head>
<body>
	<header>
	<%@ include file="particials/header.jsp"%>
	</header>
	<main>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
			<p>모임을 위한 공간, BOND</p>
			본드는 그룹 멤버와 함께 하는 공간입니다.<br>
			동호회, 스터디, 주제별 모임을 밴드로 시작하세요.
			<br>
			<input type="button" value="+ 본드 만들기"
		onclick="location.href='<%=request.getContextPath()%>/bond_Create'">
			</div>
		</div>
	</div>
	<div class="container">
	<div class="row">
	<div class="col-md-12">
	<p class="mylist">내 목록</p>
	</div>
	<div class="col-md-12">
	<c:set var="list" value="${List }"></c:set>
	<c:if test="${!empty list}">
	<c:forEach items="${list }" var="i">
		<article>
			<section class="picture">
				<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${i.getB_No() }"> 
				<img src="${pageContext.request.contextPath }/resources/upload/${i.getB_Picture() }" alt="블로그 이미지" class="blog_Img">
				</a>
			</section>
			
			<section class="bondname">
				<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${i.getB_No() }">
				<p>${i.getB_Name() }</p>
				</a>
				
				<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${i.getB_No() }">
				${i.getB_Intro() }
				</a>
			</section>
			
			<section class="bondbtn">
			<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${i.getB_No() }">
				자세히 >>
			</a>
			</section>
		</article>
	</c:forEach>
	</c:if>
	</div>
	
	<div class="col-md-12" id="paging">
		<nav aria-label="Page navigation example">
  		<ul class="pagination">
		<c:if test="${page > block }">
    		<li class="page-item">
               <a class="page-link" href="/BOND/bond?page=1" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
      			</a>
               [<a href="/BOND/bond?page=${startBlock - 1 }">◀</a>]
            </c:if>
            
            <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
               <c:if test="${i == page }">
                  <%-- <u><b>[${i }]</b></u> --%>
                  <li class="page-item"><a class="page-link" href="#">${i }</a></li>
               </c:if>
            
               <c:if test="${!(i == page) }">
                  <li class="page-item"><a class="page-link" href="/BOND/bond?page=${i }">${i }</a></li>
               </c:if>
            </c:forEach>
            
            <c:if test="${endBlock < allPage }">
               [<a href="/BOND/bond?page=${endBlock + 1 }">▶</a>]
               [<a href="/BOND/bond?page=${allPage }">▶▶</a>]
            </c:if>
            </ul>
	</nav>
	</div>
	
	<div class="col-md-12">
		<p class="mylist">인기글</p>
	</div>
            <div class="col-md-12 col-xs-12">
	
<c:set var="contList" value="${contList }"></c:set>
         <c:if test="${!empty contList}">
            <c:forEach items="${contList}" var="i">
            <c:if test="${i.getBc_Goods() != 0}">
               <div class="col-md-12">
            <div class="hotCont">              
                 <a href="<%=request.getContextPath()%>/bond_Detail2?bond_no=${i.getB_No()}&bc_no=${i.getBc_No()}">
               <div class="col-md-12">
              <span class="content">${i.getBc_Cont()}</span>            
               </div>
               <span class="goodcount">                   
               		<i class="far fa-thumbs-up"></i>  ${i.getBc_Goods()}   
                </span>               
               <span class="info">
                ${i.getBc_Date().substring(0,10)} 
               </span>
                       </a>
               </div>
             </div>
             </c:if>
            </c:forEach>
         </c:if>
	      <c:if test="${empty contList }">
	      	인기글이 존재하지 않습니다.
	      </c:if>
             </div>
	</div>
	</div>
   
	</main>
	
	<footer>
	<%@ include file="particials/footer.jsp"%></footer>
</body>
</html>