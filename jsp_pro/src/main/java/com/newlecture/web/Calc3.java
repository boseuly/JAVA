package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override				//request: Http 에서 서블릿에 요청한 값 전달받을 때 사용, response: 서버에서 http로 값을 전달할 때 사용 
	public void service(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException
	{
			Cookie[] cookies = request.getCookies();
			
			String value = request.getParameter("value");
			String operator = request.getParameter("operator");
			String dot = request.getParameter("dot");
			
			String exp = "";
			// 기존에 읽어온 값이 있으면 읽어온 값에 더해주고, 기존의 값이 없으면 이게 초기값이 된다. 
			if(cookies != null) {
					for(Cookie c : cookies) { 					// exp의 값은 calc3 서블릿에서 
						if(c.getName().equals("exp")) { // 내가 심은 op 라는 키가 ㅇ있는지 확인
							exp= c.getValue();
							break;
						}
					}
			}
			
			if(operator != null && operator.equals("=")) {
					// 계산하기
					ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
					try {
						exp = String.valueOf(engine.eval(exp));
					} catch (ScriptException e) {
						e.printStackTrace();
					}
			}else if (operator != null && operator.equals("C")){
					exp = "";
			}else {
					// 만약 = 연산자가 아니라면 누적만 해줘라
					// 아래 세 개를 작성했지만 실직적으로 exp 에 누적되는 것은 하나의 값이다. 즉, value, operator, dot 중 하나
					exp += (value == null)? "" : value; // null 이 아니면 기존의 exp값에 value를 추가하기
					exp += (operator == null)? "" : operator; // null 이 아니면 기존의 exp값에 value를 추가하기
					exp += (dot == null)? "" : dot; // null 이 아니면 기존의 exp값에 value를 추가하기
			}
			// 쿠키에 저장하기 위해서 쿠키 생성
			Cookie expCookie = new Cookie("exp", exp);
			if (operator != null && operator.equals("C")) {
				expCookie.setMaxAge(0);
			}
			expCookie.setPath("/calc3"); 
			response.addCookie(expCookie);
			response.sendRedirect("calcpage"); // calcpage서블릿으로 이동
	}
}
