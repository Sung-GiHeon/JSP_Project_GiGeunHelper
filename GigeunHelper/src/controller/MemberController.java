package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

@WebServlet("*.mr")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService service = new MemberServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MemberController 요청");

		request.setCharacterEncoding("UTF-8");

		MemberServiceImpl.loginCheck(request);

		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestPath.substring(contextPath.length());
		System.out.println(command + " 요청 드러옴!!!");

		String nextPage = "";

		if (command.equals("/page1.mr")) {
			System.out.println("첫번재 페이지 요청");
			nextPage = "/common/main.jsp";
		}

		if (command.equals("/page2.mr")) {
			System.out.println("두번째 페이지요청");
			nextPage = "/common/main2.jsp";
		}

		if (command.equals("/page3.mr")) {
			System.out.println("세번째 페이지요청");
			nextPage = "/common/main3.jsp";
		}

		if (command.equals("/mailJoin.mr")) {
			System.out.println("회원가입 처리 요청");
			nextPage = "/member/mailsubmit.jsp";

		}
		if (command.equals("/passAcceptJoin.mr")) {
			nextPage = "/member/join.jsp";
		}

		if (command.equals("/join.mr")) {
			nextPage = "/member/join.jsp";
		}
		if (command.equals("/memberJoin.mr")) {
			System.out.println("회원가입 처리 요청");
			service.memberJoin(request, response);
		}
		
		if(command.equals("addCreditJoin.mr")) {
		}

		if (command.equals("/memberJoinCheck.mr")) {
			System.out.println("회원가입 처리 요청");
			service.memberJoinCheck(request, response);

		}

		if (command.equals("/login.mr")) {
			nextPage = "/member/login.jsp";
		}

		if (command.equals("/memberLogin.mr")) {
			System.out.println("로그인 처리 요청");
			service.memberLogin(request, response);
		}

		if (command.equals("/info.mr")) {
			System.out.println("회원정보 화면 요청");
			nextPage = "/member/info.jsp";
		}

		if (command.equals("/update.mr")) {
			System.out.println("회원정보 수정 화면 요청");
			nextPage = "/member/update.jsp";
		}

		if (command.equals("/memberUpdate.mr")) {
			System.out.println("회원 정보 수정 요청");
			service.memberUpdate(request, response);
		}

		if (command.equals("/withdraw.mr")) {
			System.out.println("회원탈퇴 비밀번호 입력창 요청");
			// 비밀번호 재입력 요청
			nextPage = "/member/withdraw.jsp";
		}

		if (command.equals("/withdrawSubmit.mr")) {
			System.out.println("회원탈퇴 요청");
			service.withdrawSubmit(request, response);
		}

		if (command.equals("/logOut.mr")) {
			request.setAttribute("test", "LogOut 완료");
			service.logOut(request, response);
			nextPage = "main.pd";
		}

		// 관리자 회원정보 페이지 요청
		if (command.equals("/management.mr")) {
			System.out.println("회원관리 정보 페이지 요청");

			boolean isCheck = service.checkAdmin(request, response);
			if (!isCheck)
				return;

			ArrayList<MemberVO> list = service.getMemberList(request);
			request.setAttribute("memberList", list);
			nextPage = "/member/management.jsp";
		}

		if (command.equals("/findPass.mr")) {
			nextPage = "/member/findPass.jsp";
		}

		if (command.equals("/findPassSubmit.mr")) {
			System.out.println("비밀번호 변경 메일 발송 요청");
			service.findPassSubmit(request, response);
		}

		if (command.equals("/passAccept.mr")) {
			System.out.println(request.getParameter("mail2"));
			service.checkPassCode(request, response);
		}

		if (command.equals("/changePass.mr")) {
			System.out.println("비밀번호 변경 요청");
			service.changePass(request, response);
		}

		if (nextPage != null && !nextPage.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
