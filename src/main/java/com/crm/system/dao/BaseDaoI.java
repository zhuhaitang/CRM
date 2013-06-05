package com.crm.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.crm.system.httpModel.base.Page;

/**
 * 基础数据库操作类接口
 * @author Administrator
 *
 */
public interface BaseDaoI {
	/**
	 * 插入一条记录
	 * @param SQL语句ID号
	 * @param parameterObject 要插入的对象(map javaBean)
	 */
	public void insert(String statementName, Object parameterObject);
	
	/**
	 * 插入一条记录
	 * @param SQL语句ID号
	 */
	public void insert(String statementName);
	
	/**
	 * 查询一条记录
	 * @param SQL语句ID号
	 * @param parameterObject 查询条件对象(map javaBean)
	 */
	public Object queryForObject(String statementName, Object parameterObject);
	
	/**
	 * 查询一条记录
	 * @param SQL语句ID号
	 */
	public Object queryForObject(String statementName);
	
	/**
	 * 查询记录集合
	 * @param SQL语句ID号
	 * @param parameterObject 查询条件对象(map javaBean)
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName, Object parameterObject);
	
	/**
	 * 查询记录集合
	 * @param SQL语句ID号
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName);
	
	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	@SuppressWarnings("rawtypes")
	public List queryForPage(String statementName,Object parameterObject,Page page) throws SQLException;
	
	/**
	 * 更新记录
	 * @param SQL语句ID号
	 * @param parameterObject 更新对象(map javaBean)
	 */
	public int update(String statementName, Object parameterObject);
	
	/**
	 * 更新记录
	 * @param SQL语句ID号
	 */
	public int update(String statementName);
	
	/**
	 * 删除记录
	 * @param SQL语句ID号
	 * @param parameterObject 更新对象(map javaBean)
	 */
	public int delete(String statementName, Object parameterObject);
	
	/**
	 * 删除记录
	 * @param SQL语句ID号
	 */
	public int delete(String statementName);
}
