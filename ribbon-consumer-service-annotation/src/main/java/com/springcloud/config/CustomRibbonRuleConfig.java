package com.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
@ExcludeComponetScan
public class CustomRibbonRuleConfig {

	@Autowired
	IClientConfig clientConfig;
	
	@Bean
	public IRule customRandom(IClientConfig clientConfig) {
		return new RandomRule();
	}
}
