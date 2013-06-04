package com.crm.system.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.system.constans.StringConstans;
import com.crm.system.dao.BaseDaoI;
import com.crm.system.httpModel.base.Page;
import com.crm.system.httpModel.base.PageJson;
import com.crm.system.httpModel.base.TreeNode;
import com.crm.system.service.DeptServiceI;

/**
 * 部门信息service
 * @author Administrator
 *
 */
@Service("DeptService")
public class DeptServiceImpl implements DeptServiceI {
	@Autowired
	private BaseDaoI baseDao;
	/**
	 * 查询部门信息
	 * @param page
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public PageJson findDept(Page page, HashMap map) throws SQLException {
		PageJson pageJson = new PageJson();
		pageJson.setRows(baseDao.queryForPage("DeptMapper.selectDeptForManage", map,page));
		pageJson.setTotal((Long)baseDao.queryForObject("DeptMapper.selectDeptForCount", map));
		return pageJson;
	}
	/**
	 * 查询部门树 
	 * @param id
	 * @return
	 */
	public List<TreeNode> findTree(HashMap map){
		List<TreeNode> TreeNodes = new ArrayList<TreeNode>();
		List<Object> values = new ArrayList<Object>();
		if(map==null||map.get("id")==null||"".equals(map.get("id"))){
			map.put("id", StringConstans.ROOT_ID);
		}
		List<Map> list = baseDao.queryForList("DeptMapper.selectDeptForManage",map);
		 for (Map t : list){
            TreeNodes.add(tree(t,true));
        }
		return TreeNodes;
	}
	/**
	 * 生成树节点
	 * @param t
	 * @param recursive
	 * @return
	 */
	private TreeNode tree(Map t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId((String)t.get("id"));
		node.setText((String)t.get("name"));
		Map m = new HashMap();
		m.put("parentId", t.get("id"));
		List list=  baseDao.queryForList("DeptMapper.selectDeptForManage",m);
		if (list != null && list.size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Map> l = list;
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Map r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
				node.setState("open");
			}
		}
		return node;
	}
}
