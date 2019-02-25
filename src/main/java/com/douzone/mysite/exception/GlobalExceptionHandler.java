package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request,Exception e) {
		
		//1.로깅 작업
		//errors를 string 으로 바꾸는 작업 printwriter 연결이 메모리로 되있음.
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());
		
		//서비스할때는 log 찍어줘야함 -> 에러내용 파일저장
		//log.error(errors.toString());
		
		
		
		//2.시스템 오류 안내 화면 
		ModelAndView mav = new ModelAndView();
		mav.addObject("errors",errors.toString());
		mav.setViewName("error/exception");
		
		return mav;
	}
	
	
}
