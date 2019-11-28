/*
 * 게시물 등록하기(Ajax)
 */
function fn_insertBoard(){
   if($("input:checkbox[id='writeType']").is(":checked") == true){  
      $.ajax({
           type:'POST',
           url : "/BOND/create_Notice",
           data:$("#create_board").serialize(),
           success : function(data){
              if(data=="success")
             {
            	 
            	  $('#myModal').modal("hide");       
                 getBoardtList();
             	 getnoticeList();  
             	$("#writeType").prop("checked", false);
                 $(".fileurl").remove();
                 $(".thumbnail").remove();
                 $(".hiddenInput").remove();
                 $(".deletebtn").remove();
                 $("#bc_Cont").val("");
             } else if(data=="notPermission"){
                
                alert("허락되지않은 등급입니다.");
             } else {
        
             }
           },
           error:function(request,status,error){
               //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            
          }          
       });  
      } else {
          $.ajax({
                 type:'POST',
                 url : "/BOND/create_board",
                 data:$("#create_board").serialize(),
                 success : function(data){
                    if(data=="success")
                   {
                    	$('#myModal').modal("hide");       
                       getBoardtList();  
                   	 getnoticeList();  
                   	 	$("#writeType").prop("checked", false);
                       $(".fileurl").remove();
                       $(".thumbnail").remove();
                       $(".hiddenInput").remove();
                       $(".deletebtn").remove();
                       $("#bc_Cont").val("");
                   } else if(data=="notPermission"){
                      alert("허락되지않은 등급입니다.");
                      
                   } else {
                     
                   }
                 },
                 error:function(request,status,error){
                     //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                   
                }
                 
             });
         
      }
   
}
 
/**
 * 초기 페이지 로딩시 게시물 불러오기
 */
$(function(){	   
	      setTimeout(function() {
	         getBoardtList();
	         }, 2000);
	      setTimeout(function() {
	         fileupload();
	         }, 100);
	      setTimeout(function() {
	         if(searchno != "info"){
	            //alert("확인을 누르면 인기글을 보여주도록 하지");
	         var _scrollX = $('html, main').scrollTop();
	           $('html, main').scrollTop(_scrollX - 120);
	         }
	      }, 2710)

   $("#myModal").on('hidden.bs.modal', function(e){
	   $("#bc_Cont").val("");
	    $(".fileurl").remove();
	    $(".thumbnail").remove();
	    $(".hiddenInput").remove();
	    $(".deletebtn").remove();
		$('.modal-title').text('글쓰기');  // title 글수정으로 바꾸기
		$("#delete_board").remove();
		$("#submit_board").attr("onClick","fn_insertBoard()");
		$("#create_board").attr("action", "/BOND/create_board");
   });
});
 
/**
 * 게시물 불러오기(Ajax)
 */


