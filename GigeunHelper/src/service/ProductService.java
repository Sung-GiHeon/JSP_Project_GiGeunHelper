package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;

import vo.ProductVO;

public interface ProductService {
	
	// 전체 목록 - 작성일순
	HashMap<String, Object> getList(HttpServletRequest request);
	
	// 게시물 작성
	void productWrite(HttpServletRequest request, HttpServletResponse response);
	
	// 파일 다운
	void fileDown(HttpServletRequest request, HttpServletResponse response);
	
	void productDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

	void myProductList(HttpServletRequest request);

	void cartList(HttpServletRequest request);

	void cartDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;

	HashMap<String, Object> productManager(HttpServletRequest request);

	boolean deleteProductManager(HttpServletRequest request);

	boolean freeProduct(HttpServletRequest request);

	boolean unfreeProduct(HttpServletRequest request);

	void productMain(HttpServletRequest request);

	void getProductFree(HttpServletRequest request);
}
