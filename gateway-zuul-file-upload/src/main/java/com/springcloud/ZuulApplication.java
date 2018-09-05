package com.springcloud;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

	// private int maxUploadSizeMb = 20 * 1024 * 1024;

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	// 重置tomcat连接
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
					((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
				}
			}
		});
		return tomcat;
	}
}
