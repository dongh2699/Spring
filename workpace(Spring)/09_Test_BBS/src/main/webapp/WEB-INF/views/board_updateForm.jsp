<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<hr width="50%" color="orange">
		<h3>JSP_BBS테이블 게시물 수정</h3>
		<hr width="50%" color="orange">
		<c:set var="dto" value="${update }"></c:set>
		<form action="<%=request.getContextPath() %>/board_update_ok.do" method="post">
			<table border="1" cellspacing="0">
			<c:if test="${!empty dto }">
			<input type="hidden" name="board_no" value="${dto.getBoard_no() }">
			<tr>
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input type="text" name="board_title" value="${dto.getBoard_title() }"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="8" cols="30" name="board_cont">${dto.getBoard_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>${dto.getBoard_date() }</td>
				</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<input type="reset" value="다시작성">
					<input type="button" value="뒤로가기"
									onclick="location.href='board_cont.do?no=${dto.getBoard_no()}'">
				</td>
				</tr>
			</c:if>
			</table>
		</form>
</div>
</body>
</html>