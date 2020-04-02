package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.MemberVO;

public interface MemberService {
	
	// 회원 가입 처리
	void memberJoin(HttpServletRequest request, HttpServletResponse response);
	/* 
	 * 로그인 처리
	 * @result true - 로그인 성공
	 * @result false - 로그인 실패
	 */
	void memberLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/**
	 * attr - 회원정보 수정
	 * @param request - 수정 정보
	 * @param response - 응답 정보
	 */
	void memberUpdate(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 회원탈퇴 요청
	 * @param request - 재입력 비밀번호
	 * @param response - 응답 정보
	 */
	void withdrawSubmit(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 로그아웃 처리
	 * Session Cookie 정보 초기화
	 */
	void logOut(HttpServletRequest request, HttpServletResponse response);
	
	
	/**
	 * 관리자 정보 확인 check 'admin'
	 * @return true - 관리자
	 * @return false - 일반회원
	 */
	boolean checkAdmin(HttpServletRequest request, HttpServletResponse response);
	
	
	/**
	 * 회원관리 페이지 회원목록 요청
	 * @param request
	 * @return 회원목록(ArrayList<MemberVO>)
	 */
	ArrayList<MemberVO> getMemberList(HttpServletRequest request);
	
	/**
	 * 비밀번호 변경 사용자 정보 확인 / 메일 발송
	 * @param request - member id , member name
	 * @param response - 요청 정보 처리에 따른 화면 전환
	 */
	void findPassSubmit(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 비밀번호 변경 요청 정보 확인
	 * @param request - id(email) , code
	 * @param response - 인증
	 */
	void checkPassCode(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 사용자 새 비밀번호 
	 * @param request id _ code _ pass
	 * @param response 비밀번호 변경 요청 처리 응답
	 */
	void changePass(HttpServletRequest request, HttpServletResponse response);
	
	
	
	
	//이메일 인증
	
	void memberJoinCheck(HttpServletRequest request, HttpServletResponse response);
	
	

}





