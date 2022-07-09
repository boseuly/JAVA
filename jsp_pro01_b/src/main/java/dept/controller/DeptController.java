package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Parameter;
import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	private Parameter param = new Parameter();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키를 확인 할 때 select 요청할 때와 부서, 지역 등 다른 서버 페이지로 이동하거나 할 때를 다 신경써야 한다.
		// 먼저 확인을 한 다음에 설정을 해준다. 
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page", "1");
		int pageCount = param.defaultIntValue(request, "pgc", "10");
		
		boolean pageCountCookieExist = false;	// 없으면 false 
		
		// 쿠키가 있는지 확인
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());	// 쿠키가 있으면 새로 요청온 값을 저장해준다.
				pageCountCookieExist = true;
			}
		}
		// pageCount의 변경을 위해 요청을 했는지, 만약 그렇다면 페이지 행 개수 변경
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {	// pgc가 null이거나 쿠키가 없다면
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		// 쿠키 생성 후 전달 (있으나 없으나)
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount));
		response.addCookie(cookie);
		
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			deptDatas = service.getPage(page, pageCount);
			request.setAttribute("pageList", service.getPageList(pageCount));
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();
					deptDatas.add(data);
				}
			}
		}
		
		request.setAttribute("deptDatas", deptDatas);
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);	// 매퍼이름은 알아서
	}

}
