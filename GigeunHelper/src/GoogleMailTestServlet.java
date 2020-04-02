

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/googleMailTest")
public class GoogleMailTestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	class MyAuthentication extends Authenticator{
		PasswordAuthentication pa;
		
		public MyAuthentication() {
			String id = "chlrlrms1@gmail.com";
			String pw = "chlrlrms123456";
			pa = new PasswordAuthentication(id, pw);
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GoogleMailTestServlet 요청 들어옴");
		
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.auth","true");
		p.put("mail.smtp.port","587");
		
		System.out.println(p);
		
		
		Authenticator auth = new MyAuthentication();
		Session session = Session.getDefaultInstance(p,auth);
		
		try {
			MimeMessage msg = new MimeMessage(session);
			// 보낸시간 현재시간
			msg.setSentDate(new Date());
			// 받는 사람
			InternetAddress to
				= new InternetAddress("hap0p9y@nate.com");
			msg.setRecipient(Message.RecipientType.TO, to);
			// Message.RecipientType.TO 받는사람
			// Message.RecipientType.CC 참조
			// Message.RecipientType.BCC 숨은참조
			InternetAddress from
				= new InternetAddress("master@koreate.net","MASTER");
			msg.setFrom(from);
			msg.setSubject("테스트입니다!!!","UTF-8");
			msg.setText("테스트 내용입니다.","UTF-8");
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			
			javax.mail.Transport.send(msg);
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
