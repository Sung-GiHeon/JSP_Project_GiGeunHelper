<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<div class ="container">
<section>
	
	<p class="h3">비밀번호 변경</p>
		
	
	<form action="changePass.mr" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<input type="hidden" name="code" value="${code}"/>
		<table class="table table-bordered table-striped table-hover ">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="member_id"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="member_pw"/>
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="변경요청"/>
				</td>
			</tr>
		</table>
	</form>
	
	
</section>
</div>
<jsp:include page="../common/footer.jsp"/>