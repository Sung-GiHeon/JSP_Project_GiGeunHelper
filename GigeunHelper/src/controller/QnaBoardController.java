package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import service.NoticeService;
import service.NoticeServiceImpl;
import service.QNABoardService;
import service.QNABoardServiceImpl;
import vo.BoardVO;
import vo.CommentVO;

/**
 * @date 2019-12-02 추가
 * @apiNote 질문과 답변 게시판 추가
 */
@WebServlet("*.bo")
public class QnaBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	 
	MemberService ms = new MemberServiceImpl();
	
	QNABoardService service = new QNABoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		MemberServiceImpl.loginCheck(request);
		
		
		
		
		
		String cmd 
		= request.getRequestURI().substring(
				request.getContextPath().length()+1);
		System.out.println("요청 URI : " + cmd);
		
		
		String nextPage = null;
		String suc = "/board/qna/qna_success.jsp";
		String fail = "/board/qna/qna_fail.jsp";
		
		if(cmd.equals("boardList.bo")) {
			//ArrayList<BoardVO> list = service.getBoardList();
			ArrayList<BoardVO> list = service.getBoardList(request);
			request.setAttribute("boardList", list);
			nextPage ="/board/qna/qna_list.jsp";
		}
		
		if(cmd.equals("boardWrite.bo")) {
			nextPage ="/board/qna/qna_write.jsp";
		}
		
		if(cmd.equals("boardWriteSubmit.bo")) {
			System.out.println("게시물을 등록 요청");
			service.boardWrite(request);
			response.sendRedirect("boardList.bo");
		}
	
		if(cmd.equals("getBoardVOByUpdate.bo")) {
		System.out.println("공지사항 수정 화면 요청");
		if(!ms.checkAdmin(request, response))return;
			service.getBoardVOByUpdate(request);
		nextPage = "/board/qna/qna_update.jsp";
		}
	
		if(cmd.equals("BoardUpdate.bo")) {
			System.out.println("게시물 수정 요청");
			if(!ms.checkAdmin(request, response))return;
			boolean isUpdate = service.BoardUpdate(request);
			System.out.println(isUpdate);
			nextPage = (isUpdate) ? suc : fail;
		}

		if(cmd.equals("boardDetail.bo")) {
			service.updateReadCount(request);
			response.sendRedirect("boardRead.bo?qna_num="+request.getParameter("qna_num"));
		}
		
		if(cmd.equals("boardRead.bo")){
			BoardVO board = service.getBoardVO(request);
			request.setAttribute("boardVO", board);
			
			ArrayList<CommentVO> commentList = service.getCommentList(request);
			request.setAttribute("commentList", commentList);
			
			nextPage ="/board/qna/qna_detail.jsp";
		}
		
		if(cmd.equals("boardReplyForm.bo")) {
			BoardVO board = service.getBoardVO(request);
			request.setAttribute("boardVO", board);
			nextPage ="/board/qna/qna_reply.jsp";
		}
		
		if(cmd.equals("boardReplySubmit.bo")) {
			service.boardReplySubmit(request);
			response.sendRedirect("boardList.bo");
		}
		
		if(cmd.equals("file_down.bo")) {
			System.out.println("파일 다운로드 요청");
			service.fileDown(request, response);
		}
		
		if(cmd.equals("boardDelete.bo")) {
			System.out.println("게시물 삭제 요청");
			if(!ms.checkAdmin(request, response))return;
			nextPage = service.boardDelete(request) ? suc : fail;
			
		}
		
		/*
		 * 20191206 comment 추가
		 */
		if(cmd.equals("commentWrite.bo")) {
			System.out.println("댓글 작성 요청");
			service.insertComment(request, response);
		}
		
		if(cmd.equals("commentDelete.bo")) {
			System.out.println("댓글 삭제 요청");
			service.deleteComment(request, response);
		}
		
		
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
