

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.test")
public class UploadTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		
		if(command.equals("upload.test")) {
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		
		if(command.equals("uploadFile.test")) {
			
			System.out.println("파일 업로드 요청");
			
			String test = request.getParameter("test");
			String uploadFile = request.getParameter("uploadFile");
			
			System.out.println("test : " + test);
			System.out.println("uploadFile : " + uploadFile);
			
			/*
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(request.getInputStream())
			);
			String str = "";
			while((str = reader.readLine()) != null) {
				System.out.println(str);
			}
			*/
			
			String saveDir = "/upload";
			String realPath 
			= request.getSession().getServletContext().getRealPath(saveDir);
			System.out.println(realPath);
			
			File file = new File(realPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath,
					1024*1024*10,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			Enumeration<String> fileNames = multi.getFileNames();
			
			ArrayList<String> list = new ArrayList<>(); 
			
			while(fileNames.hasMoreElements()) {
				String name = fileNames.nextElement();
				System.out.println("file name : " + name);
				String fileName = multi.getFilesystemName(name);
				System.out.println("upload 된 파일 이름 : " + fileName);
				list.add(fileName);
				String origin = multi.getOriginalFileName(name);
				System.out.println("원본 파일 이름 : " + origin);
			}
			
			String testText = multi.getParameter("test");
			System.out.println("input type='text' : " + testText);
			
			request.setAttribute("fileList", list);
			
			request.getRequestDispatcher("/download.jsp").forward(request, response);
			
			
		}
		
		if(command.equals("fileDown.test")) {
			String file_name = request.getParameter("file_name");
			System.out.println(file_name);
			String realPath = request.getServletContext().getRealPath("/upload");
			System.out.println(realPath);
			String filePath = realPath+File.separator+file_name;
			//String filePath = realPath+"/"+file_name;
			System.out.println(filePath);
			
			String mimeType = request.getServletContext().getMimeType(filePath);
			System.out.println(mimeType);
			
			if(mimeType == null) {
				// 8비트 바이너리 배열을 의미
				mimeType="application/octet-stream";
			}
			
			response.setContentType(mimeType);
			
			
			String agent = request.getHeader("User-Agent");
			System.out.println("요청을 보낸 사용자 브라우저 정보확인 : "+agent);
			
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
