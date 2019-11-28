<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
<span> 개인정보를 수정하실려면 비밀번호를 입력하세요 </span><br>
<form action="<%=request.getContextPath() %>/userDetail" method="post">
<span>비밀번호: </span> <input type="password" name="m_Pw">
<input type="submit" value="확인">
</form>
</div>

</body>
</html>