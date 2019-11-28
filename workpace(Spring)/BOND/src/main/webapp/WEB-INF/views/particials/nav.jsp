<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
 <ul>
      <li><a href="<%=request.getContextPath()%>/bond_Detail?bond_no=${b_No}">전체글</a></li>
      <li><a href="<%=request.getContextPath()%>/bond_gallary?bond_no=${b_No}">사진첩</a></li>
      <li><a href="<%=request.getContextPath()%>/bond_Member?bond_no=${b_No}">멤버</a></li>
   </ul>
</body>
</html>