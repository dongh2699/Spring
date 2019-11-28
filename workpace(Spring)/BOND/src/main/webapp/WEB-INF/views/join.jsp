<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOND | JOIN</title>
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/join.css">
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
                  onsubmit="return member_check()" enctype="multipart/form-data">
                  <!-- 맨처음 이메일창 보여주기 -->
                  	<span class="title">
                     	 이메일 인증
                    </span>
            </div>
            
            <div class="col-md-12">
            	<span class="desc">
            		본드에 가입해주셔서 감사합니다.
            		<br>
            		본드의 모든 기능을 이용하시려면 반드시 이메일 인증을 완료해야 합니다.
            		<br>
            		본드 로그인에 사용하실 이메일 주소를 올바르게 입력하신 후
            		<br>
            		'인증 메일 보내기'버튼을 눌러주세요.
            		<br>
            		입력하신 이메일 주소로 전송된 인증 메일의 코드를 입력하여 인증을 완료해주세요.
            	</span>
            </div>
            
            <div class="col-md-12">
                        <input type="email" id="m_Email" name="m_Email"
                           placeholder="E-mail" required="required" >
            </div>
            <div class="col-md-12">
                        <input
                           type="button" value="인증 메일 보내기" id="mailSendBtn"
                           onclick="mail_Chk()">
            </div>

                  <!-- 이메일 전송 버튼 클릭 후 생기기 -->
                  
                     <div class="col-md-12">
                  <div id="AuthChk">
                           
                           <input type="text" name="inputNumber" id="inputNumber"
                              placeholder="인증번호" required="required">
                             
                           <div class="col-md-12">
                           <input
                              type="button" id="authCheckBtn" onclick="AuthNumber_Chk()"
                              value="인증번호확인" onclick="static_Email()">
                           </div>
                  
                  </div>
                  <!-- 인증 후 생기기 -->
                  
                  <div id="AuthChkEnd">
                  <div class="col-md-12">
                  
                           <input type="password" name="m_Pw" id="m_Pw"
                              placeholder="Password" required="required">
                              </div>
                              <div class="col-md-12">
                              <span class="AuthChkEnd">
                              <input type="password"
                                 name="PwChk" id="PwChk" placeholder="Password"
                                 required="required"></span>
                                 </div>
                                 <div class="col-md-12">
                           <input type="text" name="m_Name" id="m_Name"
                              placeholder="Name" required="required">
                              </div>
                              
                              <div class="col-md-12">
                              <input type="text" name="m_Nickname" id="m_Nickname"
                              placeholder="NickName">
                              </div>
                              
                              <div class="col-md-12">
                              <input type="date" name="m_Birth"
                              placeholder="Birthday" required="required">
                              </div>
                              
                              <div class="col-md-12">
                           <input type="text" name="m_Phone" placeholder="Phone"
                              required="required">
                              </div>
                              
                              <div class="col-md-12">
                           <input type="file" name="member_Picture"  accept="image/*"
                              placeholder="Picture">
                              </div>
                              
                              <div class="col-md-12">
                          <input type="submit" value="회원가입">
                          </div>
                     </div>
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