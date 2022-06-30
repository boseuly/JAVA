package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException
	{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // response Header로 utf-8로 해석하라고 전달을 하는 거다.
			
			PrintWriter out = response.getWriter();
			
			String[]  y_ = request.getParameterValues("y");
			
			int result = 0;
			
			// 연산은 반복되지만 선언은 반복되지 않는다.
			for(int i =0 ; i < y_.length; i++) {
				int y = Integer.parseInt(y_[i]);
				result+=y;
			}
			response.getWriter().printf("result is %d\n", result);
	}
}
