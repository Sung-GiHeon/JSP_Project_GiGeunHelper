package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import DAO.NoticeDAO;
import DAO.NoticeDAOImpl;
import vo.Criteria;
import vo.NoticeVO;
import vo.PageMaker;
import vo.SearchCriteria;

public class NoticeServiceImpl implements NoticeService{
	
	NoticeDAO dao = new NoticeDAOImpl();

	@Override
	public boolean noticeWrite(HttpServletRequest request) {
		System.out.println("공지사항 작성 요청");
		String notice_author = request.getParameter("notice_author");
		String notice_category = request.getParameter("notice_category");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		NoticeVO notice = new NoticeVO();
		notice.setNotice_author(notice_author);
		notice.setNotice_category(notice_category);
		notice.setNotice_title(notice_title);
		notice.setNotice_content(notice_content);
		System.out.println(notice);
		return dao.noticeWrite(notice);
	}

	@Override
	public void noticeList(HttpServletRequest request) {
		int page = 1;
		String paramPage = request.getParameter("page");
		if(paramPage != null)page = Integer.parseInt(paramPage);
		
		int listCount = dao.getListCount();
		
		Criteria cri = new Criteria(page,10);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		System.out.println(pageMaker);
		
		ArrayList<NoticeVO> noticeList 
			= dao.getNoticeList(cri.getPageStart(), cri.getPerPageNum());
		System.out.println(noticeList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageMaker", pageMaker);
		
	}

	@Override
	public void noticeDetail(HttpServletRequest request) {
		String num = request.getParameter("notice_num");
		int notice_num = Integer.parseInt(num);
		request.setAttribute("notice", dao.getNoticeVO(notice_num));
		
	}

	@Override
	public boolean noticeUpdate(HttpServletRequest request) {
		System.out.println("공지사항 수정 요청");
		String num = request.getParameter("notice_num");
		int notice_num = Integer.parseInt(num);
		
		String notice_author = request.getParameter("notice_author");
		String notice_category = request.getParameter("notice_category");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		NoticeVO notice = new NoticeVO();
		
		notice.setNotice_num(notice_num);
		
		notice.setNotice_author(notice_author);
		notice.setNotice_category(notice_category);
		notice.setNotice_title(notice_title);
		notice.setNotice_content(notice_content);
		System.out.println(notice);
		return dao.noticeUpdate(notice);
	}

	@Override
	public boolean noticeDelete(HttpServletRequest request) {
		String num = request.getParameter("notice_num");
		int notice_num = Integer.parseInt(num);
		return dao.noticeDelete(notice_num);
	}

	@Override
	public void search(HttpServletRequest request) {
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		SearchCriteria cri = new SearchCriteria(page,10,searchName,searchValue);
		System.out.println(cri);
		int listCount = dao.getSearchListCount(cri);
		System.out.println("listCount "+listCount);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		ArrayList<NoticeVO> noticeList = dao.getSearchList(pageMaker);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageMaker", pageMaker);
	}
	
	

}
