package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ProductService;
import service.ProductServiceImpl;

@WebServlet("*.pd")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service = new ProductServiceImpl();
		
		request.setCharacterEncoding("UTF-8");
		
		String cmd = request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
		System.out.println(cmd);
		
		String nextPage = null;
		
		Gson gson = new Gson();
		
		String json = null;
		
		if(cmd.equals("main.pd")) {
			service.productMain(request);
			nextPage="/common/main.jsp";
		}
		
		if(cmd.equals("productList.pd")) {
			nextPage = "/product/productList.jsp";
		}
		
		if(cmd.equals("productWrite.pd")) {
			nextPage = "/product/productWrite.jsp";
		}
		if(cmd.equals("productWriteSubmit.pd")) {
			System.out.println("등록 처리 요청");
			System.out.println(request.getParameter("product_member_num"));
			service.productWrite(request, response);
		}
		
		if(cmd.equals("list.pd")) {
			System.out.println("리스트 요청");
			System.out.println(request.getParameter("page"));
			System.out.println(request.getParameter("product_category"));
			System.out.println("searchValue : "+request.getParameter("searchValue"));
			json = gson.toJson(service.getList(request));
		}
		
		if(cmd.equals("myProduct.pd")) {
			System.out.println("물품관리 창 요청");
			System.out.println(request.getParameter("member_num"));
			service.myProductList(request);
			nextPage = "/product/myProduct.jsp";
		}
		
		if(cmd.equals("productDelete.pd")) {
			System.out.println("물품 삭제 요청");
			service.productDelete(request, response);
		}
		
		if(cmd.equals("productDeleteManager.pd")) {
			json = gson.toJson(service.deleteProductManager(request));
		}
		
		if(cmd.equals("productFree.pd")) {
			json = gson.toJson(service.freeProduct(request));
		}
		
		if(cmd.equals("productUnfree.pd")) {
			json = gson.toJson(service.unfreeProduct(request));
		}
		
		if(cmd.equals("fileDown.pd")) {
			service.fileDown(request, response);
		}
		
		if(cmd.equals("cart.pd")) {
			service.cartList(request);
			nextPage = "product/cart.jsp";
		}
		
		if(cmd.equals("cartDelete.pd")) {
			service.cartDelete(request,response);
		}
		
		if(cmd.equals("productManagerPage.pd")) {
			nextPage="product/productManagement.jsp";
		}
		
		if(cmd.equals("productManager.pd")) {
			json = gson.toJson(service.productManager(request));
		}

		if(cmd.equals("productFreePage.pd")) {
			service.getProductFree(request);
		}
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
		if(json != null) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
