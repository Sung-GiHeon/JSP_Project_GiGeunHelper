<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<script src="https://code.iconify.design/1/1.0.3/iconify.min.js"></script>


<head>

<style>
.pagingWrap a{
	color:#ffffff;
	background-color:#2E2E2E;
	padding: 5px 5px 5px 5px;
	 margin: 5px 5px;
	border-radius: 15%;
}

.pagingWrap a:hover{
color:#ffffff;
	box-shadow: 0 80px 0 0 rgba(0,0,0,0.25) inset, 0 -80px 0 0 rgba(0,0,0,0.25) inset; 
	border-radius: 100%;			
	padding: 5px 5px 5px 5px;
	 margin: 5px 5px;
}

.product_categoty{
	padding: 5px 5px 5px 5px;
	margin: 5px 5px;
	border-radius: 15%;
	color:#ffffff;
	background-color:#2E2E2E;
	box-shadow: #ffffff 0 0px 0px
}
.product_categoty:hover{
	color: rgba(255, 255, 255, 0.5);
	border-radius: 100%;
	box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
}

.hovereffect {
  width: auto;
  height: auto;
  float: left;
  overflow: hidden;
  position: relative;
  text-align: center;
  cursor: default;
  padding: 5px 5px 5px 5px;
	 margin: 5px 5px;
}

.hovereffect .overlay {
  position: absolute;
  overflow: hidden;
  width: 80%;
  height: 80%;
  left: 10%;
  top: 10%;
  border-bottom: 1px solid #FFF;
  border-top: 1px solid #FFF;
  -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
  transition: opacity 0.35s, transform 0.35s;
  -webkit-transform: scale(0,1);
  -ms-transform: scale(0,1);
  transform: scale(0,1);
}

.hovereffect:hover .overlay {
  opacity: 1;
  filter: alpha(opacity=100);
  -webkit-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1);
}

.hovereffect img {
  display: block;
  position: relative;
  -webkit-transition: all 0.35s;
  transition: all 0.35s;
}

.hovereffect:hover img {
  filter: url('data:image/svg+xml;charset=utf-8,<svg xmlns="http://www.w3.org/2000/svg"><filter id="filter"><feComponentTransfer color-interpolation-filters="sRGB"><feFuncR type="linear" slope="0.6" /><feFuncG type="linear" slope="0.6" /><feFuncB type="linear" slope="0.6" /></feComponentTransfer></filter></svg>#filter');
  filter: brightness(0.6);
  -webkit-filter: brightness(0.6);
}

.hovereffect h2 {
  text-transform: uppercase;
  text-align: center;
  position: relative;
  font-size: 17px;
  background-color: transparent;
  color: #FFF;
  padding: 1em 0;
  opacity: 0;
  filter: alpha(opacity=0);
  -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
  transition: opacity 0.35s, transform 0.35s;
  -webkit-transform: translate3d(0,-100%,0);
  transform: translate3d(0,-100%,0);
}

.hovereffect a, .hovereffect p {
  color: #FFF;
  padding: 1em 0;
  opacity: 0;
  filter: alpha(opacity=0);
  -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
  transition: opacity 0.35s, transform 0.35s;
  -webkit-transform: translate3d(0,100%,0);
  transform: translate3d(0,100%,0);
}

.hovereffect:hover a, .hovereffect:hover p, .hovereffect:hover h2 {
  opacity: 1;
  filter: alpha(opacity=100);
  -webkit-transform: translate3d(0,0,0);
  transform: translate3d(0,0,0);
}




</style>

</head>

<!-- 	<div>
		<img src="../image/Chrysanthemum.jpg">
	</div> -->
<div class="container">
	
	<table class="table table-striped table-borderless table-hover">
		 <thead class="thead-dark">
		<tr>
			<th>
			<form class="form-inline">
				<div>
					<select class="form-control" data-style="btn-success" id="searchName" name="searchName">
						<option value="author">작성자</option>
						<option value="title">제목</option>
					</select>
					<input type="text"  class="form-control mr-sm-2" id="searchValue" name="searchValue" />
					<input type="button"  class="btn btn-outline-success my-2 my-sm-0" id="search" value="검색"/>
				</div>
				</form>
			</th>		
		</tr>
		</thead>
		<tr>
			<td>	
				<div class="container">
					<form class="form-inline">
					<input type="button" onclick="javascript:getList(1,'Christmas',null,null);" class="product_categoty" name="product_category" value ="Christmas"/>
					<input type="button" onclick="javascript:getList(1,'See the Future',null,null);" class="product_categoty" name="product_category" value ="See the Future"/>
					<input type="button" onclick="javascript:getList(1,'Our winter',null,null);" class="product_categoty" name="product_category" value ="Our winter"/>
					<input type="button" onclick="javascript:getList(1,'Beauty and Fashion',null,null);" class="product_categoty" name="product_category" value ="Beauty and Fashion"/>
					<input type="button" onclick="javascript:getList(1,'Sports',null,null);" class="product_categoty" name="product_category" value ="Sports"/>
					<input type="button" onclick="javascript:getList(1,'Celebrity',null,null);" class="product_categoty" name="product_category" value ="Celebrity"/>
					<input type="button" onclick="javascript:getList(1,null,null,null);" class="product_categoty" name="product_category" value ="전체보기"/>
					<select class="form-control" id="line" name="line" style="width:115px;">
					<option value='product_num' selected>최신순</option>
					<option value='product_readcount'>조회수순</option>
					<option value='product_likecount'>좋아요순</option>
					</select>
					</form>
				</div>
			</td>
		</tr>	
		<tr>
			<td>	
			<div id="imageList">
			</div>
			</td>
		</tr>	
		<tr>
			<td class="table-light">	
		
				<div  id="pagingWrap" class="pagingWrap">
			
				</div>
			
			</td>	
		</tr>
	</table>
	
	
	
	
