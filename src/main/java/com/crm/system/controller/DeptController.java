package com.crm.system.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crm.system.constans.DeptConstans;
import com.crm.system.httpModel.base.Page;
import com.crm.system.httpModel.base.PageJson;
import com.crm.system.httpModel.base.TreeNode;
import com.crm.system.service.DeptServiceI;
import com.crm.system.util.WebUtils;

/**
 * 
*    
* 项目名称：dome0.2  
* 类名称：DeptController  
* 类描述：   部门管理控制层
* 创建人：Administrator  
* 创建时间：2012-12-3 下午5:53:44  
* 修改人：Administrator  
* 修改时间：2012-12-3 下午5:53:44  
* 修改备注：   
* @version：    1.0
*
 */
@Controller
@RequestMapping(value = "deptController")
public class DeptController {
	@Autowired
	private DeptServiceI deptService;
	/**
	 * 初始化部门信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/initDept")
	public ModelAndView initDept(ModelMap modelMap){
		return new ModelAndView(DeptConstans.LIST_DEPT, modelMap);
	}
	/**
	 * 查询部门信息
	 * @param page
	 * @param request
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/findDept")
	@ResponseBody
	public PageJson findDept(Page page,HttpServletRequest request) throws SQLException{
		return deptService.findDept(page, WebUtils.getPraramsAsMap(request));  
	}
	/**
	 * 查询部门树
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findTree")
	@ResponseBody
	public List<TreeNode> findTree(HttpServletRequest request){
		return deptService.findTree(WebUtils.getPraramsAsMap(request));
	}
	/**
	 * 初始化form页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/initForm")
	public ModelAndView initSave(ModelMap modelMap){
		return new ModelAndView(DeptConstans.FORM_DEPT, modelMap);
	}
}
