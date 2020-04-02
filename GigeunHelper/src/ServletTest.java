

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/test")
public class ServletTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletTest 요청");
		
		
		
		String requestPath = request.getRequestURI();
		System.out.println("전체요청경로 : " + requestPath);
		
		String contextPath = request.getContextPath();
		System.out.println("ProjectPath : " + contextPath);
		
		String command = requestPath.substring(contextPath.length()+1);
		System.out.println("요청 경로 : " + command);
		
		request.setAttribute("test", "Main Page");
		
		RequestDispatcher rd = request.getRequestDispatcher("/common/main.jsp");
		rd.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
