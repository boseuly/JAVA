package jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsp_script")
public class JspController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// servlet 파일로 바로 실행할 게 아니라 jsp로 실행을 할 거니까 아래와 같이 해줘야 한다. 
		// MainController 와 다를 거 없음. 단지 path와 jsp 파일 경로가 다를 뿐이다.
		String view = "/WEB-INF/jsp/jsp_script.jsp"; // 이 경로에 맞춰서 jsp 파일 만들기
		request.getRequestDispatcher(view).forward(request, response);
	}
}
