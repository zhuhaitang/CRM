package com.crm.system.service;

import com.crm.dto.Dto;
import com.crm.system.httpModel.model.User;

/**
 * 用户信息service
 * @author 朱海堂
 *
 */
public interface UserServiceI {
	
	/**
	 * 根据用户名和密码查询用户信息
	 * @param map
	 * @return
	 */
	public User getUserByNameAndPassword(Dto dto);
}
