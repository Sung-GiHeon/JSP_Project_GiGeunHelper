<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/comment.css" rel="stylesheet" type="text/css"/>
<c:if test="${!empty member}">
	<div class="container">
		<h3 style="font-size:20px; font-weight:bolder;">댓글 작성</h3>
		<br/>
		<div class="commentWrite">
			<textarea name="comment_content" id="comment_content" class="comment_content"></textarea>
			<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="First group">
			<input  class="btn btn-dark" type="button" id="commentWriteBtn" value="등록"/>
			</div>
			</div>
		</div>
	</div>
</c:if>
<!-- 댓글 목록 -->
<br/>
<div class="container">
<h3 style="font-size:20px; font-weight:bolder;" id="comment_title">댓글 목록[0]</h3  >
</div>
<div id="comment" class="container">

</div>
<div id="pagingWrap" class="contanier">

</div>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var page = 1;
	
	getList(page);
	
	$("#commentWriteBtn").click(function(){
		$.ajax({
			type : "POST",
			url : "commentWrite.co",
			data : {
				qna_num : '${boardVO.qna_num}',
				comment_writer_num : '${member.member_num}', 
				comment_name :  '${member.member_name}',
				comment_content : $("#comment_content").val()
			},
			success : function(data){
				alert(data ? "등록 성공" : "등록 실패");
				getList(1);
				$("#comment_content").val('');
			}
		});
	});
	
	
	
	function getList(pageNum){
		$.ajax({
			type : "GET",
			url : "list.co",
			data : {
				qna_num : '${boardVO.qna_num}',
				page : pageNum
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				console.log(data.pm);
				
				$("#comment_title").html("댓글 목록["+data.pm.totalCount+"]");
				//$("#comment").append(data.toString());
				page = data.pm.cri.page;
				printList(data.list);
				printPage(data.pm);
			}
		});
	}
	
	function printList(list){
		console.log(list);
		var html = "";
		$.each(list,function(){
			html +="<div class='commentListWrap'>";
			if(this.comment_delete == 'N'){
				if('${!empty member}' && '${member.member_num}' == this.comment_writer_num){
					// 삭제 버튼
					html +="<form>";
					html +="<div class='closeBtn'>";
					html +="<input type='button' data-num='"+this.comment_num+"' value='X'/>";
					html +="</div>";
					html +="</form>";
				}
				html += "<div>";
				html += this.comment_name+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"+getDate(this.comment_date);
				html += "</div>";
				html += "<div>";
				html += "<textarea readonly>"+this.comment_content+"</textarea>";
				html += "</div>";
				
			}else{
				// 삭제된 댓글
				html +="<div>";
				html +="삭제된 게시물 입니다.";
				html +="</div>";
			}
			html+="</div>";
		});
		
		$("#comment").html(html);
	}
	function getDate(date){
		
		var dt = new Date(date);
		
		var year = dt.getFullYear();
		var month = dt.getMonth()+1;
		var day = dt.getDate();
		
		if(month < 10) month = "0"+month;
		if(day < 10) day = "0"+day;
		
		var hour = dt.getHours();
		var minute = dt.getMinutes();
		var second = dt.getSeconds();
		
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
	
	function printPage(pm){
		console.log(pm);
		
		var html = "";
		
		if(pm.cri.page > 1){
			html += "<a href='javascript:getList(1);'>[처음]</a>";
			if(pm.prev){
				html += "<a href='javascript:getList("+(pm.startPage-1)+");'>[이전]</a>";	
			}
		}
		
		for(var i= pm.startPage; i<=pm.endPage; i++){
			html +="<a href='javascript:getList("+i+")'>["+i+"]</a>";
		}
		
		if(pm.cri.page < pm.maxPage){
			if(pm.next){
				html += "<a href='javascript:getList("+(pm.endPage+1)+");'>[다음]</a>";
			}
			html += "<a href='javascript:getList("+pm.maxPage+");'>[마지막]</a>";	
		}
		
		$("#pagingWrap").html(html);
		
	}
	
	$("#comment").on("click",".closeBtn input",function(){
		var comment_num = $(this).attr("data-num");
		console.log(comment_num);
		if(confirm(comment_num +" 댓글을 삭제하시겠습니까?")){
			deleteComment(comment_num);
		}
	});
	
	function deleteComment(comment_num){
		$.ajax({
			type : "POST",
			url : "commentDelete.co",
			data : {
				comment_writer_num : '${member.member_num}',
				comment_num : comment_num
			},
			success : function(isSuccess){
				alert(isSuccess ? '삭제 성공' : '삭제 실패');
				getList(page);
			}
		});
	}
	
	
</script>










