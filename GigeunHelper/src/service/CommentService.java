package service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {

	// 댓글 작성
	boolean insertComment(HttpServletRequest request);
	
	// 댓글 리스트 / 페이징 정보
	HashMap<String,Object> getCommentList(HttpServletRequest request);
	
	//댓글 삭제
	boolean deleteComment(HttpServletRequest request);
	
}
