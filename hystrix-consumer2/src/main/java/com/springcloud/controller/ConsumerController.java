package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		long start = System.currentTimeMillis();
		restTemplate.getForEntity("http://TEST-SERVICE/index/hello", 
				String.class).getBody();
		
		long end = System.currentTimeMillis();
		Long time = end - start;
		return time.toString();
	} 
	
	public String fallback() {
		return "error";
	}
	
}