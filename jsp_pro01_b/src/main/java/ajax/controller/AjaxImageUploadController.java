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
@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class AjaxImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		Part part = request.getPart("uploadImage"); // multipart
		response.setContentType("");
		if(!part.getSubmittedFileName().isEmpty()) {	// 업로드한 파일이 없으면 
			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); 
//			part.write(realPath + empData.getEmpId() + ".png"); // 해당 경로 + 파일이름으로 저장해줘라
			
			// 간단해서 그냥 직접 만들어준 거임
			out.println("{");	// 이거 역슬래쉬 만들어 주기 귀찮으면 메모장에서 작성하고 복붙하면 된다.
			out.println("	\"msg\": \"저장 성공\", ");
			out.println("	\"loc\": \"/static/img/emp/"+ empData.getEmpId() + ".png\"");
			out.println("}");	// 속성명 : 속성값 
				
		}else {
			out.println("{");
			out.println("	\"msg\": \"저장 실패\", ");
			out.println("	\"loc\": \"/static/img/emp/default.png\"");
			out.println("}");
		}
		out.flush();// 사용자에게 데이터를 전달 
	}
}
