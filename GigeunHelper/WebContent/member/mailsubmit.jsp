<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <jsp:include page="../common/header.jsp"/>
<div class="container">
<section>
	<form action="memberJoinCheck.mr" method="post" >
		<table class="table table-stripe table-hover Nanum">
			<tr>
				<td style=>email 체크</td>
				<td>
				  <div class="form-group">
    				<label for="exampleInputEmail1">Email address</label>
						<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="mail2"/>
				 <small id="emailHelp" class="form-text text-muted">JAVAStock에 오신것을 환영합니다.♥</small>
  				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input  class="btn btn-outline-info" type="submit" value="확인"/></td>
			</tr>
		</table>
	</form>
</section>
</div>
</body>
</html>
<jsp:include page="../common/footer.jsp"/>