/*
 * 게시물 등록하기(Ajax)
 */
function fn_insertBoard(){
    
    $.ajax({
        type:'POST',
        url : "/BOND/create_board",
        data:$("#create_board").serialize(),
        success : function(data){
        	if(data=="success")
    		{
        		getBoardtList();
        		$("#bc_Cont").val("");
    		}
        },
        error:function(request,status,error){
            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
        
    });
}
 
/**
 * 초기 페이지 로딩시 게시물 불러오기
 */
$(function(){
    
	getBoardtList();
    
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
                    html += "<div>";
                    html += "<div><table><h6><strong>"+data[i].writer+"</strong></h6>";
                    html += "<tr><td>"+ data[i].content +"</td></tr>";
                    html += "</table></div>";
                    html += "</div>";
                    html += "<div>";
                    html += "<div><table><tr><td>"+data[i].writer+"</td><td></td></tr>";
                    html += "</table></div>";
                    html += "</div>";
                    
                }
                
            } else {
                
                html += "<div>";
                html += "<div><table><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div>";
                
            }
            
           
            $("#boardList").html(html);
            
        },
        error:function(request,status,error){
            
       }
        
    });
}