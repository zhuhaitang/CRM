package com.crm.system.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crm.system.httpModel.model.User;

public class AuthorityInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
		if(modelAndView==null){
			modelAndView=new ModelAndView();
		}	
		modelAndView.getModel().put("path", request.getContextPath());
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestPath = request.getServletPath();
		if(requestPath.indexOf("Controller")!=-1){
			if(requestPath.equals("/loginController/validate")){
				return true;
			}
			if(requestPath.equals("/loginController/login")){
				return true;
			}
			User user = (User) request.getSession().getAttribute("userInfo");
			if(user!=null){
				return true;
			}else{
				request.getRequestDispatcher("/loginController/login").forward(request, response);
				return false;
			}
		}else{
			return true;
		}
	}
}
