<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<div class ="container">
<section>
	<table class="table table-striped table-hover">
		<tr class="table-info">
			<th colspan=2>회원정보</th>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${sessionScope.member.member_id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${sessionScope.member.member_name}</td>
		</tr>
		<tr>
			<td>메일</td>
			<td>${sessionScope.member.member_mail}</td>
		</tr>
		<tr>
			<td>회원등록일</td>
			<td>${sessionScope.member.member_date}</td>
		</tr>
		<tr>
			<td colspan=2>
				<input type="button" class="btn btn-outline-info" onclick="location.href='main.pd'" value="메인"/>
				<input type="button"  class="btn btn-outline-success" onclick="location.href='update.mr'" value="정보수정"/>
				<input type="button" class="btn btn-outline-warning" onclick="location.href='myProduct.pd?member_num=${sessionScope.member.member_num}'" value="물품관리"/>
				<input type="button" class="btn btn-outline-danger" onclick="location.href='withdraw.mr'" value="회원탈퇴"/>
			</td>
		</tr>
	</table>
</section>
</div>
<jsp:include page="../common/footer.jsp"/>