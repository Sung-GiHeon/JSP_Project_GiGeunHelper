<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style>
.form-inline1 i{
padding: 5px 5px 5px 5px;
	 margin: 5px 5px ;
	 color:#FE2E2E;
}
.delete1{
	border:none;
}



.delete1:hover{
	background-color:black;
	color:#ffffff;
	border-style: outset;
	border:none;
}
.cartimage img {
  display: block;
  position: relative;
  -webkit-transition: all 0.4s cubic-bezier(0.88,-0.99, 0, 1.81);
  transition: all 0.4s cubic-bezier(0.88,-0.99, 0, 1.81);
}
.cartimage {
  width: 100px;
  height: 100px;
  float: left;
  overflow: hidden;
  position: relative;
  text-align: center;
  cursor: default;
}

.cartimage:hover img {
  -ms-transform: scale(1.2);
  -webkit-transform: scale(1.2);
  transform: scale(1.2);
}


</style>
</head>

<jsp:include page="../common/header.jsp"/>
<div class="container">
	<form class="form-inline1">
	<h3>장 바 구 니</h3>
	<i class="material-icons">shopping_cart</i>
	</form>
	<c:choose>
		<c:when test="${empty list}">
			<h3>장바구니가 비어있습니다.</h3>
		</c:when>
		<c:otherwise>
			<table class="table table-striped table-borderless table table-hover">
				<thead class="thead-dark">
					<tr>
						<th>이미지</th>
						<th>타이틀</th>
						<th>판매자</th>
						<th>가격</th>
						<th>카트 비우기</th>
					</tr>
				</thead>	
				<c:forEach var="d" items="${list}">
					<tr>
						<td>
						<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
    						<div class="cartimage">
								<img src="upload/${d.product_origin_file}" width = '100px'/>
							</div>
						</div>
						</td>
						<td>${d.product_title}</td>
						<td>${d.member_name}</td>
						<td>${d.product_credit}</td>
						<td><button class="delete1" onclick="location.href='cartDelete.pd?cart_num=${d.cart_num}&member_num=${d.member_num}'"><i class="material-icons">delete_outline</i></button></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="../common/footer.jsp"/>