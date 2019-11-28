$(function(){
	setTimeout(() => {
	   getHotPicture();	
}, 200);
   setTimeout(() => {
	   getnoticeList();
}, 400);

});



function getHotPicture(){
   
   var b_No = $("#b_No").val();
   $.ajax({
      type:'POST',
      url : "/BOND/getHotPicture",
     datatype : "json",
       data: {"b_No" : b_No},
       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       success : function(data){
          var html = "";
          html+="<div class='rankarea'><span class='title'>"+"인기글"+"</span><div class='border_top'></div>";
           if(data.length >= 1){
                for(i=0; i<data.length; i++){   
                   

                   html+="<span>" +"<a class='img_Area' data-toggle='modal' href='#modal-fullscreen"+data[i].bc_no+"'>"
                         +"<img width='80px' height='80px' src='/BOND/upload/displayFile?fileName="+data[i].file_name+"' data-toggle='modal' data-target='.bd-example-modal-xl"+data[i].bc_no+"' class='gallarythumbnail'>" +
                               "</a></span>";  
                
                html+="<div class='modal fade modal-fullscreen' id='modal-fullscreen"+data[i].bc_no+"' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>";
                html+="<div class='modal-dialog'>";
                html+="<div class='modal-content'>";
                /*html+="<span>"+data[i].m_picture+"</span>";*/
                html+=" <div class='uploadArea'>";
                html+="<img  class='uploadFiles' src='/BOND/upload/displayFile?fileName="+data[i].file_name+"'>";
                html+="</div>";
               
                html+="<div class='contentRight'>";
                html+="<button type='button' class='close' data-dismiss='modal'>❌</button>";
                html +=   "<div class='col-md-3'><img src='/BOND/resources/upload/"+data[i].m_picture+"' class='member_Picture'></div>" ;
                html+=" <div class='col-md-8'><div class='who'><a data-toggle='modal' href='#myModalProfile"+data[i].bc_no+"'>"+data[i].m_nickname+"</a></div>";
                html+="<div class='when'>"+data[i].upload_date+"</div>";
                html+=" <div class='content'>"+data[i].bc_cont+"</div>";
                html+=" </div>";
                
                html+="</div>";
                html+="</div>";
                html+="</div>";
                html+="</div>";

                
                if(i == 2){
                   html+="<br>";  
                }
                
              
                html += "<div class='modal fade' id='myModalProfile"+data[i].bc_no+"' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"
                 + "<div class='modal-dialog'>"
                 + "<div class='modal-content'>"
                 + "  <div class='modal-header'>"
                 + "  <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>×</span></button>"
                 + " <h4 class='modal-title' id='myModalLabel'>Profile</h4>"
                 + "  </div>"
                 + "  <div class='modal-body' id='profile_body'><img src='/BOND/resources/upload/"
                 + data[i].m_picture
                 + "' class='member_Picture'>"+data[i].m_nickname+""+data[i].m_email+"</div>"
                 + " <div class='modal-footer'>"
                 + "  <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"
                 + "  </div></div></div></div>";
                
               
               }
            }else{
            	html+="<h5 class=text-center><b>업로드된 이미지가 없습니다.</b></h5>";
            } 
           $("#rightaside1").html(html);
           
        },
        error:function(request,status,error){
//            alert("인기글 통신에러");
        }
   })
}

function getnoticeList(){
   $.ajax({
      type:'POST',
      url : "/BOND/noticeList",
       datatype : "json",
       data: {"b_No" : $("#b_No").val()},
       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       success : function(data){
          var html = "";
          html+="<div class='noticearea'><span class='title'>"+"공지사항"+"</span><div class='border_top'></div>";
           if(data.length >= 1){
                for(i=0; i<data.length; i++){
                  html+="<span class='notice'>"+data[i].bc_cont+"</span>";
               }
            } else{
            	html+="<span class='notice text-center'><b>공지사항이 없습니다</b></span>";
            }
           html += "</div>";
            $("#rightaside2").html(html);
        },
        error:function(request,status,error){
         
        }
   })
}