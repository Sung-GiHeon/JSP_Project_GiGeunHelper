<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 수정 -->
<jsp:include page="../../common/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="static/js/service/HuskyEZCreator.js"></script>
<section>
<div class="container">
	<form id="frm" action="noticeUpdate.do" method="post">
		<input type="hidden" name="notice_num" value="${notice.notice_num}"/>
		<input type="hidden" name="notice_author" value="${member.member_name}"/>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<th colspan=2>공지사항 수정</th>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${member.member_name}</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
					<select name="notice_category">
						<option value="${notice.notice_category}" selected>${notice.notice_category}</option>
						<option value="공지">공지</option>
						<option value="알림">알림</option>
						<option value="이벤트">이벤트</option>
						<option value="당첨">당첨</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="notice_title" value="${notice.notice_title}"/></td>
			</tr>
			<tr style="background-color:white;">
				<td>내용</td>
				<td style="width:710px;">
					<textarea name="notice_content" id="se">${notice.notice_content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
					<div class="btn-group mr-2" role="group" aria-label="First group">
					<button type="button" class="btn btn-dark" id="saveBtn">
						작성완료
					 </button>
					 <button type="reset" class="btn btn-dark">
				    	초기화
				    </button>
				    </div>
				 </div>
				</td>
			</tr>
		</table>
	</form>
</div>
</section>
<script>
	$(function(){
		var oEditors =[];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "se",
			sSkinURI : "static/SmartEditor2Skin.html",
			fCreator : "createSEditor2",
			// SE2BasicCreator.js method 명
			htParams : {
				bUseToolbar : true,
				// 창 크기 조정 바 사용여부
				bUseVerticalResizer : true,
				// html / text / Editro 사용여부
				bUseModeChanger : true				
			}
		});
		
		$("#saveBtn").click(function(){
			if(confirm("저장하시겠습니까?")){
				oEditors.getById["se"].exec("UPDATE_CONTENTS_FIELD",[]);
				$("#frm").submit();
			}
		});
	});
</script>
<jsp:include page="../../common/footer.jsp"/>