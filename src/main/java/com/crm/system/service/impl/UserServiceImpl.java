package com.crm.system.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.system.dao.BaseDaoI;
import com.crm.system.httpModel.model.User;
import com.crm.system.service.UserServiceI;

/**
 * 用户信息service
 * @author 朱海堂
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private BaseDaoI baseDao;

	/**
	 * 根据用户名和密码查询用户信息
	 * @param map
	 * @return
	 */
	public User getUserByNameAndPassword(HashMap map) {
		return (User)baseDao.queryForObject("UserMapper.selectByNameAndPassword", map);
	}
}
