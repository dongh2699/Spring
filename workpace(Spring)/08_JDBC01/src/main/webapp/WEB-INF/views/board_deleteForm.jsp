<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<hr width="50%" color="olive">
			<h3>BOARD 테이블 게시글 삭제 폼</h3>
		<hr width="50%" color="olive">
		<form action="<%=request.getContextPath()%>/board_delete_ok.do"method="post">
		<c:set var="dto" value="${delete }" ></c:set>
		<input type="hidden" name="board_no" value=${dto.getBoard_no() }>
		<table border="1" cellspacing="0">
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="board_pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="삭제">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
				</td>
			</tr>
		</table>
		
		</form>
	</div>
</body>
</html>