<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 수정 -->
<jsp:include page="../../common/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="static/js/service/HuskyEZCreator.js"></script>
	
	
<section>
	<div class="container">
	<form id="frm" action="BoardUpdate.bo" method="post">
		
		<input type="hidden" name="qna_num" value="${boardVO.qna_num}"/>
		<input type="hidden" name="qna_name" value="${member.member_name}"/>
		
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th colspan=2>공지사항 수정</th>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${member.member_name}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="qna_title" value="${boardVO.qna_title}"/></td>
			</tr>
			<tr style="background-color:white;">
				<td>내용</td>
				<td style="width:710px;">
					<textarea name="qna_content"  cols=50 rows=10>${boardVO.qna_content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="First group">
					<button type="button" class="btn btn-dark" id="saveBtn">
						작성완료
					 </button>
					 <button type="reset" class="btn btn-dark">
				    	초기화
				    </button>
				    </div>
				 </div>
				</td>
			</tr>
		</table>
	</form>
	
</div>
</section>

<script>
$(function(){
$("#saveBtn").click(function(){
	if(confirm("저장하시겠습니까?")){
		$("#frm").submit();
	}
});
});
</script>
<jsp:include page="../comment/commentAsync.jsp"/>
<jsp:include page="../../common/footer.jsp"/>