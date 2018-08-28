package com.springcloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.feign.client.FeignService;
import com.springcloud.feign.client.FeignService2;

@RestController
public class FeignWithoutHystrixController {
	
	@Autowired
	private FeignService feignClient;
	@Autowired
	private FeignService2 feignService2;

	@GetMapping("/hello")
	public String helloConsumer() {
		return feignClient.helloConsumer();
	} 
	
	@RequestMapping(value = "/{serviceName}", method = RequestMethod.GET)
	public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
		return feignService2.findServiceInfoFromEurekaByServiceName(serviceName);
	}
}
