package com.crm.system.service;

import java.sql.SQLException;
import java.util.List;

import com.crm.dto.Dto;
import com.crm.system.httpModel.base.Page;
import com.crm.system.httpModel.base.PageJson;
import com.crm.system.httpModel.base.TreeNode;

public interface DeptServiceI {
	/**
	 * 查询部门信息
	 * @param page
	 * @param map
	 * @return
	 */
	public PageJson findDept(Page page,Dto dto)throws SQLException;
	/**
	 * 查询部门树 
	 * @param id
	 * @return
	 */
	public List<TreeNode> findTree(Dto dto);
	/**
	 * 保存部门信息
	 * @param Map
	 * @throws Exception
	 */
	public void save(Dto dto) throws Exception;
	/**
	 * 修改部门信息
	 * @param map
	 */
	public void update(Dto dto)throws Exception;
	/**
	 * 删除部门信息
	 * @param id
	 * @throws Exception
	 */
	public void delete(String codes)throws Exception;
}
