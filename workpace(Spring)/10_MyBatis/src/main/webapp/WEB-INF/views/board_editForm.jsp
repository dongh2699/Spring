<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<div align="center">
					<hr width="50%" color="purple">
						<h3>게시글 수정하기</h3>
					<hr width="50%" color="purple">
				<form method="post"
								action="<%=request.getContextPath()%>/board_edit_ok.do">
				
				
				<c:set var="dto" value="${modify }"></c:set>
				<input type="hidden" name="board_no" value=${dto.getBoard_no() }>
				<c:if test="${!empty dto }">
					<table border="1" width="450" cellspacing="0">
					<tr>
						<th>글번호</th>
						<td>${dto.getBoard_no() }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${dto.getBoard_writer() }</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td><input name="board_title" value="${dto.getBoard_title() }"></td>
					</tr>
					<tr>
						<th>글내용</th>
						<td><textarea rows="8" cols="30" name="board_cont">${dto.getBoard_cont() }</textarea></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="board_pwd"></td>
					</tr>
					<tr>
							<td colspan="2" align="center">
								<input type="submit" value="수정">
								<input type="reset" value="취소">
								<input type="button" value="뒤로가기"
									onclick="location.href='board_cont.do?no=${dto.getBoard_no() }'">
					</tr>
					</table>
				
				</c:if>
				
				
				
				
				
				
			
				
				
				
				
				</form>
				
								
				
				</div>
				
</body>
</html>