<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="blog_Imglayout">
  <img src="${pageContext.request.contextPath }/resources/upload/${bond.getB_Picture() }" alt="블로그 이미지" class="blog_Img">
  </div>
   <p class="bondname">${bond.getB_Name() }</p>
   <p class="membercount">멤버  ${member_Count }</p>
   <p class="bondintro">${bond.getB_Intro() }</p>
   <c:choose>
      <c:when test="${ joinCK ne 0}">
         <c:choose>
            <c:when test="${ manageCK ne 0}">
               <a class="setting_bond"
                  href="<%=request.getContextPath() %>/bond_modify?bond_no=${bond.getB_No()}"><i
                  class="fas fa-cog"></i> BOND설정</a>
            </c:when>
            <c:otherwise>
               <form id="frm"
                  action="<%=request.getContextPath()%>/withdrawalBond"
                  method="POST">
                  <input type="hidden" name="b_No" id="b_No"
                     value="${bond.getB_No()}"> <input type="hidden"
                     name="M_Email" id="M_Email" value="${member.getM_Email()}">
                  <input type="button"  class="btn btn-outline-secondary" value="탈퇴하기" onclick="button_withdrawal(); ">
               </form>
               <script type="text/javascript">
                  function button_withdrawal() {
                     if (confirm("탈퇴하시겠습니까??") == true) { //확인
                        document.getElementById('frm').submit();
                     } else { //취소
                        return;
                     }
                  }
               </script>
            </c:otherwise>
         </c:choose>
      </c:when>
      <c:otherwise>
         <form id="frm" action="<%=request.getContextPath()%>/joinBond"
            method="POST">
            <input type="hidden" name="b_No" id="b_No" value="${bond.getB_No()}">
            <input type="hidden" name="M_Email" id="M_Email"
               value="${member.getM_Email()}"> <input type="button" class="btn btn-outline-secondary"
               value="가입하기" onclick="button_join();">
         </form>
         <script type="text/javascript">
            function button_join() {
               if (confirm("가입하시겠습니까??") == true) { //확인
                  document.getElementById('frm').submit();
               } else { //취소
                  return;
               }
            }
         </script>
      </c:otherwise>
   </c:choose>

</body>
</html>