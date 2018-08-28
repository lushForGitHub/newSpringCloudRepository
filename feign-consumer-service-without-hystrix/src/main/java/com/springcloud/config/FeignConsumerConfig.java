package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class FeignConsumerConfig {
	
	@Bean
	public Contract feignContract(){
		return new Contract.Default();//feign默认注解
	}
	
}