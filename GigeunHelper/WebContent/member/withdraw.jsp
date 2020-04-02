<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/header.jsp"/>
	<div class ="container">
	<section>
		<form action="withdrawSubmit.mr" method="post">
			<table class="table">
				<tr class="table-danger">
					<td>비밀번호 확인</td>
					<td>
					 <div class="form-group">
						<input type="password" 
								class="form-control" 
								id="exampleInputPassword1"
								name="tempPass" 
								placeholder="비밀번호를 입력해주세요"/>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input class="btn btn-outline-danger"  type="submit" value="회원탈퇴"/></td>
				</tr>
			</table>
		</form>
	</section>
	</div>
<jsp:include page="../common/footer.jsp"/>