function getBoardtList(){

    $.ajax({
        type:'POST',
        url : "/BOND/getboard_List",
        datatype : "json",
        data:$("#create_board").serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){                
            var html = "";            
            if(data.length > 0){
            	for(i=0; i<data.length; i++){
            		html+= "<div id='info'>"
            		html += "<div class='info' id='info"+data[i].no+"'>" ;
                    html +=	"<div class='col-md-2 col-xs-3'><img src='/BOND/resources/upload/"+data[i].picture+"' class='member_Picture'></div>" ;                    
                    html += "<div class='col-md-8 col-xs-6'><div class='who'><a data-toggle='modal' href='#myModalProfile"+data[i].no+"'>"+ data[i].writer+"</a></div>" +
                   "<div class='when'>"+data[i].date+"</div></div><div class='col-md-2 col-xs-3' id='board_goods'><span id='heart'><i class='fas fa-heart' ></i></span><span class='goods"+data[i].no+"'>"+data[i].goods_count+"</span></div>"; // 1열

                    html += "<a data-toggle='modal' href='#myModal"+data[i].no+"'><div class='col-md-12 col-xs-12' id='content'>"+data[i].content+"</div>";  // 2열                   
                    if(data[i].uploadfiles){
                        for(j=0; j<data[i].uploadfiles.length;j++){
                            html += "<img src='/BOND/upload/displayFile?fileName="+data[i].uploadfiles[j]+"' class='uploadFiles'>"                                             
                         }
                        }
                    html += "</a><input type='button' class='good' id='GoodsBtn"+data[i].no+"' onclick='actionGoods("+data[i].no+")'>";                    
                    html += "<button class='comment' type='button' " +
                    		"onclick='getCommentList("+data[i].no+")' data-toggle='collapse' " +
                    				"data-target='#collapseExample"+data[i].no+"' aria-expanded='false' aria-controls='collapseExample"+data[i].no+"'>" +
                    						"댓글보기" +
                    		"</button>";        				
                    html += "<div class='collapse' id='collapseExample"+data[i].no+"'" +
                    			"<div class='well'>" +
                    				"<div id='comment"+data[i].no+"'>" +
                    				"</div>" +
                    			"</div>" +
                    		"</div>" ;                   		
                    html += "<form action=/Bond/create_Comment method='POST' id='board"+data[i].no+"'>"; // 3열
                    html += 	"<input type='hidden' name='bc_No' value='"+data[i].no+"'>";      
                    html += "<div class='input-group'>";
                    html += "<input type='text' name='cc_Cont' id='txt"+data[i].no+"' class='form-control' placeholder='댓글을 입력해주세요!' aria-label='Recipients username with two button addons' aria-describedby='button-addon4'>";
                    html += "<div class='input-group-append' id='button-addon4'>";
                    html +=  "<button class='btn btn-outline-secondary' type='button' onclick='create_Comment("+data[i].no+")'>등록</button>";
                    html += "</div>";
                    html += "</div>";
                    html += "</form>" +
                          "</div>"; // end of id='info'                                        
                    html += "<div class='modal fade' id='myModal"+data[i].no+"' role='dialog'>";
                    html += "<div class='modal-dialog'>";
                    html +=  "<!-- Modal content-->";
                    html +=   "<div class='modal-content'>";
                    html +=      "<div class='modal-body'> ";                  
                    html +=	"<div class='col-md-2 col-xs-3'><img src='/BOND/resources/upload/"+data[i].picture+"' class='member_Picture'></div>" ;                    
                    html += "<div class='col-md-8 col-xs-6'><div class='who'><a data-toggle='modal' href='#myModalProfile'>"+ data[i].writer+"</a></div>" +
                   "<div class='when'>"+data[i].date+"</div></div><div class='col-md-2 col-xs-3' id='board_goods'><span id='heart'><i class='fas fa-heart' ></i></span><span class='goods"+data[i].no+"'>"+data[i].goods_count+"</span></div><div id='modify_BondBtn'> "; // 1열
                    var selectdata = JSON.stringify(data[i]);
                    if(data[i].login_email===data[i].writer_email){
                    html +=	"<button type='button'   class='btn btn-primary' data-toggle='modal' data-target='#myModal' data-whatever='@mdo' onclick='board_modify("+selectdata+")'>글 수정</button>";
                    }else{
                    html +=	"<button type='button' class='btn btn-primary' data-dismiss='modal'>닫기</button>";
                    }
                    html += "</div><hr>"
                    html +=    "<div class='content'>"+data[i].content+"</div>";
                    if(data[i].uploadfiles){
                        for(j=0; j<data[i].uploadfiles.length;j++){
                            html += "<img src='/BOND/upload/displayFile?fileName="+data[i].uploadfiles[j]+"' class='uploadFiles'>"                                             
                         }
                        }
                    html +=   "   </div>";                                          
                    html += "  </div><!-- modal-content end -->";
                    html += "</div><!--modal-dialog end-->";
                    html += "</div><!--modal fade end -->";
                    html += "</div>";
                    html +=  "<div class='modal fade' id='myModalProfile"+data[i].no+"' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>";
                    html +=  "<div class='modal-dialog' id='profile-dialog'>";
                    html +=  "<div class='modal-content'>";
          	        html += "  <div class='modal-header' id='profile_Header'>";
                    html += "  <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>×</span></button>";
                     html +=  " <h4 class='modal-title' id='myModalLabel'>Profile</h4>";
                     html += "  </div>";
                     html +=  "  <div class='profile-body' id=><span class='profile'><img src='/BOND/resources/upload/" +data[i].picture+"";
                	 html +=  "' class='member_Picture_Profile'></span><span class='profile'><b>"+data[i].writer+"</b></span><span class='profile'><b>"+data[i].writer_email+"</b></span></div>";
                	 html +=  " <div class='modal-footer'>";
                	 html += "  <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
                    html +=  "  </div></div></div></div>";                                                 
                    checkGoods(data[i].no); 
                }                
            } else {               
                html += "<div>";
                html += "<div><table><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div></div>";                
            }         
            $("#boardList").html(html);
          if(searchno != "info"){
                document.getElementById(searchno).scrollIntoView();             
             }
        },
        error:function(request,status,error){
             
       }       
    });
    
}

