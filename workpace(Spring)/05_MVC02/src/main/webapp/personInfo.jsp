<%@page import="com.sist.model.PersonDTO"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 
 			WebApplicationContext ctx = 
 			WebApplicationContextUtils.getWebApplicationContext(application);
 			
 			PersonDTO dto =  ctx.getBean("dto", PersonDTO.class);
 			
 			request.setAttribute("person", dto);
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h3>${person.name } / ${person.age } / ${person.phone }/ ${person.getAddr() }</h3>

<%-- --%>




</body>
</html>