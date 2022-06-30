package dept.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		DeptDTO data = service.addDept(deptId, deptName, mngId, locId);
		
		String view = "/WEB-INF/jsp/dept/add.jsp";
		if(data != null) {
			if(data.getDeptId() == -1) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "부서코드 중복!");
				request.getRequestDispatcher(view).forward(request, response);
			} else if(data.getMngId() == -1) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 관리자 ID 없음");
				request.getRequestDispatcher(view).forward(request, response);
			} else if(data.getLocId() == -1) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 지역 ID 없음");
				request.getRequestDispatcher(view).forward(request, response);
			} else {
				// 저장 성공 후 리다이렉트를 사용하여 페이지를 이동하게 함
				response.sendRedirect("/jsp01/depts?search=" + data.getDeptId());
			}
		} else {
			// 저장 실패 후 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

}
