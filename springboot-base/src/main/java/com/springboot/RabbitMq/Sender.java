package com.springboot.RabbitMq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	/**
	 * 通过注入AmqpTemplate接口的实例来实现消息的发送，
	 * AmqpTemplate接口定义了一套针对AMQP协议的基础操作
	 */
	public void send() {
		rabbitTemplate.convertAndSend("first", "test rabbitmq message");
	}
	
}
