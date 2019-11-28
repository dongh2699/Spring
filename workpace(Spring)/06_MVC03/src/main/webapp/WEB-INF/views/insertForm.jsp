<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="blue">
		<h3>개인 정보 입력</h3>
		<hr width="50%" color="blue">

		<form method="post" action="<%=request.getContextPath()%>/inputOk">
			<table border="1" width="300" cellspacing="0">
				<tr>
					<th>이 름</th>
					<td>
					<input name="name">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
					<input name="id">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="전송">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
						</td>
				</tr>

			</table>
		</form>


	</div>

</body>
</html>