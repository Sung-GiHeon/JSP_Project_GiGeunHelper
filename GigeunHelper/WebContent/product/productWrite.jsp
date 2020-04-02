<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
	<form action="productWrite.pd" enctype="multipart/form-data"
		method="post">
		<input type="hidden" name="product_member_num" value="1" />
		<div style='width: 1000px;'>
			<img id="blah" src="#" alt='미리보기 이미지' width='500px'> 
			<div style='float: right;'>
				<table>
					<tr>
						<td>작성자</td>
						<td>관리자</td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="product_title" required /></td>
					</tr>
					<tr>
						<td>카테고리</td>
						<td>
							<select name="product_category">
								<option value="Christmas" selected>Christmas</option>
								<option value="See the Future">See the Futhure</option>
								<option value="Our winter">Our winter</option>
								<option value="Beauty and Fashion">Beauty and Fashion</option>
								<option value="Sports">Sports</option>
								<option value="Celebrity">Celebrity</option>	
							</select>
						</td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" accept="image/*" name="product_file"	id="imgInp" required /></td>
					</tr>
					<tr>
						<td>이미지 사이즈</td>
						<td><input type ="text" id = "product_size" name = "product_size" readonly/></td>
					</tr>
					<tr>
						<td>가격</td>
						<td><input type="number" name="product_credit" required />credit</td>
					</tr>
					<tr>
						<td colspan=2><input type="submit" value="등록" /> | <input type="button" onclick="" value="취소" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#imgInp").on('change', function() {
			/* fnImg_Check(this.value); */
			
			var src = getFileExtension(this.value);
			if (!((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg") || (src.toLowerCase() == "png"))) {
				alert('이미지 파일만 지원합니다.');
				$("#imgInp").val("");
				return;
			};
			
			var file = this.files[0];
			var _URL = window.URL || window.webkitURL;
			var img = new Image();
			
			img.src = _URL.createObjectURL(file);
			
			img.onload = function(){
				$("#product_size").val(img.width+"X"+img.height);
/* 				document.getElementById("IMG_SIZE_W").innerText = img.width;
				document.getElementById("IMG_SIZE_H").innerText = img.height;	
 */			}
			readURL(this);
		});
	});

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	function getFileExtension(filePath) { // 파일의 확장자를 가져옮
		var lastIndex = -1;
		lastIndex = filePath.lastIndexOf('.');
		var extension = "";
		if (lastIndex != -1) {
			extension = filePath.substring(lastIndex + 1, filePath.len);
		} else {
			extension = "";
		}
		return extension;
	}
</script>
<jsp:include page="../common/footer.jsp"/>