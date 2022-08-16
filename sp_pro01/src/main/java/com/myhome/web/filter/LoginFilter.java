package com.myhome.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(
//		filterName = "LoginFilter",
//		urlPatterns = {
//				"/myinfo",
//				"/depts", "/locs", "/emps",
//				"/depts/*", "/locs/*", "/emps/*"
//		}
//)
@WebFilter(
		urlPatterns = {
				"/board", "/board/*"
		}
		 // 로그인을 하지 않은 상태에서
)

public class LoginFilter extends HttpFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String qs = "";
		// -> 파라미터 가져오기 : getRequestURI와 URL까지만 하면 path까지만 나오고, 파라미터는 나오지 않음 -> 따라서 파라미터를 가져오고 싶으면 getQueryString을 사용해야 한다.
		if(((HttpServletRequest)request).getQueryString() != null) { // 만약 파라미터가 비어있을 수도 있기 때문에 null처리 해주기 -> null이 아닌 경우에만 파라미터를 추가해추기
			qs = "?" + ((HttpServletRequest)request).getQueryString();
		}
		String path = ((HttpServletRequest)request).getRequestURI();
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);
		} else {
			// 로그인을 하지 않는 경우
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login?url=" + path + qs);
			// login?url= .... -> 파라미터에 들어가는 url 주소는 사용자가 들어가고자 한 페이지의 url 주소이다.
			// 예시) /spring/login?url=/spring/board/detail?id=16&name=other 이런식으로 사용자가 이동하고자 하는 url로 이동하려 한다.
		}
	}
	
}
