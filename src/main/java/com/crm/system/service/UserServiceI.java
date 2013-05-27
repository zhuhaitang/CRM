package com.crm.system.service;

import com.crm.system.model.User;

/**
 * 用户信息service
 * @author 朱海堂
 *
 */
public interface UserServiceI {
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(String id);
}
