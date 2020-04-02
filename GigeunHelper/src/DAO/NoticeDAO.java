package DAO;

import java.util.ArrayList;

import vo.NoticeVO;
import vo.PageMaker;
import vo.SearchCriteria;

public interface NoticeDAO {
	
	// 게시물 작성
	boolean noticeWrite(NoticeVO vo);
	
	// 게시물 상세보기
	NoticeVO getNoticeVO(int notice_num);
	
	// 게시물 수정
	boolean noticeUpdate(NoticeVO vo);
	
	// 게시물 삭제
	boolean noticeDelete(int notice_num);
	
	//게시물 리스트
	ArrayList<NoticeVO> getNoticeList(int pageStart, int perPageNum);
	
	// 전체 게시물의 개수
	int getListCount();

	// 검색 결과에 포함된 게시물 전체개수
	int getSearchListCount(SearchCriteria cri);

	// 검색 결과에 포함된 게시물 리스트 
	ArrayList<NoticeVO> getSearchList(PageMaker pageMaker);
	

}
