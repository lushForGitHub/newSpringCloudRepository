package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("test-service") //绑定服务名称
public interface FeignServiceClient {
	
	//绑定服务内的方法
	@RequestMapping(value = "/index/hello", method = RequestMethod.GET)
	public String helloConsumer();
	
	/**
	 * 带参方法
	 * @RequestMapping(value = "/index/hello", method = RequestMethod.GET)
	 * public String helloConsumer(@RequestParam String name);
	 * public String helloConsumer(@RequestHeader String name,@RequestHeader Integer age);
	 * public String helloConsumer(@RequestBody User user);
	 */
	
}
