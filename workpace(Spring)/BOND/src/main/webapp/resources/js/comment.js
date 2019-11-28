
function getCommentList(bc_No){
    
    $.ajax({
        type:'POST',
        url : "/BOND/getComment_List",
        datatype : "json",
        data:$("#board"+bc_No).serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){
            var html = "";         
            if(data.length > 0){
                for(i=0; i<data.length; i++){                                         
                html+="<div class='cc_Containar' style='margin-left:"+data[i].cc_Indent*2+"0px;'><div class='cc_Content"+data[i].cc_no+"'>";                
                 html += "<input type='hidden' id='cc_Group"+data[i].cc_no+"'  value="+data[i].cc_group+">" ;
                 html += "<input type='hidden' id='cc_Indent"+data[i].cc_no+"' value="+data[i].cc_Indent+">" ;
                 html += "<input type='hidden' id='cc_step"+data[i].cc_no+"' value="+data[i].cc_step+">" ;
                 html += "<div class='cc_Contbody"+data[i].cc_no+"'>" ;
                 html+= "<div><div class='col-md-2 col-xs-3'><img src='/BOND/resources/upload/"+data[i].picture+"' id='C_m_picture'></div>";
                 html+=	"<div class='col-md-8 col-xs-6' id='comment_body'><b>"+data[i].nickname+"</b><b class='cc_Cont'>"+data[i].content+"</b> <b>"+data[i].cc_Date+"</b></div> ";         
                 html+=  "<div class='col-md-2 col-xs-3'><button class='comment_option' id='update_formbtn' name='update_formbtn' onclick='update_form_btn("+data[i].bc_no+","+data[i].cc_no+")'><i class='fas fa-wrench'></i>  </button>";
                 html+=  "<button class='comment_option' id='delete_Commentbtn' name='deleteCc_Btn' value="+data[i].cc_no+" onclick='delete_Comment("+data[i].bc_no+","+data[i].cc_no+")'>❌</button></div><div class='col-md-12 col-xs-12'><button class='comment_option' id='toggle-reply' onclick='reply_toggle("+data[i].cc_no+") '>답글 달기</button></div></div>";
                 html+= " </div></div></div></div>"
                 html += "<div class='input-group' id='reply_comment'>";
                 html += "<input type='text' name='cc_Cont' id='txt"+data[i].cc_no+"' style='display:none;'  class='form-control' placeholder='답글을 입력해주세요!' aria-label='Recipient's username with two button addons' aria-describedby='button-addon4'>";
                 html += "<div class='input-group-append' id='button-addon4'>";
                 html +=  "<button class='btn btn-outline-secondary' id='form-control"+data[i].cc_no+"' style='display:none;' type='button' onclick='create_Reply_Comment("+data[i].bc_no+","+data[i].cc_no+")'>등록</button>";       
                 html += "</div>";
                 html += "</div><div class='border_top'></div>";
                }              
            } else {              
                html += "<div>";
                html += "<div><table><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div>";                
            }                    
            $("#comment"+bc_No).html(html);            
        },
        error:function(request,status,error){            
       }     
    });

}



function reply_toggle(cc_No) {
	$("#txt"+cc_No).toggle();
	$("#form-control"+cc_No).toggle();
	
}
function create_Reply_Comment(bc_No,cc_no) { 
	
    $.ajax({
          type:'POST',
          url : "/BOND/create_Reply_Comment",
          data: {"bc_No":bc_No ,
        	  "cc_No":cc_no,
        	  "cc_Cont":$("#txt"+cc_no).val(),
        	  "cc_Step":$("#cc_step"+cc_no).val(),
        	  "cc_Indent":$("#cc_Indent"+cc_no).val(),
        	  "cc_Group":$("#cc_Group"+cc_no).val(),
          },
          
          success : function(data){
             if(data=="success")
            {
                getCommentList(bc_No);
                $("txt"+cc_no).val("");
            }
          },
          error:function(request,status,error){
              // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }          
      });
}
function create_Comment(bc_No) {   
    $.ajax({
          type:'POST',
          url : "/BOND/create_Comment",
          data:$("#board"+bc_No).serialize(),
          success : function(data){
             if(data=="success")
            {
                getCommentList(bc_No);
                $("#txt"+bc_No).val("");
            }
          },
          error:function(request,status,error){
              // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }          
      });
}

function update_form_btn(bc_No,cc_No){
	$(".cc_Contbody"+cc_No).remove();
	$(".cc_Content"+cc_No).prepend(
			
			" <div class='update_Cc_form'><input type='text' name='cc_Cont' id='update_txt"+cc_No+"' class='form-control' placeholder='답글을 입력해주세요!'" +
					" aria-label='Recipient's username with two button addons' aria-describedby='button-addon4'>" +
					"<button  class='btn btn-outline-secondary' id='update_Commentbtn' name='updateCc_Btn' value="+cc_No+" onclick='update_Comment("+bc_No+","+cc_No+")'>수정</button>" +
							"<button  class='btn btn-outline-secondary' id='reset_Btn' name='reset_Btn' onclick='reset_form("+bc_No+","+cc_No+")'>취소</button> </div>");
}

function reset_form(bc_No,cc_No){
	  getCommentList(bc_No);
}

function update_Comment(bc_No,cc_No) {
	
	$.ajax({
	    type:'POST',
	    url : "/BOND/update_Comment",
	    data: {"cc_No":  cc_No,"cc_Cont":$("#update_txt"+cc_No).val()}, // json방식
        success: function(result){
          if( result == "success" ){
        	
        	  getCommentList(bc_No);
          }else{
        	  alert("댓글 수정 실패");
          }
        }
	});
}    

function delete_Comment(bc_No,cc_No) {
	$.ajax({
	    type:'POST',
	    url : "/BOND/delete_Comment",
	    data: {"cc_No":  cc_No ,
	   	  "cc_Indent":$("#cc_Indent"+cc_No).val(),
	   	  "cc_Group":$("#cc_Group"+cc_No).val(), // json방식
	   	 "cc_Step":$("#cc_step"+cc_No).val() },
        success: function(result){
          if( result == "success" ){
        	
        	  getCommentList(bc_No);
          }else{
        	  alert("댓글삭제 실패");
          }
        }
	});
}    