package error.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "/WEB-INF/error/error.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", request.getSession().getAttribute("error"));
		request.getSession().removeAttribute("error"); // session에 담긴 에러 지우기
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}


}
