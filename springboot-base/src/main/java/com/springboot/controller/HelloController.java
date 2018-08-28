package com.springboot.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RequestMapping("/index")
@RestController //无法使用视图映射渲染
public class HelloController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private EurekaClient eurekaClient;

	@GetMapping("/hello")
	public String hello() {
		@SuppressWarnings("deprecation")
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		return "/hello, host: " + instance.getHost() + ", service_ID: "+ instance.getServiceId();
	}
	
	@GetMapping("/eureka-server")
	public String serviceUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("test-service", false);
	    return instance.getHomePageUrl();
	}
	
	@RequestMapping(value = "/thymeleaf",method = RequestMethod.GET)
	public ModelAndView testThymeleaf(@Param("thymeleaf") String thymeleaf) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/thymeleaf/index");
		mav.addObject("thymeleaf", thymeleaf);
		return mav;
	}
	
	@GetMapping("/freemarker")
	@ResponseBody
	public ModelAndView getFreemarker(@Param("name") String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/freemarker/index");
		mav.addObject("name", name);
		return mav;
	}
}
