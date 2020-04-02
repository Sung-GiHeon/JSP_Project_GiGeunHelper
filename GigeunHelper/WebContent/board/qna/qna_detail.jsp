<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/header.jsp"/>
<section>
<div class="container">
</br>
	<h1>게시물 상세보기</h1>
</br>
	<!-- boardVO -->
	<table class="table table-bordered table-striped table-hover">
		<tr>
			<td>작성자</td>
			<td>${boardVO.qna_name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardVO.qna_title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea readonly cols=50 rows=10>${boardVO.qna_content}</textarea></td>
		</tr>
		<c:if test="${!empty boardVO.qna_file}">
			<tr>
				<td>첨부파일</td>
				<td>
					<a href="file_down.bo?file_name=${boardVO.qna_file}">
						${boardVO.qna_file_origin}
					</a>
				</td>
			</tr>	
		</c:if>
		<tr>
			<td colspan=2>
				<c:if test="${!empty member}">
				
					<c:if test="${boardVO.qna_writer_num eq member.member_num}">
						<a style="color:black;" href="getBoardVOByUpdate.bo?qna_num=${boardVO.qna_num}">[수정]</a>
						<a style="color:black;" href="boardDelete.bo?qna_num=${boardVO.qna_num}">[삭제]</a>
					</c:if>
				</c:if>
				<a style="color:black;" href="boardList.bo">[목록]</a>
			</td>
		</tr>
	</table>
	</div>	
</section>
<script>
	function deleteBoard(){
		if(confirm("게시물을 삭제하시겠습니까? 첨부된 파일도 삭제됩니다.")){
			location.href='boardDelete.bo?qna_num=${boardVO.qna_num}';
		}
	}
</script>
<jsp:include page="../comment/commentAsync.jsp"/>
<jsp:include page="../../common/footer.jsp"/>




