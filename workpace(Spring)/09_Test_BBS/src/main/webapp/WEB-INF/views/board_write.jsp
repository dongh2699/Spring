<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 입력폼</title>
</head>
<body>
<div align="center">
		<hr width="50%" color="skyblue">
			<h3>JSP_BBS 테이블 게시글 작성 폼</h3>
		<hr width="50%" color="skyblue">	
		<form method="post" action="<%=request.getContextPath()%>/board_write_ok.do">
			<table border="1" width="400" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td> <input name="board_writer"> </td>
				</tr>
				<tr>
					<th>글제목</th>
					<td> <input name="board_title"> </td>
				</tr>
				<tr>
					<th>글내용</th>
					<td> <textarea rows="8" cols="30" name="board_cont"> </textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="board_pwd"> </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">
						<input type="reset" value="취소">
						<input type="button" value="뒤로가기"
									onclick="location.href='board_list.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>