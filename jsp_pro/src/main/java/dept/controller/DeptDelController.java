package dept.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		String deptId = request.getParameter("deptId");
		
		DeptDTO data = service.getId(deptId);
		request.setAttribute("deptData", data);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/depts/del.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		
		boolean result = service.deptDel(deptId);
		
		if(result) { // 삭제 됨
			response.sendRedirect(request.getContextPath() + "/depts");
		}else { // 삭제 안 됨
			request.setAttribute("errorMsg","부서 정보 삭제 작업 중 오류가 발생하였습니다." );
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp");
			rd.forward(request, response);
		}
	}

}
