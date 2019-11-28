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
		<hr width="600" color="red">
			<h3>BOARD 테이블 게시판 전체 리스트</h3>
		<hr width="600" color="red">
		
		<table border="1" width="600" cellspacing="0">
			<tr>
				<th>글번호</th><th>글제목</th><th>조회수</th><th>작성일자</th>
			</tr>
			<tr>
			<c:set var="list" value="${List }"></c:set>
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
					<input type="button" value="글쓰기" onclick="location.href='board_writer.do'">
				</td>
			</tr>
		</table>
		<br>
			<form method="post" action="<%=request.getContextPath() %>/board_search.do">				   		   	
		   			<select  name="find_field">
		   				<option value="title">제목</option>
		   				<option value="content">내용</option>
		   				<option value="title_cont">제목+내용</option>
		   				<option value="writer">작성자</option>
		   			</select>
		   	
		   		
		   			<input type="text" name="find_name">		   			   	
		   			<input type="submit" value="검색">		   			   			 
		   	</form>
	
	</div>
</body>
</html>