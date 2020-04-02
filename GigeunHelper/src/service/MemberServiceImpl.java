package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import util.GoogleAuthenticator;
import util.NaverAuthenticator;
import util.PageInfo;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	
	MemberDAO dao = new MemberDAO();	

	@Override
	public void memberJoin(HttpServletRequest request
			, HttpServletResponse response) {
		
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		String member_mail = request.getParameter("member_mail");
		String member_phone = request.getParameter("member_phone");
		String member_name= request.getParameter("member_name");
		
		MemberVO member = new MemberVO(member_id,member_pw,member_mail,member_phone,member_name);
		System.out.println(member);
		
		boolean isSuccess = dao.memberJoin(member);
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			PrintWriter out = response.getWriter();
			String url = "login.mr";
			out.print("<script>");
			if(isSuccess) {
				out.print("alert('회원가입 성공');");
				out.print("alert('회원가입 기념 30credit이 주어집니다.');");
			}else {
				out.print("alert('회원가입 실패');");
				url = "join.mr";
			}
			out.print("location.href='"+url+"';");
			out.print("</script>");
		} catch (IOException e) {}
	}

	@Override
	public void memberLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		String check = request.getParameter("check");
		boolean isCookie = Boolean.parseBoolean(check);
		
		System.out.println("member_id : " + member_id);
		System.out.println("member_pw : " + member_pw);
		System.out.println("check : " + check);
		System.out.println("isCookie : " + isCookie);
		
		MemberVO member = dao.memberLogin(member_id,member_pw);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(member != null) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			if(check != null) {
				// Cookie 처리
				Cookie cookie = new Cookie("id",member.getMember_id());
				cookie.setMaxAge(60*60*7);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			out.print("<script>");
			out.print("alert('로그인 성공');");
			out.print("location.href='main.pd';");
			out.print("</script>");
		}else {
			System.out.println("로그인 실패");
			out.print("<script>");
			out.print("alert('로그인 실패! 아이디와 비밀번호를 확인하세요!');");
			out.print("location.href='main.pd';");
			out.print("</script>");
		}
	}
	
	/**
	 * 회원정보 수정
	 * request - MemberVO
	 */
	@Override
	public void memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		MemberVO member = new MemberVO(
				request.getParameter("member_id"),
				request.getParameter("member_pw"),
				request.getParameter("member_phone"),
				request.getParameter("member_name")
			
		);
		
		member.setMember_num(Integer.parseInt(request.getParameter("member_num")));
		
		System.out.println("memberUpdate : " + member);
		
		boolean isUpdate = dao.memberUpdate(member);
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			PrintWriter out = response.getWriter();
			String url = "info.mr";
			String msg = "회원 정보 수정 완료";
			if(isUpdate) {
				MemberVO memberUpdate = dao.getMemberById(member.getMember_id());
				request.getSession().setAttribute("member", memberUpdate);
			} else {
				url = "update.mr";
				msg = "회원정보 수정 실패";
			}
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href='"+url+"';");
			out.print("</script>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void withdrawSubmit(HttpServletRequest request, HttpServletResponse response) {
		String tempPass = request.getParameter("tempPass");
		System.out.println("temp pass : " + tempPass);
		
		MemberVO member = (MemberVO)request.getSession().getAttribute("member");
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			PrintWriter writer = response.getWriter();
			writer.print("<script>");
			if(member != null && tempPass.equals(member.getMember_pw())){
				dao.deleteMember(member.getMember_num());
				// 회원탈퇴 완료 시 
				// Session 정보 삭제 - Cookie 정보 삭제
				logOut(request,response);
				
				writer.print("alert('회원탈퇴 완료');");
				writer.print("location.href='test';");
			
			}else{
				// 회원 정보 불일치 회원 탈퇴 실패
				writer.print("alert('회원탈퇴 실패');");
				writer.print("history.go(-1);");
			}
			writer.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 로그아웃 처리
	// session // cookie - 초기화
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("session 초기화");
		//session.removeAttribute("member");
		
		Cookie cookie = new Cookie("id","");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("쿠키 삭제완료");
		
	}
	
	
	// 관리자 check
	@Override
	public boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) {
		boolean isCheck = false;
		
		MemberVO member = (MemberVO)request.getSession().getAttribute("member");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		
		try {
			
			out = response.getWriter();
			if(member == null) {
				out.print("<script>alert('로그인 이후에 사용이 가능합니다.');</script>");
				out.print("<script>location.href='login.mr';</script>");
				return isCheck;
			}
			
			if(!member.getMember_id().equals("admin")) {
				out.print("<script>alert('접근 권한이 없는 사용자입니다. 관리자에게 문의하세요.');</script>");
				out.print("<script>location.href='test';</script>");
				return isCheck;
			}
			isCheck = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isCheck;
	}

	
	
	// 회원목록
	@Override
	public ArrayList<MemberVO> getMemberList(HttpServletRequest request) {
		// 기본 페이지
		int defaultPage = 1;
		// 한번에 보여줄 게시물의 수
		int perPageNum = 10;
		
		String page = request.getParameter("page");
		
		if(page != null) {
			defaultPage = Integer.parseInt(page);
		}
		
		System.out.println("defaultPage : " + defaultPage);
		int memberCount = dao.getMemberListCount();
		System.out.println("전체 회원 인원수 : " + memberCount);
		
		int startPage = (defaultPage-1)/perPageNum*perPageNum+1;
		System.out.println("시작 페이지 번호 : " + startPage);
		
		int endPage = startPage+(perPageNum-1);
		System.out.println("마지막 페이지 번호 : " + endPage);
		
		int maxPage = (memberCount-1)/perPageNum+1;
		System.out.println("전체 페이지 개수 : " + maxPage);
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		System.out.println("수정된 endPage : "+endPage);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(defaultPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setListCount(memberCount);
		
		request.setAttribute("pageInfo", pageInfo);
		
		ArrayList<MemberVO> memberList = dao.getMemberList(defaultPage,perPageNum);
		
		
		return memberList;
		//return dao.getMemberList();
	}
	
	// 비밀번호 찾기 메일발송 요청
	@Override
	public void findPassSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		String member_id = request.getParameter("member_id");
		String member_mail = request.getParameter("member_mail");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			
			boolean isCheck = dao.checkMember(member_id,member_mail);
			
			if(!isCheck) {
				System.out.println("일치하는 정보가 없음");
				throw new NullPointerException("일치하는 정보가 없음");
			}
			
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<5; i++) {
				// 0 ~ 9
				int random = (int)(Math.random()*10);
				sb.append(random);
			}
			
			String code = sb.toString();
			System.out.println("code : " + code);
			dao.addPassCode(member_mail,code);
			
			// 메일 발송
			//GoogleAuthenticator auth = new GoogleAuthenticator();
			NaverAuthenticator auth = new NaverAuthenticator();
			Session session
				= Session.getDefaultInstance(auth.getProperties(),auth);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setRecipient(
					Message.RecipientType.TO, 
					new InternetAddress(member_mail)
			);
			msg.setFrom(new InternetAddress("910528jin@naver.com","JAVASTOCK"));
			msg.setSubject("JAVASTOCK 비밀번호 찾기 메일입니다.","UTF-8");
			StringBuilder mail = new StringBuilder();
			mail.append("<!DOCType html>");
			mail.append("<html>");
			mail.append("<head>");
			mail.append("<meta charset='utf-8'>");
			mail.append("<title>비밀번호 찾기</title>");
			mail.append("<script>");
			mail.append("function submitPass(){window.open('','w')}");
			mail.append("</script>");
			mail.append("</head>");
			mail.append("<body>");
			mail.append("<h1>@@@ 사이트 비밀번호 찾기 이메일 인증</h1>");
			mail.append("<form action='http://192.168.1.34:8080"+request.getContextPath()+"/passAccept.mr' method='post' onsubmit='submitPass()' target='w'>");
			mail.append("<input type='hidden' name='id' value='"+member_mail+"'/>");
			mail.append("<input type='hidden' name='code' value='"+code+"'/>");
			mail.append("<input type='submit' value='이메일 인증 완료'/>");
			mail.append("</form>");
			mail.append("</body>");
			mail.append("</html>");
			
			String content = new String(mail);
			msg.setContent(content,"text/html;charset=utf-8");
			
			Transport.send(msg);
			
			out.print("<script>alert('이메일 발송 완료! 메일을 확인해주세요');");
			out.print("location.href='test';</script>");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			out.print("<script>alert('서비스에 문제가 있습니다. 다시 이용해 주세요!');");
			out.print("location.href='login.mr';</script>");
		}
	}
	
	@Override
	public void checkPassCode(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String mail = request.getParameter("mail");
		
		System.out.println("id : " + id + ", code : " + code);
		
		boolean isCheck = dao.checkPassCode(id,code);
		try {
			if(isCheck) {
				// 새비밀번호 작성 창
				System.out.println("일치");
				request.setAttribute("id", id);
				request.setAttribute("code", code);
				request.setAttribute("mail", mail);
				request.getRequestDispatcher("/member/changePass.jsp")
				.forward(request, response);
			}else {
				// 잘못된 요청 -> login.mr
					PrintWriter out = response.getWriter();
					response.setContentType("text/html;charset=utf-8");
					out.print("<script>");
					out.print("alert('잘못된 요청입니다.');");
					out.print("location.href='login.mr';");
					out.print("</script>");
			}
		} catch (Exception e) {}
	}
	
	// 비밀번호 찾기 -> 새 비밀번호 
	@Override
	public void changePass(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		
		
		boolean isCheck = dao.checkPassCode(id, code);
		
		try {
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			if(isCheck) {
				// 비밀번호 변경
				dao.changePass(member_id,member_pw, id);
				out.print("alert('변경 요청 처리 완료');");
			}else {
				// 비정상적인 접근
				out.print("alert('올바른 접근이 아닙니다.');");
			}
			
			out.print("location.href='login.mr';");
			out.print("</script>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// Cookie 정보를 이용한 사용자 자동로그인
	public static void loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("id")) {
						String id = c.getValue();
						System.out.println("쿠키의 id 정보 : " + id);
						MemberDAO dao = new MemberDAO();
						MemberVO member = dao.getMemberById(id);
						if(member != null) {
							session.setAttribute("member", member);
						}
					}
				}
			}
		}
		
	}

	
	
	
	@Override
	public void memberJoinCheck(HttpServletRequest request, HttpServletResponse response) {

		String mail2 = request.getParameter("mail2");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			
			// 메일 발송
			//GoogleAuthenticator auth = new GoogleAuthenticator();
			NaverAuthenticator auth = new NaverAuthenticator();
			Session session
				= Session.getDefaultInstance(auth.getProperties(),auth);
			
			MimeMessage msg = new MimeMessage(session);
			
			msg.setSentDate(new Date());
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setRecipient(
					Message.RecipientType.TO, 
					new InternetAddress(mail2)
			);
			msg.setFrom(new InternetAddress("910528jin@naver.com","JAVASTOCK"));
			msg.setSubject("JAVASTOCK 회원가입 인증 메일입니다.","UTF-8");
			StringBuilder mail = new StringBuilder();
			mail.append("<!DOCType html>");
			mail.append("<html>");
			mail.append("<head>");
			mail.append("<meta charset='utf-8'>");
			mail.append("<title>회원가입 인증</title>");
			mail.append("<script>");
			mail.append("function submitPass(){window.open('','w')}");
			mail.append("</script>");
			mail.append("</head>");
			mail.append("<body>");
			mail.append("<h1>@@@ 이메일 인증</h1>");
			mail.append("<form action='http://192.168.1.34:8080"+request.getContextPath()+"/passAcceptJoin.mr?mail="+mail2+"' method='post' onsubmit='submitPass()' target='w'>");
			mail.append("<input type='submit' value='이메일 인증 완료'/>");
			mail.append("</form>");
			mail.append("</body>");
			mail.append("</html>");
			
			String content = new String(mail);
			msg.setContent(content,"text/html;charset=utf-8");
			
			Transport.send(msg);
			
			out.print("<script>alert('이메일 발송 완료! 메일을 확인해주세요');");
			out.print("location.href='test';</script>");
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<script>alert('서비스에 문제가 있습니다. 다시 이용해 주세요!');");
			out.print("location.href='login.mr';</script>");
		}
		
		System.out.println("확인");
	}
}
