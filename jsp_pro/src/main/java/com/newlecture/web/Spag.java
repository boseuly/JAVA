package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int num = 0;
    	// 사용하자에게 입력 받기 -> 반드시 화면상에서 입력 받을 필요 없고 url을 통해 입력 받을 수도 있다.
    	String num_ = request.getParameter("n");
    	if(num_ != null && !num_.equals("")){
    		num = Integer.parseInt(num_);
    	}
    	String result;
    	if(num%2 != 0){
    		result = "홀수";
    	}else {
    		result="짝수";
    	}
    	
    	String[] names = {"newlec", "dragon"};
    	request.setAttribute("names",names);
    	
    	Map<String, Object> notice = new HashMap<String, Object>();
    	notice.put("id", 1);
    	notice.put("title", "EL은 좋아요");
    	request.setAttribute("notice", notice);
    	
    	request.setAttribute("result", result);
    	request.getRequestDispatcher("spag.jsp").forward(request, response);
	
	}
}
