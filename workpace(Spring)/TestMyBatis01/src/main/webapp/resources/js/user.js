/*member_join.js*/
  /* 기본 회원정보 체크 */
	  function user_check(){
	  	if($.trim($("#user_id").val())==""){
	  		alert("회원아이디를 입력하세요!");
	  		$("#user_id").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_pass").val())==""){
	  		alert("비밀번호를 입력하세요!");
	  		$("#user_pass").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_pass2").val())==""){
	  		alert("비밀번호확인을 입력하세요!");
	  		$("#user_pass2").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_pass").val()) 
	  			!= $.trim($("#user_pass2").val())){
	  		alert("비밀번호가 일치하지 않습니다!");
	  		$("#user_pass").val("");//비번 입력창 초기화
	  		$("#user_pass2").val("");
	  		$("#user_pass").focus();//비번입력창으로 초기화
	  		return false;
	  	}
	  	if($.trim($("#user_name").val())==""){
	  		alert("회원이름을 입력하세요!");
	  		$("#user_name").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_nick").val())==""){
	  		alert("회원닉네임을 입력하세요!");
	  		$("#user_nick").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_birth").val())==""){
	  		alert("생년월일을 입력하세요!");
	  		$("#user_birth").val("").focus();
	  		return false;
	  	}
	  	if($.trim($("#user_phone").val())==""){
	  		alert("핸드폰번호를 입력하세요!");
	  		$("#user_phone").val("").focus();
	  		return false;
	  	}
	  	
		if($.trim($("#id_chk").val())=="false"){
	  		alert("중복아이디입니다.");
	  		$("#user_id").val("").focus();
	  		return false;
	  	}
		
	
  }