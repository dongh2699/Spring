<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 로그인 페이지</title>
</head>
<body>
		<div align="center">
			<form method="post" action="<%=request.getContextPath() %>/user_loginOk.do">
			<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="inputId">
				</td>
			</tr>
			<tr>	
				<th>비밀번호</th>
				<td>
					<input type="password" name="inputPwd">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인">
					<input type="reset" value="초기화">
					<input type="button" value="뒤로가기"
									onclick="location.href='<%=request.getContextPath()%>/user_join.do'">
				</td>
			</tr>
			</table>
			</form>
		</div>

</body>
</html>