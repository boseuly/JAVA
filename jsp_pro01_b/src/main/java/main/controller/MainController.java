package main.controller;

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

@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/index.jsp";
	
	DeptService deptService = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		// 만약 기억하기를 눌렀다면 쿠키가 저장되어야 하고, 기억하기를 풀었다면 쿠키를 삭제해줘야 한다.
		
		if(session.getAttribute("loginData") == null) {
			List<DeptDTO> deptList = deptService.getAll();
			request.setAttribute("deptList", deptList);
			
			rd = request.getRequestDispatcher(view);
		}else {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/index2.jsp");
		}
		rd.forward(request, response);
		
		
		
		 
	}

}
