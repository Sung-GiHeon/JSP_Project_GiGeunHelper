<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<jsp:include page="../common/header.jsp"></jsp:include>
<input type="hidden" id="member_id" value="${member.member_id}">
<input type="hidden" id="product_num" value="${product.product_num}">
<input type="hidden" id="member_num" value="${member.member_num}">
<input type="hidden" id="product_credit"
	value="${product.product_credit}">

<div class='bigPictureWrapper '>
	<div style="position: absolute;">
		<div style="position: relative;" class='bigPicture'></div>

	</div>
	<div id="block" style="position: absolute;">
		<div style="position: relative;" class='blockPicture'></div>
	</div>
</div>
<div class="container">
	<div class="text-center">
		<div class="card" >
			<c:choose>
				<c:when test="${empty sessionScope.member}">

					<img src="upload/${product.product_origin_file}"
						style="cursor: pointer" id="view2">

				</c:when>
				<c:otherwise>
					<img src="upload/${product.product_origin_file}"
						style="cursor: pointer" id="view">
				</c:otherwise>
			</c:choose>
			<img src="upload/${product.product_origin_file}"
				style="cursor: pointer" id="unView" hidden="">
			<div class="card-body">
				<p class="card-text">
				<table class="table table-borderless">
				  <thead class="thead">
			<tr>
				<th><div>���� Ÿ��Ʋ&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${product.product_title}</div></th>
				<th style="width:50px;"></th>
				<th><div>���� �۰�&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${photoMember.member_name}</div></th>
				<th style="width:50px;"></th>
				<th><div id="like">���ƿ�&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${product.product_likecount}</div></th>
			</tr>
			</thead>
				</table>
			</div>
			<ul class="list-group list-group-flush" >
				<c:choose>
					<c:when test="${!empty sessionScope.member}">
						 <div class="card-header">ī�װ�</div>
						<li class="list-group-item" >${product.product_category}</li>
						 <div class="card-header">������</div>
						<li class="list-group-item">${product.product_size}</li>
						<div class="card-header">Ŭ����</div>
						<li class="list-group-item">${product.product_credit}</li>
						<li class="list-group-item">
						<button type="button" id="buyBtn" class="btn btn-link btn-lg active" style="color:#ffe83b;"><i class="material-icons">credit_card</i></button>
						<button type="button" id="unBuyBtn" class="btn btn-link btn-lg active" hidden="" style="color:#ffe83b;"><i class="material-icons">credit_card</i></button>
						<button type="button" id="cartBtn" class="btn btn-link btn-lg active" style="color:#77f065;"><i class="material-icons">shopping_cart</i></button>
						<button type="button" id="likeBtn" class="btn btn-link btn-lg active" style=" size:30px;color:#FFB2D9;"><i class="material-icons">favorite_border</i></button>
						<button type="button"  hidden="" id="unlikeBtn" class="btn btn-link btn-lg active" style="color:#FFB2D9;"><i class="material-icons">favorite</i></button>
						</li>
					</c:when>
					<c:otherwise>
						<h1>�α��� ���ּ���!!</h1>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
<br />
<br />
<h1>${photoMember.member_name}���Ǵٸ��������Դϴ�.</h1>
<br />
<br />
<c:forEach var="i" items="${imageList}">
	<c:if test="${i.product_num != product.product_num}">
		<a
			href="photo_detail.ph?product_num=${i.product_num}&member_num=${member.member_num}"><img
			src="upload/${i.product_origin_file}" width="200" height="200"></a>
	</c:if>
</c:forEach>
<!-- ����¡ ó�� -->
<br />
<input type="button" value="��κ���" id="imageView">
<c:if test="${pageMaker.startPage != pageMaker.maxPage}">
	
</c:if>

<script>

//�̹��� ����Ʈ ��ư
$("#imageView").click(function(){
	location.href="photo_getProductImage.ph?product_num=${product.product_num}&member_num=${member.member_num}";
});

//���� �Ұ� ��ư
 $("#unBuyBtn").click(function(){
	 alert('�̹� �����Ͻ� ��ǰ�Դϴ�.')
 })
 
 //īƮ �߰��ϱ�
