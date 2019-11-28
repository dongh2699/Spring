<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | SEARCH</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/search.css">
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
	<%@ include file="particials/header.jsp"%></header>
	
	<main>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<c:set var="search_content" value="${Search_content }"></c:set>
					<c:set var="c_list" value="${category_BlogList }"></c:set>
					<c:if test="${!empty c_list}">
	
				<div class="col-md-12">
					<p class="mylist">카테고리가 ${search_content }인 BOND 목록</p>
				</div>
	
					<c:forEach var="i" begin="0" step="1" end="${c_list.size() -1}">
		
						<article>
							<section class="picture">
								<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${c_list.get(i).getB_No() }">
								<img src="${pageContext.request.contextPath }/resources/upload/${c_list.get(i).getB_Picture() }" alt="블로그 이미지" class="blog_Img">
								</a>
							</section>
							
							<section class="bondname">
								<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${c_list.get(i).getB_No() }">
								<p>${c_list.get(i).getB_Name() }</p>
								</a>
								
								<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${c_list.get(i).getB_No() }">
								${c_list.get(i).getB_Intro() }
								</a>
							</section>
							
							<section class="bondbtn">
							<a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${c_list.get(i).getB_No() }">
								자세히 >>
							</a>
							</section>
						</article>
		
				</c:forEach>
	</c:if>
	
	
	</div>
	</div>
	</div>
	
	
	
	<div class="container">
	<div class="row">
	<div class="col-md-12">
	<c:set var="s_list" value="${seach_BlogList }"></c:set>
	<c:if test="${!empty s_list}">
	<div class="col-md-12">
		<p class="mylist">BOND ${Search_content } 검색 결과 </p>
	</div>
	
	<c:forEach items="${s_list }" var="i">
	
	
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
	<c:if test="${empty s_list and empty c_list}">
	<div>
	<span><h3>${Search_content }에 대한 검색 결과가 없습니다!</h3></span>
	</div>
	</c:if>
	</div>
	  
            <div class="col-md-12" id="paging">
		<nav aria-label="Page navigation example">
  		<ul class="pagination">
		<c:if test="${page > block }">
    		<li class="page-item">
               <a class="page-link" href="/BOND/search?page=1&search_content=${Search_content}" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
      			</a>
               <a  class="page-link" href="/BOND/search?page=${startBlock - 1 }&search_content=${Search_content}" aria-label="Previous">
               	<span aria-hidden="true">&laquo;</span>
               </a>
            </c:if>
            
            <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
               <c:if test="${i == page }">
                
                  <li class="page-item"><a class="page-link" href="#">${i }</a></li>
               </c:if>
            
               <c:if test="${!(i == page) }">
                  <li class="page-item"><a class="page-link" href="/BOND/search?page=${i }&search_content=${Search_content}">${i }</a></li>
               </c:if>
            </c:forEach>
            
            <c:if test="${endBlock < allPage }">
               [<a href="/BOND/search?page=${endBlock + 1 }&search_content=${Search_content}">▶</a>]
               [<a href="/BOND/search?page=${allPage }&search_content=${Search_content}">▶▶</a>]
            </c:if>
            </ul>
	</nav>
	</div>
    
	</div>
	</div>
	
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
			<p>찾으시는 BOND가 존재하지 않나요?</p>
			본드는 그룹 멤버와 함께 하는 공간입니다.<br>
			동호회, 스터디, 주제별 모임을 밴드로 시작하세요.
			<br>
			<input type="button" value="+ 본드 만들기"
		onclick="location.href='<%=request.getContextPath()%>/bond_Create'">
			</div>
		</div>
	</div>
	</main>
	
	
	<footer>
	<%@ include file="particials/footer.jsp"%></footer>
</body>

</html>