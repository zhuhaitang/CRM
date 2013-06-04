package com.crm.system.util;

import java.security.MessageDigest;
/**
 * 
*    
* 项目名称：dome0.2  
* 类名称：MD5  
* 类描述：  加密工具
* 创建人：Administrator  
* 创建时间：2012-12-6 下午12:23:56  
* 修改人：Administrator  
* 修改时间：2012-12-6 下午12:23:56  
* 修改备注：   
* @version：    1.0
*
 */
public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
}
