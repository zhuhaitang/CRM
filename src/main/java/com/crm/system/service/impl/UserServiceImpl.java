package com.crm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.system.dao.UserMapper;
import com.crm.system.model.User;
import com.crm.system.service.UserServiceI;

/**
 * 用户信息service
 * @author 朱海堂
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
