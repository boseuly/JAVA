package locs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.Parameter;
import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs")
public class LocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Parameter param = new Parameter();
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page", "1");	// 처음에 1페이지
		int pageCount = 0;
		boolean pageCountCookieExist = false;
/*		
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c:cookies) {
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());
				pageCountCookieExist = true;
			}
		}
		
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pageCount", "10");
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
 */
		HttpSession session = request.getSession();
		
		// 만약 이전에 pageCount를 설정한 session이 있다면
		if(session.getAttribute("pageCount") != null) {
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountCookieExist = true;			// 세션이 존재하면 그냥 pageCount에 넣으면 된다.
		}
		
		// pgc 요청이 없거나 pageCount 쿠키 존재하지 않는다면 기본 10으로 설정해라
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pageCount", "10");
		}
		
		// session에 저장하기
		session.setAttribute("pageCount", pageCount);
		request.setAttribute("page", page);
		
		List<LocsDTO> datas = null;
		
		if(search == null || search.equals("")) {
			datas = service.getPage(page, pageCount);	// 몇 페이지, ROW수
			request.setAttribute("pageList", service.getpageList(pageCount));
		}else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) { // 만약 숫자가 맞다면
				LocsDTO data = service.getId(search);
				if(data != null) { // 만약 해당 데이터가 있다면
					datas = new ArrayList<LocsDTO>();
					datas.add(data);
				}
			}
		}
		// session에 전달하는 데이터 : pageCount, page
		// request에 전달하는 데이터 : List<LocsDTO> datas
		request.setAttribute("datas", datas);
		String view = "/WEB-INF/jsp/locs/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
