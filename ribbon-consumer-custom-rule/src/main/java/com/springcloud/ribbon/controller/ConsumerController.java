package com.springcloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
										//VIP virtual IP 虚拟IP
		return restTemplate.getForEntity("http://TEST-SERVICE/index/hello", 
				String.class).getBody();
	} 
	/**
	 * 通过choose选择调用的微服务，查看所选微服务使用调度服务器机制（默认轮询）。
	 * @return
	 */
	@GetMapping("/test")
	public String testRibbon() {
		ServiceInstance serviceInstance =  this.loadBalancerClient.choose("TEST-SERVICE");
		System.out.println( "TEST-SERVICE:" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ":" 
							+ serviceInstance.getServiceId());
		
		ServiceInstance serviceInstance1 =  this.loadBalancerClient.choose("DEV-SERVICE");
		
		if(serviceInstance1 != null) {
			System.out.println("DEV-SERVICE:" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort() + ":" 
					+ serviceInstance1.getServiceId());
		}
		
		ServiceInstance serviceInstance2 =  this.loadBalancerClient.choose("PRO-SERVICE");
		
		if(serviceInstance2 != null) {
			System.out.println("PRO-SERVICE:" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + ":" 
							+ serviceInstance2.getServiceId());
		}
		return "11";
	}
}