</div>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var page = 1;
	var product_category = null;
	var searchName = null;
	var searchValue = null;
	
	getList(page, null, null, null, null);
	
	function getList(pageNum, productCategory, search_name, search_value, filter){
		$.ajax({
			type : "GET",
			url : "list.pd",
			data : {
				page : pageNum,
				product_category : productCategory,
				searchName : search_name,
				searchValue : search_value,
				filter : filter
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				console.log(data.pm);
				
				page = data.pm.cri.page;
				printList(data.list);
				printPage(data.pm, data.list, data.categoryCount, data.search, data.filter);
				product_category = productCategory;
				searchName = data.search.searchName;
				searchName = data.search.searchValue;
			}
		});
	}
	
	function printList(list){
		console.log(list);
		var html = "";
		html +="<div class='container'>";
		
		if(list.length == 0){
			html += "<p>등록된 사진이 없습니다.</p>";
		}else{
			$.each(list,function(){
				var filePath = "upload/"+this.product_origin_file;
				html +="<div class='hovereffect' width = '300px'>";
				html +="<img class='img-responsive ' src='"+filePath+"' width = '300px'/>";
				html +="<div class='overlay'>";
				html +="<h2>"+this.product_title+"</h2>";
				html +="<p><a href='photo_detail.ph?product_num="+this.product_num+"&member_num=${member.member_num}'><span class='iconify' data-icon='ion-heart' data-inline='false' style='width:50px; height:50px; opacity: 0.3; color: #ffffff;'></span></a></p>"
				html +="</div>"
				html +="</div>";
				/* <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
			    <div class='hovereffect'>
			        <img class="img-responsive" src="http://placehold.it/350x250" alt="">
			            <div class="overlay">
			                <h2>Effect 13</h2>
							<p>
								<a href="#">LINK HERE</a>
							</p>
			            </div>
			    </div>
			</div> */
			});
		}
		
		html +="</div>";
		
		$("#imageList").html(html);
	}
	
	function printPage(pm, list, categoryCount, search, filter){
		console.log(pm);
		console.log(list);
		console.log(categoryCount);
		console.log(search);
		console.log(search.searchName);
		console.log(filter);
		
		var html2 = "";
		/* if(filter == null || filter == "product_num"){
			html2 += "<select name='filter' onchange=''>"
			html2 += "<option value='product_num' selected>최신순</option>";
			html2 += "<option value='product_readcount'>조회수</option>";
			html2 += "<option value='product_likecount'>최신순</option>";	
		}else if(filter == "product_readcount"){
			html2 += "<option value='product_num'>최신순</option>";
			html2 += "<option value='product_readcount' selected>조회수</option>";
			html2 += "<option value='product_likecount'>최신순</option>";
		}else if(filter == "product_likecount"){
			html2 += "<option value='product_num'>최신순</option>";
			html2 += "<option value='product_readcount'>조회수</option>";
			html2 += "<option value='product_likecount' selected>최신순</option>";
		} */
		/* <select id="filter" name="filter">
		<option value="product_num">최신순</option>
		<option value="product_readcount">조회수</option>
		<option value="product_likecount">좋아요</option>
		</select> */
		
		var html ="";
		if(filter == null){
			if(search.searchName != "null" && search.searchValue != "null"){
				searchName = search.searchName;
				searchValue = search.searchValue;
				if(categoryCount == 1){
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null)'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\"))'>"
					html2 += "<option value='product_num' selected>최신순</option>";
					html2 += "<option value='product_readcount'>조회수</option>";
					html2 += "<option value='product_likecount'>좋아요순</option>";	
					html2 += "</select>" */
				}else{
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+$(filter).val+"\");'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null)'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",null);'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* html2 += "<select name='filter' onchange='javascript:getLit(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
					html2 += "<option value='product_num' selected>최신순</option>";
					html2 += "<option value='product_readcount'>조회순</option>";
					html2 += "<option value='product_likecount'>좋아요순</option>";	
					html2 += "</select>" */
				}
			}else{
				if(categoryCount == 1){
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,\""+(list[0].product_category)+"\",null,null,null);'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",\""+(list[0].product_category)+"\",null,null,null);'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",\""+(list[0].product_category)+"\",null,null,null)'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",\""+(list[0].product_category)+"\",null,null,null);'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",\""+(list[0].product_category)+"\",null,null,null);'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",null,null,\""+($(filter).val)+"\")'>"
					html2 += "<option value='product_num' selected>최신순</option>";
					html2 += "<option value='product_readcount'>조회수</option>";
					html2 += "<option value='product_likecount'>좋아요순</option>";	
					html2 += "</select>" */
				}else{
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,null,null,null,null);'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",null,null,null,null);'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",null,null,null,null)'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",null,null,null,null);'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",null,null,null,null);'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* html2 += "<select name='filter' onchange='javascript:getLit(1,null,null,null,\""+($(filter).val)+"\")'>"
					html2 += "<option value='product_num' selected>최신순</option>";
					html2 += "<option value='product_readcount'>조회수</option>";
					html2 += "<option value='product_likecount'>좋아요순</option>";	
					html2 += "</select>" */
				}
				
			}
		}else{
			if(search.searchName != "null" && search.searchValue != "null"){
				searchName = search.searchName;
				searchValue = search.searchValue;
				if(categoryCount == 1){
					product_category = list[0].product_category;
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\")'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* if(filter == "product_num"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num' selected>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else if(filter == "product_readcount"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount' selected>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else{
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount' selected>좋아요순</option>";	
						html2 += "</select>"
					} */
				}else{
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\")'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					 /* if(filter == "product_num"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num' selected>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else if(filter == "product_readcount"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount' selected>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else{
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,\""+(search.searchName)+"\",\""+(search.searchValue)+"\",\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount' selected>최신순</option>";	
						html2 += "</select>"
					} */
				}
			}else{
				if(categoryCount == 1){
					product_category = list[0].product_category;
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,\""+(list[0].product_category)+"\",null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",\""+(list[0].product_category)+"\",null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",\""+(list[0].product_category)+"\",null,null,\""+(filter)+"\")'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",\""+(list[0].product_category)+"\",null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",\""+(list[0].product_category)+"\",null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* if(filter == "product_num"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num' selected>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else if(filter == "product_readcount"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount' selected>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else{
						html2 += "<select name='filter' onchange='javascript:getLit(1,\""+(list[0].product_category)+"\",null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount' selected>좋아요순</option>";	
						html2 += "</select>"
					} */
				}else{
					if(pm.cri.page > 1){
						html += "<a href='javascript:getList(1,null,null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;처음&nbsp;&nbsp;&nbsp;</a>"
						if(pm.prev){
							html += "<a href='javascript:getList("+(pm.startPage-1)+",null,null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;이전&nbsp;&nbsp;&nbsp;</a>"
						}
					}
					for(var i = pm.startPage; i <= pm.endPage; i++){
						html +="<a href='javascript:getList("+i+",null,null,null,\""+(filter)+"\")'>&nbsp;&nbsp;&nbsp;"+i+"&nbsp;&nbsp;&nbsp;</a>";
					}
					
					if(pm.cri.page < pm.maxPage){
						if(pm.next){
							html += "<a href='javascript:getList("+(pm.endPage+1)+",null,null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;다음&nbsp;&nbsp;&nbsp;</a>";
						}
						html += "<a href='javascript:getList("+pm.maxPage+",null,null,null,\""+(filter)+"\");'>&nbsp;&nbsp;&nbsp;마지막&nbsp;&nbsp;&nbsp;</a>";
					}
					/* if(filter == "product_num"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num' selected>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else if(filter == "product_readcount"){
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount' selected>조회수</option>";
						html2 += "<option value='product_likecount'>좋아요순</option>";	
						html2 += "</select>"
					}else{
						html2 += "<select name='filter' onchange='javascript:getLit(1,null,null,null,\""+($(filter).val)+"\")'>"
						html2 += "<option value='product_num'>최신순</option>";
						html2 += "<option value='product_readcount'>조회수</option>";
						html2 += "<option value='product_likecount' selected>좋아요순</option>";	
						html2 += "</select>"
					} */
				}
			}
		}
		$("#filter").html(html2);
		$("#pagingWrap").html(html);
	}
	
	$("#search").click(function(){
		getList(1,null,$("#searchName").val(),$("#searchValue").val(),null);
	})
	
	/* var product_category = null;
	var searchName = null;
	var searchValue = null; */
	
	$("#line").on("change", function(){
		if(product_category == null){
			if(searchName == null || searchValue == null){
				getList(1,null,null,null,$(line).val());
			}else{
				getList(1,null,searchName,searchValue,$(line).val());
			}
		}else{
			if(searchName == null || searchValue == null){
				getList(1,product_category,null,null,$(line).val());
			}else{
				getList(1,product_category,searchName,searchValue,$(line).val());
			}	
		}
	})
	
</script>
<jsp:include page="../common/footer.jsp"/>
