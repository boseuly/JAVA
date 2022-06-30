package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // response Header로 utf-8로 해석하라고 전달을 하는 거다.
		PrintWriter out = response.getWriter();
		
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) { 					// exp의 값은 calc3 서블릿에서 
				if(c.getName().equals("exp")) { // 내가 심은 exp 라는 키가 ㅇ있는지 확인
					exp= c.getValue();
					break;
				}
			}
		}
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input { ");
		out.write("width:50px;");
		out.write("height: 50px; ");
		out.write("}");
		out.write(".output {");
		out.write("height: 50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size: 24px;");
		out.write("font-weight: bold;");
		out.write("text-align: right;");
		out.write("padding : 0px 0px 5px;"); 
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		out.printf("			<td class=\"output\" colspan=\"4\">%d</td>", exp);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"7\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"8\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"9\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"4\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"5\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"6\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"1\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"2\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"3\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"0\"></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		expCookie.setPath("/calculator"); // calulator 에서만 쿠키가 전달  
		response.addCookie(expCookie);
		response.sendRedirect("calculator"); // calcpage서블릿으로 이동	
		}
}
