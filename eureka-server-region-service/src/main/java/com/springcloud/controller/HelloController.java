package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloController { 
	@Value("${zone.message}") 
	private String hello; 
	@RequestMapping(value="/hi") 
	public String hi() { 
		return hello; 
		} 
	}
