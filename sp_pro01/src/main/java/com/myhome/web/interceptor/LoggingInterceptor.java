package com.myhome.web.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// servlet-context.xml에 inter
public class LoggingInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		dateFormat.format(date);
		
		logger.info("[{}]URL : {}",dateFormat.format(date), request.getRequestURL());
		
		request.setAttribute("timer", date);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		
		Date oldDate = (Date)request.getAttribute("timer");
		double timer = (date.getTime() - oldDate.getTime()) / 1000.0;
		
		logger.info("[{}]초", dateFormat.format(date), timer);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	
	}
	
}
