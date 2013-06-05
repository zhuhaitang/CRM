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
import com.crm.system.util.CodeHelper;

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
	@SuppressWarnings("rawtypes")
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
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TreeNode tree(Map t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId((String)t.get("id"));
		node.setText((String)t.get("name"));
		Map code = new HashMap();
		code.put("code", t.get("code"));
		node.setAttributes(code);
		Map m = new HashMap();
		m.put("parentId", t.get("id"));
		m.put("sort", "code");
		m.put("order", "asc");
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
	/**
	 * 保存部门信息
	 * @param Map
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void save(HashMap map) throws Exception {
		map.put("id", CodeHelper.createUUID());//生成id
		map.put("deleteFlag", StringConstans.FALSE);//删除状态
		map.put("type", StringConstans.TYPE_DISABLE);//是否允许删除
        map.put("code", setCode(map));
        baseDao.insert("DeptMapper.insertSelective",map);
		
	}
	
	/**
	 * 生成部门编号
	 * @param dept
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String setCode(HashMap map) throws Exception{
		Map m = new HashMap();
		m.put("id", map.get("parentId"));
		String maxCode = (String) baseDao.queryForObject("DeptMapper.selectCodeByParentId",map.get("parentId"));
		Map parentMap =  (Map) baseDao.queryForObject("DeptMapper.selectDeptForManage",m);
		return CodeHelper.getNextCode(maxCode, (String)parentMap.get("code"));
	}
	/**
	 * 修改部门信息
	 * @param map
	 */
	public void update(HashMap map) throws Exception {
		baseDao.update("DeptMapper.updateByPrimaryKeySelective", map);
	}
	/**
	 * 删除部门信息
	 */
	public void delete(String ids) throws Exception {
		for(String id:ids.split(",")){
			baseDao.delete("DeptMapper.deleteByPrimaryKey", id);
		}
	}
}
