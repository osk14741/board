package com.kkj.board.cmn;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLogger {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.kkj.board..*Controller.*(..))")
	public Object logPrint(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		LOG.info("Start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
		Object result = pjp.proceed();
		long endTime = System.currentTimeMillis();
		LOG.info("Finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName() + " / " + "time : " + (endTime - startTime) + "ms");
		
		return result;
	}
}
