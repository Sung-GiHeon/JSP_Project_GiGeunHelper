package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBCPUtil;
import vo.BoardVO;
import vo.CommentVO;
import vo.Criteria;
import vo.PageMaker;

public class QNABoardDAOImpl implements QNABoardDAO{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	@Override
	public int getListCount() {
		
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT count(*) FROM qna_board";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		
		return listCount;
	}

	@Override
	public ArrayList<BoardVO> getBoardList() {
		ArrayList<BoardVO> list = new ArrayList<>();
		conn = DBCPUtil.getConnection();
		try {
			String sql = "SELECT * FROM qna_board ORDER BY qna_re_ref DESC, qna_re_seq ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	public BoardVO getBoard(ResultSet rs) throws SQLException{
		BoardVO board = new BoardVO();
		board.setQna_num(rs.getInt("qna_num"));
		board.setQna_name(rs.getString("qna_name"));
		board.setQna_title(rs.getString("qna_title"));
		board.setQna_content(rs.getString("qna_content"));
		board.setQna_file(rs.getString("qna_file"));
		board.setQna_file_origin(rs.getString("qna_file_origin"));
		board.setQna_re_ref(rs.getInt("qna_re_ref"));
		board.setQna_re_lev(rs.getInt("qna_re_lev"));
		board.setQna_re_seq(rs.getInt("qna_re_seq"));
		board.setQna_writer_num(rs.getInt("qna_writer_num"));
		board.setQna_readcount(rs.getInt("qna_readcount"));
		board.setQna_date(rs.getTimestamp("qna_date"));
		return board;
	}
	
	
	
	@Override
	public ArrayList<BoardVO> getBoardList(PageMaker pageMaker) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM qna_board"
					+ " ORDER BY qna_re_ref DESC ,"
					+ " qna_re_seq ASC "
					+ " limit ? , ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageMaker.getCri().getPageStart());
			pstmt.setInt(2, pageMaker.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	
	
	
	
	
	
	
	@Override
	
	
	public void boardWrite(BoardVO board) {
		
		conn = DBCPUtil.getConnection();
		
		try {
			
			conn.setAutoCommit(false);
			String sql = "INSERT INTO qna_board VALUES(null,?,?,?,?,?,0,0,0,?,0,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getQna_name());
			pstmt.setString(2, board.getQna_title());
			pstmt.setString(3, board.getQna_content());
			pstmt.setString(4, board.getQna_file());
			pstmt.setString(5, board.getQna_file_origin());
			pstmt.setInt(6, board.getQna_writer_num());
			pstmt.executeUpdate();
			
			// 가장 최근에 등록된 session의 auto_increment 값을 가져온다.
			sql = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int qna_num = 0;
			if(rs.next())qna_num=rs.getInt(1);
			System.out.println("qna_num : " + qna_num);
			
			sql = "UPDATE qna_board SET "
				+ " qna_re_ref = ? "
				+ " WHERE qna_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setInt(2, qna_num);
			pstmt.executeUpdate();
			System.out.println("등록 완료");
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {}
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
	}

	@Override
	public BoardVO getBoardVO(int qna_num) {
		BoardVO board = null;
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM qna_board WHERE qna_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			if(rs.next()) board = getBoard(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return board;
	}

	@Override
	public void updateReadCount(int qna_num) {
		
		conn = DBCPUtil.getConnection();
		
		String sql = " UPDATE qna_board SET "
				   + " qna_readcount = qna_readcount + 1 "
				   + " WHERE qna_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
	}

	@Override
	public void boardReplySubmit(BoardVO board) {
		
		int ref = board.getQna_re_ref();
		int lev = board.getQna_re_lev();
		int seq = board.getQna_re_seq();
		
		String sql = "UPDATE qna_board SET "
					+ " qna_re_seq = qna_re_seq + 1 "
					+ " WHERE qna_re_ref = ? AND qna_re_seq > ? ";
		
		conn = DBCPUtil.getConnection();
		
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			pstmt.executeUpdate();
			
			sql = "INSERT INTO qna_board "
				+ " VALUES(null,?,?,?,?,?,?,?,?,?,0,now())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getQna_name());
			pstmt.setString(2, board.getQna_title());
			pstmt.setString(3, board.getQna_content());
			pstmt.setString(4, "");
			pstmt.setString(5, "");
			pstmt.setInt(6, ref);
			pstmt.setInt(7, lev+1);
			pstmt.setInt(8, seq+1);
			pstmt.setInt(9, board.getQna_writer_num());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {}
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
	}


	@Override
	public boolean insertComment(CommentVO vo) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		String sql = "INSERT INTO qna_comment VALUES(null,?,?,?,now(),'N',?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getComment_writer_num());
			pstmt.setString(2, vo.getComment_name());
			pstmt.setString(3, vo.getComment_content());
			pstmt.setInt(4, vo.getComment_board_num());
			if(pstmt.executeUpdate() > 0 ) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(pstmt,conn);}
		return isSuccess;
	}

	@Override
	public ArrayList<CommentVO> getCommentList(int comment_board_num, Criteria cri) {
		
		String sql = "SELECT * FROM qna_comment "
					+ " WHERE comment_board_num = ? "
					+ " ORDER BY comment_num DESC limit ?, ?";
		
		/*
		 * String sql = "SELECT * FROM qna_comment " +
		 * " WHERE comment_board_num = ? AND comment_delete = 'N' " +
		 * " ORDER BY comment_num DESC limit ?, ?";
		 */
		
		ArrayList<CommentVO> list = new ArrayList<>();
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			pstmt.setInt(2, cri.getPageStart());
			pstmt.setInt(3, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentVO cv = new CommentVO();
				cv.setComment_num(rs.getInt("comment_num"));
				cv.setComment_writer_num(rs.getInt("comment_writer_num"));
				cv.setComment_name(rs.getString("comment_name"));
				cv.setComment_content(rs.getString("comment_content"));
				cv.setComment_delete(rs.getString("comment_delete"));
				cv.setComment_date(rs.getTimestamp("comment_date"));
				list.add(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		return list;
	}

	@Override
	public int getCommentListCount(int comment_board_num) {
		String sql = "SELECT count(*) FROM qna_comment WHERE comment_board_num=?";
		int listCount = 0;
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		return listCount;
	}

	@Override
	public boolean deleteCommit(int comment_num, int comment_writer_num) {
		boolean isSuccess = false;
		
		String sql = "UPDATE qna_comment SET"
				  + " comment_delete = 'Y' "
				  + " WHERE comment_num = ? "
				  + " AND comment_writer_num = ?";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			pstmt.setInt(2, comment_writer_num);
			if(pstmt.executeUpdate() > 0 ) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(pstmt,conn);}
		return isSuccess;
	}

	@Override
	public boolean BoardUpdate(BoardVO board) {
		
		boolean isSuccess = false;
		
		String sql =  "UPDATE qna_board SET"
					+ " qna_name = ? ,"
					+ " qna_title = ?, "
					+ " qna_content = ?,"
					+ "qna_date = now()"
					+ "WHERE qna_num = ?";
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getQna_name());
			pstmt.setString(2, board.getQna_title());
			pstmt.setString(3,board.getQna_content());
			pstmt.setInt(4, board.getQna_num());
			
			if(pstmt.executeUpdate() > 0 ) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {DBCPUtil.close(pstmt,conn);}
		
		return isSuccess;
	}

	@Override
	public boolean baordDelete(int qna_num) {
		boolean isSuccess = false;
		conn = DBCPUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM qna_comment WHERE comment_board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();
			
			sql ="DELETE FROM qna_board WHERE qna_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			if(pstmt.executeUpdate()>0) isSuccess = true;
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isSuccess;
	}
	
}
