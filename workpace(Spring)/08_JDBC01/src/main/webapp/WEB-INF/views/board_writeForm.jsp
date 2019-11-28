<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="50%" color="gray">
			<h3>BOARD 테이블 글쓰기 폼</h3>
		<hr width="50%" color="gray">	
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
						<input type="submit" value="글쓰기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>