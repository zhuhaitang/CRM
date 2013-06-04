package com.crm.system.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
	public PageJson findDept(Page page,HashMap map)throws SQLException;
	/**
	 * 查询部门树 
	 * @param id
	 * @return
	 */
	public List<TreeNode> findTree(HashMap map);
}
