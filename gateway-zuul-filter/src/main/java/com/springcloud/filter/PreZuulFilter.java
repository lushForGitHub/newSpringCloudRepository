package com.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreZuulFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);

	/**
	 * 过滤器具体逻辑
	 */
	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String host = request.getRemoteHost();
		logger.info("请求的HOST " + host);
		return null;
	}

	/**
	 * 是否启用过滤器
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 执行顺序，数字越大越靠后
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 类型
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
