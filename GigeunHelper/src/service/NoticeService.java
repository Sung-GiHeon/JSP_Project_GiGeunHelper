package service;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {
	
	// 게시물 작성 - request -> NoticeVO
	boolean noticeWrite(HttpServletRequest request);
	
	// 게시물 목록 -> page
	void noticeList(HttpServletRequest request);
	
	// 게시물 상세보기 - request -> 게시글 번호 notice_num
	void noticeDetail(HttpServletRequest request);
	
	// 게시물 정보 수정 -> request -> NoticeVO
	// return 성공 여부
	boolean noticeUpdate(HttpServletRequest request);
	
	// 게시물 정보 삭제 request - notice_num
	boolean noticeDelete(HttpServletRequest request);
	
	// 검색 게시물 리스트
	public void search(HttpServletRequest request);
	
	
	
	

}
