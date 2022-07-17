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

@WebServlet("/locs/mod")
public class LocsModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LocsService service = new LocsService();
	private String view = "/WEB-INF/jsp/locs/mod.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locId = request.getParameter("id"); // 해당 아이디를 가져와서 특정 아이디 정보를 알려줘야 한다.
		// locId의 타입은 숫자형 -> parameter locId는 문자형
		LocsDTO data = service.getId(Integer.parseInt(locId)); // 해당 정보를 가지고 옴 
		
		request.setAttribute("data", data); // 해당 정보를 보냄
		
		RequestDispatcher rd= request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 할 수 있는지 제약 조건에 위배되지는 않는지 확인 해야 한다.
		String locId = request.getParameter("locId");
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");
		
		// 아이디를 가지고 제약조건을 확인한다.
		LocsDTO data = service.modLocs(locId, stAddr, postal, city, state, ctyId);
		
		// 만약 오류가 하나라도 발생하면
		if(data.getLocId() == -1 || data.getCtyId() == "-1") {
			request.setAttribute("data", data);
			RequestDispatcher rd= request.getRequestDispatcher(view);
			rd.forward(request, response);
			
		}else { // 만약 오류가 발생하지 않았다면 -> 그대로 저장해준다. -> redirector
			response.sendRedirect(request.getContextPath() + "/locs");
			
		}
		
		
	
	}

}
