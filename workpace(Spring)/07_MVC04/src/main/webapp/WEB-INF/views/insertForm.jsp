<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>


		function send1(f){
				if(f.name.value == ""){
						alert('이름을 입력하세요.');
						f.name.focus();
						return;
					}
				if(f.age.value == ""){
						alert('나이를 입력하세요.');
						f.name.focus();
						return;
					}
				if(f.phone.value == ""){
						alert('연락처를 입력하세요.');
						f.name.focus();
						return;
					}

					f.action = "insert_ok1.do";
					f.submit();
			}
		function send2(f){
				if(f.name.value == ""){
						alert('이름을 입력하세요.');
						f.name.focus();
						return;
					}
				if(f.age.value == ""){
						alert('나이를 입력하세요.');
						f.name.focus();
						return;
					}
				if(f.phone.value == ""){
						alert('연락처를 입력하세요.');
						f.name.focus();
						return;
					}

					f.action = "insert_ok2.do";
					f.submit();
			}

</script>
</head>
<body>

			<div align="center">
				<hr width="50%" color="green">
					<h3>개인 정보 입럭 폼</h3>
				<hr width="50%" color="green">
			<form method="post">
			<table border="1" width="300" cellspacing="0">
				<tr>
					<th>이 름</th>
					<td>
					<input name="name">
					</td>
				</tr>
				<tr>
					<th>나 이</th>
					<td>
					<input name="age">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
					<input name="phone">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="낱개로 받기"
									onclick="send1(this.form);">
						<input type="button" value="객체로 받기"
									onclick="send1(this.form);">
						</td>
				</tr>

			</table>
		</form>
			
			</div>
</body>
</html>