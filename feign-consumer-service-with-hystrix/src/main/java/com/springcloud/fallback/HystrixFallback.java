package com.springcloud.fallback;

import org.springframework.stereotype.Component;

import com.springcloud.feign.FeignServiceClient;


@Component
public class HystrixFallback implements FeignServiceClient {

	@Override
	public String helloConsumer() {
		String str = new String();
		str = "error";
		return str;
	}
}

