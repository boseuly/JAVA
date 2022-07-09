package dept.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/mod")
public class DeptModController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DeptService service = new DeptService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		
		DeptDTO data = service.getId(id);
		
		request.setAttribute("data",data);
		
		
		String view = "/WEB-INF/jsp/dept/mod.jsp"; // jsp 파일 경로
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locationId = request.getParameter("locationId");
		
		DeptDTO data = new DeptDTO();
		data.setDeptId(Integer.parseInt(deptId));
		data.setDeptName(deptName);
		data.setMngId(Integer.parseInt(mngId));
		data.setLocationId(Integer.parseInt(locationId));
		
		// 수정 할 데이터를 찾기 위한 조건  
		int result = service.modifyDept(data);
		request.setAttribute("data",data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
		switch(result) {
			case 1: // 성공한 경우
				response.sendRedirect("/jsp01/depts?search=" + data.getDeptId());
				break;
			case 0:
				request.setAttribute("errorMsg", "수정 작업 중 알 수 없는 문제가 발생하였습니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case -1:
				request.setAttribute("errorMsg", "관리자가 존재하지 않습니다.");
					request.getRequestDispatcher(view).forward(request, response);
					break;
			case -2:
				request.setAttribute("errorMsg", "해당 지역이 존재하지 않습니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
		}
	}

}
