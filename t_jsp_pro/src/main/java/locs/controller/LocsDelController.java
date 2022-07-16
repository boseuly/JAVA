package locs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/del")
public class LocsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LocsService service = new LocsService();
	private String view = "/WEB-INF/jsp/locs/del.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get에서는 데이터 조회, 검색 등의 기능을 수행한다.
		//해당 아이디를 먼저 보여줘야 한다.	->  parameter로 받은 id를 가지고 검색해야 한다.
		String locId = request.getParameter("id");
		LocsDTO data = service.getId(Integer.parseInt(locId));
		
		request.setAttribute("data", data);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post에서는 데이터 삭제/수정 등 변경 처리를 한다.
		String locId = request.getParameter("locId");
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");
		
		boolean result = service.delLocs(locId, stAddr, postal, city, state, ctyId);
		// 만약 삭제가 성공한다면
		if(result) {
			response.sendRedirect(request.getContextPath() + "/locs");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

}
