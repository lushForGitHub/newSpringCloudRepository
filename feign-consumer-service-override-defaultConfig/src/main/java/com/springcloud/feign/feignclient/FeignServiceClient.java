package com.springcloud.feign.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.springcloud.config.CustomConfiguration;

import feign.RequestLine;

/**
 * 使用自定义的CustomConfiguration中feign默认的注解
 * @author lushuai
 *
 */
@FeignClient(name = "test-service", configuration = CustomConfiguration.class) 
//绑定服务名称,调用自定义的configration
public interface FeignServiceClient {
	
	//使用feign默认的注解，绑定服务内的方法
	@RequestLine("GET /index/hello")
	public String helloConsumer();
}
