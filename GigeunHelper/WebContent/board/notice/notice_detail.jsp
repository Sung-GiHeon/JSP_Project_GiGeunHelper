<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 상세보기 -->
<jsp:include page="../../common/header.jsp"/>

<section>
<div class="container">
	<table class="table table-striped table-bordered table-hover">
		<thead class="thead-dark ">
		<tr>
			<th colspan=2>공지사항 확인</th>
		</tr>
		</thead>
		<tr>
			<td>작성자</td>
			<td>${notice.notice_author}</td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td>
				${notice.notice_category}
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${notice.notice_title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<div style="width:600px;min-height:300px;border:1px solid #ccc;">
				${notice.notice_content}
				</div>
			</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${notice.notice_date}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a style="color:black;" href="noticeUpdateForm.do?notice_num=${notice.notice_num}">수정</a>
				 | 
			    <a style="color:black;" href="noticeDelete.do?notice_num=${notice.notice_num}">삭제</a>
			</td>
		</tr>
	</table>
</div>
</section>

<jsp:include page="../../common/footer.jsp"/>