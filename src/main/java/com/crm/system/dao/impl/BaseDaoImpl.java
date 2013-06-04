package com.crm.system.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crm.system.dao.BaseDaoI;
import com.crm.system.httpModel.base.Page;

@Repository("BaseDao")
public class BaseDaoImpl implements BaseDaoI{
	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);
	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate; 
	
	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            要插入的对象(map javaBean)
	 */
	public void insert(String statementName, Object parameterObject) {
		sqlSessionTemplate.insert(statementName, parameterObject);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 */
	public void insert(String statementName) {
		sqlSessionTemplate.insert(statementName);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public Object queryForObject(String statementName, Object parameterObject) {
		return sqlSessionTemplate.selectOne(statementName, parameterObject);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 */
	public Object queryForObject(String statementName) {
		return sqlSessionTemplate.selectOne(statementName);
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	public List queryForList(String statementName, Object parameterObject) {
		return sqlSessionTemplate.selectList(statementName, parameterObject);
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 */
	public List queryForList(String statementName) {
		return sqlSessionTemplate.selectList(statementName);
	}

	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 * @throws SQLException 
	 */
	public List queryForPage(String statementName, Object parameterObject,Page page) throws SQLException {
		return sqlSessionTemplate.selectList(statementName, parameterObject,new RowBounds((page.getPage() - 1) * page.getRows(),page.getRows()));
	}

	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	public int update(String statementName, Object parameterObject) {
		return sqlSessionTemplate.update(statementName, parameterObject);
	}

	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 */
	public int update(String statementName) {
		return sqlSessionTemplate.update(statementName);
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	public int delete(String statementName, Object parameterObject) {
		return sqlSessionTemplate.delete(statementName, parameterObject);
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 */
	public int delete(String statementName) {
		return sqlSessionTemplate.delete(statementName);
	}
}
