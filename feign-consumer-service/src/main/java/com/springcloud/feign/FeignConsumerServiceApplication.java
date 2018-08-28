package com.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FeignConsumerServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerServiceApplication.class, args);
	}
}
