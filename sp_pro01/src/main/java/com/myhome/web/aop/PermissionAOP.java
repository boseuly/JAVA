package com.myhome.web.aop;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.login.model.PermDTO;
import com.myhome.web.login.model.PermissionDAO;

@Component
@Aspect
public class PermissionAOP { // 권한 확인하는 AOP
	
	/*
		1. 로그인을 하면 권한 정보를 가지는 객체를 세션에 저장하고, 세션에 저장된 권한 정보를 확인 한다.
			-> 이 방식은 PermissionDAO 객체 필요 없음
		2. 로그인을 한 유저들의 정보를 세션에서 찾아서 서비스 요청을 할 때마다 권한 테이블의 정보를 조회하여 확인한다.
			-> 이 경우는 PermissionDAO 객체 필요
			
		공통점 : 어떤 서비스의 권한을 확인해야 하는지 구분하기 위한 값이 필요하다.
	
	*/
	@Autowired
	private PermissionDAO dao;		// 얘는 public 넣어주기 
	
	// 앞으로는 service의 첫번째 매개변수로 HttpSession이 들어가도록 만들어야 한다. -> 메소드에서 사용하지 않아도 AOP에서 사용될 거기 때문에
	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.get*(javax.servlet.http.HttpSession, ..))")
	private void permSelectCut() {}

	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.add*(javax.servlet.http.HttpSession, ..))\")")
	private void permInsertCut() {}

	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.modify*(javax.servlet.http.HttpSession, ..))\")")
	private void permUpdateCut() {}

	@Pointcut(value="execution(public * com.myhome.web.*.service.*Service.remove*(javax.servlet.http.HttpSession, ..))\")")
	private void permDeleteCut() {}
	
	
	private PermDTO getPermission(JoinPoint joinPoint){
		HttpSession session = (HttpSession) joinPoint.getArgs()[0]; // 매개변수 중 첫번째 매개변수라서 [0]이다.
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String name = joinPoint.getSignature().toShortString().split("\\.")[0];
		name = name.split("Servce")[0].toLowerCase();
		
		PermDTO data = new PermDTO();
		data.setEmpid(empData.getEmpId());
		data.setTablename(name);
		
		boolean result = dao.selectData(data);
		if(!result) {
			return data;
		}else {
			return null;
		}
	}
	
	
	
	@Before(value="permSelectCut()")
	public void beforePermSelect(JoinPoint joinPoint) throws Exception {
		//dao에서 조회한 결과를 우리가 전달한 참조객체에 다시 저장 -> 이 작업을 dao에서 한다. 굳이 반환할 필요 없음 참조값이 같으니까
		  
//		HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
		
		PermDTO perm = getPermission(joinPoint);
		if (perm != null) {
			if(!perm.ispAdd()) { // 만약 읽기 권한이 존재 하지 않으면 예외를 발생시켜 던진다.
				// resp.sendError(HttpServletResponse.FORBIDDEN, "읽기 권한이 없습니다.");
				throw new PermissionDeniedDataAccessException("읽기 권한이 없습니다.", null);
			}
			// 만약 권한이 있으면 아무 조치 안 함
		}else {
			throw new PermissionDeniedDataAccessException("로그인 정보가 없습니다.", null);
		}
		
//		System.out.println(joinPoint.getSignature().toShortString());
//		System.out.println(joinPoint.getSignature().toLongString());
	}
	@Before(value="permInsertCut()")
	public void beforePermInsert(JoinPoint joinPoint) throws Exception {
		
	}
	@Before(value="permUpdateCut()")
	public void beforePermUpdate(JoinPoint joinPoint) throws Exception {
		
	}
	@Before(value="permDeleteCut()")
	public void beforePermDelete(JoinPoint joinPoint) throws Exception {
		
	}
}
