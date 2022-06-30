package com.newlecture.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		arg0.setCharacterEncoding("UTF-8"); // 모든 환경 설정을 결정함
		
		arg2.doFilter(arg0, arg1); // 요청이 오면 흐름을 넘겨서 다음 필터 서블릿을 실행하는 것, 그것의 response가 오면 
	}

}
