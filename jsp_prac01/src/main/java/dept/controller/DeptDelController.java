package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/dept/del")
public class DeptDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("id");
		DeptDTO data = service.getId(deptId);
		
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/del.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		
		boolean result = service.deptDel(deptId, deptName, mngId, locId);
		if(result) {	// 삭제을 못 한 경우
			response.sendRedirect(request.getContextPath() + "/dept");
		}else {
			String view = "/WEB-INF/jsp/error/error.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

}
