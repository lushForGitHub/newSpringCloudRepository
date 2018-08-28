package com.springcloud.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.springcloud.config.FeignConsumerConfig;
import com.springcloud.feign.fallback.HystrixFallback;

import feign.RequestLine;


@FeignClient(name = "test-service", configuration = FeignConsumerConfig.class, fallback = HystrixFallback.class) // 绑定服务名称
public interface FeignService {

	// 绑定服务内的方法
	@RequestLine("GET /index/hello")
	public String helloConsumer();
}
