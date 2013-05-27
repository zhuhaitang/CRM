package com.crm.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.crm.system.constans.LoginConstans;

/**
 * 用户登录控制层
 * @author 朱海堂
 *
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
	/**
	 * 根据id查询用户信息
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/init")
	public ModelAndView init(ModelMap modelMap) {
		return new ModelAndView(LoginConstans.LOGIN_INIT, modelMap);
	}
}
