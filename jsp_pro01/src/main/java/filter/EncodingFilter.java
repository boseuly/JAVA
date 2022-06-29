package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//@WebFilter("/EncodingFilter")
public class EncodingFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); 		// 클라이언트가 서버에 보낸 한글이 깨질 때 사용
		response.setCharacterEncoding("UTF-8");  // 서버에서 클라이언트에 응답을 보낼 때 한글이 깨지는 걸 막기 위해 사용
		
		/*
		 * doFilter 메서드 동작 전 : 요청 필터
		 */
		chain.doFilter(request, response);
		/*
		 * doFilter 메서드 동작 후 : 응답 필터
		 */
	}
}
