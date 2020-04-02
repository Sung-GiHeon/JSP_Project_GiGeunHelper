package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.QNABoardDAO;
import DAO.QNABoardDAOImpl;
import vo.BoardVO;
import vo.CommentVO;
import vo.Criteria;
import vo.PageMaker;

public class QNABoardServiceImpl implements QNABoardService{
	
	QNABoardDAO dao = new QNABoardDAOImpl();
	

	@Override
	public ArrayList<BoardVO> getBoardList() {
		return dao.getBoardList();
	}
	

	@Override
	public ArrayList<BoardVO> getBoardList(HttpServletRequest request) {
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = dao.getListCount();
		
		Criteria cri = new Criteria(page,15);
		PageMaker pageMaker  = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		request.setAttribute("pageMaker", pageMaker);
		
		return dao.getBoardList(pageMaker);
	}

	@Override
	public void boardWrite(HttpServletRequest request) {
		
		String saveDir ="/upload";
		
		try {
			
			String realPath 
			= request.getServletContext().getRealPath(saveDir);
			System.out.println("realPath : "+realPath);
			
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath,
					1024*1024*30,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			BoardVO board = new BoardVO();
			int qna_writer_num 
			= Integer.parseInt(multi.getParameter("qna_writer_num"));
			String qna_name = multi.getParameter("qna_name");
			String qna_title = multi.getParameter("qna_title");
			String qna_content = multi.getParameter("qna_content");
			String file = (String)multi.getFileNames().nextElement();
			String qna_file = multi.getFilesystemName(file);
			String qna_file_origin = multi.getOriginalFileName(file);
			
			board.setQna_name(qna_name);
			board.setQna_title(qna_title);
			board.setQna_content(qna_content);
			board.setQna_file(qna_file);
			board.setQna_file_origin(qna_file_origin);
			board.setQna_writer_num(qna_writer_num);
			
			dao.boardWrite(board);		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoardVO getBoardVO(HttpServletRequest request) {
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		return dao.getBoardVO(qna_num);
	}

	@Override
	public void updateReadCount(HttpServletRequest request) {
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		dao.updateReadCount(qna_num);
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("File Down 요청");
		
		String realPath = request.getServletContext().getRealPath("/upload");
		String file_name = request.getParameter("file_name");
		System.out.println(file_name);
		String filePath = realPath+"/"+file_name;
		System.out.println(filePath);
		String mimeType = request.getServletContext().getMimeType(filePath);
		System.out.println(mimeType);
		
		if(mimeType ==null) mimeType="application/octet-stream";
		response.setContentType(mimeType);
		
		try {
			
			String agent = request.getHeader("User-Agent");
			if(agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1) {
				file_name = URLEncoder.encode(file_name,"utf-8").replaceAll("\\+", "%20");
			}else {
				file_name = new String(file_name.getBytes("utf-8"),"iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		response.setHeader(
				"Content-Disposition", 
				"attachment;fileName="+file_name
		);
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			OutputStream os = response.getOutputStream();
			
			byte[] bytes = new byte[4096];
			int numRead = 0;
			while((numRead = fis.read(bytes,0, bytes.length)) != -1) {
				os.write(bytes,0,numRead);
			}
			os.flush();
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public BoardVO boardReply(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardReplySubmit(HttpServletRequest request) {
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		int qna_writer_num = Integer.parseInt(request.getParameter("qna_writer_num"));
		int qna_re_ref = Integer.parseInt(request.getParameter("qna_re_ref"));
		int qna_re_lev = Integer.parseInt(request.getParameter("qna_re_lev"));
		int qna_re_seq = Integer.parseInt(request.getParameter("qna_re_seq"));
		String qna_name = request.getParameter("qna_name");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		
		BoardVO board =new BoardVO();
		board.setQna_num(qna_num);
		board.setQna_name(qna_name);
		board.setQna_title(qna_title);
		board.setQna_content(qna_content);
		board.setQna_writer_num(qna_writer_num);
		board.setQna_re_ref(qna_re_ref);
		board.setQna_re_lev(qna_re_lev);
		board.setQna_re_seq(qna_re_seq);
		System.out.println(board);
		dao.boardReplySubmit(board);
		
		
	}

	@Override
	public void getBoardVOByUpdate(HttpServletRequest request) {
		String num = request.getParameter("qna_num");
		int qna_num = Integer.parseInt(num);
		request.setAttribute("boardVO", dao.getBoardVO(qna_num));
	}





	@Override
	public void insertComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int comment_writer_num 
		= Integer.parseInt(request.getParameter("comment_writer_num"));
		int comment_board_num 
		= Integer.parseInt(request.getParameter("comment_board_num"));
		String comment_name = request.getParameter("comment_name");
		String comment_content = request.getParameter("comment_content");
		
		CommentVO vo = new CommentVO(
				comment_writer_num,
				comment_name,
				comment_content,
				comment_board_num);
				
		if(dao.insertComment(vo)) {
			System.out.println("댓글작성 성공");
			response.sendRedirect("boardDetail.bo?qna_num="+comment_board_num);
		}else {
			System.out.println("댓글작성 실패");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(
					"<script> "
					+ " alert('댓글 등록 실패!'); "
					+ " history.go(-1);"
					+ "</script>"
			);
		}
	}

	@Override
	public ArrayList<CommentVO> getCommentList(HttpServletRequest request) {
		ArrayList<CommentVO> list = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		int page = 1;
		String pageNum = request.getParameter("page");
		if(pageNum != null) {
			page = Integer.parseInt(pageNum);
		}
		int commentListCount = dao.getCommentListCount(qna_num);
		Criteria cri = new Criteria(page,5);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentListCount);
		list = dao.getCommentList(qna_num, cri);
		request.setAttribute("pm", pageMaker);
		System.out.println(list);
		System.out.println(pageMaker);
		return list;
	}

	@Override
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int comment_writer_num = Integer.parseInt(request.getParameter("comment_writer_num"));
		int comment_board_num = Integer.parseInt(request.getParameter("comment_board_num"));
		
		dao.deleteCommit(comment_num, comment_writer_num);
		
		response.sendRedirect("boardRead.bo?qna_num="+comment_board_num);
		
	}


	@Override
	public boolean BoardUpdate(HttpServletRequest request) {
		System.out.println("qna 공지사항 수정 요청");
		String num = request.getParameter("qna_num");
		int qna_num = Integer.parseInt(num);
		
		String qna_name = request.getParameter("qna_name");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		BoardVO  board = new BoardVO();
		
		board.setQna_num(qna_num);
		board.setQna_name(qna_name);
		board.setQna_title(qna_title);
		board.setQna_content(qna_content);
		System.out.println(board);
		return dao.BoardUpdate(board);
	}


	@Override
	public boolean boardDelete(HttpServletRequest request) {
		
		String num = request.getParameter("qna_num");
		System.out.println(num);
		int qna_num = Integer.parseInt(num);
		
		return dao.baordDelete(qna_num);
	}


	



	
}
