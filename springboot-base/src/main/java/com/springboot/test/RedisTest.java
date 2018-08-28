package com.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.TestSpringbootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSpringbootApplication.class)
public class RedisTest {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void testRedis() throws Exception{
		
		ValueOperations<String, String> vo = redisTemplate.opsForValue();
		vo.set("name", "RedisTestDemo");
		String value = vo.get("name");
		System.out.println("value: "+ value);
	}

}
