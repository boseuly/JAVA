package jsp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp_request") //서블릿명
public class JspRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// get 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp_request.jsp"; // 선택할 jsp 파일 경로(제대로 작성하기)
		
		// jsp에서 하는 거랑, Servlet에서 하는 거랑 동일한 request 객체이다.
		System.out.println("param_name : " + request.getParameter("param_name"));
		
		if( request.getParameterValues("param_chk") != null) {
			System.out.println("param_chk : " + Arrays.asList( request.getParameterValues("param_chk"))); // 배열이니까 Arrays.asList에 담아주면 좋다.
		}
		
		Iterator<String> iter = request.getParameterNames().asIterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	// post 방식
	// 윈도우는 cp949 로 ms전용 인코딩  ->  서버에서도 cp949로 인코딩  됨 ->  한글이 깨져서 utf-8로 직접 설정해줘야 한다. 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8"); // JSP로 넘어가기 전에 서블릿에서 UTF-8로 설정해버리기
//		response.setCharacterEncoding("UTF-8"); // 서버 -> 클라이언트
		
		System.out.println(request.getParameter("username"));
		
		String view = "/WEB-INF/jsp/jsp_request.jsp"; // 선택할 jsp 파일 경로(제대로 작성하기)
		request.getRequestDispatcher(view).forward(request, response);
	}
}
