<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/comment.css" rel="stylesheet" type="text/css"/>
<c:if test="${!empty member}">
<div class="container">
	<div class="commentWrap">
		<h3>댓글 작성</h3>
		<br/>
		<div class="commentWrite">
			<form action="commentWrite.bo" method="post">
				<input type="hidden" name="comment_writer_num" value="${member.member_num}"/>
				<input type="hidden" name="comment_name" value="${member.member_name}"/>
				<input type="hidden" name="comment_board_num" value="${boardVO.qna_num}"/>
				<textarea name="comment_content" class="comment_content"></textarea>
				<input type="submit" value="등록"/>
			</form>
		</div>
	</div>
</div>
</c:if>
<div class="container">
<!-- 댓글 목록 -->
<c:if test="${!empty commentList}">
	<h3>댓글 목록[${pm.totalCount}]</h3>
	<c:forEach var="c" items="${commentList}">
		
		<div class="commentListWrap">
			<c:choose>
				<c:when test="${c.comment_delete eq 'N'}">
					<c:if test="${!empty member && member.member_num == c.comment_writer_num}">
						<form action="commentDelete.bo" method="post">
							<input type="hidden" name="comment_num" value="${c.comment_num}"/>
							<input type="hidden" name="comment_board_num" value="${boardVO.qna_num}"/>
							<input type="hidden" name="comment_writer_num" value="${member.member_num}"/>
							<div class="closeBtn">
								<input class="btn btn-dark" type="submit" value="X"/>
							</div>
						</form>
					</c:if>
					<div>
						${c.comment_name}&nbsp;&nbsp;&nbsp;
						|&nbsp;&nbsp;&nbsp;
						${c.comment_date}
					</div>
					<div>
						<textarea readonly>${c.comment_content}</textarea>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<h3>삭제된 댓글 입니다.</h3>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
	<div class="pagingWrap">
		<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
			<a href="boardRead.bo?qna_num=${boardVO.qna_num}&page=${i}">[${i}]</a>
		</c:forEach>
	</div>
</c:if>
</div>



