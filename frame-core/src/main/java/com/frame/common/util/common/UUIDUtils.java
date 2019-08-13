
package com.frame.common.util.common;

import java.util.UUID;

/**
 * 
 * [对UUID进行封装处理] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月3日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class UUIDUtils {

	/**
	 * 
	 * [获取UUID信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
}

