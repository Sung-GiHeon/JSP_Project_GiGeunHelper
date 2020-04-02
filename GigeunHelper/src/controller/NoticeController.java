package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import service.NoticeService;
import service.NoticeServiceImpl;
/**
 * @author admin
 * @date 2019-11-28
 * @apiNote 공지사항 기능 추가
 */
@WebServlet("*.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeService ns = new NoticeServiceImpl(); 
	MemberService ms = new MemberServiceImpl();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("NoticeController 요청 받음");
		request.setCharacterEncoding("UTF-8");		
		
		MemberServiceImpl.loginCheck(request);
		
		String command 
		= request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println("command : " + command);
		
		
		String nextPage = null;
		String suc = "/board/notice/notice_success.jsp";
		String fail = "/board/notice/notice_fail.jsp";
		
		if(command.equals("notice.do")) {
			System.out.println("공지사항 목록 화면 요청");
			ns.noticeList(request);
			nextPage = "/board/notice/notice_list.jsp";
		}
		
		if(command.equals("noticeWriteForm.do")) {
			System.out.println("공지사항 작성 화면 요청");
			if(!ms.checkAdmin(request, response))return;
			nextPage = "/board/notice/notice_write.jsp";
		}
		
		if(command.equals("noticeWrite.do")) {
			System.out.println("공지글 작성 요청");
			if(!ms.checkAdmin(request, response))return;
			boolean isWrite = ns.noticeWrite(request);
			if(isWrite) {
				nextPage = suc;
			}else {
				nextPage = fail;
			}
		}
		
		if(command.equals("noticeDetail.do")) {
			System.out.println("공지사항 상세 화면 요청");
			ns.noticeDetail(request);
			nextPage = "/board/notice/notice_detail.jsp";
		}
		
		if(command.equals("noticeUpdateForm.do")) {
			System.out.println("공지사항 수정 화면 요청");
			if(!ms.checkAdmin(request, response))return;
			ns.noticeDetail(request);
			nextPage = "/board/notice/notice_update.jsp";
		}
		
		if(command.equals("noticeUpdate.do")) {
			System.out.println("게시물 수정 요청");
			if(!ms.checkAdmin(request, response))return;
			boolean isUpdate = ns.noticeUpdate(request);
			nextPage = (isUpdate) ? suc : fail;
		}
		
		if(command.equals("noticeDelete.do")) {
			System.out.println("게시글 삭제요청");
			if(!ms.checkAdmin(request, response))return;
			nextPage = ns.noticeDelete(request) ? suc : fail; 
		}
		
		if(command.equals("noticeSearch.do")) {
			System.out.println("검색 요청");
			ns.search(request);
			nextPage = "/board/notice/notice_list.jsp";
		}
		
		
		if(nextPage != null ){
			request.getRequestDispatcher(nextPage).forward(request, response);
		}else {
			request.getRequestDispatcher(fail).forward(request, response);
		}
		
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
