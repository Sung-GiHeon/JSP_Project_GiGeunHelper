package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBCPUtil;
import vo.MemberVO;

public class MemberDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boolean memberJoin(MemberVO member) {
		conn = DBCPUtil.getConnection();
		
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT * FROM member_tb WHERE member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			rs = pstmt.executeQuery();
			if(rs.next())return false;
			
			sql = "INSERT INTO member_tb(member_id,member_pw,member_mail,member_phone,member_name) "
				  + " VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getMember_id());
			pstmt.setString(2,member.getMember_pw());
			pstmt.setString(3,member.getMember_mail());
			pstmt.setString(4,member.getMember_phone());
			pstmt.setString(5,member.getMember_name());
			
			int result = pstmt.executeUpdate();
			if(result <= 0) return false;
			
			sql = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int member_num = 0;
			if(rs.next()) member_num = rs.getInt(1);
			
			sql = "UPDATE member_tb SET member_credit = 30 WHERE member_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("회원가입 실패 : " + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return true;
	}

	// 로그인 check
	public MemberVO memberLogin(String member_id, String member_pw) {
		MemberVO member = null;
		conn = DBCPUtil.getConnection();
		
		try {
			
			String sql = "SELECT * FROM member_tb WHERE member_id=? AND member_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pw);
			rs = pstmt.executeQuery();
			// 아이디 패스워드 일치하는 회원정보 존재
			if(rs.next()) {
				member = new MemberVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getTimestamp(7),
						rs.getString(8),
						rs.getString(9)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return member;
	}

	public MemberVO getMemberById(String member_id) {
		MemberVO member = null;
		
		conn = DBCPUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM member_tb WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO(
						rs.getInt("member_num"),
						rs.getString("member_id"),
						rs.getString("member_pw"),
						rs.getString("member_mail"),
						rs.getString("member_phone"),
						rs.getInt("member_credit"),
						rs.getTimestamp("member_date"),
						rs.getString("member_join"),
						rs.getString("member_name")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return member;
	}

	// 회원 자신의 정보 수정
	public boolean memberUpdate(MemberVO member) {
		boolean isUpdate = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE member_tb SET "
					+ " member_pw = ? ,"
					+ " member_phone = ? ,"
					+ " member_name = ? "
					+ " WHERE member_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_pw());
			pstmt.setString(2, member.getMember_phone());
			pstmt.setString(3, member.getMember_name());
			pstmt.setInt(4, member.getMember_num());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0 ) isUpdate = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return isUpdate;
	}

	// 회원정보 삭제
	/*
	 * @param - 회원번호
	 */
	public void deleteMember(int member_num) {
		conn = DBCPUtil.getConnection();
		try {
			String sql = "DELETE FROM member_tb WHERE member_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
	}

	// 회원 전체 목록
	public ArrayList<MemberVO> getMemberList() {
		ArrayList<MemberVO> memberList = null;
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM member_tb ORDER BY member_num DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<>();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMember_num(rs.getInt("member_num"));
				member.setMember_id(rs.getString("member_id"));
				member.setMember_mail(rs.getString("member_mail"));
				member.setMember_phone(rs.getString("member_phone"));
				member.setMember_credit(rs.getInt("member_credit"));
				member.setMember_date(rs.getTimestamp("member_date"));
				member.setMember_name(rs.getString("member_name"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return memberList;
	}

	public int getMemberListCount() {
		int listCount = 0;
		conn = DBCPUtil.getConnection();
		String sql = "SELECT count(*) FROM MVC_member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return listCount;
	}

	// paging 처리된 회원 목록
	public ArrayList<MemberVO> getMemberList(int page, int perPageNum) {
		
		int startPage = (page-1)*perPageNum;
		
		
		ArrayList<MemberVO> memberList = new ArrayList<>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM member_tb ORDER BY member_num DESC limit ? , ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, perPageNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMember_num(rs.getInt("member_num"));
				member.setMember_id(rs.getString("member_id"));
				member.setMember_mail(rs.getString("member_mail"));
				member.setMember_phone(rs.getString("member_phone"));
				member.setMember_credit(rs.getInt("member_credit"));
				member.setMember_date(rs.getTimestamp("member_date"));
				member.setMember_name(rs.getString("member_name"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return memberList;
	}
	
	// id + name으로 member 확인
	public boolean checkMember(String member_id, String member_mail ){
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM member_tb WHERE member_id = ? AND member_mail = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_mail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return false;
	}

	// 비밀번호 찾기용 임시 코드 등록
	public void addPassCode(String id, String code) {
		try {
			conn = DBCPUtil.getConnection();
			
			String sql = "SELECT * FROM test_code WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			System.out.println("id : " + id+", code : " + code);
			
			if(rs.next()) {
				System.out.println("수정완료");
				sql = "UPDATE test_code SET code = ? WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				pstmt.setString(2, id);
			}else {
				System.out.println("삽입완료");
				sql = "INSERT INTO test_code VALUES(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, code);
			}
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
	}

	public boolean checkPassCode(String id, String code) {
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM test_code WHERE id = ? AND code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs);
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		return false;
	}

	// 비밀번호 수정
	public void changePass(String member_id, String member_pw, String id) {
		conn = DBCPUtil.getConnection();
		
		try {
			String sql = "UPDATE member_tb SET member_pw = ? WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			pstmt.setString(2, member_id);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				sql = "DELETE FROM test_code WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt);
			DBCPUtil.close(conn);
		}
		System.out.println("변경완료");
	}


	
	
	
	
	
	
	
	
	

}




