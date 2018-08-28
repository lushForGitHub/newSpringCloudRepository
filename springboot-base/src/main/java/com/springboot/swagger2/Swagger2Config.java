package com.springboot.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration 			//让Spring加载该类配置
@EnableSwagger2			//启用Swagger2
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.springboot.controller"))
					.paths(PathSelectors.any())
					.build();
	}
	
	//创建api基本信息
	private ApiInfo apiInfo() {
		Contact contact=new Contact("@lushuai",
		          "http://localhost:8084/swagger-ui.html","ls75650@foxmail.com");
		return new ApiInfoBuilder()
				.title("集成Swagger2构建RESTful APIs")
				.description("集成Swagger2构建RESTful APIs")
//				.termsOfServiceUrl("https://www.baidu.com")
//				.contact("@lushuai")
				.contact(contact)
				.version("1.0")
				.build();
	}

}
