package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.ProductDAO;
import DAO.ProductDAOImpl;
import vo.BuyVO;
import vo.CartVO;
import vo.Criteria;
import vo.PageMaker;
import vo.ProductManagerVO;
import vo.ProductVO;
import vo.SearchCriteria;
import vo.SearchVO;

public class ProductServiceImpl implements ProductService{

	ProductDAO dao = new ProductDAOImpl();

	@Override
	public void productMain(HttpServletRequest request) {
		
		ArrayList<ProductVO> list = dao.getProductListMain();
		request.setAttribute("list", list);
	}

	@Override
	public HashMap<String, Object> productManager(HttpServletRequest request) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int listCountFree = 0;
		int listCountUnfree = 0;
		
		int page = 1;
		int pageUF = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("pageUF") != null) {
			pageUF = Integer.parseInt(request.getParameter("pageUF"));
		}
		
		listCountFree = dao.FreeCount();
		listCountUnfree = dao.UnfreeCount();
		Criteria cri = new Criteria(page,5);
		Criteria criUF = new Criteria(pageUF,5);
		PageMaker pmFree = new PageMaker();
		PageMaker pmUnfree = new PageMaker();
		
		pmFree.setCri(cri);
		pmFree.setTotalCount(listCountFree);
		pmUnfree.setCri(criUF);
		pmUnfree.setTotalCount(listCountUnfree);
		
		System.out.println("페이지 : "+pmUnfree.getCri().getPage());
		
		ArrayList<ProductManagerVO> listFree = dao.productListFree(pmFree);
		ArrayList<ProductManagerVO> listUnfree = dao.productListUnfree(pmUnfree);
		
		map.put("listFree", listFree);
		map.put("pmFree", pmFree);
		map.put("listUnfree", listUnfree);
		map.put("pmUnfree", pmUnfree);
		
		return map;
	}

	@Override
	public HashMap<String, Object> getList(HttpServletRequest request) {
		System.out.println("리스트 목록 요청");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String product_category = request.getParameter("product_category");
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		String filter = request.getParameter("filter");
		
		System.out.println("필터 : "+filter);
		
		System.out.println("카테고리:" + product_category);
		System.out.println(searchName);
		System.out.println(searchValue);
		
		int listCount = 0;
		int page = 1;
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("searchName : "+searchName);
		
		SearchCriteria sc = new SearchCriteria(page, 5, searchName, searchValue);
		PageMaker pm = new PageMaker();
		SearchVO search = new SearchVO();
		
		if(searchName == null || searchName == "" || searchValue == "" || searchValue == null){
			search.setSearchName("null");
			search.setSearchValue("null");
			if(product_category == null || product_category.equals("")) {
				listCount = dao.TotalCount();
				pm.setCri(sc);
				pm.setTotalCount(listCount);
				list = dao.getProductList(pm, filter);
			}else {
				listCount = dao.TotalCountCategory(product_category);
				System.out.println("listCount:"+listCount);
				pm.setCri(sc);
				pm.setTotalCount(listCount);
				list = dao.getProductListCategory(pm, product_category, filter);
			}
		}else {
			listCount = dao.getSeachListCount(sc);
			pm.setCri(sc);
			pm.setTotalCount(listCount);
			list=dao.getSearchList(pm, filter);
			search.setSearchName(searchName);
			search.setSearchValue(searchValue);
		}
		
		int categoryCount = dao.CategoryCount(pm, product_category);
		System.out.println("pm :"+ pm);
		System.out.println(list);
		System.out.println(search);

		map.put("filter", filter);
		map.put("search", search);
		map.put("categoryCount", categoryCount);
		map.put("list", list);
		map.put("pm", pm);
		
		return map;
	}
	
	@Override
	public void getProductFree(HttpServletRequest request) {
		/*
		 * int product_num = Integer.parseInt(request.getParameter("product_num"));
		 * ProductVO product = dao.getProductVO(product_num); request.setAttribute("",
		 * o);
		 */
	}

	@Override
	public void productWrite(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("작성 처리");
		String saveDir="/upload";
		
		try {
			String realPath = request.getServletContext().getRealPath(saveDir);
			System.out.println("realPath : "+realPath);
			
			MultipartRequest multi = new MultipartRequest(request, realPath,1024*1024*30,"utf-8",new DefaultFileRenamePolicy());
			ProductVO product = new ProductVO();
			int product_member_num = Integer.parseInt(multi.getParameter("product_member_num"));
			String product_title = multi.getParameter("product_title");
			String product_category = multi.getParameter("product_category");
			int product_credit = Integer.parseInt(multi.getParameter("product_credit"));
			String product_size = multi.getParameter("product_size");
			String file = (String)multi.getFileNames().nextElement();
			String product_file = multi.getFilesystemName(file);
			String product_origin_file = multi.getOriginalFileName(file);
			
			product.setProduct_member_num(product_member_num);
			product.setProduct_title(product_title);
			product.setProduct_category(product_category);
			product.setProduct_size(product_size);
			product.setProduct_credit(product_credit);
			product.setProduct_file(product_file);
			product.setProduct_origin_file(product_origin_file);
			
			boolean isSuccess = dao.productWrite(product);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(isSuccess) {
				out.print(getScript("판매등록 완료", "productList.pd"));
			}else {
				out.print("<script>");
				out.print("alert('판매등록 실패');");
				out.print("history.go(-1);");
				out.print("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void myProductList(HttpServletRequest request) {
		ArrayList<BuyVO> buyList = dao.buyProductList(Integer.parseInt(request.getParameter("member_num")));
		ArrayList<ProductVO> productList = dao.getProductListMember(Integer.parseInt(request.getParameter("member_num")));
		request.setAttribute("buyList", buyList);
		request.setAttribute("productList", productList);
	}


	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		String realPath = request.getServletContext().getRealPath("/upload");
		System.out.println(realPath);
		String file_name = request.getParameter("file_name");
		System.out.println(file_name);
		String filePath = realPath+"/"+file_name;
		System.out.println(filePath);
		String mimeType = request.getServletContext().getMimeType(filePath);
		System.out.println(mimeType);
		
		if(mimeType == null) mimeType="application/octet-stream";
		response.setContentType(mimeType);
		try {
			String agent = request.getHeader("User-Agent");
			
			if(agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1) {
				file_name = URLEncoder.encode(file_name,"UTF-8").replaceAll("\\+", "%20");
			}else {
				file_name = new String(file_name.getBytes("utf-8"),"iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment;fileName="+file_name);
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			OutputStream os = response.getOutputStream();
			
			byte[] bytes = new byte[4096];
			int numRead = 0;
			while((numRead = fis.read(bytes,0,bytes.length)) != -1) {
				os.write(bytes,0,numRead);
			}
			os.flush();
			os.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void cartList(HttpServletRequest request) {
		
		int cart_member_num = Integer.parseInt(request.getParameter("member_num"));
		ArrayList<CartVO> list = dao.getProductListCart(cart_member_num);
		System.out.println(list);
		request.setAttribute("list", list);
	}

	@Override
	public void cartDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		boolean isSuccess = dao.cartDelete(cart_num);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(isSuccess) {
			out.print(getScript("삭제 성공","cart.pd?member_num="+member_num));
		}else {
			out.print(getScript("삭제 실패","cart.pd?member_num="+member_num));
		}
		
	}

	@Override
	public void productDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		boolean isSuccess = dao.productDelete(product_num);
		
		response.setContentType("text/html;charset=utf-8");
		System.out.println(response.getContentType());
		PrintWriter out = response.getWriter();
		if(isSuccess) {
			out.print(getScript("삭제 성공","myProduct.pd?member_num="+member_num));
		}else {
			out.print(getScript("삭제 실패","myProduct.pd?member_num="+member_num));
		}
	}
	
	@Override
	public boolean freeProduct(HttpServletRequest request) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		return dao.freeProduct(product_num);
	}

	@Override
	public boolean unfreeProduct(HttpServletRequest request) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int product_credit = Integer.parseInt(request.getParameter("product_credit"));
		ProductManagerVO product = new ProductManagerVO();
		product.setProduct_num(product_num);
		product.setProduct_credit(product_credit);
		return dao.unfreeProduct(product);
	}

	@Override
	public boolean deleteProductManager(HttpServletRequest request) {
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		return dao.productDelete(product_num);
	}

	public String getScript(String notice,String location) {
		String result = "";
		result +="<script>";
		result +="alert('"+notice+"');";
		result +="location.href='"+location+"';";
		result +="</script>";
		System.out.println(result);
		return result;
	}
}
