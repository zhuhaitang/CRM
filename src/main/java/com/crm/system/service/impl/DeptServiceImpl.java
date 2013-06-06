package com.crm.system.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.system.constans.StringConstans;
import com.crm.system.dao.BaseDaoI;
import com.crm.system.httpModel.base.Page;
import com.crm.system.httpModel.base.PageJson;
import com.crm.system.httpModel.base.TreeNode;
import com.crm.system.service.DeptServiceI;
import com.crm.system.util.CodeHelper;
import com.crm.system.util.Dto;
import com.crm.system.util.impl.BaseDto;

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
	public PageJson findDept(Page page, Dto dto) throws SQLException {
		PageJson pageJson = new PageJson();
		pageJson.setRows(baseDao.queryForPage("DeptMapper.selectDeptForManage",dto,page));
		pageJson.setTotal((Long)baseDao.queryForObject("DeptMapper.selectDeptForCount", dto));
		return pageJson;
	}
	/**
	 * 查询部门树 
	 * @param id
	 * @return
	 */
	public List<TreeNode> findTree(Dto dto){
		List<TreeNode> TreeNodes = new ArrayList<TreeNode>();
		List<Object> values = new ArrayList<Object>();
		if(dto==null||dto.getAsString("id")==null||"".equals(dto.getAsString("id"))){
			dto.put("id", StringConstans.ROOT_ID);
		}
		List list = baseDao.queryForList("DeptMapper.selectDeptForManage",dto);
		for (int i=0;i<list.size();i++){
			Dto t = (Dto) list.get(i);
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
	private TreeNode tree(Dto t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getAsString("id"));
		node.setText(t.getAsString("name"));
		node.setAttributes(new BaseDto("code",t.get("code")));
		Dto m = new BaseDto();
		m.put("parentId", t.get("id"));
		m.put("sort", "code");
		m.put("order", "asc");
		List list=  baseDao.queryForList("DeptMapper.selectDeptForManage",m);
		if (list != null && list.size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Dto> l = list;
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (Dto r : l) {
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
	public void save(Dto dto) throws Exception {
		dto.put("id", CodeHelper.createUUID());//生成id
		dto.put("deleteFlag", StringConstans.FALSE);//删除状态
		dto.put("type", StringConstans.TYPE_DISABLE);//是否允许删除
		dto.put("code", setCode(dto));
        baseDao.insert("DeptMapper.insertSelective",dto);
		
	}
	
	/**
	 * 生成部门编号
	 * @param dept
	 */
	private String setCode(Dto dto) throws Exception{
		Dto m = new BaseDto("id", dto.getAsString("parentId"));
		String maxCode = (String) baseDao.queryForObject("DeptMapper.selectCodeByParentId",dto.getAsString("parentId"));
		Dto parentDto =  (Dto) baseDao.queryForObject("DeptMapper.selectDeptForManage",m);
		return CodeHelper.getNextCode(maxCode,parentDto.getAsString("code"));
	}
	/**
	 * 修改部门信息
	 * @param map
	 */
	public void update(Dto dto) throws Exception {
		baseDao.update("DeptMapper.updateByPrimaryKeySelective", dto);
	}
	/**
	 * 删除部门信息
	 */
	public void delete(String codes) throws Exception {
		for(String code:codes.split(",")){
			baseDao.delete("DeptMapper.deleteByPrimaryKey", new BaseDto("code",code));
		}
	}
}
