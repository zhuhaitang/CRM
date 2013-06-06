package com.crm.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.crm.system.constans.LoginConstans;
import com.crm.system.httpModel.base.Message;
import com.crm.system.httpModel.model.User;
import com.crm.system.service.UserServiceI;
import com.crm.system.util.Dto;
import com.crm.system.util.MD5;
import com.crm.system.util.impl.BaseDto;

/**
 * 用户登录控制层
 * @author 朱海堂
 *
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
	@Autowired
	private UserServiceI userService;
	/**
	 * 初始化登录页面
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/login")
	public ModelAndView login(ModelMap modelMap) {
		return new ModelAndView(LoginConstans.LOGIN_INIT, modelMap);
	}
	
	/**
	 * 用户登录验证
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/validate")
	@ResponseBody
	public Message validate(ModelMap modelMap,HttpServletRequest request,HttpSession session){
		boolean success = false;
		Dto dto =new BaseDto();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		dto.put("account", account);
		dto.put("password",MD5.md5(password));
		User user = userService.getUserByNameAndPassword(dto);
		if(user!=null){
			session.setAttribute("userInfo", user);
			success=true;
		}
		return new Message("",success);
	}
	
	/**
	 * 初始化main页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView main(ModelMap modelMap,HttpSession session) {
		return new ModelAndView(LoginConstans.MAIN_INIT, modelMap);
	}
	/**
	 * 退出
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
    public ModelAndView logout(ModelMap modelMap, HttpSession session){
        session.invalidate();
        return new ModelAndView(LoginConstans.LOGIN_INIT, modelMap);
    }
}
