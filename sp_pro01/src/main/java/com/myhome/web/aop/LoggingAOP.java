package com.myhome.web.aop;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component  // bean객체로 등록하기 위해서 
@Aspect		// 이 클래스가 AOP 클래스라는 것을 지정
public class LoggingAOP {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAOP.class);
	
	// 조인 포인트 설정 				// 메소드 형식을 그대로 넣으면 된다. 단, 패키지도 같이 넣는다. -> 이때 사용되는 *의 의미는 모든 것을 포함한다는 의미다.
	@Pointcut(value="execution(* com.myhome.web.*.controller.*Controller.*(..))") // value에 들어가는 것이 포인트 식이라고 한다. -> 이 식으로 조인 포인트가 들어가는 곳을 지정한다. 
	private void loggingControllerCut() {} // getData가 핵심관점으로 등록되고, getData가 Joinpoint가 된다. -> 나중에 어드바이스랑 조인이 되어 여기서 메소드가 실행됨
	
	@Pointcut(value="execution(* com.myhome.web.*.service.*Service.*(..))")
	private void loggingServiceCut() {}
	
	@Pointcut(value="execution(* com.myhome.web.*.model.*DAO.*(..))")
	private void loggingDaoCut() {}
	
	@Pointcut(value="loggingControllerCut() || loggingServiceCut() || loggingDaoCut()")
	private void loggingMvcCut() {}
	
	
	@Before(value="loggingMvcCut()")// JoinPoint를 기준으로 언제 실행할 거냐 -> before -> JoinPoint 전에 실행
	public void beforeLogging(JoinPoint joinPoint) throws Exception { // after -> JoinPoint에 해당하는 메소드가 완전히 종료된 이후에 실행
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		logger.info("[{}] BEFORE {} {}", dateFormat.format(date), joinPoint.getTarget().toString(), joinPoint.getSignature().getName());
		
		for(Object arg:joinPoint.getArgs()) { // 메소드의 아규먼트 -> 매개변수에 대한 정보를 출력
			logger.info("		{}:{}", arg.getClass().getName(), arg.toString()); // 매개변수 종류와 매개변수들이 가지고 있는 값들도 확인해 볼수 있다. 
		}
	}
	
	//어드바이스 구현  
	@After(value="loggingMvcCut()")// JoinPoint를 기준으로 언제 실행할 거냐 -> before -> JoinPoint 전에 실행
	public void afterLogging(JoinPoint joinPoint) throws Exception { // after -> JoinPoint에 해당하는 메소드가 완전히 종료된 이후에 실행
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		logger.info("[{}] AFTER {} {}", dateFormat.format(date), joinPoint.getTarget().toString(), joinPoint.getSignature().getName());
		
		for(Object arg:joinPoint.getArgs()) { // 메소드의 아규먼트 -> 매개변수에 대한 정보를 출력
			logger.info("		{}:{}", arg.getClass().getName(), arg.toString()); // 매개변수 종류와 매개변수들이 가지고 있는 값들도 확인해 볼수 있다. 
		}
		
		/*
		logger.info("AOP 테스트 중");
		logger.info("joinPoint:{}", joinPoint);
		logger.info("joinPoint.getArgs:{}", joinPoint.getArgs());
		logger.info("joinPoint.getTarget():{}", joinPoint.getTarget());
		logger.info("joinPoint.getThis():{}", joinPoint.getThis());
		logger.info("joinPoint.getKind():{}", joinPoint.getKind());
		logger.info("joinPoint.getSignature().getName():{}", joinPoint.getSignature().getName());
		*/
		
	}
	
}
