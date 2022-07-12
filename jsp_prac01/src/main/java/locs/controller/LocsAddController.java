package locs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Parameter;
import locs.service.LocsService;

@WebServlet("/locs/add")
public class LocsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private LocsService service = new LocsService();
	String view = "/WEB-INF/jsp/locs/add.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력 정보가 post로 전달되면 해당 정보를 추가할 수 있는지 제약조건 등을 검사한다.
		String locId = request.getParameter("locId"); // primary키
		String stAddr = request.getParameter("stAddr"); 
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId"); // 외래키 
		
		int result = service.locsAdd(locId, stAddr, postal, city, state, ctyId);
		
		switch(result) {
		case -2 : 
			request.setAttribute("errorMsg", "해당 국가는 존재하지 않습니다.");
			view = "/WEB-INF/jsp/error/error.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			break;
		case -1:
			request.setAttribute("errorMsg", "해당 아이디는 이미 존재합니다.");
			view = "/WEB-INF/jsp/error/error.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			break;
		case 0 :
			request.setAttribute("errorMsg", "추가 작업 중 알 수 없는 오류가 발생하였습니다.");
			break;
		case 1 :
			response.sendRedirect("${pageContext.request.contextPath}/locs");
			return;
		}
		
	}

}
