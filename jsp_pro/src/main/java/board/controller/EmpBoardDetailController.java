package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emp.model.EmpDTO;
import emp.service.EmpService;

@WebServlet("/board/detail")
public class EmpBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/detail.jsp";
		
		String id = request.getParameter("id");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		service.incViewCnt(request.getSession(),data); // 조회수를 올려야 한다.
		
		if (data != null) {
			EmpService empService = new EmpService();
			EmpDTO empData = empService.getId("" + data.getEmpId()); // 사원 아이디에 따른 이름을 가져오기 위해서 
			
			// data.setContent(data.getContent().replace("\r\n", "<br>")); // \r : 캐리지 리턴 , \n : 뉴라인
			// 자바코드에서는 \n으로 저장되기 때문에 html코드로 바꿔줘야 한다. 그래야 화면으로 봤을 때 개행이 됨
			// 이건 ckeditor 사용하면 사용할 필요 없음
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			// 별도의 페이지로 데이터가 없음을 알려야 한다.
			// 1. 포워드 : url 주소는 바뀌지 않는다.  -> 이거로 한 번 만들어보기 (경고창 띄우는 걸로)
			// 2. 리다이렉트 : url 주소가 바뀐다. 
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(data != null) {
			service.incLike(request.getSession(), data);
			sb.append(String.format("\"%s\": \"%s\",", "code", "success"));
			sb.append(String.format("\"%s\": %d", "likeCnt", data.getLike()));
		}
		sb.append("}");
		
	
		
		out.append(sb.toString());
		out.flush();
		
	}

}
