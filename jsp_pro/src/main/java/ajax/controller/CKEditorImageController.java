package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emp.model.EmpDTO;

@WebServlet("/image/upload")
@MultipartConfig
public class CKEditorImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		Part part = request.getPart("upload"); // 파일 가지고 오기 // ckeditor에서 form-data를 보면 upload라는 이름으로 이미지를 전달하고 있음 
		response.setContentType("application/json; charset=utf-8");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		if(!part.getSubmittedFileName().isEmpty()) { // 파일이 존재한다면
			String realPath = request.getServletContext().getRealPath("/static/img/board/");
			part.write(realPath + part.getSubmittedFileName()); // 업로드한 이미지 이름 그대로 나오도록 함
			
			sb.append(String.format("\"%s\": %d","uploaded",1));// ck가 요구하는 응답 사항이 다르다. ck는 응답 양식이 msg, loc가 아니다.// ckeditor 4 - guides에서 양식을 찾아봐야 한다. 
			sb.append(String.format("\"%s\": \"%s\", ", "fileName", part.getSubmittedFileName()));
			sb.append(String.format("\"%s\": \"%s\" ","url", "/static/img/board" + part.getSubmittedFileName())); // 어디에 업로드가 됐는지 알려주기 위함
		}else {	// 파일이 존재하지 않다면
			sb.append(String.format("\"%d\": \"%s\" ", "fail")); 
		}
		sb.append("}"); 
		out.flush();
	}

}
