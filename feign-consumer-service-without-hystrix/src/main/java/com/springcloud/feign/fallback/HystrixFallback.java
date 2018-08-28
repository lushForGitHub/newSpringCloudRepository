package com.springcloud.feign.fallback;

import org.springframework.stereotype.Component;

import com.springcloud.feign.client.FeignService;


@Component
public class HystrixFallback implements FeignService {

	public String helloConsumer() {
		String str = new String();
		str = "error";
		return str;
	}
}

