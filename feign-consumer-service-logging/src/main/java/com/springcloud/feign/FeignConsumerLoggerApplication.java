package com.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.springcloud.controller")
public class FeignConsumerLoggerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerLoggerApplication.class, args);
	}
}
