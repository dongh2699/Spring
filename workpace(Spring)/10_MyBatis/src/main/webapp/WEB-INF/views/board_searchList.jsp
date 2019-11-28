<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 검색결과 창</title>
</head>
<body>
		<div align="center">
		<hr width="600" color="green">
			<h3>검색 결과</h3>
		<hr width="600" color="green">
		
		<table border="1" width="600" cellspacing="0">
			<tr>
				<th>글번호</th><th>글제목</th><th>조회수</th><th>작성일자</th>
			</tr>
			<tr>
			<c:set var="list" value="${Sc_List }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="i">
					<tr>
						<td>${i.getBoard_no() }</td>
						<td><a href="board_cont.do?no=${i.getBoard_no() }">
						${i.getBoard_title() }</a></td>
						<td>${i.getBoard_hit() }</td>
						<td>${i.getBoard_regdate().substring(0,10) }</td>
					</tr>
				</c:forEach>
		    </c:if>
		   <c:if test="${empty list }">
		   	<tr>
		   		<td colspan="4" align="center">
		   		<h3>검색된 레코드가 없습니다</h3>
		   		</td>
		   	</tr>
		   </c:if>		   
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="뒤로가기" onclick="history.back()">
				</td>
			</tr>
		</table>
		</div>

</body>
</html>