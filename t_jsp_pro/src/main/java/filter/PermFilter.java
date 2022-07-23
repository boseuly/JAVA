package filter;

import java.io.IOException;
import java.util.Map;

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

import login.model.PermDTO;

/**
 * Servlet Filter implementation class PermFilter
 */
@WebFilter(
			filterName = "PermFilter", // web.xml 에 작성한 filter-name을 적어준다.
			urlPatterns = { // 해당 주소에 대해서만 필터 체크를 하겠다.
					"/emps", "/emps/*", 
					"/depts", "/depts/*"
			}
		)
public class PermFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		java.util.Map<String, PermDTO> perm = (Map<String, PermDTO>)session.getAttribute("permData"); // map으로 만듦
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		if(uri.equals("/emps")) {	//사용자가 어떤 요청을 했는지 확인 하기 emps 에서 요청을 했는지 depts 에서 했는지 
			if (!perm.get("employees").ispRead()) { // employees 에 대한 읽기 권한이 없다면
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		} else if(uri.equals("/depts")) {
			if (!perm.get("departments").ispRead()) { // departments 에 대한 읽기 권한이 없다면
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN);	// 에러 전달 후 돌려보내기
				return;
			}
		}
		chain.doFilter(request, response);
	}


}
