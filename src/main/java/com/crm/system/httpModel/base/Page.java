package com.crm.system.httpModel.base;

import java.io.Serializable;

/**
 	* 
	*    
	* 项目名称：dome0.2  
	* 类名称：Page  
	* 类描述：   分页工具类
	* 创建人：朱海堂  
	* 创建时间：2012-10-28 上午1:18:16  
	* 修改人：朱海堂  
	* 修改时间：2012-10-28 上午1:18:16  
	* 修改备注：   
	* @version：    1.0
	*
 */
public class Page implements Serializable{
	
	private static final long serialVersionUID = -5477914317324525624L;
	
	private Long total;//总数
	private int page;//json当前页码
	private int rows;//json每页记录数
	private String sort = null;// 排序字段名
	private String order = "asc";// 按什么排序(asc,desc)

	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
