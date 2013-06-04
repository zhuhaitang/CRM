package com.crm.system.httpModel.base;


import java.io.Serializable;

/**
 * 
*    
* 项目名称：dome0.2  
* 类名称：Message  
* 类描述：  返回页面信息提示
* 创建人：Administrator  
* 创建时间：2012-10-28 上午1:16:44  
* 修改人：Administrator  
* 修改时间：2012-10-28 上午1:16:44  
* 修改备注：   
* @version：    1.0
*
 */
public class Message implements Serializable{

	private static final long serialVersionUID = -2070718819868643179L;
	
	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 其他信息
	
	public Message(){
		
	}
	public Message(String msg){
		this.msg=msg;
	}
	public Message(String msg,boolean success){
		this.success=success;
		this.msg=msg;
	}
	public Message(String msg,boolean success,Object obj){
		this.success=success;
		this.msg=msg;
		this.obj=obj;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
