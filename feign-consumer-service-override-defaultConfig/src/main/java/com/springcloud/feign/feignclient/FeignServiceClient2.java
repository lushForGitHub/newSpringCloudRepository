package com.springcloud.feign.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 测试定义的FeignServiceClient所绑定的服务是否会连接到其他服务
 * @author lushuai
 *
 */

@FeignClient(name = "xxxx", url = "http://localhost:8095/")
//eureka配置安全认证时，feign通过CustomConfiguration2开启账号密码认证
//@FeignClient(name = "xxxx", url = "http://localhost:8095/", configuration = CustomConfiguration2.class)
//有url时候，那么属性只是为client命名，若无url，且与eureka连用，name则是服务名称
public interface FeignServiceClient2 {

	@GetMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName")String serviceName);
}
