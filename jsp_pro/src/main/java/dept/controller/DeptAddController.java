package dept.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
	private String view = "/WEB-INF/jsp/depts/add.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		// 해당 정보 저장 
		DeptDTO deptData = service.deptAdd(deptId, deptName, mngId, locId);
		RequestDispatcher rd = null;
		
		if(deptData != null) {
			if(deptData.getDeptId() != -1 && deptData.getMngId() != -1 && deptData.getLocId() != -1) {
				// 만약 저장에 성공했다면 리다이렉트
				response.sendRedirect(request.getContextPath() + "/depts?search=" + deptData.getDeptId());
				return;
			}else { // 오류 페이지 -> 알 수 없는 오류 발생
				Map<String, String> error = new HashMap<String, String>();
				
				if(deptData.getDeptId() == -1) {
					error.put("deptId", "동일한 부서 ID가 존재합니다.");
				}
				if(deptData.getMngId() == -1) {
					error.put("mngId", "관리자ID 정보가 존재하지 않습니다.");
				}
				if(deptData.getLocId() == -1) {
					error.put("locId", "지역ID 정보가 존재하지 않습니다.");
				}
				request.setAttribute("error", error);
				
				rd = request.getRequestDispatcher(view + "dept/add.jsp");
			}
		}else {	// 만약 추가에 실패했다면 -> 해당 정보를 다시 돌려줘야 한다.
			rd = request.getRequestDispatcher(view + "dept/add"); // 
		}
		rd.forward(request, response);
	/*
		if(deptData.getDeptId() == -1) { // 숫자 오류
			request.setAttribute("error","numberError");
			request.setAttribute("errorMsg", "숫자를 입력하세요.");
		}else if(deptData.getMngId() == -1) {	// mng 제약조건 오류 
			request.setAttribute("error","mngError");
			request.setAttribute("errorMsg", "해당 관리자 ID는 존재하지 않습니다.");
		}else if(deptData.getLocId() == -1) {	// loc 제약조건 오류 
			request.setAttribute("error","error");
			request.setAttribute("errorMsg", "지역 추가 작업 중 알 수 없는 오류가 발생하였습니다.");
		}else {
			// 추가 성공
			response.sendRedirect(request.getContextPath() + "/depts");
		}
		
	*/
	}

}
