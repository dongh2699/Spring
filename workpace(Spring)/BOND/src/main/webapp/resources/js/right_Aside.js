$(function(){
	  setTimeout(function() {
		   getHotList();
     	}, 200);
	  setTimeout(function() {
		   getnoticeList();
    	}, 300);

});

function getHotList(){
   $.ajax({
      type:'POST',
      url : "/BOND/getHotlist",
       datatype : "json",
       data: {"b_No" : $("#b_No").val()},
       contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       success : function(data){
          var html = "";
          html+="<div class='rankarea'><span class='title'>"+"인기글"+"</span><div class='border_top'></div>";
           if(data.length > 0){
                for(i=0; i<data.length; i++){
                		if(data[i].bc_cont.length>15){
                			html+="<span class='ranking'><a href='#info"+data[i].bc_no+"'>"+(i+1)+". "+data[i].bc_cont.substring(0, 15)+"...</a></span>";                   			
                		}else{
                			html+="<span class='ranking'><a href='#info"+data[i].bc_no+"'>"+(i+1)+". "+data[i].bc_cont+"</a></span>";                   			
                		}
               }
            } 
           html += "</div>";
            $("#rightaside1").html(html);
        },
        error:function(request,status,error){
       
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
        		if(data[i].bc_cont.length>20){
        			html+="<span class='notice'><a href='#info"+data[i].bc_no+"'>"+(i+1)+". "+data[i].bc_cont.substring(1, 15);+"...</a></span>";                   			
        		}else{
        			html+="<span class='notice'><a href='#info"+data[i].bc_no+"'>"+(i+1)+". "+data[i].bc_cont+"</a></span>";                   			
        		}
        	   }
            } else{
            	html+="<span class='notice'>공지사항이 없습니다</span>";
            }
           html += "</div>";
            $("#rightaside2").html(html);
        },
        error:function(request,status,error){
          
        }
   })
}