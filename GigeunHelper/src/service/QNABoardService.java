package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BoardVO;
import vo.CommentVO;

public interface QNABoardService {
	
	// 전체 게시물 목록 요청
	ArrayList<BoardVO> getBoardList();
	
	// 페이징 처리된 전체 목록 정보 요청
	ArrayList<BoardVO> getBoardList(HttpServletRequest request);
	
	// 게시물 등록 요청, 파일 업로드
	void boardWrite(HttpServletRequest request);
	
	// 게시물 상세 보기
	BoardVO getBoardVO(HttpServletRequest request);
	
	// 게시물 조회수 증가
	void updateReadCount(HttpServletRequest request);
	
	// 파일 다운로드 처리
	void fileDown(HttpServletRequest request, HttpServletResponse response);
	
	// 답변글 작성창 요청
	BoardVO boardReply(HttpServletRequest request);
	
	// 답변글 작성 요청
	void boardReplySubmit(HttpServletRequest request);
	
	// 수정 게시물 정보 요청
	void getBoardVOByUpdate(HttpServletRequest request);
	
	// 수정 게시물 등록 요청

	boolean BoardUpdate(HttpServletRequest request);
	
	// 게시물 삭제 요청
	
	
	/**
	 * 20191206 comment 추가
	 */
	
	// 댓글 작성
	void insertComment(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException;
	
	// 댓글 목록 요청
	ArrayList<CommentVO> getCommentList(HttpServletRequest request);
	
	// 댓글 삭제 요청
	void deleteComment(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException;

	
	boolean boardDelete(HttpServletRequest request);

	
	


	

	

	

	
	
}












