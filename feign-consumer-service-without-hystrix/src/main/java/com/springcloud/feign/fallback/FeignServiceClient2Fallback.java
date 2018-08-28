package com.springcloud.feign.fallback;

import org.springframework.stereotype.Component;

import com.springcloud.feign.client.FeignService2;

@Component
public class FeignServiceClient2Fallback implements FeignService2{

	@Override
	public String findServiceInfoFromEurekaByServiceName(String serviceName) {
		// TODO Auto-generated method stub
		return "error";
	}

}
