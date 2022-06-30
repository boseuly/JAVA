package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException
	{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // response Header로 utf-8로 해석하라고 전달을 하는 거다.
			
			PrintWriter out = response.getWriter();
			
			String  x_ = request.getParameter("x"); //  html의 name이  getParameter("x") 이런식으로 사용된다. -> x에 해당 하는 입력값을 가져온다. -->
			String y_ = request.getParameter("y");
			
			int x = 0; int y = 0;
			
			// 유효성 검사
			if(!x_.equals(""))
				x = Integer.parseInt(x_);
			
			if(!y_.equals(""))
				y = Integer.parseInt(y_);
			
			int result = x + y;
			
			
			// 지금은 그냥 내용을 잘 받았다고 사용자에게 값을 돌려주기만 하기
			response.getWriter().printf("result is %d\n", result);
	}
}
