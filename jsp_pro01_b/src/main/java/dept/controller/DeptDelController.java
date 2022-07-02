package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/del")
public class DeptDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		
		DeptDTO data = service.getId(id); // id로 데이터를 먼저 조회하도록 하고
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/del.jsp"; // 삭제할 데이터가 null이 아니면 그대로 del.jsp로 이동(전달)
		if(data == null) {
			view = "/WEB-INF/jsp/dept/del_no.jsp"; // 삭제할 데이터가 null이라면 del_no.jsp페이지로 이동
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId"); // get에서 하던 작업을 post에서 하기 -> post로 정보가 들어오면 삭제할 데이터를
		int result = service.deleteDept(deptId); // 오류를 세부적으로 나누고 싶다면 반환타입을 정수타입으로 해서 어떤 오류인지 알려준다.
		
		String view = "/WEB-INF/jsp/dept/del_error.jsp";
		
		switch(result) {
		case 1 : 
			response.sendRedirect(request.getContextPath() +"/depts"); // 만약 오류가 없이 성공한다면 
			return;
		case 0 :
			request.setAttribute("error", true);
			request.setAttribute("errorMsg", "처리 중 문제 발생");
			break;
		case -1 : 
			request.setAttribute("error", true);
			request.setAttribute("errorMsg", "삭제할 데이터가 존재하지 않습니다.");
			break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

}
