<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/bond_detail.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>

	<header>
		<%@ include file="particials/header.jsp"%>
	</header>
	<c:set var="bond" value="${Bond }"></c:set>
	<c:set var="member_Count" value="${Member_Count }"></c:set>
	<c:set var="member" value="${Member }"></c:set>
	<!-- 전체글 사진첩 멤버 -->
	<nav>
		<ul>
			<li>전체글</li>
			<li>사진첩</li>
			<li>멤버</li>
		</ul>
	</nav>
	<!-- 블로그명, 사진 ,멤버수 ,소개 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<aside>
					<img src="${bond.getB_Picture() }" alt="블로그 이미지">
					<p>${bond.getB_Name() }</p>
					<p>멤버수 : ${member_Count }</p>
					<p>${bond.getB_Intro() }</p>
				</aside>
			</div>
			<!-- 게시물 -->

			<!-- layout 상속 받아서 main만 변경  -->
			<div class="col-md-6">
				<main>
					<!-- Links -->
					<a data-toggle="modal" href="#myModal">글쓰기</a>

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<h4 class="modal-title">글쓰기</h4>
								</div>
								<div class="modal-body">
									<form action=/BOND/create_board method="post" id="create_board">
										<input type="hidden" name="m_Email"
											value="${member.getM_Email() }"> <input type="hidden"
											name="blog_no" value="${bond.getB_No() } " id="blog_no">
										<textarea cols="77" rows="5" name="bc_Cont"
											required="required" id="bc_Cont"> </textarea>
										<input type="file" name="bc_Picture" value="사진"> <input
											type="file" name="bc_Video" value="비디오"> <input
											type="file" name="bc_File" value="파일"> <input
											type="button" value="작성완료" onClick="fn_insertBoard()"
											class="btn btn-primary">
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">Close</button>
								</div>
							</div>
							<!-- modal-content end -->

						</div>
						<!--modal-dialog end-->
					</div>
					<!--modal fade end -->

					<div id="boardList">
						<!-- 게시물 나오는곳 Ajax처리  -->
					</div>
					<script type="text/javascript"
						src="${pageContext.request.contextPath }/resources/js/board.js"></script>
				</main>
				<!-- 공지사항 ,인기글  -->
			</div>
			<div class="col-md-3">
				<aside>
					<div>
						<!-- 공지사항  -->
					</div>
					<div>
						<!--  인기글 -->
					</div>
				</aside>
			</div>
</body>
</html>