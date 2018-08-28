package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.springcloud.config.CustomConfiguration;

import feign.RequestLine;

@FeignClient(name = "test-service", configuration = CustomConfiguration.class) //绑定服务名称
public interface FeignServiceClient {
	
	//绑定服务内的方法
	@RequestLine("GET /index/hello")
	public String helloConsumer();
	
}
