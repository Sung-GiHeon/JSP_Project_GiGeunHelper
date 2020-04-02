<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"/>
<script>
	function directJoin(){
		alert('click');
		var form = document.getElementById("joinForm");
		var member_id = form.member_id;
		var member_pw = form.member_pw;
		var member_re_pw = document.getElementById("member_re_pw");
		
		if(member_id.value.length > 12 || member_id.value.length < 5){
			alert("아이디는 5 ~ 12 글자 이내로 작성해주세요.");
			member_id.value = "";
			member_id.focus();
		}else if(member_pw.value.length > 8 || member_pw.value.length < 5){
			alert("비밀번호는 5 ~ 8 글자 이내로 작성해주세요.");
			member_pw.value = "";
			member_pw.focus();
		}else if(member_pw.value != member_re_pw.value){
			alert("비밀번호가 일치하지 않습니다.");
			member_re_pw.focus();
			member_re_pw.value="";
		}else{
			form.submit();
		}
		
	}

</script>

<!-- 회원관려   *.mr -->

<section>
	<div class ="container">
	<form  id="joinForm" action="memberJoin.mr" method="post">
		<table class="table table-bordered table-striped table-hover">
			<tr class="table-success">
				<th colspan="2">회원가입</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="member_id"  required/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="member_pw" required/></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" id="member_re_pw" required/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="member_mail" placeholder="email형식" value="${param.mail}" required/></td>
			</tr>	
			<tr>
				<td>phone</td>
				<td><input type="number" name="member_phone" maxlength="3" required/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="member_name" required/></td>
			</tr>
			<tr>
			<td colspan="2">
				<input type="button" class="btn btn-outline-success" onclick="javascript:directJoin()" value="회원가입"/> | 
				<input type="button"  class="btn btn-outline-warning"onclick="location.href='main.jsp?page=login';" value="로그인"/>
			</td>		
		</tr>
		</table>
	</form >
	</div>
</section>

<jsp:include page="../common/footer.jsp"/>