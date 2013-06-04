package com.crm.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crm.system.constans.UserConstans;
import com.crm.system.service.UserServiceI;

/**
 * 用户信息控制层
 * @author 朱海堂
 *
 */
@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private UserServiceI userService;
	
	/**
	 * 根据id查询用户信息
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/getUserById/{id}")
	public ModelAndView getUserById(ModelMap modelMap,@PathVariable String id) {
		//modelMap.put("user", userService.getUserById(id));
		return new ModelAndView(UserConstans.LIST_USER, modelMap);
	}
}
