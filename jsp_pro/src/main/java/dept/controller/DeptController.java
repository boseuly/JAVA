package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	private String view = "/WEB-INF/jsp/depts/index.jsp";
	private DeptService deptService = new DeptService();
	private Parameter param = new Parameter();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징 하기 위한 조건 
		// 1. session이 필요하다.  ->  현재 몇 페이지인지 알기 위해서 -> 현재 몇 페이지인지 page 저장 
		// 2. 한 페이지에 몇 개의 행을 보여줄지 정해야 한다. -> pageCount
		HttpSession session = request.getSession(); // 세션 생성
		
		String search = request.getParameter("search"); // 얘가 deptId
		int page = param.defaultIntValue(request, "page", "1"); // 기본적으로 1페이지로 설정 -> 처음 페이지를 요청한 경우 즉, session이 존재하지 않는 사람들은 1페이지부터 시작
		int pageCount = 0;
		boolean pageCountCookieExist = false; // 이전에 쿠키에 pageCount 를 저장했다면
		
		// 만약 이전에 session 에 pageCount 를 저장한 적이 있다면 -> pageCount 를 변경해줘야 한다. 
		// pageCount 를 바꾸는 경우가 아닌 경우 -> pageCount 는 유지해줘야 하기 때문에
		if(session.getAttribute("pageCount") != null) { // row 개수 구하는 거
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountCookieExist = true;
		}
		// 만약 pgc 파라미터가 존재하거나 이전에 저장해둔 쿠키가 존재하지 않는다면 -> 
		// pageCount 를 바꾸는 경우
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultSessionIntValue(request, "pgc", "10"); // 이전에
		}
		
		// pageCount를 바꾸든, 바꾸지 않든 세션에 저장해야 한다.
		session.setAttribute("pageCount", pageCount); 
		request.setAttribute("page", page);
		
		
		// 만약 search 가 있으면 getId 만 해주기 / 만약 search 가 비어있거나 빈문자열이면 getAll 해주기
		// 페이징 해주기 -> getId
		List<DeptDTO> deptDatas = new ArrayList<DeptDTO>();
		if(search == null) { // 전체
			deptDatas = deptService.getPage(page, pageCount); // row수, 현재 페이지 전달
			request.setAttribute("pageList", deptService.getPageList(pageCount)); // 페이지 리스트에 들어가는 숫자를 가져온다.
		}else {  // 조회를 목적으로 한 경우
			DeptDTO data = deptService.getId(search);
			if(data == null) { // 이거 숫자형 아닌 경우
				request.setAttribute("errorMsg", "부서 ID를 잘못 입력하였습니다."); // 에러 정보를 줘야 하니까 forward 사용!!
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp");
				rd.forward(request, response);
			} // 만약 숫자형이 맞다면
			deptDatas.add(data);
		}
		
		request.setAttribute("deptDatas", deptDatas); // 데이터 전달
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}

}
