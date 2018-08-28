package com.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/err")
public class ErrorController {
	
	@SuppressWarnings("unused")
	@RequestMapping("/error")
	public String error(Map<String,String> map) {
		int i = 1 / 0;
		return "thymeleaf/error";
	}
	

}
