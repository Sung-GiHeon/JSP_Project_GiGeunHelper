<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
	<div class ="container">
		<section>
			<form action="memberLogin.mr" method="post">
		
			<table class="table table-striped table-hover ">
				<tr class="table-primary">
					<th colspan="2">로그인</th>
				</tr>
				<tr class="active">
					<td>아이디</td>
					<td><input type="text"  class="form-control" placeholder="아이디" name="member_id" required/></td>
				</tr>
				<tr class="active">
					<td>비밀번호</td>
					<td><input type="password" class="form-control" placeholder="비밀번호"name="member_pw" required/></td>
				</tr>
				<tr class="success">
					<td colspan=2>
						<label>
							
							<input type="checkbox" name="check" />
							로그인 정보 저장
						</label>
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="submit" class="btn btn-outline-primary" value="로그인"/>
						<input type="button" class="btn btn-outline-danger" onclick="location.href='findPass.mr';" value="비밀번호 찾기"/>
					</td>
				</tr>
			</table>
		
		</form>	
	</section>
	</div>
<jsp:include page="../common/footer.jsp"/>