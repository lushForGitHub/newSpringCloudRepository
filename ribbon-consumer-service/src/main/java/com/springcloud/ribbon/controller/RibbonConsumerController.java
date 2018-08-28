package com.springcloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient balancerClient;
	
	@RequestMapping(value = "/ribbon", method = RequestMethod.GET)
	public String ribbonConsumer() {
		ServiceInstance instance = balancerClient.choose("test-service");
		
		if(instance != null) {
			System.out.println(instance.getServiceId() + ": " + instance.getHost() + ": " + instance.getPort());
		}
		return restTemplate.getForEntity("http://test-service/index/hello", String.class).getBody();
	}
}
