package service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DAO.QNABoardDAO;
import DAO.QNABoardDAOImpl;
import vo.CommentVO;
import vo.Criteria;
import vo.PageMaker;

public class CommentServiceImpl implements CommentService{
	
	QNABoardDAO dao = new QNABoardDAOImpl();

	@Override
	public boolean insertComment(HttpServletRequest request) {
		
		String qnaNum = request.getParameter("qna_num");
		String writerNum = request.getParameter("comment_writer_num");
		String comment_name = request.getParameter("comment_name");
		String comment_content = request.getParameter("comment_content");
		
		CommentVO cv = new CommentVO();
		cv.setComment_board_num(Integer.parseInt(qnaNum));
		cv.setComment_writer_num(Integer.parseInt(writerNum));
		cv.setComment_name(comment_name);
		cv.setComment_content(comment_content);
		
		
		return dao.insertComment(cv);
	}

	@Override
	public HashMap<String, Object> getCommentList(HttpServletRequest request) {
		
		HashMap<String,Object> map = new HashMap<>();
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = dao.getCommentListCount(qna_num);
		Criteria cri = new Criteria(page, 5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(listCount);
		
		map.put("list", dao.getCommentList(qna_num, cri));
		map.put("pm", pm);
		return map;
	}

	@Override
	public boolean deleteComment(HttpServletRequest request) {
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int comment_writer_num = Integer.parseInt(request.getParameter("comment_writer_num"));
		return dao.deleteCommit(comment_num, comment_writer_num);
	}
	
	

}
