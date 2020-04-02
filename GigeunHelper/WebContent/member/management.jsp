<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<div class ="container">
<section>
	<table class="table table-striped table-bordered table-hover">
		<thead class="thead-dark ">
		<tr>
			<th colspan="7">회원목록</th>
		</tr>
		
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>메일</th>
			<th>Phone</th>
			<th>Credit</th>
			<th>회원등록일</th>
			<th>이름</th>
		</tr>
		</thead>
		<!-- 회원목록 -->
		<c:choose>
			<c:when test="${!empty memberList}">
				<c:forEach var="user" items="${memberList}">
					<tr>
						<td>${user.member_num}</td>
						<td>${user.member_id}</td>
						<td>${user.member_mail}</td>
						<td>${user.member_phone}</td>
						<td>${user.member_credit}</td>
						<td>${user.member_date}</td>
						<td>${user.member_name}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="6">등록된 회원정보가 없습니다.</td></tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class ="container">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
		<div class="btn-group mr-2" role="group" aria-label="First group">
		<c:if test="${pageInfo.page > 1}">
			<button type="button" class="btn btn-dark">
			<a style="color:white;" href="management.mr?page=${pageInfo.page-1}">[이전]</a>
			</button>
		</c:if>
		<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
			<button type="button" class="btn btn-dark">
			<a style="color:white;" href="management.mr?page=${i}">[ ${i} ]</a>	
			</button>	
		</c:forEach>
		<c:if test="${pageInfo.page < pageInfo.maxPage}">
			<button type="button" class="btn btn-dark">
			<a style="color:white;" href="management.mr?page=${pageInfo.page+1}">다음</a>
			</button>
		</c:if>
		</div>
	</div>
	</div>
</section>
</div>
<jsp:include page="../common/footer.jsp"/>