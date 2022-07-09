package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/dept/mod")
public class DeptModController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DeptService service = new DeptService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("id");
		
		DeptDTO data = service.getId(deptId);	// 해당 정보를 전달하기 위해서 
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");	// deptId는 수정 불가
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		// 받은 데이터로 검사를 해야 한다. -> 어디서 잘못됐는지 오류 메시지를 보내야 한다. 
		int result = service.deptMod(deptId, deptName, mngId, locId);
		
		String view = "/WEB-INF/jsp/error/error.jsp";
		if(result == -3) {	// 숫자가 아닌 경우
			request.setAttribute("errorMsg", "입력한 값의 타입이 올바르지 않습니다." );
			request.getRequestDispatcher(view).forward(request, response);
		}else if(result == -2){
			request.setAttribute("errorMsg", "해당 지역 ID는 존재하지 않습니다.");
			request.getRequestDispatcher(view).forward(request, response);
		}else if(result == -1) {
			request.setAttribute("errorMsg", "해당 매니저 ID는 존재하지 않습니다.");
			request.getRequestDispatcher(view).forward(request, response);
		}else if(result == 0) {
			request.setAttribute("errorMsg", "수정 작업 중 알 수 없는 오류가 발생하였습니다.");
			request.getRequestDispatcher(view).forward(request, response);
		}else if(result == 1) {
			response.sendRedirect(request.getContextPath() + "/dept?search=" + deptId);	// 수정된 정보를 보여주기
		}
		
	}

}
