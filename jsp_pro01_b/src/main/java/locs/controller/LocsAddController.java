package locs.controller;

import java.io.IOException;
import java.util.List;

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
	
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/locs/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/locs/index.jsp";

		int locId = Integer.parseInt(request.getParameter("locId"));
		String stAddr = request.getParameter("stAddr");
		String postal = request.getParameter("postal");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ctyId = request.getParameter("ctyId");

		LocsDTO data = new LocsDTO();
		data.setLocId(locId); data.setStAddr(stAddr); data.setPostal(postal); 
		data.setCity(city); data.setState(state); data.setCtyId(ctyId);
			
		// 값을 하나라도 안 입력한 경우
		int resultNum = service.datasAdd(data);	
		if(data != null ){  
			// 값을 다 입력한 경우
			// -2 : 기본키 위배, -1 : 외래키 위배, 0 : 알수 없는 오류, 1 : 저장성공 
			switch(resultNum) {
			case -2 : 
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 지역 ID는 이미 존재합니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case -1 : 
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 국가 ID는 존재하지 않습니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case 0 :
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "지역 추가 작업 중 알 수 없는 오류가 발생했습니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case 1 : 
				// 저장된 데이터를 전달해준다. 
				response.sendRedirect(request.getContextPath() + "/locs?search=" + data.getLocId());
			}
		}else {// 저장 실패
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

}
