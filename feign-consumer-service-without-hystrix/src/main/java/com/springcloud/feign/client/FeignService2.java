package com.springcloud.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcloud.config.FeignConsumerConfig2;
import com.springcloud.feign.fallback.FeignServiceClient2Fallback;

@FeignClient(name = "xxxx", url = "http://localhost:8095/", configuration = FeignConsumerConfig2.class, fallback = FeignServiceClient2Fallback.class)
public interface FeignService2 {

	@GetMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName")String serviceName);
}
