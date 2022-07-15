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

@WebServlet("/locs/add")
public class LocsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String view = "/WEB-INF/jsp/locs/add.jsp"; 
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션에 저장해뒀다가 로그인 실패한 경우 적었던 정보를 그대로 유지해서 보여준다. 
		// 그러려면 getpost에서 세션에 저장을 해둬야 한다.
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 해당 parameter 들을 받아서 처리해야 한다.
		String locId = request.getParameter("locId");	// 중복되면 안 된다.
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");	// 제약조건 (외래키 - COUNTRIES테이블의 country_id컬럼)
		
		LocsDTO data = service.addLocs(request.getSession(),locId, stAddr, postal, city, state, ctyId);
		request.setAttribute("data", data);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
