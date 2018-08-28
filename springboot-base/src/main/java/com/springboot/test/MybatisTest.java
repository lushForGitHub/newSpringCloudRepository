package com.springboot.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.TestSpringbootApplication;
import com.springboot.bean.User;
import com.springboot.mybatis.UserMapper;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSpringbootApplication.class)
public class MybatisTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	
	@Autowired
	private UserMapper userMapper;
	
//	@Test
//	public void testInsert() {
//		int num = userMapper.insert("test2", 16, "上海-南京路", "1111");
//		TestCase.assertEquals(num, 1);
//		logger.info("num = "+ num);
//	}

//	@Test
//	public void testFindById() {
//		User user = userMapper.findById("2");
//		TestCase.assertNotNull(user);
//		logger.info("userInfo: name = "+ user.getName() + ", age = "+ user.getAge()
//					+", address = "+ user.getAddress() + ", phone = "+ user.getPhone());
//	}
	
	@Test
	public void testFindAll() {
		List<User> userList = userMapper.findAll();
		TestCase.assertNotNull(userList);
		for(User user : userList) {
			logger.info("userInfo: name = "+ user.getName() + ", age = "+ user.getAge()
			+", address = "+ user.getAddress() + ", phone = "+ user.getPhone());
		}
		
	}
}
