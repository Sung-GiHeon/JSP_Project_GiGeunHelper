package DAO;

import java.util.ArrayList;

import vo.BoardVO;
import vo.CommentVO;
import vo.Criteria;
import vo.PageMaker;

public interface QNABoardDAO {
	
	// 전체 게시물 개수
	int getListCount();
	
	// 전체 게시물 목록
	public ArrayList<BoardVO> getBoardList();
	
	// 페이징 처리된 게시물 목록
	public ArrayList<BoardVO> getBoardList(PageMaker pageMaker);
	
	// 게시물 등록 요청
	void boardWrite(BoardVO board);
	
	// 게시물 한개의 정보 요청
	BoardVO getBoardVO(int qna_num);
	
	// 조회수 증가
	void updateReadCount(int qna_num);
	
	// 답변글 작성
	void boardReplySubmit(BoardVO board);
	
	// 게시물 수정 처리
	boolean BoardUpdate(BoardVO board);
	

	
	/**
	 * 20191206 comment 추가
	 */
	// 댓글 삽입
	boolean insertComment(CommentVO vo);
	
	// 해당 게시물의 페이징 처리된 댓글 리스트 정보
	ArrayList<CommentVO> getCommentList(int comment_board_num, Criteria cri);
	
	// 해당 게시물의 댓글 총 개수
	int getCommentListCount(int comment_board_num);

	//댓글 삭제처리
	boolean deleteCommit(int comment_num, int comment_writer_num);

	
	boolean baordDelete(int qna_num);



}




