function fn_deleteBoard(grade){
	 var grade =grade;
    $.ajax({
        type:'POST',
        url : "/BOND/delete_board",
        data:$("#create_board").serialize(),
        success : function(data){
           if(data=="success")
          {
        	   $('#myModal').modal("hide");       
              getBoardtList();
          }
        },
        error:function(request,status,error){
            // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
          
       }       
    });
    reset_Modal(grade);

}

function fn_updateBoard(grade){
    var grade =grade;
    $.ajax({
        type:'POST',
        url : "/BOND/update_board",
        data:$("#create_board").serialize(),
        success : function(data){
           if(data=="success")
          {
        	   $('#myModal').modal("hide");            
              getBoardtList();         
          }
        },
        error:function(request,status,error){
            // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         
       }    
    });
    reset_Modal(grade);
}
function reset_Modal(grade){
    $("#bc_Cont").val("");
    $(".fileurl").remove();
    $(".thumbnail").remove();
    $(".hiddenInput").remove();
    $(".deletebtn").remove();
	$('.modal-title').text('글쓰기');  // title 글수정으로 바꾸기
	$("#delete_board").remove();
	$("#submit_board").attr("onClick","fn_insertBoard()");
	$("#create_board").attr("action", "/BOND/create_board");
	if(grade===1){
	$("#model-body-form").prepend(" <p>공지사항으로 등록<input type='checkbox' name='writeType' id='writeType'></p>");
	}
}

