package com.crm.system.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.crm.system.model.User;
import com.crm.system.service.UserServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class UserTestService {
	private static final Logger logger = Logger.getLogger(UserTestService.class);
	@Autowired
	private UserServiceI userService;
	
	@Test
	public void test1() {
		User u = userService.getUserById("19d6bf1f5f304063a53f8262fef91687");
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
	}
}
