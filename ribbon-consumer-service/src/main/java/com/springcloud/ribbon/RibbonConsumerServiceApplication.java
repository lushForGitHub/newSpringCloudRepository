package com.springcloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.springcloud.config.CustomRibbonRule;

@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "test-service", configuration = CustomRibbonRule.class)
public class RibbonConsumerServiceApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerServiceApplication.class, args);
	}
}
