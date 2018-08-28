package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/hello")
	public String hi() {
		return restTemplate.getForEntity("http://zone-demo/hi", String.class).getBody();
	}
}
