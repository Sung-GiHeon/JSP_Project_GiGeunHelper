<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/header.jsp"/>
<div class ="container">
<section>
	<form action="memberUpdate.mr" method="post">
		<input type="hidden" name="member_num" value="${member.member_num}"/>
		<table class="table table table-bordered table-striped table-hover">
			<tr class="table-warning">
				<th colspan="2">회원정보 수정</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="email" name="member_id" value="${member.member_id}" readonly/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="member_pw" value="${member.member_pw}" required/></td>
			</tr>
			
			<tr>
				<td>전화번호</td>
				<td><input type="number" name="age" maxlength="3" value="${member.member_phone}" required/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="member_name" value="${member.member_name}" required/></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-outline-warning" type="submit" value="수정완료"/></td>
			</tr>
		</table>
	</form>
</section>
</div>
<jsp:include page="../common/footer.jsp"/>