function board_modify(data) {
	$('#myModal'+data.no).modal("hide");  //상세보기 창내리기
	$('.modal-title').text('글수정');  // title 글수정으로 바꾸기
	$('#model-body-form').remove();
	var html="";
	html+=' <div class="modal-header">';   //새로운 바디 삽입
    html+=' <button type="button" class="close" data-dismiss="modal">❌</button>';
    html+=' <h4 class="modal-title">글수정</h4></div>';
    html+='<div class="modal-body" id="model-body-form"><form action=/BOND/update_board  method="post" id="create_board">';    
    html+=	'<input type="hidden" name="board_no" value='+data.no+' id="board_no">';
    html+=' <input type="hidden" name="m_Email" value="'+data.login_email+'"> ';
    html+=	'<input type="hidden" name="blog_no" value='+data.b_no+' id="blog_no">';
    html+=	'<textarea cols="77" rows="5" name="bc_Cont"  required="required" id="bc_Cont">'+data.content+'</textarea>';
 	html+=		'<div>';
	html+=			'<h4>파일 업로드 </h4>';
	html+=			'<div class="fileDrop">업로드할 파일을 드래그 해주세요!</div>';
	html+=			'<div class="uploadedList">';	
		  if(data.uploadfiles){
              for(j=0; j<data.uploadfiles.length;j++){
                  if(checkImageType(data.uploadfiles[j])){                	
                      html += "<div><a class='fileurl' href='/BOND/upload/displayFile?fileName="+data.uploadfiles[j]+"'>";
                      html += "<img class='thumbnail' src='/BOND/upload/displayFile?fileName="+data.uploadfiles[j]+"'></a>";
                      html += "<input class='hiddenInput' type='hidden' name='bc_files' value="+data.uploadfiles[j]+">";
                  // 일반파일이면 다운로드링크
                  } else { 
                	  html+= "<div><a class='fileurl' href='/BOND/upload/displayFile?fileName="+data.uploadfiles[j]+"'>"+data.uploadfiles[j]+"</a>";
                      html+="<input class='hiddenInput' type='hidden' name='bc_files' value="+data.uploadfiles[j]+">";
                  }
                  // 삭제 버튼
                  html += "<span class='deletebtn' data-src="+data.uploadfiles[j]+">❌</span></div>";
               }
              }
	html+='	</div>';
	html+='</div>';
	html+='<div class="modal-footer">';
	if(data.grade==="admin" || data.grade==="manager"){
	var grade="1";
	}else{
	var grade="2";	
	}
 	html+='<input type="button" value="작성완료" onClick="fn_updateBoard('+grade+')" class="btn btn-primary" id="submit_board">';
 	html+='<input type="button" value="삭제하기" onClick="fn_deleteBoard('+grade+')" class="btn btn-primary" id="delete_board">';
 	html+='<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>';
 	html+='</div></form></div>';
    
 	fileupload();
    $("#modal-content-form").html(html);
	//$('#myModal').modal({backdrop: 'static', keyboard: false}) ;
}


// 좋아요, 좋아요취소 동작 함수
function actionGoods(bc_No){
   
   
   if( $("#GoodsBtn"+bc_No).val() == '좋아요 취소' ){
      $.ajax({
         type:'POST',
         url : "/BOND/Goods_Cancel",
         data :{
            "bc_No" : bc_No
         },
         success : function(data){
            
            if(data == 1){
            
            	checkGoods(bc_No); 
          	  showGoodsCount(bc_No);      
          
             
            } else {
               alert("좋아요 취소를 할수없습니다.");
               return false;
            }
            
         },
         error:function(request,status,error){
                      
             }
      });
   } else {
      $.ajax({
         type:'POST',
         url : "/BOND/Goods_Regist",
         data :{
            "bc_No" : bc_No
         },
         success : function(data){
            if(data == 1){
             	checkGoods(bc_No); 
            	  showGoodsCount(bc_No);      
            
            
            } else {
               alert("좋아요를 누를수 없습니다.");
               return false;
            }
            
         },
         error:function(request,status,error){
                           
             }
      });
   }
   
   
   
}



// 좋아요 , 좋아요 취소 체크 함수
function checkGoods(bc_No){
   $.ajax({
      type:'POST',
      url : "/BOND/Check_Goods",
      data :{
         "bc_No" : bc_No
      },
   
      success : function(data){
          if (data == "true") {
             document.getElementById("GoodsBtn"+bc_No).value="좋아요 취소";

               } else {
               document.getElementById("GoodsBtn"+bc_No).value="좋아요";
               }
      },
      error:function(request,status,error){
            /* alert("좋아요 체크 통신에러"); */
          }
   });
   
   
}

// 좋아요횟수 보여주기
function showGoodsCount(bc_No){
        
     $.ajax({
         type:'POST',
         url : "/BOND/content_goods",
         data :{
            "bc_No" : bc_No
         },
         success : function(data){
                  
               $(".goods"+bc_No).text(data).val();
               getHotList();
           
                   
         },
         error:function(request,status,error){
           
               
          }
      });    
}


