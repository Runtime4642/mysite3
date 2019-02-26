package com.douzone.mysite.aspect;

import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
//@Component
public class GlobalExceptionHandler {

	@AfterThrowing(value="execution(* *..*.ProductService.*(..))",throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("call [afterThrowing advice] : "+ex);
	}
	
	
}
