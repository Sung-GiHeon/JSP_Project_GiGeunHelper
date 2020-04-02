<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css?family=Sunflower:500" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>board_list</title>
<style>
.material-icons {
  font-family: 'Material Icons';
  font-weight: normal;
  font-style: normal;
  font-size: 35px;  /* Preferred icon size */
  display: inline-block;
  line-height: 1;
  text-transform: none;
  letter-spacing: normal;
  word-wrap: normal;
  white-space: nowrap;
  direction: ltr;
  }
   .form-inline {
   	padding-top:15px;
   }
  .form-inline >i{
  color:#000000;
  margin-right:10px;
  }
 .form-inline >a{
 	pointer-events: none;
	cursor: default;
 	font-size:18px;
 } 
</style>
</head>
<body>

	<div  class="container-fluid" >
		<nav class="navbar navbar-expand-lg navbar-light bg-pink"style="height:90px; background-color:#ffffff;">
		 <a class="navbar-brand" href="main.pd" style="font-family:LotteMartHappy;font-size:30px;">Java Stock</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
			<ul  class="navbar nav">
				<c:choose>
				<c:when test="${!empty sessionScope.member}">
					<li  class="nav-item">
						<a class="nav-link " href="info.mr">${member.member_name}님 반갑습니다.</a>
					</li>				
					<c:if test="${sessionScope.member.member_id eq 'admin'}">
						
						<li class ="nav-item">
							<a class="nav-link" href="productManagerPage.pd">물품관리</a>
						</li>
					</c:if>
					<li  class="nav-item">
						<a class="nav-link" href="logOut.mr">로그아웃</a>
					</li>
					
				</c:when>
				
				<c:otherwise>
					<li  class="nav-item">
						<a class="nav-link" href="login.mr" >로그인</a>
					</li>
					<li  class="nav-item">
						<a class="nav-link" href="mailJoin.mr">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
				<li class="nav-item">
					<a class="nav-link" href="boardList.bo" style="font-family:LotteMartHappy;">Q & A</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="noticeSearch.do" style="font-family:LotteMartHappy;">Notice</a>
				</li>
				<c:if test="${!empty sessionScope.member}">
					<li>
						<form class="form-inline">
						<i class="material-icons">monetization_on </i><a>${member.member_credit}</a>
						</form>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="cart.pd?member_num=${sessionScope.member.member_num}"> 
							<i class="material-icons">shopping_cart</i>
						</a>	
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
<% request.setCharacterEncoding("UTF-8"); %>