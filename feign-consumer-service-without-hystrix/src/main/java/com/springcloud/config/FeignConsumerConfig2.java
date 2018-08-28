package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;


/**
 * 禁用Hystrix回退
 * @author lushuai
 *
 */
@Configuration
public class FeignConsumerConfig2 {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
