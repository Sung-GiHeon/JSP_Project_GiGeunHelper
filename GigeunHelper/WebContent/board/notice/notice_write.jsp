<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 공지사항 작성 -->
<jsp:include page="../../common/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="static/js/service/HuskyEZCreator.js"></script>
<section>
	<form id="frm" action="noticeWrite.do" method="post">
		<input type="hidden" name="notice_author" value="${member.member_name}"/>
		<table class="list">
			<tr>
				<th colspan=2>공지사항 작성</th>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${member.member_name}</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
					<select name="notice_category">
						<option value="공지" selected>공지</option>
						<option value="알림">알림</option>
						<option value="이벤트">이벤트</option>
						<option value="당첨">당첨</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="notice_title" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td style="width:710px;">
					<textarea name="notice_content" id="se" cols="50" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="saveBtn" value="작성완료" />
					 | 
				    <input type="reset" value="초기화"/>
				</td>
			</tr>
		</table>
	</form>
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



