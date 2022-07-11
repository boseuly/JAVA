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
import javax.servlet.http.HttpSession;

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
		int page = param.defaultIntValue(request, "page", "1");	// 기본적으로 1 페이지부터 시작한다.
		int pageCount = param.defaultIntValue(request, "pgc", "10"); // pageCount는 기본적으로 10개의 행씩 보여준다.
		boolean pageCountCookieExist = false;	// 없으면 false 

/*		세션 메소드
		session.getAttribute("pageCount"); 	// 세션 조회 -> object 형으로 반환하기 때문에 형변환 필수
		session.setAttribute("pageCount", page); 	// 세션에 값 저장하기
		session.removeAttribute("pageCount"); 		// 세션에 설정한 pageCount 제거
		
		session.setMaxInactiveInterval(60*60*24); 	// 세션 유효기간 설정하기 , 초단위
		session.invalidate(); 						// 세션을 만료 시켜 새로운 세션을 만들 수 있게 한다.
		request.getSession(true); 					// 유효한 세션이 없는 경우 새로 만들고 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
		request.getSession(false);					// 유효한 세션이 없는 경우 null 반환, 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
*/		
		// 세션 이용
		HttpSession session = request.getSession();	// 세션 가져오기 (생성 및 얻기)
		// 있는지 없는지 확인 -> 있으면 저장시키기
		if(session.getAttribute("pageCount") != null) {
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountCookieExist = true;
		}
		
		// 변경된 값이 저장되어야 한다.
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		session.setAttribute("pageCount", pageCount);	
//		request.setAttribute("pageCount", pageCount);	 // session에 저장했으니까 request에는 저장할 필요 없음 단, jsp에서 sessionScope로 변경해줘야 한다.
		request.setAttribute("page", page);
		
		/* 쿠키 이용
		boolean pageCountCookieExist = false;	// 없으면 false 
		
		// 쿠키가 있는지 확인 // 만약 쿠키가 없다면 처음 화면을 요청한 상황임
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c:cookies) {
			if(c.getName().equals("pageCount")) {	// 만약 쿠키 중에 pageCount 라는 쿠키가 있다면 
				pageCount = Integer.parseInt(c.getValue());	// 만약 쿠키가 있다면 pageCount(행)를 해당 쿠키값으로 설정하기 
				pageCountCookieExist = true;		// 해당 쿠키가 존재한다.
			}
		}
		// pageCount의 변경을 위해 요청을 했는지, 만약 그렇다면 페이지 행 개수 변경
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {	// pgc가 null이 아니거나 쿠키가 존재하지 않는다면
			pageCount = param.defaultIntValue(request, "pgc", "10");		// pageCount를 10으로 한다.	
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		// 쿠키 생성 후 전달 (있으나 없으나)
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount));
//		cookie.setMaxAge(60*60*24);   해당 쿠키를 하루동안 유지
//		cookie.setPath("/");	root가 기본 설정이다. -> 특정 위치로 요청을 할 때만 해당 쿠키를 전달해라
		response.addCookie(cookie);
		*/
		
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
