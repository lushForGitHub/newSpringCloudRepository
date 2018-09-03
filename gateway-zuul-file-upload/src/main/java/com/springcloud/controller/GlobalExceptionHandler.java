package com.springcloud.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 捕获超出最大上传大小异常
 * 
 * @author lushuai
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String exceptionHandler(MultipartException me, RedirectAttributes attributes) {

		attributes.addFlashAttribute("message", me.getCause().getMessage());
		return "redirect:/uploadStatus";
	}

}
