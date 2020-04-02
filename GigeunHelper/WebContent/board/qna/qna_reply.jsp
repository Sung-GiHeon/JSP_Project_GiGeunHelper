<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/header.jsp"/>
<section>
	<h1>답변글 작성</h1>
	<form action="boardReplySubmit.bo" method="post">
		<input type="hidden" name="qna_num" value="${boardVO.qna_num}"/>
		<input type="hidden" name="qna_re_ref" value="${boardVO.qna_re_ref}"/>
		<input type="hidden" name="qna_re_lev" value="${boardVO.qna_re_lev}"/>
		<input type="hidden" name="qna_re_seq" value="${boardVO.qna_re_seq}"/>
		<input type="hidden" name="qna_name" value="${member.name}"/>
		<input type="hidden" name="qna_writer_num" value="${member.num}"/>
		<table class="table table-bordered table-stripe table-hover">
			<tr>
				<td colspan="2">
					원본글 번호${boardVO.qna_re_ref} /  
					원본글 레벨 ${boardVO.qna_re_lev} / 
					원본글 정렬번호${boardVO.qna_re_seq}
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${member.name}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="qna_title" required/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="qna_content" required cols=50 rows=10></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답변작성완료"/>
					<input type="reset" value="새로작성"/>
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../../common/footer.jsp"/>