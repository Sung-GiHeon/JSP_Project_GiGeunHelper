<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/header.jsp"/>
<section>
	<h1>게시글 작성</h1>
	<form action="boardWriteSubmit.bo" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="qna_name" value="${sessionScope.member.member_name}"/>
		<input type="hidden" name="qna_writer_num" value="${sessionScope.member.member_num}"/>
		<table class="table table-bordered table-stripe table-hover">
			<tr>
				<td>작성자</td>
				<td>${sessionScope.member.member_name}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="qna_title" required/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="qna_content" cols=50 rows=10></textarea>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="qna_file" /></td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="submit" value="작성완료"/> | 
					<input type="reset" value="새로작성"/>
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../../common/footer.jsp"/>











