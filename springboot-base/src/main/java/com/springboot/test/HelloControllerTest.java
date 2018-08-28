package com.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.springboot.controller.HelloController;

//import junit.framework.TestCase;

@SpringBootTest(classes=HelloController.class)//SpringBoot test 支持 
@WebAppConfiguration //web项目，按照Web方式运行测试
@RunWith(SpringJUnit4ClassRunner.class) //Spring junit的启动支持类
public class HelloControllerTest {
	
	@Autowired
//	private HelloController hello;
	
	@Test
	public void testHello() {
//		TestCase.assertEquals(hello.testThymeleaf(), "welcome springboot test");
	}
	
}
