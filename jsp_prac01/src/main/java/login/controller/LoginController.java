package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.service.LoginService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");	
		String deptId = request.getParameter("deptId");
		String empName = request.getParameter("empName");
		
		LoginService service = new LoginService(); 		// 만약 employee 테이블에 해당 직원이 존재한다면 Session에 저장하여 로그인 시킨다.
		boolean result = service.login(request.getSession(), empId, deptId, empName);	// employee테이블에서 해당 데이터를 가진 직원이 있는지 확인
		
		if(result) {
			// 로그인 성공
		}else {
			// 로그인 실패
		}
		
	}

}
