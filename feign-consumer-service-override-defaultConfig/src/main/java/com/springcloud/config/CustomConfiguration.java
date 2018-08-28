package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class CustomConfiguration {
	
	@Bean
	public Contract feignContract(){
		return new feign.Contract.Default();//feign默认注解
	}
	
}