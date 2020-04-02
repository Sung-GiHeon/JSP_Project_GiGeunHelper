<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

</script>
<head>
<style>
.down{
		border:none;
		background-color:#ffffff;
}	
.down:hover{
	background-color:#ffffff;
	color:#00FF00;
	border-style: outset;
	border:none;
}
.delete1{
	border:none;
	background-color:#ffffff;
}



.delete1:hover{
	background-color:black;
	color:#FF0000;
	border-style: outset;
	border:none;
}



</style>
</head>


<div class="container">
	<h2>구매목록</h2>
	<table class="table table-striped table-borderless table table-hover">
		<c:choose>
			<c:when test="${empty buyList}">
				<h3>구매물품이 없습니다.</h3>
			</c:when>
			<c:otherwise>
			<thead class="thead-dark">
				<tr>
					<th>이미지</th>
					<th>제목</th>
					<th>작성자</th>
					<th>구매시간</th>
					<th>재 다운로드<th>
				</tr>
			</thead>
				<c:forEach var="d" items="${buyList}">
					<tr>
						<td><img class="img-responsive" src="upload/${d.product_origin_file}" width = '100px'></td>
						<td>${d.product_title}</td>
						<td>${d.member_name}</td>
						<td>${d.buy_date}</td>
						<td><button  class="down" onclick="location.href='fileDown.pd?file_name=${d.product_origin_file}'" ><i class="material-icons">get_app</i></button></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</div>
<br/>
<br/>

<div class="container">
	<h2>판매목록</h2>
	<table class="table table-striped table-borderless table table-hover">
		<c:choose>
			<c:when test="${empty productList}">
				<h3>판매물품이 없습니다.</h3>
			</c:when>
			<c:otherwise>
			<thead class="thead-dark">
				<tr>
					<th>이미지</th>
					<th>타이틀</th>
					<th>가격</th>
					<th>삭제</th>
				</tr>
			</thead>
				<c:forEach var="d" items="${productList}">
					<tr>
						<td><img src="upload/${d.product_origin_file}" width = '100px'/></td>
						<td>${d.product_title}</td>
						<td>${d.product_credit}</td>
						<td><button class="delete1" onclick="location.href='productDelete.pd?product_num=${d.product_num}&member_num=${d.product_member_num}'"><i class="material-icons">delete_forever</i></button></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
	</table>
</div>
<jsp:include page="../common/footer.jsp"/>