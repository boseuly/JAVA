package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/dept")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<DeptDTO> datas = null;
		DeptService service = new DeptService(); // 우선 null로 
		
		// 우선 사용자가 어떤 데이터를 가져오고 싶어하는지 알아야 한다. -> 서버에 들어오는 정보는 다 문자열로 들어온다. 
		String search = request.getParameter("search");
		
		// 데이터 확인하기
		if(search == null || search.equals("")) { // 만약 데이터가 null 이거나 빈문자열이라면
			datas = service.getAll(); // 전체 목록 리스트 가져오기
		}else { // 만약 데이터가 존재한다면
			datas = new ArrayList<DeptDTO>();
			DeptDTO DeptData = service.getId(search);	// 특정 아이디 목록만 가져오기 
			datas.add(DeptData);	// 특정 아이디 정보 넣기
		}
		
		request.setAttribute("datas", datas); //정보 넘기기 

		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
