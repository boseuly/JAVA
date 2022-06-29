package main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") //매핑명(원래는 엄청 긴 url 주소인데 /라는 path로 매핑하였다.)
public class MainController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	   
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String view = "/WEB-INF/jsp/index.jsp"; // 내가 사용할 jsp 경로
			request.getRequestDispatcher(view).forward(request, response);  // 요청 정보, 응답 정보를 jsp페이지에서도 사용할 수 있게 forward 즉, 전달하는 거다.
			
			
		}
}
