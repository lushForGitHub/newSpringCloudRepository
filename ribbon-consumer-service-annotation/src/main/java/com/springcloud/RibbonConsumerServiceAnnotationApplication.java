package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.springcloud.config.CustomRibbonRuleConfig;
import com.springcloud.config.ExcludeComponetScan;

@SpringBootApplication
@EnableDiscoveryClient
//定义名为test-service的微服务，负载均衡策略采用自定义的CustomRibbonRuleConfig
@RibbonClient(name = "test-service", configuration = CustomRibbonRuleConfig.class)
//配置类和启动类为同一包下，扫描时，不扫描带有ExcludeComponetScan的类
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeComponetScan.class) })
public class RibbonConsumerServiceAnnotationApplication {
	
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerServiceAnnotationApplication.class, args);
	}
}
