package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg")	// 어노테이션을 이용해서 설정 하는 방법(추천) , web.xml 파일을 통해서 설정 하는 방법도 있음. -> 매핑
public class NoticeReg extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException
	{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // response Header로 utf-8로 해석하라고 전달을 하는 거다.
			
			PrintWriter out = response.getWriter();
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// 지금은 그냥 내용을 잘 받았다고 사용자에게 값을 돌려주기만 하기
			out.print(title);
			out.print(content);
	}
}
