package com.w3villa.main.authentication.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	private Logger LOGGER = null;

	@Pointcut("execution(public * * (..))")
	private void anyPublicMethod() {
	}

	@Pointcut("within(com.w3villa.main.controller..*)")
	private void inController() {
	}

	@Pointcut("within(com.w3villa.main.userServiceImpl..*)")
	private void inUserServiceImpl() {
	}

	@Pointcut("within(com.w3villa.main.service..*)")
	private void inService() {
	}

	@Pointcut("within(com.w3villa.main.daoImpl..*)")
	private void inDaoImpl() {
	}

	@Around("(anyPublicMethod() &&  inController()) || (anyPublicMethod() &&  inDaoImpl()) || (anyPublicMethod() &&  inService()) || (anyPublicMethod() &&  inUserServiceImpl())")
	public Object loggingInController(ProceedingJoinPoint pjp) {
		Object obj = null;
		long startTime = 0l;
		long endTime = 0l;
		LOGGER = Logger.getLogger(pjp.getTarget().getClass().getName());
		LOGGER.info(pjp.getTarget().getClass().getName() + " : "
				+ pjp.getSignature() + " entry.");
		// System.out.println(pjp.getTarget().getClass().getName() + " : "
		// + pjp.getSignature() + " entry.");
		try {
			startTime = System.currentTimeMillis();
			obj = pjp.proceed();
			endTime = System.currentTimeMillis();
		} catch (Throwable e) {
			System.out.println(pjp.getTarget().getClass().getName() + " : "
					+ pjp.getSignature() + " Error occured.");
			e.printStackTrace();
		}
		System.out.println("Time taken to execute  " + pjp.getSignature()
				+ " method :" + (endTime - startTime) + " milliseconds.");
		LOGGER.info(pjp.getTarget().getClass().getName() + " : "
				+ pjp.getSignature() + " exit.");
		// System.out.println(pjp.getTarget().getClass().getName() + " : "
		// + pjp.getSignature() + " exit.");
		return obj;
	}

}
