<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<script type="text/javascript"
   src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
   <header>
      <%@ include file="/WEB-INF/views/particials/header.jsp"%>
   </header>
   <main>
      <form method="post" action="<%=request.getContextPath()%>/modify_pwd_ok"
         onsubmit="return member_check()">
         <input type="hidden" name="m_Email" value="${m_Email }">
         <table align="center">
            <tr>
               <th>변경할 비밀번호</th>
               <td><input type="password" name="m_Pw" id="m_Pw"
                  required="required"></td>
            </tr>
            <tr>
               <th>비밀번호 확인</th>
               <td><input type="password" name="PwChk" id="PwChk"
                  required="required"></td>
            </tr>
            <tr>
               <td colspan="2"><input type="submit" value="비밀번호 수정">
               </td>
            </tr>

         </table>
      </form>
   </main>
   <footer>
      <%@ include file="/WEB-INF/views/particials/footer.jsp"%>
   </footer>
</body>
</html>