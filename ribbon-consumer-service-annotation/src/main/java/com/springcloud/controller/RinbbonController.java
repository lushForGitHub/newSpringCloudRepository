package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RinbbonController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalanceClient;
	
	@GetMapping("/ribbon")
	public String ribbonConsumer() {
		ServiceInstance serviceInstance = loadBalanceClient.choose("test-service");
		
		if(serviceInstance != null) {
			System.out.println(serviceInstance.getServiceId() + ": " + serviceInstance.getHost() + ": " + serviceInstance.getPort());
		}
		return restTemplate.getForEntity("http://test-service/index/hello", String.class).getBody();
	}
}
