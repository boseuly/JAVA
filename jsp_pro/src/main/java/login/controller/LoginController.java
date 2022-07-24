package login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import login.service.LoginService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService deptService = new DeptService();
	private String view = "/WEB-INF/jsp/login/login.jsp"; // 로그인 하면 로그인 성공 화면으로 이동 -> 첫화면임
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제공해줘야 할 것 -> deptName, deptId -> 이건 deptDTO 이용
		HttpSession session = request.getSession();	// 이전에 이미 로그인 한 사람일 수도 있으니까, 그리고 로그인 성공하면 session에 저장해줘야 한다. 
		RequestDispatcher rd = null;
		if(session.getAttribute("loginData") == null) { 	// 로그인을 한 사람이 아니고, 처음 로그인을 시도하는 사람인 경우
			List<DeptDTO> deptList = deptService.getAll(); 	// dept의 모든 객체를 가져온다. -> deptName, deptId 리스트 보여주기 위해
			request.setAttribute("deptList", deptList);		
			rd = request.getRequestDispatcher(view);		
		}else {												// 이미 로그인에 성공한 사람
			rd = request.getRequestDispatcher("/WEB-INF/jsp/login/logOk.jsp");	// 로그인 성공 페이지로 보내버리기
		}
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String deptId = request.getParameter("deptId");
		String empName = request.getParameter("empName");
		String deptRe = request.getParameter("deptRe");	// 부서 기억 체크한 경우
		
		LoginService loginService = new LoginService(); 
		boolean result = loginService.login(request.getSession(), empId, deptId, empName); // service 에서 session에 저장시켜줌
		
		if(result) {
			// 로그인 성공
			if(deptRe != null) { // 만약 deptRe가 체크되어 있다면 -> 새로운 쿠키에 해당 deptId를 deptRe라는 키명으로 저장한다.
				Cookie cookie = new Cookie("deptRe", String.valueOf(deptId)); 
				cookie.setMaxAge(60 * 60 * 24 * 5); 	// 쿠키 유효기간 설정
				response.addCookie(cookie); 			// 쿠키 저장
			}else {				// 만약 deptRe가 체크되어 있지 않다면 -> 새로운 쿠키에 빈문자열을 저장한다.
				Cookie cookie = new Cookie("deptRe", "");	// 근데 왜 굳이 쿠키를 만들어서 빈문자열을 넣어주는 건지 모르겠음(jsp 삼항연산에서 .value로 비교해서 그런가?)
				cookie.setMaxAge(0); // 해당 쿠키는 생성하자마자 바로 없애준다. 
				response.addCookie(cookie);
			}
			response.sendRedirect(request.getContextPath() + "/login"); // 만약 로그인에 성공하면
		}else {
			// 로그인 실패
			List<DeptDTO> deptList = deptService.getAll();
			request.setAttribute("deptList", deptList);
			request.setAttribute("error", "로그인 정보를 다시 확인하세요!");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp"); // 다시 로그인 페이지로 이동
			rd.forward(request, response);
		}
	}
}
