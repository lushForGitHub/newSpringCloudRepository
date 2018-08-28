package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * eureka配置安全认证时，feign通过该类进行账号密码配置
 * @author lushuai
 *
 */

@Configuration
public class CustomConfiguration2 {

	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
