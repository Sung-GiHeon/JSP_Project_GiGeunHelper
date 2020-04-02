<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<head>
<style>
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
.money{
	background-color:#ffffff;
	border:none;
}
.money:hover{
	background-color:black;
	color:#FFBF00;
	border-style: outset;
	border:none;
}

.free1{
	
	border:none;
}

.free1:hover{
	background-color:#ffffff;
	color:#0000FF;
	border-style: outset;
	border:none;
}

</style>
</head>
<div class="container">
	<h1>물품관리</h1>
	
	<div id ="productListUnfree">
		
	</div>
	
	<div id="pageWrapUnfree">
	
	</div>
	
	<div id ="productListFree">
		
	</div>
	
	<div id="pageWrapFree">
	
	</div>
	
</div>
<jsp:include page="../common/footer.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var page = 1;
	var pageUF = 1;
	
	getList(page,pageUF);
	
	function getList(pageNum,pageNumUF){
		$.ajax({
			type : "GET",
			url : "productManager.pd",
			data : {
				page : pageNum,
				pageUF : pageNumUF
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				page = data.pmFree.cri.page;
				pageUF = data.pmUnfree.cri.page;
				printListFree(data.listFree);
				printPageFree(data.pmFree);
				printListUnfree(data.listUnfree);
				printPageUnfree(data.pmUnfree);
			}
		});
	}
	
	function printListFree(listFree){
		var html = "";
		html +="<h2>무료 물품</h2>";
		if(listFree.length == 0){
			html = "<h3>등록된 물품이 없습니다.</h3>"
		}else{
			html +="<table class='table  table-borderless table-hover'>";
			html +="<thead class='thead-dark'>";
			html +="<tr>";
			html +="<th>물품번호</th>";
			html +="<th>이미지</th>";
			html +="<th>타이틀</th>";
			html +="<th>작성자</th>";
			html +="<th>이벤트 시간</th>"
			html +="<th>유로전환</th>";
			html +="<th>삭제</th>";
			html +="</tr>";
			html +="</thead>";
			$.each(listFree,function(){
				var filePath = "upload/"+this.product_origin_file;
				html +="<tr>";
				html +="<td>"+this.product_num+"</td>";
				html +="<td><img src="+filePath+" width='100px'/></td>";
				html +="<td>"+this.product_titile+"</td>";
				html +="<td>"+this.member_name+"</td>";
				html +="<td>"+this.product_free_date+"</td>";
				html +="<td class='unfree'><button class='money' type='button' data-num='"+this.product_num+"' data-credit='"+this.product_origin_credit+"'><i class='material-icons'>money</i></button></td>";	
				html +="<td class='delete'><button class='delete1' type='button' data-num='"+this.product_num+"'><i class='material-icons'>delete_forever</i></button></td>";
				html +="</tr>";
			})
			html +="</table>";
		}
		$("#productListFree").html(html);
	}
	
	
	function printListUnfree(listUnfree){
		var html = "";
		html +="<h2>일반 물품</h2>";
		if(listUnfree.length == 0){
			html = "<h3>등록된 물품이 없습니다.</h3>"
		}else{
			html +="<table class='table table-borderless table-hover'>";
			html +="<thead class='thead-dark'>";
			html +="<tr>";
			html +="<th>물품번호</th>";
			html +="<th>이미지</th>";
			html +="<th>타이틀</th>";
			html +="<th>작성자</th>";
			html +="<th>가격</th>";
			html +="<th>무료전환</th>";
			html +="<th>삭제</th>";
			html +="</tr>";
			html +="</thead>";
			$.each(listUnfree,function(){
				var filePath = "upload/"+this.product_origin_file;
				html +="<tr>";
				html +="<td>"+this.product_num+"</td>";
				html +="<td><img src="+filePath+" width='100px'/></td>";
				html +="<td>"+this.product_titile+"</td>";
				html +="<td>"+this.member_name+"</td>";
				html +="<td>"+this.product_credit+"</td>";
				html +="<td class='free'><button class='free1' type='button' data-num='"+this.product_num+"' ><i class='material-icons'>restore</i></button></td>";
				html +="<td class='delete'><button class='delete1' type='button' data-num='"+this.product_num+"' ><i class='material-icons'>delete_forever</i></button></td>";
				html +="</tr>";
			})
			html +="</table>";
		}
		$("#productListUnfree").html(html);
	}
	// 안무료 페이지
	function printPageUnfree(pmUnfree){
		console.log(pmUnfree);
		
		var html ="";
		
		if(pmUnfree.cri.page > 1){
			html += "<button type='button' class='btn btn-dark'>";
			html += "<a style='color:white;' href='javascript:getList("+page+",1);'>처음</a>"
			html += "</button>";
			if(pmUnfree.prev){
				html += "<button type='button' class='btn btn-dark'>";
				html += "<a style='color:white;' href='javascript:getList("+page+","+(pmUnfree.startPage-1)+");'>이전</a>"
				html += "</button>";
			}
		}
		for(var i = pmUnfree.startPage; i <= pmUnfree.endPage; i++){
			html += "<button type='button' class='btn btn-dark'>";
			html +="<a style='color:white;' href='javascript:getList("+page+","+i+")'>"+i+"</a>";
			html += "</button>";
		}
		
		if(pmUnfree.cri.page < pmUnfree.maxPage){
			if(pmUnfree.next){
				html += "<button type='button' class='btn btn-dark'>";
				html += "<a style='color:white;' href='javascript:getList("+page+","+(pmUnfree.endPage+1)+");'>다음</a>";
				html += "</button>";
			}
			html += "<button type='button' class='btn btn-dark'>";
			html += "<a style='color:white;' href='javascript:getList("+page+","+pmUnfree.maxPage+");'>마지막</a>";
			html += "</button>";
		}
		$("#pageWrapUnfree").html(html);
	}
	// 무료 페이지
	function printPageFree(pmFree){
		console.log(pmFree);
		
		var html ="";
		
		if(pmFree.cri.page > 1){
			html += "<button type='button' class='btn btn-dark'>";
			html += "<a style='color:white;' href='javascript:getList(1,"+pageUF+");'>처음</a>"
			html += "</button>";
			if(pmFree.prev){
				html += "<button type='button' class='btn btn-dark'>";
				html += "<a style='color:white;' href='javascript:getList("+(pmFree.startPage-1)+","+pageUF+");'>이전</a>"
				html += "</button>";
			}
		}
		for(var i = pmFree.startPage; i <= pmFree.endPage; i++){
			html += "<button type='button' class='btn btn-dark'>";
			html +="<a style='color:white;' href='javascript:getList("+i+","+pageUF+")'>"+i+"</a>";
			html += "</button>";
		}
		
		if(pmFree.cri.page < pmFree.maxPage){
			if(pmFree.next){
				html += "<button type='button' class='btn btn-dark'>";
				html += "<a style='color:white;' href='javascript:getList("+(pmFree.endPage+1)+","+pageUF+");'>다음</a>";
				html += "</button>";
			}
			html += "<button type='button' class='btn btn-dark'>";
			html += "<a style='color:white;' href='javascript:getList("+pmFree.maxPage+","+pageUF+");'>마지막</a>";
			html += "</button>";
		}
		$("#pageWrapFree").html(html);
	}
	
	$("#productListFree").on("click",".delete input",function(){
		var product_num = $(this).attr("data-num");
		console.log(product_num);
		if(confirm(product_num+"물품을 삭제 하시겠습니까?")){
			deleteProduct(product_num);
		}
	});
	
	$("#productListUnfree").on("click",".delete input",function(){
		var product_num = $(this).attr("data-num");
		console.log(product_num);
		if(confirm(product_num+"물품을 삭제 하시겠습니까?")){
			deleteProduct(product_num);
		}
	});
	
	function deleteProduct(product_num){
		$.ajax({
			type : "POST",
			url : "productDeleteManager.pd",
			data : {
				product_num : product_num
			},
			success : function(isSuccess){
				alert(isSuccess ? '삭제 성공' : '삭제 실패');
				getList(page,pageUF);
			}
		});
	}
	
	$("#productListUnfree").on("click",".free input",function(){
		var product_num = $(this).attr("data-num");
		console.log(product_num);
		if(confirm(product_num+"물품을 무료로 전환하겠습니까?")){
			freeProduct(product_num);
		}
	});
	
	function freeProduct(product_num){
		$.ajax({
			type : "POST",
			url : "productFree.pd",
			data : {
				product_num : product_num
			},
			success : function(isSuccess){
				var page = 1;
				var pageUF = 1;
				alert(isSuccess ? '전환 성공' : '전환 실패');
				getList(page,pageUF);
			}
		});
	}
	
	$("#productListFree").on("click",".unfree input",function(){
		var product_num = $(this).attr("data-num");
		
		var product_credit = $(this).attr("data-credit")
		console.log(product_num);
		console.log(product_credit);
		if(confirm(product_num+"물품을 다시 유료로 전환하겠습니까?")){
			unfreeProduct(product_num, product_credit);
		}
	});
	
	function unfreeProduct(product_num,product_credit){
		$.ajax({
			type : "POST",
			url : "productUnfree.pd",
			data : {
				product_num : product_num,
				product_credit : product_credit
			},
			success : function(isSuccess){
				var page = 1;
				var pageUF = 1;
				alert(isSuccess ? '전환 성공' : '전환 실패');
				getList(page,pageUF);
			}
		});
	}
</script>
