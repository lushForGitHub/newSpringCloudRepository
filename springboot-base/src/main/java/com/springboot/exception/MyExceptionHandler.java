package com.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {
	
	public static final String ERRO_VIEW = "thymeleaf/error";
	
	@ExceptionHandler(value = Exception.class)
	public Object erroHandler(HttpServletRequest request,
				HttpServletResponse response,Exception e) throws Exception {
		e.printStackTrace();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURI());
		mav.setViewName(ERRO_VIEW);
		return mav;
	}

}