$("#cartBtn").click(function(){
	var product_num =$("#product_num").val();
	var member_num =$("#member_num").val();
	$.ajax({
		type: "POST",
		url:"photo_cart.ph",
		data:{
			product_num:product_num,
			member_num:member_num
		},
		success:function(result){
			console.log(result);
			if(result.isSuccess == "true"){
				alert('��ٱ��Ͽ� �߰��ϼ̽��ϴ�.')
			}else{
				alert('�̹� ��ϵ� �����Դϴ�.')
			}
		},
		error:function(request,status,error){
			console.log("code : "+ request.status);
		} 
	}); 
});
	//���ƿ� ��ưü����
	if("${likeCheck}" == "true"){
		console.log("like,likeCheck : true");
		$("#likeBtn").attr('hidden','');
		$("#unlikeBtn").removeAttr("hidden");	
	}else{
		console.log("unlike,likeCheck : false");
		$("#unlikeBtn").attr('hidden','');
		$("#likeBtn").removeAttr("hidden");	
	}
	//�Ⱦ�� ��ư
	$("#unlikeBtn").click(function(){
		var input_product_num =$("#product_num").val();
		var member_num =$("#member_num").val();
		$.ajax({
			type: "POST",
			url:"photo_unlike.ph",
			data:{
				product_num:input_product_num,
				member_num:member_num
			},
			// success: �ڵ����� call back�Լ��� ȣ���Ѵٴ� �ǹ�
			success:function(result){
				console.log(result);
				$("#like").text('���ƿ�:'+result.product_num);
				$("#unlikeBtn").attr('hidden','');
				$("#likeBtn").removeAttr("hidden");	
				//$("#result table").append("<tr><td>"+result.name+"</td><td>"+result.age+"</td></tr>");
			},
			error:function(request,status,error){
				console.log("code : "+ request.status);
			} 
		});
	});
	
	//����ũ ��ư
	$("#likeBtn").click(function(){
		var input_product_num =$("#product_num").val();
		var member_num =$("#member_num").val();
			$.ajax({
				type: "POST",
				url:"photo_like.ph",
				data:{
					product_num:input_product_num,
					member_num:member_num
				},
				// success: �ڵ����� call back�Լ��� ȣ���Ѵٴ� �ǹ�
				success:function(result){
					console.log(result);
					$("#like").text('���ƿ�:'+result.product_num);
					$("#likeBtn").attr('hidden','');
					$("#unlikeBtn").removeAttr("hidden");	
					//location.href="photo_like_tb.ph?product_num="+input_product_num+"&member_num="+member_num
					//$("#result table").append("<tr><td>"+result.name+"</td><td>"+result.age+"</td></tr>");
				},
				error:function(request,status,error){
					console.log("code : "+ request.status);
				} 
		});
	});
	
	
	
	/* $(document).ready(function (e){
		
		$(document).on("click",$("#view"),function(){
			var path = $(this).attr('src')
			showImage(path);
		}); */
		
		//end click event
		
		//showImage path == hello.jpg
		
		//�̹��� ����
		if(${buyProduct}){
			$("#view").attr('hidden','');	
			$("#unView").removeAttr("hidden");		
		}else{
			$("#unView").attr('hidden','');	
			$("#view").removeAttr("hidden");	
		} 
		
		/* �̹��� ���� ��� */
		$("#view").click(function(){
			var path = $(this).attr('src')
			showImage(path);
		});
		
		$("#unView").click(function(){
			var path = $(this).attr('src')
			hiddenImage(path);
		});
		
		$("#view2").click(function(){
			var path = $(this).attr('src')
			showImage(path);
		});
		function hiddenImage(fileCallPath){
		    $(".bigPictureWrapper").css("display","flex").show();
		    /* $(".bigPictureWrapper").css("display","flex").s */
		    $(".blockPicture")
		    .html("<img src=''>");
		    $(".bigPicture")
		    .html("<img src='"+fileCallPath+"'>");
		}	
		
		function showImage(fileCallPath){
		    $(".bigPictureWrapper").css("display","flex").show();
		    /* $(".bigPictureWrapper").css("display","flex").s */
		    $(".blockPicture")
		    .html("<img src='image/test2.png'>");
		    
		    $(".bigPicture")
		    .html("<img src='"+fileCallPath+"'>");
		}
		//end fileCallPath
		$(".bigPictureWrapper").on("click", function(e){
		    setTimeout(function(){
		      $('.bigPictureWrapper').hide();
		    },0);
		  });
		//end bigWrapperClick event
		 $("#buyBtn").click(function(){
		var product_credit=$("#product_credit").val();
		var member_id=$("#member_id").val();
		var product_num=$("#product_num").val();
		var member_num =$("#member_num").val();
	
		
		
		if(${buyProduct} == false){
				if(${product.product_credit}>${member.member_credit}+0){
					alert('�����ϰ� �ִ� Ŭ������ �����մϴ�.');
				}else{
					if(confirm("�ش� ũ������ŭ ���� �˴ϴ�.������ �����Ͻðڽ��ϱ�?")){
						$.ajax({
							type: "POST",
							url:"photo_minus_credit.ph",
							data:{
								product_credit:product_credit,
								member_id:member_id,
								product_num:product_num,
								member_num:member_num
							},
							success:function(result){
								console.log(result);
								console.log(result.isSuccess);
								$("#profile").html("${member.member_name}�� �ݰ����ϴ�.&nbsp;&nbsp;&nbsp; ���� ũ���� : "+result.credit)
									if(result.isSuccess == "true"){
										$("#buyBtn").attr('hidden','');	
										$("#unBuyBtn").removeAttr("hidden");
										
										$("#view").attr('hidden','');	
										$("#unView").removeAttr("hidden");
									}else{
										$("#unBuyBtn").attr('hidden','');	
										$("#buyBtn").removeAttr("hidden");
									}
								location.href="photo_down.ph?file_name=${product.product_origin_file}"
							},
							error:function(request,status,error){
								console.log("code : "+ request.status);
							} 
						});
					}
				}
			}else{
				alert('�̹� �����Ͻ� ��ǰ�Դϴ�.');
			}
}); 
		
</script>
<style>
.blockPicture {
	mix-blend-mode: multiply;
	
}


.bigPictureWrapper {
	
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 100;
	background: rgba(0, 0, 0, 1);

}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
}


</style>