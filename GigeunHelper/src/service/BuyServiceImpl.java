package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import vo.Criteria;
import vo.MemberVO;
import vo.PageMaker;
import vo.ProductVO;

public class BuyServiceImpl implements BuyService{
	
	ProductDAO dao = new ProductDAOImpl();
	
	@Override
	public void productDetail(HttpServletRequest request,HttpServletResponse response) {
		boolean isSuccess = false;
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = 0;
		System.out.println("member_num : "+request.getParameter("member_num"));
		
		if(request.getParameter("member_num").equals("")) {
			member_num = 0;
		}else {
			member_num = Integer.parseInt(request.getParameter("member_num"));
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("buyProduct", dao.getBuyTable(product_num,member_num));
		
		System.out.println("product_num: "+product_num);
		ProductVO product = dao.getProductVO(product_num);
		isSuccess = dao.getLike(product_num,member_num);
		
		System.out.println("isSuccess : "+isSuccess);
		request.setAttribute("likeCheck",isSuccess);
		request.setAttribute("product", product);
		request.setAttribute("photoMember", dao.getMemberVO(product.getProduct_member_num()));
	}
	
	@Override
	public void photoMember(HttpServletRequest request) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		ProductVO product = dao.getProductVO(product_num);
		request.setAttribute("product", product);
		request.setAttribute("allPhoto", dao.getMemberVO(product.getProduct_member_num()));
	}

	@Override
	public ArrayList<ProductVO> getImageList(HttpServletRequest request) {
		ProductVO product = null;
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		product = dao.getProductVO(product_num);
		System.out.println("product_getProduct_member_num : "+product.getProduct_member_num());
		int listCount = dao.getListCount(product.getProduct_member_num());
		
		System.out.println("listCount : "+listCount);
		
		Criteria cri = new Criteria(page,6);
		PageMaker pageMaker  = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		request.setAttribute("pageMaker", pageMaker);
		
		return dao.getImageList(pageMaker,product.getProduct_member_num());
	}
	
	@Override
	public ArrayList<ProductVO> allGetImageList(HttpServletRequest request) {
		ProductVO product = null;
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		product = dao.getProductVO(product_num);
		System.out.println("product_getProduct_member_num : "+product.getProduct_member_num());
		int listCount = dao.getListCount(product.getProduct_member_num());
		
		System.out.println("listCount : "+listCount);
		
		Criteria cri = new Criteria(page,15);
		PageMaker pageMaker  = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		request.setAttribute("getPageMaker", pageMaker);
		
		return dao.getImageList(pageMaker,product.getProduct_member_num());
	}

	@Override
	public void likeUp(HttpServletRequest request,HttpServletResponse response) {
		
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		
		//파일 입출력 처리에는 예외 처리를 해주어야한다.
		//response.getWriter().print(json); == ajax쪽으로 입출력을 보내 주겠다.
		try {
			response.setContentType("application/json;charset=utf-8");
			String json = "{\"product_num\":\""+dao.likeUp(product_num,member_num)+"\"}";
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unLikeUp(HttpServletRequest request, HttpServletResponse response) {
		
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		
		try {
			response.setContentType("application/json;charset=utf-8");
			String json = "{\"product_num\":\""+dao.unLikeUp(product_num,member_num)+"\"}";
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void minusCredit(HttpServletRequest request,HttpServletResponse response) {
		MemberVO member = null;
		boolean isSuccess = false;
		String member_id = request.getParameter("member_id");
		int product_credit = Integer.parseInt(request.getParameter("product_credit"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		
		System.out.println("product_num : "+product_num);
		System.out.println("member_num : "+member_num);
		
		member = dao.minusCredit(member_id,product_credit);
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		try {
			response.setContentType("application/json;charset=utf-8");
			String json = "{\"credit\":\""+dao.getCreditVO(member_id)+"\",\"isSuccess\":\""+dao.buyProduct(product_num,member_num)+"\"}";
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		

	@Override
	public void goCart(HttpServletRequest request,HttpServletResponse response) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		System.out.println("product_num :"+product_num);
		System.out.println("member_num :"+member_num);
		try {
			response.setContentType("application/json;charset=utf-8");
			String json = "{\"isSuccess\":\""+dao.goCart(product_num,member_num)+"\"}";
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void photoLikeTb(HttpServletRequest request) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		dao.photoLikeTb(product_num,member_num);
	}



	@Override
	public void getProductImage(HttpServletRequest request) {
		ProductVO product= null;
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		product = dao.getProductImage(product_num);
		
	}
}
