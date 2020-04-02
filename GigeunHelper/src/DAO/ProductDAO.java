package DAO;

import java.util.ArrayList;
import java.util.HashMap;

import vo.BuyVO;
import vo.CartVO;
import vo.MemberVO;
import vo.PageMaker;
import vo.ProductManagerVO;
import vo.ProductVO;
import vo.SearchCriteria;

public interface ProductDAO {

	// 전체 게시물 수
	int TotalCount();
	
	public ArrayList<ProductVO> getProductListMember(int product_member_num);
	
	// 전체 게시물 리스트 - 최신순
	public ArrayList<ProductVO> getProductList(PageMaker pm, String filter);
	
	// 전체 게시물 리스트 - 조회수
	public ArrayList<ProductVO> getProductListReadcount(PageMaker pm);
	
	// 전체 게시물 리스트 - 좋아요
	public ArrayList<ProductVO> getProductListLikecount(PageMaker pm);
	
	// 게시물 작성
	boolean productWrite(ProductVO product);
	
	// 물품 정보
	ProductVO getProductVO(int product_num);
	
	// 게시물 삭제
	boolean productDelete(int product_num);

	int getSeachListCount(SearchCriteria cri);

	ArrayList<ProductVO> getSearchList(PageMaker pageMaker, String filter);

	ArrayList<ProductVO> getProductListCategory(PageMaker pm, String product_category, String filter);

	int TotalCountCategory(String product_category);
	
	int CategoryCount(PageMaker pm, String product_category);
	
	ArrayList<BuyVO> buyProductList(int buy_member_num);

	ArrayList<CartVO> getProductListCart(int cart_member_num);

	boolean cartDelete(int cart_num);

	MemberVO getMemberVO(int product_member_num);

	int getListCount(int product_member_num);

	ArrayList<ProductVO> getImageList(PageMaker pageMaker, int product_member_num);

	int likeUp(int product_num);

	int unLikeUp(int product_num);

	boolean goCart(int product_num, int member_num);

	int getCreditVO(String member_id);

	MemberVO minusCredit(String member_id, int product_credit);

	int FreeCount();
	
	int UnfreeCount();
	
	ArrayList<ProductManagerVO> productListFree(PageMaker pm);
	
	ArrayList<ProductManagerVO> productListUnfree(PageMaker pm);

	boolean freeProduct(int product_num);

	boolean unfreeProduct(ProductManagerVO product);

	ArrayList<ProductVO> getProductListMain();
	
	boolean getBuyTable(int product_num, int member_num);
	
	boolean getLike(int product_num, int member_num);
	
	int likeUp(int product_num,int member_num);
	
	int unLikeUp(int product_num,int member_num);
	
	boolean buyProduct(int product_num, int member_num);
	
	void photoLikeTb(int product_num,int member_num);
	
	ProductVO getProductImage(int product_num);
}
