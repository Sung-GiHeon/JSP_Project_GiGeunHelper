<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 목록 -->
<jsp:include page="../../common/header.jsp"/>
<section>
	<div class="container">
		<table class="table table-striped table-bordered table-hover">
			<thead class="thead-dark ">
			<tr>
				<th colspan=4>공지사항 목록</th>
			</tr>
			<tr>
				<th colspan=3>
					<form action="noticeSearch.do" method="GET">
						<select name="searchName">
							<option value="author">작성자</option>
							<option value="title">제목</option>
						</select>	
						<input type="text" name="searchValue"/>
						<input type="submit" value="검색"/>
					</form>
				</th>
				<th colspan=2>
					<c:if test="${!empty member && member.member_id eq 'admin'}">
						<a style="color:white;" href="noticeWriteForm.do">공지글 작성</a>
					</c:if>
				</th>
			</tr>
			</thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>		
				<th>작성자</th>		
				<th>작성일</th>				
			</tr>
			
			<!-- 게시물 목록 -->
			<c:choose>	
				<c:when test="${!empty noticeList}">
					<c:forEach var="n" items="${noticeList}">
						<tr>
							<td>${n.notice_num}</td>
							<td>
								<a style="color:black;" href="noticeDetail.do?notice_num=${n.notice_num}">
								[${n.notice_category}] ${n.notice_title}
								</a>
							</td>
							<td>${n.notice_author}</td>
							<td>${n.notice_date}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan=4>등록된 게시물이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<!-- paging  처리 -->
	<%-- <div class="pageWrap">
		<c:if test="${pageMaker.cri.page > 1}">
			<a href="notice.do?page=1">[처음]</a>
			<c:if test="${pageMaker.prev}">
				<a href="notice.do?page=${pageMaker.startPage - 1}">[이전]</a>
			</c:if>
			
		</c:if>
		<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<c:choose>
				<c:when test="${pageMaker.cri.page == i}">
					[ ${i} ] 
				</c:when>
				<c:otherwise>
					<a href="notice.do?page=${i}">[ ${i} ]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageMaker.cri.page < pageMaker.maxPage}">
			<c:if test="${pageMaker.next}">
				<a href="notice.do?page=${pageMaker.startPage+pageMaker.displayPageNum}">[다음]</a>
			</c:if>
			<a href="notice.do?page=${pageMaker.maxPage}">[마지막]</a>
		</c:if>
	</div> --%>
	<div class="container">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
	<div class="btn-group mr-2" role="group" aria-label="First group">
		<c:if test="${pageMaker.cri.page > 1}">
			<button type="button" class="btn btn-dark">
			<a style="color:white;" href="noticeSearch.do${pageMaker.make(1)}">처음</a>
			</button>
			<c:if test="${pageMaker.prev}">
				<button type="button" class="btn btn-dark">
				<a style="color:white;" href="noticeSearch.do${pageMaker.make(pageMaker.startPage - 1)}">이전</a>
				</button>
			</c:if>
			
		</c:if>
		<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<c:choose>
				<c:when test="${pageMaker.cri.page == i}">
					<button type="button" class="btn btn-dark">
					<a style="color:white;"> ${i} </a> 
					</button>
				</c:when>
				<c:otherwise>
					<%-- <a href="noticeSearch.do?page=${i}&searchName=${pageMaker.search.searchName}&searchValue=${pageMaker.search.searchValue}">[ ${i} ]</a> --%>
					<button type="button" class="btn btn-dark">
					<a style="color:white;" href="noticeSearch.do${pageMaker.make(i)}"> ${i} </a>
					</button>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageMaker.cri.page < pageMaker.maxPage}">
			<c:if test="${pageMaker.next}">
				<button type="button" class="btn btn-dark">
				<a style="color:white;" href="noticeSearch.do${pageMaker.make(pageMaker.endPage+1)}">다음</a>
				</button>	
			</c:if>
			<button type="button" class="btn btn-dark">
			<a style="color:white;" href="noticeSearch.do${pageMaker.make(pageMaker.maxPage)}">마지막</a>
			</button>
		</c:if>
		</div>
	</div>
</div>
</section>
<jsp:include page="../../common/footer.jsp"/>




