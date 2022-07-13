package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.service.DeptService;

@WebServlet("/dept/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 추가하려는 Dept id 가 이미 존재하는지 확인해야 한다. // 제약조건 확인 -> deptId primary key 확인
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		int result = service.insertDept(deptId, deptName, mngId, locId);
		String view = "/WEB-INF/jsp/error/error.jsp";
		switch(result) {
		case -3:	// deptId 기본키 위배	-> error 페이지
			request.setAttribute("errorMsg", "해당 부서 ID는 이미 존재합니다.");
			break;
		case -2:	// mngId 외래키 위배	-> error 페이지
			request.setAttribute("errorMsg", "해당 관리자 ID는 존재하지 않습니다.");
			break;
		case -1:	// locId 외래키 위배	-> error 페이지
			request.setAttribute("errorMsg", "해당 지역 ID는 존재하지 않습니다.");
			break;
		case 0: 	// 알 수 없는 오류		-> error 페이지
			request.setAttribute("errorMsg", "부서 추가 작업 중 알 수 없는 오류가 발생했습니다.");
			break;
		case 1:		// 저장 성공			-> 추가된 페이지 보여주기
			response.sendRedirect(request.getContextPath() + "/dept?id=" + Integer.parseInt(deptId));
			return;
		}
	
		request.getRequestDispatcher(view).forward(request, response);
	}

}
