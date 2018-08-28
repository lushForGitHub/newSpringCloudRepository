package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.feign.FeignServiceClient;


@RestController
public class ConsumerController {
	
	@Autowired
	private FeignServiceClient feignClient;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloConsumer() {
		return feignClient.helloConsumer();
	} 
}
