
function mail_Chk() {
   if ($.trim($("#m_Email").val()) == "") {
      alert("이메일을 입력해주세요.");
      $("#m_Email").focus();
      
   } else {

      $.ajax({
         type : "POST",
         url : "/BOND/mailsend",
         data : {"m_Email" : $("#m_Email").val()},
         
         success : function(data) {
            alert("해당메일로 인증번호를 발송하였습니다.");
            $('#AuthNumber').val(data);
            $("#AuthChk").show();
         },
         error : function(e) {
            alert("통신에러!")
         }
      });
   }
}
function AuthNumber_Chk() {

   if ($.trim($("#AuthNumber").val()) != $.trim($("#inputNumber").val())) {
      alert("인증번호가 틀렸습니다..");
      $("#inputNumber").val("");
      $("#inputNumber").focus();// 비번입력창으로 초기화
      return false;
   } else {
      alert("인증번호 확인!");
      return true;
   }
}