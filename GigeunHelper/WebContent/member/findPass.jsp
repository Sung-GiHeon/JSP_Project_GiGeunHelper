<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<div class ="container">
<section>
	<form action="findPassSubmit.mr" method="post">
		<table class="table table-bordered table-striped table-hover">
				<tr class="table-dark">
					<th colspan="2" >비밀번호 찾기</th>
				</tr>
			<tr>
				<td>id</td>
				<td><input type="text" name="member_id"/></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="member_mail"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="확인"/></td>
			</tr>
		</table>
	</form>
</section>
</div>
<jsp:include page="../common/footer.jsp"/>