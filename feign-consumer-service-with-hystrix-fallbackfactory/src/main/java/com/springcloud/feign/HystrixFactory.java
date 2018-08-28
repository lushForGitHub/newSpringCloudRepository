package com.springcloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixFactory implements FallbackFactory<FeignServiceClient> {

	private static final Logger LOGGER = LoggerFactory.getLogger(HystrixFactory.class);

	@Override
	public FeignServiceClient create(Throwable cause) {
		HystrixFactory.LOGGER.info("fallback: reason was: {}", cause.getMessage());

		return new FeignClientFactory() {

			@Override
			public String helloConsumer() {
				return "fallback";
			}
		};
	}

}
