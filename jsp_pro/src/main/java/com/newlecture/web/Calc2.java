package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override				//request: Http 에서 서블릿에 요청한 값 전달받을 때 사용, response: 서버에서 http로 값을 전달할 때 사용 
	public void service(HttpServletRequest request, HttpServletResponse response)
											throws IOException, ServletException
	{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); // response Header로 utf-8로 해석하라고 전달을 하는 거다.
			
			ServletContext application = request.getServletContext();
			HttpSession session = request.getSession();
			Cookie[] cookies = request.getCookies();
			
			// http에서 요청된 값을 문자열 또는 null로 반환한다.
			String v_ = request.getParameter("v");
			String op = request.getParameter("operator");
			
			int v = 0;
			if(!v_.equals(""))
				v=Integer.parseInt(v_);
			
			// 상태유지를 위한 ServletContext
			if(op.equals("=")) { // = 이라는 operator을 전달한 경우
					int result = 0; 
	//				int x = (Integer)application.getAttribute("value"); // servletContext 저장소 안에 있는 값을 가져오기 위해서는 키명을 적어야 한다.
	//				int x = (Integer)session.getAttribute("value");
					
					// 쿠키를 브라우저에서 꺼내기
					int x= 0;
					for(Cookie c : cookies) { 
						if(c.getName().equals("value")) { // 내가 심은 value 라는 키가 ㅇ있는지 확인
								x = Integer.parseInt(c.getValue());
								break;
						}
					}
					int y = v;
					
	//				String operator = (String)application.getAttribute("op");
	//				String operator = (String)session.getAttribute("op");
					
					// 쿠키를 브라우저에서 꺼내기
					String operator = "";
					for(Cookie c : cookies) { 
						if(c.getName().equals("op")) { // 내가 심은 op 라는 키가 ㅇ있는지 확인
								 operator = c.getValue();
								break;
						}
					}
					
					if(operator.equals("+")) {
							result = x + y;
					}else {
							result = x - y;
					}
						response.getWriter().printf("result is %d\n", result);
			} else {  // =이 아니라 + 또는 - operator을 전달한 경우
					//application 으로 상태 유지 
//					application.setAttribute("value", v);	// map의 키와 값을 저장하듯이 value = v 이렇게 저장한 거다.
//					application.setAttribute("op",op);
					
					// session으로 상태 유지
//					session.setAttribute("value", v);	// map의 키와 값을 저장하듯이 value = v 이렇게 저장한 거다.
//					session.setAttribute("op",op);
					
					// cookie 넣기
					Cookie valueCookie = new Cookie("value",String.valueOf(v)); // cookie는 문자열만 저장 가능하다. -> int를 String 으로
					Cookie opCookie = new Cookie("op", op);
					valueCookie.setPath("/calc2");	// 어느 경우에 사용자로부터 전달되어야 하는지 결정 -> /라고 하면 모든 페이지를 요청할 때마다 value 쿠키를 가져오라는 의미,
					opCookie.setPath("/calc2");		// Calc2 페이지를 요청할 때마다 해당 쿠키를 가져오라는 의미다.
					
					// 브라우저가 닫혀도 만료날짜까지 유지된다. -> 만료날짜를 설정하지 않으면 브라우저를 완전히 닫으면 쿠키가 사라진다. 
					valueCookie.setMaxAge(24*60*60);
					
					
					
					// 브라우저에 쿠키 전달하기
					response.addCookie(valueCookie); 
					response.addCookie(opCookie);
					
					// 서버에서 페이지 전환하기
					response.sendRedirect("calc2.html"); // 해당 페이지를 보여주겠다.
					
			}
	}
}
