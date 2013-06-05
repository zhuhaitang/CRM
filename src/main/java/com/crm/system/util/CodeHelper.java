package com.crm.system.util;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * 
*    
* 项目名称：dome0.2  
* 类名称：CodeHelper  
* 类描述：   生成id及code工具
* 创建人：Administrator  
* 创建时间：2012-12-6 下午12:22:21  
* 修改人：Administrator  
* 修改时间：2012-12-6 下午12:22:21  
* 修改备注：   
* @version：    1.0
*
 */
public class CodeHelper {
	
	/**
	 * 部门编号的每一分段长度，如为4则code将类似1234-5678
	 */
	public static int CODE_LENGTH = 4;
	
	public static String createUUID(){
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	}
	
	/**
	 * 获得下一个code
	 * @param map map包含的key有2个：“code”和“parentCode”
	 * @return
	 * @throws CRUDException
	 */
	public static String getNextCode(String selfCode, String parentCode) throws Exception{
		String nextCode = "";
		DecimalFormat df = new DecimalFormat("0000");
		
		
		if(parentCode != null && !parentCode.equals("")){
			if(selfCode == null || selfCode.equals("")){//第一次添加部门的子部门
				nextCode = parentCode + "-0001";
			}else{
				int beginIndex = selfCode.lastIndexOf("-");
				
				int lastNum = Integer.parseInt(selfCode.substring(beginIndex+1))+1;
				
				nextCode = selfCode.substring(0,beginIndex+1)+df.format(lastNum);
			}
		}else{
			if(selfCode == null || selfCode.equals("")){
				nextCode = "0001";
			}else{
				
				int lastNum = Integer.parseInt(selfCode)+1;
				
				nextCode = df.format(lastNum);
				
			}
		}
			
		return nextCode;
	}
}
