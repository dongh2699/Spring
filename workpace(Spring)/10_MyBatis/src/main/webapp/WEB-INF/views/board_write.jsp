<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력 창</title>
</head>
<body>
<div align="center">
		<hr width="50%" color="green">
			<h3>BOARD 테이블 글쓰기 폼</h3>
		<hr width="50%" color="green">	
		<form method="post" action="<%=request.getContextPath() %>/board_write_ok.do">
			<table border="1" width="400" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input name="board_writer"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input name="board_title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><textarea rows="8" cols="30" name="board_cont"></textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">
						<input type="reset" value="취소">
						<input type="button" value="목록으로"
									onclick="location.href='<%=request.getContextPath() %>/board_list.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>