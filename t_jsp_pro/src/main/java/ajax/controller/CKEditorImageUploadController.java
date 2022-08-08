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

import emps.model.EmpDTO;

@WebServlet("/image/upload")
@MultipartConfig
public class CKEditorImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		Part part = request.getPart("upload"); // upload라는 파라미터로 전달된 파일이 있으면 가지고 와라
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		// 만약 해당
		if(!part.getSubmittedFileName().isEmpty()) {
			String realPath = request.getServletContext().getRealPath("/static/img/board/"); // board 폴더의 진짜 위치를 저장
			part.write(realPath + part.getSubmittedFileName());
			
			sb.append(String.format("\"%s\": %d, ", "uploaded", 1));
			sb.append(String.format("\"%s\": \"%s\", ", "fileName", part.getSubmittedFileName()));
			sb.append(String.format("\"%s\": \"%s\" ", "url", "/jsp/static/img/board/" + part.getSubmittedFileName())); // 파일의 진짜 이름을 가져와서 저장해준다.
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
		
	}

}
