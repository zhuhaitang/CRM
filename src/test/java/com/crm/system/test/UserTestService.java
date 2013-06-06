package com.crm.system.test;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.crm.system.httpModel.model.User;
import com.crm.system.service.UserServiceI;
import com.crm.system.util.Dto;
import com.crm.system.util.impl.BaseDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class UserTestService {
	private static final Logger logger = Logger.getLogger(UserTestService.class);
	@Autowired
	private UserServiceI userService;
	
	@Test
	public void test1() throws SQLException {
		Dto map = new BaseDto();
		map.put("account", "system");
		map.put("password", "e10adc3949ba59abbe56e057f20f883e");
		User u = userService.getUserByNameAndPassword(map);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
	}
}
