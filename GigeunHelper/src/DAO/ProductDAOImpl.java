package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DBCPUtil;
import vo.BuyVO;
import vo.CartVO;
import vo.MemberVO;
import vo.PageMaker;
import vo.ProductManagerVO;
import vo.ProductVO;
import vo.SearchCriteria;

public class ProductDAOImpl implements ProductDAO{

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public ArrayList<ProductVO> getProductListMain() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb WHERE product_free='Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ArrayList<ProductVO> getImageList(PageMaker pageMaker,int product_member_num) {
		
		conn = DBCPUtil.getConnection();
		
		ArrayList<ProductVO> list = new ArrayList<>();
		ProductVO product = new ProductVO();
		
		String sql = "SELECT * FROM  product_tb WHERE product_member_num = ? ORDER BY product_member_num DESC limit ? , ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_member_num);
			pstmt.setInt(2, pageMaker.getCri().getPageStart());
			pstmt.setInt(3, pageMaker.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_title(rs.getString("product_title"));
				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_origin_file(rs.getString("product_origin_file"));
				product.setProduct_file(rs.getString("product_file"));
				product.setProduct_credit(rs.getInt("product_credit"));
				product.setProduct_likecount(rs.getInt("product_likecount"));
				product.setProduct_readcount(rs.getInt("product_readcount"));
				product.setProduct_member_num(rs.getInt("product_member_num"));
				product.setProduct_free(rs.getString("product_free"));
				product.setProduct_free_time(rs.getTimestamp("product_free_time"));
				product.setProduct_date(rs.getTimestamp("product_date"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}
	
	@Override
	public int FreeCount() {
		int count = 0;
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT count(*) FROM product_tb WHERE product_free = 'Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return count;
	}

	@Override
	public int UnfreeCount() {
		int count = 0;
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT count(*) FROM product_tb WHERE product_free = 'N'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return count;
	}

	@Override
	public ArrayList<ProductManagerVO> productListUnfree(PageMaker pm) {
		ArrayList<ProductManagerVO> list = new ArrayList<ProductManagerVO>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE product_tb.product_free='N' ORDER BY product_tb.product_num DESC limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pm.getCri().getPageStart());
			pstmt.setInt(2, pm.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductManagerVO product = new ProductManagerVO();
				product.setMember_name(rs.getString("member_name"));
				product.setProduct_credit(rs.getInt("product_credit"));
				product.setProduct_free(rs.getString("product_free"));
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_origin_file(rs.getString("product_origin_file"));
				product.setProduct_titile(rs.getString("product_title"));
				product.setProduct_origin_credit(rs.getInt("product_origin_credit"));
				product.setProduct_free_date(rs.getTimestamp("product_free_time"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	public ArrayList<ProductManagerVO> productListFree(PageMaker pm) {
		ArrayList<ProductManagerVO> list = new ArrayList<ProductManagerVO>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE product_tb.product_free='Y' ORDER BY product_tb.product_num DESC limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pm.getCri().getPageStart());
			pstmt.setInt(2, pm.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductManagerVO product = new ProductManagerVO();
				product.setMember_name(rs.getString("member_name"));
				product.setProduct_credit(rs.getInt("product_credit"));
				product.setProduct_free(rs.getString("product_free"));
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_origin_file(rs.getString("product_origin_file"));
				product.setProduct_titile(rs.getString("product_title"));
				product.setProduct_origin_credit(rs.getInt("product_origin_credit"));
				product.setProduct_free_date(rs.getTimestamp("product_free_time"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}


	@Override
	public int likeUp(int product_num) {
		
		ProductVO product = null;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET "
				+ "product_likecount = product_likecount+1 "
				+ "WHERE product_num= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			pstmt.executeUpdate();
		
			sql ="SELECT product_likecount FROM product_tb WHERE product_num =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_likecount(rs.getInt("product_likecount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return product.getProduct_likecount();
	}
	
	@Override
	public int getListCount(int product_member_num) {
		
int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT count(*) FROM product_tb WHERE product_member_num= ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_member_num);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		
		return listCount;
	}
	
	@Override
	public ArrayList<ProductVO> getProductListMember(int product_member_num) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb WHERE product_member_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_member_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int TotalCount() {
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT count(*) FROM product_tb";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return listCount;
	}

	@Override
	public int TotalCountCategory(String product_category) {
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT count(*) FROM product_tb WHERE product_category=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_category);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return listCount;
	}

	@Override
	public ArrayList<ProductVO> getProductList(PageMaker pm, String filter) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		System.out.println("filter : "+filter);
		conn = DBCPUtil.getConnection();
		String sql = "";
		
		if(filter == null || filter.equals("") || filter.equals("product_num")) {
			sql = "SELECT * FROM product_tb ORDER BY product_num DESC limit ?, ?";
		}else if(filter.equals("product_likecount")) {
			sql = "SELECT * FROM product_tb ORDER BY product_likecount DESC limit ?, ?";
		}else{
			sql = "SELECT * FROM product_tb ORDER BY product_readcount DESC limit ?, ?";
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pm.getCri().getPageStart());
			pstmt.setInt(2, pm.getCri().getPerPageNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public ArrayList<ProductVO> getProductListCategory(PageMaker pm, String product_category, String filter) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		conn = DBCPUtil.getConnection();
		String sql = "";
		if(filter == null || filter.equals("product_num")) {
			sql = "SELECT * FROM product_tb WHERE product_category = ? ORDER BY product_num DESC limit ?, ?";
		}else if(filter.equals("product_likecount")) {
			sql = "SELECT * FROM product_tb WHERE product_category = ? ORDER BY product_likecount DESC limit ?, ?";
		}else {
			sql = "SELECT * FROM product_tb WHERE product_category = ? ORDER BY product_readcount DESC limit ?, ?";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_category);
			pstmt.setInt(2, pm.getCri().getPageStart());
			pstmt.setInt(3, pm.getCri().getPerPageNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public ArrayList<ProductVO> getProductListReadcount(PageMaker pm) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM product_tb ORDER BY product_readcount DESC limit ?, ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pm.getCri().getPageStart());
			pstmt.setInt(2, pm.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public ArrayList<ProductVO> getProductListLikecount(PageMaker pm) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM product_tb ORDER BY product_likecount DESC limit ?, ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pm.getCri().getPageStart());
			pstmt.setInt(2, pm.getCri().getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public boolean productWrite(ProductVO product) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "INSERT INTO product_tb VALUES(null,?,?,?,?,?,?,0,0,?,now(),'N',null,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProduct_title());
			pstmt.setString(2, product.getProduct_category());
			pstmt.setString(3, product.getProduct_size());
			pstmt.setInt(4, product.getProduct_credit());
			pstmt.setString(5, product.getProduct_file());
			pstmt.setString(6, product.getProduct_origin_file());
			pstmt.setInt(7, product.getProduct_member_num());
			pstmt.setInt(8, product.getProduct_credit());
			
			if(pstmt.executeUpdate() > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public MemberVO getMemberVO(int product_member_num) {
		
		String sql = "SELECT member_name FROM member_tb WHERE member_num= ?";
		conn = DBCPUtil.getConnection();
		MemberVO member = new MemberVO();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_member_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setMember_name(rs.getString("member_name"));
			}
			System.out.println("member :"+member);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(rs,pstmt,conn);
		}
		return member;
	}
	
	@Override
	public ProductVO getProductVO(int product_num) {
		ProductVO product = new ProductVO();
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb WHERE product_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();
			if(rs.next()) product = getProduct(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return product;
	}
	
	@Override
	public int unLikeUp(int product_num) {
		ProductVO product = null;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET "
				+ "product_likecount = product_likecount-1 "
				+ "WHERE product_num= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			pstmt.executeUpdate();
		
			sql ="SELECT product_likecount FROM product_tb WHERE product_num =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_likecount(rs.getInt("product_likecount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return product.getProduct_likecount();
	}

	@Override
	public boolean goCart(int product_num,int member_num) {
		
conn = DBCPUtil.getConnection();
		
		boolean isSuccess = false;
		
		
		String sql ="SELECT * FROM cart_tb WHERE cart_member_num = ? AND cart_product_num = ?"; 
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, product_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return isSuccess;
			}else {
				
				sql = "INSERT INTO cart_tb VALUES (null,?,?)";

				conn = DBCPUtil.getConnection();
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, member_num);
					pstmt.setInt(2, product_num);
					pstmt.executeUpdate();
			
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return isSuccess;
	}
	
	@Override
	public MemberVO minusCredit(String member_id, int product_credit) {
		MemberVO member = null;
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE member_tb SET "
				+ "member_credit= member_credit-"+product_credit
				+ " WHERE member_id= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member_id);
			pstmt.executeUpdate();
			
		sql ="SELECT * FROM member_tb WHERE member_id = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,member_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
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
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return member;
	}
	
	@Override
	public int getCreditVO(String member_id) {
		MemberVO member = null;
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT member_credit,member_id FROM member_tb WHERE member_id =?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				member = new MemberVO();
				member.setMember_credit(rs.getInt("member_credit"));
				member.setMember_id(rs.getString("member_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return member.getMember_credit();
	}
	
	@Override
	public boolean freeProduct(int product_num) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET product_free='Y', product_free_time=now(), product_credit=0 WHERE product_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			if(pstmt.executeUpdate() > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		
		return isSuccess;
	}
	
	@Override
	public boolean unfreeProduct(ProductManagerVO product) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET product_free='N', product_free_time=null, product_credit=? WHERE product_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProduct_credit());
			pstmt.setInt(2, product.getProduct_num());
			if(pstmt.executeUpdate() > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}


	@Override
	public boolean productDelete(int product_num) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		try {
		conn.setAutoCommit(false);
		String sql = "DELETE FROM product_tb WHERE product_num=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, product_num);
		if(pstmt.executeUpdate() > 0) isSuccess = true;
		
		sql = "DELETE FROM like_tb WHERE like_product_num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, product_num);
		pstmt.executeUpdate();
		
		conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	public ArrayList<CartVO> getProductListCart(int cart_member_num) {
		ArrayList<CartVO> list = new ArrayList<CartVO>();
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT cart_tb.cart_num, member_tb.member_num, product_tb.product_origin_file, product_tb.product_title, product_tb.product_credit, member_tb.member_name FROM product_tb INNER JOIN cart_tb ON product_tb.product_num = cart_tb.cart_product_num INNER JOIN member_tb ON cart_tb.cart_member_num = member_tb.member_num WHERE cart_tb.cart_member_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_member_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setMember_num(rs.getInt("member_num"));
				cart.setCart_num(rs.getInt("cart_num"));
				cart.setProduct_origin_file(rs.getString("product_origin_file"));
				cart.setProduct_credit(rs.getInt("product_credit"));
				cart.setProduct_title(rs.getString("product_title"));
				cart.setMember_name(rs.getString("member_name"));
				list.add(cart);
			}
			System.out.println("list : "+list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	public boolean cartDelete(int cart_num) {
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM cart_tb WHERE cart_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			if(pstmt.executeUpdate() > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public int getSeachListCount(SearchCriteria cri) {
		int listCount = 0;
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT COUNT(*) FROM product_tb";
		try {
			if(cri.getSearchName() == null || cri.getSearchName().trim().equals("") || cri.getSearchName().trim().equals("null")) {
				pstmt = conn.prepareStatement(sql);
			}else {
				if(cri.getSearchName().equals("author")) {
					sql = "SELECT * FROM member_tb WHERE member_name LIKE CONCAT('%',?,'%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cri.getSearchValue());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						sql = "SELECT COUNT(*) FROM product_tb WHERE product_member_num=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, rs.getInt("member_num"));
						rs = pstmt.executeQuery();
						if(rs.next()) {
							listCount = rs.getInt(1);
						}
					}
				}else if(cri.getSearchName().equals("title")) {
					sql = "SELECT COUNT(*) FROM product_tb WHERE product_title LIKE CONCAT('%',?,'%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,cri.getSearchValue());
					rs = pstmt.executeQuery();
					if(rs.next()) {
						listCount = rs.getInt(1);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return listCount;
	}

	@Override
	public ArrayList<ProductVO> getSearchList(PageMaker pageMaker, String filter) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		System.out.println("pageStart : "+ pageMaker.getCri().getPageStart());
		System.out.println("perPageNum : " + pageMaker.getCri().getPerPageNum());
		
		SearchCriteria cri = null;
		if(pageMaker.getCri() instanceof SearchCriteria) {
			cri = (SearchCriteria)pageMaker.getCri();
		}
		
		System.out.println(cri);
		
		boolean isSearchQuery = true;
		
		String sql = "SELECT * FROM product_tb";
		
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
					if(filter == null || filter.equals("product_num")) {
						sql += " INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE member_tb.member_name LIKE CONCAT('%',?,'%') ORDER BY product_num DESC";	
					}else if(filter.equals("product_readcount")) {
						sql += " INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE member_tb.member_name LIKE CONCAT('%',?,'%') ORDER BY product_readcount DESC";
					}else {
						sql += " INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE member_tb.member_name LIKE CONCAT('%',?,'%') ORDER BY product_likecount DESC";
					}
				}else if(cri.getSearchName().equals("title")){
					if(filter == null || filter.equals("product_num")) {
						sql += " WHERE product_title LIKE CONCAT('%',?,'%') ORDER BY product_num DESC";
					}else if(filter.equals("product_likecount")) {
						sql += " WHERE product_title LIKE CONCAT('%',?,'%') ORDER BY product_likecount DESC";
					}else {
						sql += " WHERE product_title LIKE CONCAT('%',?,'%') ORDER BY product_readcount DESC";
					}
				}
			}
			sql += " limit ?, ? ";
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
				list.add(getProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}

	@Override
	public int CategoryCount(PageMaker pm, String product_category) {
		int categoryCount = 0;
		conn =DBCPUtil.getConnection();
		
		try {
			if(product_category.equals("") || product_category == null) {
				String sql = "SELECT count(DISTINCT product_category) FROM product_tb limit ?, ?";
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, pm.getCri().getPageStart());
					pstmt.setInt(2, pm.getCri().getPerPageNum());
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						categoryCount = rs.getInt(1);
					}
			}else {
				categoryCount = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return categoryCount;
	}
	
	@Override
	public ArrayList<BuyVO> buyProductList(int buy_member_num) {
		
		ArrayList<BuyVO> list = new ArrayList<BuyVO>();
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT product_tb.product_title, product_tb.product_origin_file, buy_tb.buy_date, member_tb.member_name FROM product_tb INNER JOIN buy_tb ON product_tb.product_num = buy_tb.buy_product_num INNER JOIN member_tb ON product_tb.product_member_num = member_tb.member_num WHERE buy_tb.buy_member_num =? ORDER BY buy_tb.buy_date";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buy_member_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BuyVO buy = new BuyVO();
				buy.setBuy_date(rs.getTimestamp("buy_date"));
				buy.setMember_name(rs.getString("member_name"));
				buy.setProduct_origin_file(rs.getString("product_origin_file"));
				buy.setProduct_title(rs.getString("product_title"));
				list.add(buy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	public ProductVO getProduct(ResultSet rs) throws SQLException{
		ProductVO product = new ProductVO();
		product.setProduct_num(rs.getInt("product_num"));
		product.setProduct_title(rs.getString("product_title"));
		product.setProduct_category(rs.getString("product_category"));
		product.setProduct_size(rs.getString("product_size"));
		product.setProduct_credit(rs.getInt("product_credit"));
		product.setProduct_file(rs.getString("product_file"));
		product.setProduct_origin_file(rs.getString("product_origin_file"));
		product.setProduct_readcount(rs.getInt("product_readcount"));
		product.setProduct_likecount(rs.getInt("product_likecount"));
		product.setProduct_date(rs.getTimestamp("product_date"));
		product.setProduct_member_num(rs.getInt("product_member_num"));
		product.setProduct_free(rs.getString("product_free"));
		product.setProduct_free_time(rs.getTimestamp("product_free_time"));
		product.setProduct_origin_credit(rs.getInt("product_origin_credit"));
		return product;
	}

	@Override
	public boolean getBuyTable(int product_num, int member_num) {
		boolean isSuccess = false;
		conn = DBCPUtil.getConnection();
		
		String sql ="SELECT * FROM buy_tb WHERE buy_product_num = ? AND buy_member_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			pstmt.setInt(2, member_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isSuccess = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public boolean getLike(int product_num, int member_num) {
boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM like_tb WHERE like_product_num = ? AND like_member_num = ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			pstmt.setInt(2, member_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public int likeUp(int product_num, int member_num) {
ProductVO product = null;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET "
				+ "product_likecount = product_likecount+1 "
				+ "WHERE product_num= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			pstmt.executeUpdate();
		
			sql ="SELECT product_likecount FROM product_tb WHERE product_num =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_likecount(rs.getInt("product_likecount"));
			}
			
			sql = "INSERT INTO like_tb VALUES (?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.setInt(2, product_num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return product.getProduct_likecount();
	}

	@Override
	public int unLikeUp(int product_num, int member_num) {
ProductVO product = null;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "UPDATE product_tb SET "
				+ "product_likecount = product_likecount-1 "
				+ "WHERE product_num= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			pstmt.executeUpdate();
		
			sql ="SELECT product_likecount FROM product_tb WHERE product_num =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,product_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_likecount(rs.getInt("product_likecount"));
			}
			
			sql = "DELETE FROM like_tb WHERE like_member_num = ? AND like_product_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,member_num);
			pstmt.setInt(2,product_num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return product.getProduct_likecount();
	}

	@Override
	public boolean buyProduct(int product_num, int member_num) {
boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM buy_tb WHERE buy_product_num = ? AND buy_member_num = ?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			pstmt.setInt(2, member_num);
			rs = pstmt.executeQuery();
			
			System.out.println("rs.next : "+rs.next());
			
			if(rs.next()) {
				return isSuccess;
			}else {
				sql = "INSERT INTO buy_tb VALUES (null,?,?,now())";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, product_num);
				pstmt.setInt(2, member_num);
				pstmt.executeUpdate();
				
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public void photoLikeTb(int product_num, int member_num) {
		conn = DBCPUtil.getConnection();
		
		String sql = "INSERT INTO like_tb VALUES (?,?,N)";
		
	}

	@Override
	public ProductVO getProductImage(int product_num) {
ProductVO product = null;
		
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM product_tb WHERE product_member_num = ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product = new ProductVO();
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_title(rs.getString("product_title"));
				product.setProduct_size(rs.getString("product_size"));
				product.setProduct_origin_file(rs.getString("product_origin_file"));
				product.setProduct_file(rs.getString("product_file"));
				product.setProduct_credit(rs.getInt("product_credit"));
				product.setProduct_likecount(rs.getInt("product_likecount"));
				product.setProduct_readcount(rs.getInt("product_readcount"));
				product.setProduct_member_num(rs.getInt("product_member_num"));
				product.setProduct_free(rs.getString("product_free"));
				product.setProduct_free_time(rs.getTimestamp("product_free_time"));
				product.setProduct_date(rs.getTimestamp("product_date"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	
}
