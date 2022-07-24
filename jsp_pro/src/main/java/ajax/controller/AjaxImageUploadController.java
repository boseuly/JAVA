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

@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class AjaxImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		Part part = request.getPart("uploadImage"); // 파일 가지고 오기
		response.setContentType("application/json; charset=utf-8");
		
		if(!part.getSubmittedFileName().isEmpty()) { // 파일이 존재한다면
			String realPath = request.getServletContext().getRealPath("/static/img/emp/");
			part.write(realPath + empData.getEmpId() + ".png");
			
			out.println("{");
			out.println("    \"msg\": \"저장 성공\",");
			out.println("    \"loc\": \"/static/img/emp/" + empData.getEmpId() + ".png\"");
			out.println("}");
		}else {	// 파일이 존재하지 않다면
			out.println("{");
			out.println(" \"msg\": \"저장 실패\",");
			out.println(" \"loc\": \"/static/img/emp/default.png\"");
			out.println("}");
		}
		out.flush();
	}
}
