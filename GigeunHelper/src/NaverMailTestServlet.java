

import java.io.IOException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NaverAuthenticator;

@WebServlet("/naverMailTest")
public class NaverMailTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NaverAuthenticator auth = new NaverAuthenticator();
		
		Session session = Session.getDefaultInstance(auth.getProperties(),auth);
		
		MimeMessage msg = new MimeMessage(session);
		
		try {
			
			msg.setSentDate(new Date());
			msg.setRecipient(
					Message.RecipientType.TO,
					new InternetAddress()
			);
			msg.setFrom(new InternetAddress("910528jin@naver.com","MASTER"));
			msg.setSubject("네이터 메일 테스트 입니다.");
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setText("<a href='http://192.168.1.6:8080"+request.getContextPath()+"/test'>눌러보세요</a>");
			Transport.send(msg);
			System.out.println("메일 전송 성공");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("메일 전송 실패");
		}
		
		response.sendRedirect(request.getContextPath()+"/test");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
