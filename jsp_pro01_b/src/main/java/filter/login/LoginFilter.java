package filter.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = {"/depts", "/locs", "/emps", "/depts/*", "/locs/*", "/emps/*"}
		)
public class LoginFilter extends HttpFilter implements Filter {
       // 로그인 한 사람들만 dept, locs, 등의 정보 수정, 삭제 등 기능 사용할 수 있도록 필터에서 session을 통해 거른다.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/");	// 로그인 페이지로 이동
		}
	}

}
