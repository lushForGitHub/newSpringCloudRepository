package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.feign.feignclient.FeignServiceClient;
import com.springcloud.feign.feignclient.FeignServiceClient2;



@RestController
public class FeignConsumerController {
	
	@Autowired
	private FeignServiceClient feignClient;
	
	@Autowired
	private FeignServiceClient2 feignClient2;

	@GetMapping(value = "/helloConsumer")
	public String helloConsumer() {
		return this.feignClient.helloConsumer();
	} 
	
	@GetMapping("/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
		return this.feignClient2.findServiceInfoFromEurekaByServiceName(serviceName);
	}
}
