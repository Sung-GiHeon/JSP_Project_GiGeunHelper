package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("*.co")
public class AsyncCommentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	CommentService service = new CommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getRequestURI().substring(req.getContextPath().length()+1);
		Gson gson = new Gson();
		
		String json = "";
		
		if(cmd.equals("commentWrite.co")) {
			json = gson.toJson(service.insertComment(req));
		}
		
		if(cmd.equals("list.co")) {
			json = gson.toJson(service.getCommentList(req));
		}
		
		if(cmd.equals("commentDelete.co")) {
			json = gson.toJson(service.deleteComment(req));
		}
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
