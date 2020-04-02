package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBCPUtil;
import vo.NoticeVO;
import vo.PageMaker;
import vo.SearchCriteria;

public class NoticeDAOImpl implements NoticeDAO{
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public boolean noticeWrite(NoticeVO vo) {
		conn = DBCPUtil.getConnection();
		
		String sql = "INSERT INTO notice_board VALUES(null,?,?,?,?,now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getNotice_category());
			pstmt.setString(2,vo.getNotice_author());
			pstmt.setString(3,vo.getNotice_title());
			pstmt.setString(4,vo.getNotice_content());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return false;
	}

	@Override
	public NoticeVO getNoticeVO(int notice_num) {
		
		String sql = "SELECT * FROM notice_board WHERE notice_num = ? ";
		
		conn = DBCPUtil.getConnection();
		NoticeVO notice = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_category(rs.getString("notice_category"));
				notice.setNotice_author(rs.getString("notice_author"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		return notice;
	}

	@Override
	public boolean noticeUpdate(NoticeVO vo) {
		
		boolean isSuccess = false;
		
		String sql = "UPDATE notice_board SET "
				 	+ " notice_category = ?, "
				 	+ " notice_author = ?, "
				 	+ " notice_title = ?, "
				 	+ " notice_content = ? ,"
				 	+ " notice_date = now() "
				 	+ " WHERE notice_num = ?";
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNotice_category());
			pstmt.setString(2, vo.getNotice_author());
			pstmt.setString(3, vo.getNotice_title());
			pstmt.setString(4, vo.getNotice_content());
			pstmt.setInt(5, vo.getNotice_num());
			
			if(pstmt.executeUpdate() > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public boolean noticeDelete(int notice_num) {
		boolean isSuccess = false;
		conn = DBCPUtil.getConnection();
		String sql = "DELETE FROM notice_board WHERE notice_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			if(pstmt.executeUpdate() > 0 ) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(pstmt,conn);}
		return isSuccess;
	}

	@Override
	public ArrayList<NoticeVO> getNoticeList(int pageStart, int perPageNum) {
		ArrayList<NoticeVO> noticeList = new ArrayList<>();
		
		String sql = "SELECT * FROM notice_board ORDER BY notice_num DESC limit ?, ?";
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageStart);
			pstmt.setInt(2, perPageNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeVO n = new NoticeVO();
				n.setNotice_num(rs.getInt("notice_num"));
				n.setNotice_category(rs.getString("notice_category"));
				n.setNotice_author(rs.getString("notice_author"));
				n.setNotice_title(rs.getString("notice_title"));
				n.setNotice_content(rs.getString("notice_content"));
				n.setNotice_date(rs.getTimestamp("notice_date"));
				noticeList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		return noticeList;
	}

	@Override
	public int getListCount() {
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT count(*) FROM notice_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(rs,pstmt,conn);
		}
		return listCount;
	}

	// 검색 결과에 포함된 리스트 전체 개수
	@Override
	public int getSearchListCount(SearchCriteria cri) {
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT count(*) FROM notice_board ";
		System.out.println(cri.getSearchName());
		System.out.println(cri.getSearchValue());
		
		try {
			
			if(cri.getSearchName() == null 
					|| 
			   cri.getSearchName().trim().equals("")
			   		||
			   cri.getSearchName().trim().equals("null")) {
				System.out.println("검색 내용이 없음");
				pstmt = conn.prepareStatement(sql);
			}else {
				if(cri.getSearchName().equals("author")) {
					sql += " WHERE notice_author LIKE CONCAT('%',?,'%') ";
				}else if(cri.getSearchName().equals("title")){
					sql += " WHERE notice_title LIKE CONCAT('%',?,'%') ";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cri.getSearchValue());
			}
			
			System.out.println(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return listCount;
	}

	// 검색 조건에 맞는 게시물 리스트
	@Override
	public ArrayList<NoticeVO> getSearchList(PageMaker pageMaker) {
		ArrayList<NoticeVO> noticeList = new ArrayList<>();
		
		System.out.println("pageStart : "+ pageMaker.getCri().getPageStart());
		System.out.println("perPageNum : " + pageMaker.getCri().getPerPageNum());
		
		SearchCriteria cri = null;
		if(pageMaker.getCri() instanceof SearchCriteria) {
			cri = (SearchCriteria)pageMaker.getCri();
		}
		
		System.out.println(cri);
		
		boolean isSearchQuery = true;
		
		String sql = "SELECT * FROM notice_board ";
		
		conn = DBCPUtil.getConnection();
		try {
			if(cri.getSearchName() == null 
					|| 
			   cri.getSearchName().trim().equals("")
			   		||
			   cri.getSearchName().trim().equals("null")) {
				System.out.println("검색 내용이 없음");
				isSearchQuery = false;
			}else {
				if(cri.getSearchName().equals("author")) {
					sql += " WHERE notice_author LIKE CONCAT('%',?,'%') ";
				}else if(cri.getSearchName().equals("title")){
					sql += " WHERE notice_title LIKE CONCAT('%',?,'%') ";
				}
			}
			sql += "ORDER BY notice_num DESC limit ? , ? ";
			System.out.println("getSearchList sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			if(isSearchQuery) {
				pstmt.setString(1, cri.getSearchValue());
				pstmt.setInt(2, cri.getPageStart());
				pstmt.setInt(3, cri.getPerPageNum());
			}else {
				pstmt.setInt(1, cri.getPageStart());
				pstmt.setInt(2, cri.getPerPageNum());
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeVO n = new NoticeVO();
				n.setNotice_num(rs.getInt("notice_num"));
				n.setNotice_category(rs.getString("notice_category"));
				n.setNotice_author(rs.getString("notice_author"));
				n.setNotice_title(rs.getString("notice_title"));
				n.setNotice_content(rs.getString("notice_content"));
				n.setNotice_date(rs.getTimestamp("notice_date"));
				noticeList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return noticeList;
	}
	

}
