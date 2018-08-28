package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "test-service", fallbackFactory = HystrixFactory.class) // 绑定服务名称
public interface FeignServiceClient {

	// 绑定服务内的方法
	@RequestMapping(value = "/index/hello", method = RequestMethod.GET)
	public String helloConsumer();
}
