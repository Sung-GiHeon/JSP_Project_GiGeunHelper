<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/header.jsp"/>
<section>
<div class="container">
	</br>
	<p class="h1">질문과 답변 목록</p>
	</br>
	<table class="table table-bordered table-striped table-hover">
		<c:if test="${!empty sessionScope.member}">
		<thead class="thead-dark ">
		<tr>
			<th colspan="5">
				<a style="color:white;" href="boardWrite.bo">질문 작성하러 가기</a>
			</th>
		</tr>
		</thead>
		</c:if>
		
		<c:choose>
			<c:when test="${!empty boardList}">
				<thead class="thead-dark ">
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				</thead>
				<!-- 게시글 목록 -->
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.qna_num}</td>
						<td>
							<c:if test="${board.qna_re_lev != 0}">
								<c:forEach var="i" begin="1" end="${board.qna_re_lev}">
									&nbsp;&nbsp;&nbsp;
								</c:forEach>
								└▶
							</c:if>
							<a style="color:#000000;"  href="boardDetail.bo?qna_num=${board.qna_num}">${board.qna_title}</a>
						</td>
						<td>${board.qna_name}</td>
						<td>${board.qna_date}</td>
						<td>${board.qna_readcount}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan=5>등록된 게시물이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="pageWrap">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
	<div class="btn-group mr-2" role="group" aria-label="First group">
		<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<c:choose>
				<c:when test="${pageMaker.cri.page eq i}">
					<button type="button" class="btn btn-dark">
					<span style="color:white;">${i}</span>
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-dark">
					<a style="color:white;"  href="boardList.bo?page=${i}">${i}</a>
					</button>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	</div>
	</div>
</div>	
</section>
<jsp:include page="../../common/footer.jsp"/>












