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

@WebServlet("/dept")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService(); // 우선 null로 
	private Parameter param = new Parameter();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 쿠키를 저장하기 전에
		 * 확인해야할 것
		 * 1. 행 수를 바꾸기 위해 pgc(pageCount)를 재설정하는 건가 -> page 그대로, pageCount 변경
		 * 2. 페이지를 바꾸려는 게 아니라 다른 요청을 하려는 건가 -> pageCount, page 그대로
		 * 3. 페이지를 바꾸기 위해 page를 재설정하는 건가 -> pageCount 는 그대로 page는 변경
		 * 4. 처음 페이지를 요청한 경우	-> pageCount 와 page는 기본 10과 1로 설정
		 */
		
		// 쿠키를 저장하는 과정 (pageCount -> 행수)
		String search = request.getParameter("search");
		int page = param.defaultIntValue(request, "page","1");
		int pageCount = 0;	// 행 수는 0으로 초기화
		
		boolean pageCountCookieExist = false;	// 해당 쿠키가 존재하지 않는 경우는 페이지를 처음 요청한 경우
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("pageCount")) {	// 만약 pageCount가 존재한다면
				pageCount = Integer.parseInt(c.getValue());	// pageCount 는 해당 쿠키값
				pageCountCookieExist = true;	
			}
		}
		// 만약 pageCount를 변경하는 게 아니라 처음 페이지 요청을 하는 경우 
		if(request.getParameter("pgc") != null || !pageCountCookieExist){
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		// page를 전달하는 이유 -> 그걸 알아야지 페이지 css 넣을 수 있음 -> 해당 페이지 번호 스타일을 다르게 한다던가 등
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount));
		response.addCookie(cookie);

		List<DeptDTO> datas = null;
		
		// 데이터 확인하기
		if(search == null || search.equals("")) { // 만약 데이터가 null 이거나 빈문자열이라면
			datas = service.getPage(page, pageCount); // 전체 목록 리스트 가져오기
			request.setAttribute("pageList", service.getPageList(pageCount));	// 여기서 pageList는 List<Integer> 객체임 -> 몇 페이지로 이동할지 설정
		}else { // 만약 search 파라미터가 존재한다면 해당 아이디 객체만 전달해주면 되니까 getId만해주면 된다.
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {	// 숫자인지 확인
				DeptDTO data = service.getId(search);
				if(data != null) {
					datas = new ArrayList<DeptDTO>();
					datas.add(data);
				}
			}
		}
		
		request.setAttribute("datas", datas); //정보 넘기기 

		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	
	}
}
