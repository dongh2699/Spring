﻿$(function() {
   $("#AuthChk").hide();
   $("#AuthChkEnd").hide();

});

function mail_Chk() {
   if ($.trim($("#m_Email").val()) == "") {
      alert("이메일을 입력해주세요.");
      $("#m_Email").focus();

   } else {
      // 이메일 중복 체크 부분
      $.ajax({
         type : "POST", // 데이터 전송 방식
         url : "/BOND/EmailCheck", // 파일
         // 주소와
         // 경로
         data : {
            "m_Email" : $("#m_Email").val()
         },

         success : function(data) {

            if (data == "false") {
               $("#idcheck").text(''); // idcheck 영역 초기화
               alert("이미 사용중인 아이디 입니다.");

            } else { // 아이디가 중복이 되지 않는 경우

               // 이메일 발송 부분
               $.ajax({
                  type : "POST",
                  url : "/BOND/mailsend",
                  data : {
                     "m_Email" : $("#m_Email").val()
                  },

                  success : function(data) {
                     alert("해당메일로 인증번호를 발송하였습니다.");
                     $('#AuthNumber').val(data);
                     $("#AuthChk").show();
                  },
                  error : function(e) {
                     alert("이메일 발송 에러!")
                  }
               });
               // 끝 이메일 발송 부분
               return false;
               
            }
         },
         error : function(e) { // 비동기통신이 실패한 경우
            alert("이메일 중복 체크 에러")
         }
      });

   }
}
function AuthNumber_Chk() {

   if ($.trim($("#AuthNumber").val()) != $.trim($("#inputNumber").val())) {
      alert("인증번호가 틀렸습니다..");
      $("#inputNumber").val("");
      $("#inputNumber").focus();// 비번입력창으로 초기화

   } else {
      alert("인증번호 확인!");
      $("#AuthChkEnd").show();
      $("#m_Email" ).attr( 'readonly', 'readonly' );
   }
}

function member_check() {

   if ($.trim($("#m_Pw").val()) != $.trim($("#PwChk").val())) {
      alert("비밀번호가 일치하지 않습니다!");
      $("#m_Pw").val("");// 비번 입력창 초기화
      $("#PwChk").val("");
      $("#m_Pw").focus();// 비번입력창으로 초기화
      return false;
   }

   if ($.trim($("#id_chk").val()) == "false") {
      alert("중복아이디입니다.");
      $("#m_Email").val("").focus();
      return false;
   }

}

function delete_confirm() {
	if(confirm("정말로 아이디를 삭제하시겠습니까")){
		 $.ajax({
		      type:'POST',
		      url : "/BOND/delete_Member",
		      data :{
		         "m_Email" : $("#m_Email").val()
		      },  
		      success : function(data){
		        alert("성공");
		        location.href="/BOND/log_out";
		      },
		      error:function(request,status,error){
		           
		          }
		   });
				 } 		
}

$(function(){
$("#delete_btn").click(function() { // 이메일 입력란에 입력하고 다른 곳으로 넘어갈때 이벤트함수
     var m_Email = $('#m_Pw').val();
     var id_chk = $('#PwChk').val();


     $.ajax({
        type : "POST",          // 데이터 전송 방식
        url : "${pageContext.request.contextPath}/pw_Check",    // 파일 주소와 경로
        data : { "m_Pw" : $("#m_Pw").val()

        },   

        success : function(data) {
           
           if (data == "false") {
              alert("비밀번호가 틀렸습니다!");
              $("#m_Pw").text('');  // password 영역 초기화
              $("#PwChk").text('');  // password check 영역 초기화
              } else { // 아이디가 중복이 되지 않는 경우
            	  alert("비밀번호가 틀렸습니다!");
                  $("#m_Pw").text('');  // password 영역 초기화
                  $("#PwChk").text('');  // password check 영역 초기화
                 return false;
                 
              }
           },    error : function(e) {  // 비동기통신이 실패한 경우
              alert("data error")
           }
        });




     return false;
  })

}) // 무명함수 end
