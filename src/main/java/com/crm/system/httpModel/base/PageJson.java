package com.crm.system.httpModel.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
*    
* 项目名称：dome0.2  
* 类名称：PageJson  
* 类描述：   分页返回信息
* 创建人：朱海堂  
* 创建时间：2012-10-28 上午1:19:03  
* 修改人：朱海堂  
* 修改时间：2012-10-28 上午1:19:03  
* 修改备注：   
* @version：    1.0
*
 */
@SuppressWarnings("rawtypes")
public class PageJson implements Serializable{
	
	private static final long serialVersionUID = -4664804695593232363L;
	
	private Long total;// 总记录数
	private List rows;// 每行记录
	
	public PageJson(){
		
	}
	public PageJson(Long total,List rows){
		this.total=total;
		this.rows=rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