//파일업로드
function fileupload() {
	$(document).ready(function(){
	        $(".fileDrop").on("dragenter dragover", function(event){
	            event.preventDefault(); // 기본효과를 막음
	        });
	        // event : jQuery의 이벤트
	        // originalEvent : javascript의 이벤트
	        $(".fileDrop").on("drop", function(event) {
	            event.preventDefault(); 
	            var files = event.originalEvent.dataTransfer.files;
	            var file = files[0];
	            console.log(file);
	            var formData = new FormData();
	            formData.append("file", file);

	            $.ajax({
	                type: "post",
	                url: "/BOND/upload",
	                data: formData,
	                dataType: "text",
	                processData: false,
	                contentType: false,
	                // 업로드 성공하면
	                success: function(data) {
	                    var str = "";
	                    var html="";
	                    // 이미지 파일이면 썸네일 이미지 출력
	                    if(checkImageType(data)){ 
	                        str = "<div><a class='fileurl' href='/BOND/upload/displayFile?fileName="+getImageLink(data)+"'>";
	                        str += "<img class='thumbnail' src='/BOND/upload/displayFile?fileName="+data+"'></a>";
	                        html="<input class='hiddenInput' type='hidden' name='bc_files' value="+getImageLink(data)+">"
	                    // 일반파일이면 다운로드링크
	                    } else { 
	                        str = "<div><a class='fileurl' href='/BOND/upload/displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a>";
	                        html="<input class='hiddenInput' type='hidden' name='bc_files' value="+getImageLink(data)+">"
	                    }
	                    // 삭제 버튼
	                    str += "<span class='deletebtn' data-src="+data+">[삭제]</span></div>";
	                    $(".uploadedList").append(str);
	                    $(".uploadedList").append(html);
	                }
	            });
	        });
	        $(".uploadedList").on("click", "span", function(event){
	           
	            var that = $(this); // 여기서 this는 클릭한 span태그
	            $.ajax({
	                url: "/BOND/upload/deleteFile",
	                type: "post",
	                // data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
	                // 태그.attr("속성")
	                data: {fileName:$(this).attr("data-src")}, // json방식
	                dataType: "text",
	                success: function(result){
	                    if( result == "deleted" ){
	                        // 클릭한 span태그가 속한 div를 제거
	                        that.parent("div").remove();
	                    }
	                }
	            });
	        });
	    });
	};
	 // 원본파일이름을 목록에 출력하기 위해
	    function getOriginalName(fileName) {
	        // 이미지 파일이면
	        if(checkImageType(fileName)) {
	            return; // 함수종료
	        }
	        // uuid를 제외한 원래 파일 이름을 리턴
	        var idx = fileName.indexOf("_")+1;
	        return fileName.substr(idx);
	    }
	 // 이미지파일 링크 - 클릭하면 원본 이미지를 출력해주기 위해
	    function getImageLink(fileName) {
	        // 이미지파일이 아니면
	        if(!checkImageType(fileName)) { 
	            return; // 함수 종료 
	        }
	        // 이미지 파일이면(썸네일이 아닌 원본이미지를 가져오기 위해)
	        // 썸네일 이미지 파일명 - 파일경로+파일명 /2017/03/09/s_43fc37cc-021b-4eec-8322-bc5c8162863d_spring001.png
	        var front = fileName.substr(0, 12); // 년원일 경로 추출
	        var end = fileName.substr(14); // 년원일 경로와 s_를 제거한 원본 파일명을 추출
	        console.log(front); // /2017/03/09/
	        console.log(end); // 43fc37cc-021b-4eec-8322-bc5c8162863d_spring001.png
	        // 원본 파일명 - /2017/03/09/43fc37cc-021b-4eec-8322-bc5c8162863d_spring001.png
	        return front+end; // 디렉토리를 포함한 원본파일명을 리턴
	    }
	 // 이미지파일 형식을 체크하기 위해
	    function checkImageType(fileName) {
	        // i : ignore case(대소문자 무관)
	        var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
	        return fileName.match(pattern); // 규칙이 맞으면 true
	    }