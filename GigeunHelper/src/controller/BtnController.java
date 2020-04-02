package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BuyService;
import service.BuyServiceImpl;
import vo.ProductVO;


@WebServlet("*.ph")
public class BtnController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	BuyService buyService = new BuyServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String command 
		= request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println("command : " + command);
		
		String nextPage = null;
		
		if(command.equals("photo_project.ph")) {
			System.out.println("photo_project");
			nextPage = "BuyDetail/photo_project.jsp";
		}
		
		if(command.equals("photo_upload.ph")) {
			System.out.println("photo_upload 요청");
			nextPage = "BuyDetail/photo_upload.jsp";
		}
		
		if(command.equals("photo_minus_credit.ph")) {
			System.out.println("photo_minus_credit요청");
			buyService.minusCredit(request,response);
		}
		
		if(command.equals("photo_like_tb.ph")) {
			System.out.println("photo_like_tb 요청");
			buyService.photoLikeTb(request);
		}
		
		
		if(command.equals("photo_like.ph")) {
			System.out.println("photo_like 요청");
//			System.out.println("request product_num : "+request.getParameter("product_num"));
			buyService.likeUp(request,response);
			//nextPage = "BuyDetail/photo_upload.jsp";
		}
		
		if(command.equals("photo_unlike.ph")) {
			System.out.println("photo_like 요청");
			buyService.unLikeUp(request,response);
		}
		
		if(command.equals("photo_cart.ph")) {
			System.out.println("photo_cart 요청");
			buyService.goCart(request,response);
		}
		
		if(command.equals("photo_getProductImage.ph")) {
			System.out.println("photo_getMemberImage 요청");
			ArrayList<ProductVO> list = buyService.allGetImageList(request);
			request.setAttribute("allImageList", list);
			buyService.photoMember(request);
			nextPage = "BuyDetail/photo_getProductImage.jsp";
		}
		
		if(command.equals("photo_detail.ph")) {
			System.out.println("photo_detail 요청");
			ArrayList<ProductVO> list = buyService.getImageList(request);
			request.setAttribute("imageList", list);
			buyService.productDetail(request,response);
			nextPage = "BuyDetail/photo_detail.jsp";
		}
		
		if(command.equals("photo_down.ph")) {
			
			System.out.println("photo_down 요청");
			String file_name = request.getParameter("file_name");
			System.out.println(file_name);
			String realPath = request.getServletContext().getRealPath("/upload");
			System.out.println(realPath);
			String filePath = realPath+File.separator+file_name;
			//String filePath = realPath+"/"+file_name;
			System.out.println(filePath);
			
			String mimeType = request.getServletContext().getMimeType(filePath);
			System.out.println(mimeType);
			
			//mime Type에 지정된 포맷이 존재하지 않을때
			if(mimeType == null) {
				mimeType="application/octet-stream";
			}
			
			response.setContentType(mimeType);
			
			
			String agent = request.getHeader("User-Agent");
			
			boolean isBrowser = (agent.indexOf("MSIE") > -1 || agent.indexOf("Trident")> -1);
			
			if(isBrowser) {
				file_name = URLEncoder.encode(file_name,"UTF-8").replaceAll("\\+", "%20");
			}else {
				file_name = new String(file_name.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			
			System.out.println(file_name);
			
			response.setHeader("Content-Disposition", "attachment;fileName="+file_name);
			
			//response.setHeader("Content-Disposition", "inline");
			
			FileInputStream fis = new FileInputStream(filePath);
			OutputStream out = response.getOutputStream();
			int numRead=0;
			
			byte[] bytes = new byte[4096];
			while((numRead = fis.read(bytes,0,bytes.length)) != -1) {
				out.write(bytes,0,numRead);
			}
			out.flush();
			out.close();
			fis.close();
		}
		
		if(nextPage != null ){
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
