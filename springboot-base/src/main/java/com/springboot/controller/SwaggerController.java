package com.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/swgger")
public class SwaggerController {
	
	
	@ApiOperation(value="获取最终结果",notes="根据id获取传输的值")
	@ApiImplicitParam(name="id",value="结果",required=true,dataType="String")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Map<String,String> getInfo(@PathVariable String id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "SwaggerController");
		map.put("value", "Is success");
		return map;
	}

}
