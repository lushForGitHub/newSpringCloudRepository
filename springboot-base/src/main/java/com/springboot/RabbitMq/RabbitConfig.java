package com.springboot.RabbitMq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类主要用来配置队列、交换器、路由等高级信息
 * @author lushuai
 *
 */

@Configuration
public class RabbitConfig {

	@Bean
	public Queue firstQueue() {
		//创建一个队列，名称为first
		return new Queue("first");
	}
}
