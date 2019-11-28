<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath }/resources/css/common.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath }/resources/js/member.js"></script>
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

   <header>
      <%@ include file="particials/header.jsp"%>
   </header>

   <main>
      <div class="container-fluid" align="center">
         <div class="row">
            <div class="col-md-12">
               <input type="hidden" name="AuthNumber" id="AuthNumber">
               <form action="<%=request.getContextPath()%>/join_Ok" method="POST"
                  onsubmit="return member_check()">
                  <!-- 맨처음 이메일창 보여주기 -->
                  <table>
                     <tr class="email">
                        <th>Email</th>
                        <td><input type="email" id="m_Email" name="m_Email"
                           placeholder="E-mail" required="required"> <input
                           type="button" value="이메일 전송" id="mailSendBtn"
                           onclick="mail_Chk()"></td>
                     </tr>
                  </table>

                  <!-- 이메일 전송 버튼 클릭 후 생기기 -->
                  <div id="AuthChk">
                     <table>
                        <tr class="checkword">
                           <th>CheckNum</th>
                           <td><input type="text" name="inputNumber" id="inputNumber"
                              placeholder="checkword" required="required"> <input
                              type="button" id="authCheckBtn" onclick="AuthNumber_Chk()"
                              value="인증번호확인"></td>
                        </tr>
                     </table>
                  </div>
                  <!-- 인증 후 생기기 -->
                  <div id="AuthChkEnd">
                     <table>
                        <tr>

                           <th>Password</th>
                           <td><input type="password" name="m_Pw" id="m_Pw"
                              placeholder="Password" required="required"></td>

                        </tr>



                        <tr>
                           <th>Password</th>
                           <td><span class="AuthChkEnd"><input type="password"
                                 name="PwChk" id="PwChk" placeholder="Password"
                                 required="required"></span></td>
                        </tr>
                        <tr>
                           <th>Name</th>
                           <td><input type="text" name="m_Name" id="m_Name"
                              placeholder="Name" required="required"></td>
                        </tr>
                        <tr>
                           <th>NickName</th>
                           <td><input type="text" name="m_Nickname" id="m_Nickname"
                              placeholder="NickName"></td>
                        </tr>
                        <tr>
                           <th>Birth</th>

                           <td><input type="date" name="m_Birth"
                              placeholder="Birthday" required="required"></td>
                        </tr>
                        <tr>
                           <th>Phone</th>
                           <td><input type="text" name="m_Phone" placeholder="Phone"
                              required="required"></td>
                        </tr>
                        <tr>
                           <th>Picture</th>
                           <td><input type="file" name="m_Picture"
                              placeholder="Picture"></td>
                        </tr>
                        <tr>
                           <td colspan="2"><input type="submit" value="가입하기"></td>
                        </tr>
                     </table>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </main>
   <footer>
      <%@ include file="/WEB-INF/views/particials/footer.jsp"%>
   </footer>
</body>
</html>