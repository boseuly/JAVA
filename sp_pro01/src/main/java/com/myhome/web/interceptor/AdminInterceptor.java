package com.myhome.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myhome.web.emp.model.EmpDTO;

public class AdminInterceptor implements HandlerInterceptor{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") != null) {
			EmpDTO empDto = (EmpDTO)session.getAttribute("loginData");
			
			// 관리부(Administration)로 로그인을 하면 관리자 페이지로 이동하도록 해라
			if(empDto.getDeptId() == 10) {
				String oldView = modelAndView.getViewName(); 	// getViewName 하면 어떠한 뷰 페이지를 사용하는지 알아낼 수 있다.
				
				if(oldView.startsWith("redirect:")) { 			// 리다이렉트의 경우 admin/이 붙도록 한다. -> 로그인이 성공한 경우에만 보내야 하니까
					modelAndView.setViewName("admin/" + oldView); // 별도의 관리자 페이지가 사용되게 한다.
					// 이전의 뷰 페이지와 다른 별도의 관리자 페이지가 사용되게 한다.
				}
				
			}
		}
		
	}
}
