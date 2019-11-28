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
				<hr width="50%" color="green">
					<h3>입력 정보</h3>
				<hr width="50%" color="green">
			<form method="post">
			<table border="1" width="300" cellspacing="0">
				<tr>
					<th>이 름</th>
					<td>
							${dto.name }
					</td>
				</tr>
				<tr>
					<th>나 이</th>
					<td>
							${dto.getAge() }
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
							${dto.phone }
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="<%=request.getContextPath() %>/insert.do">[돌아가기]</a>
					</td>
				</tr>
				</table>
					</div>
</body>
</html>