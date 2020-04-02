package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ProductVO;

public interface BuyService {
	
	void productDetail (HttpServletRequest request, HttpServletResponse response);

	ArrayList<ProductVO> getImageList(HttpServletRequest request);

	void likeUp(HttpServletRequest request, HttpServletResponse response);

	void unLikeUp(HttpServletRequest request, HttpServletResponse response);

	void minusCredit(HttpServletRequest request, HttpServletResponse response);

	void goCart(HttpServletRequest request, HttpServletResponse response);

	void photoLikeTb(HttpServletRequest request);

	void getProductImage(HttpServletRequest request);

	ArrayList<ProductVO> allGetImageList(HttpServletRequest request);

	void photoMember(HttpServletRequest request);

}
