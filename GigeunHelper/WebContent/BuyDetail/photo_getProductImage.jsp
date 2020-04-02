<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp"></jsp:include>
<br/>
<hr/>
<br/>
<br/>
<br/>
<h2>${allPhoto.member_name}님의 전체사진</h2>
<c:forEach var="i" items="${allImageList}">
 	<a href="photo_detail.ph?product_num=${i.product_num}&member_num=${member.member_num}"><img src="upload/${i.product_origin_file}" width="400" height="400"></a>
</c:forEach>
<div>
	<c:if test="${getPageMaker.prev}">
        <a href="photo_getProductImage.ph?page=${pageMaker.startPage-1}&product_num=${product.product_num}">&lt;</a>
	</c:if>
	<c:forEach var="i" begin="${getPageMaker.startPage}" end="${getPageMaker.endPage}">
		<c:choose>
			<c:when test="${getPageMaker.cri.page eq i}">
				<span style="color">[${i}]</span>
			</c:when>
			<c:otherwise>
				<a href="photo_getProductImage.ph?page=${i}&product_num=${product.product_num}">[${i}]</a>
			</c:otherwise>
		</c:choose>		
	</c:forEach>
	<c:if test="${getPageMaker.next}">
    	<a href="photo_getProductImage.ph?page=${getPageMaker.endPage+1}&product_num=${product.product_num}">&gt;</a>
	</c:if>	
</div>