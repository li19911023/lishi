
package com.frame.common.util.common;

import org.springframework.beans.BeanUtils;

/**
 * 
 * [POJO转换] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class ObjectConvertUtils {

	/**
	 * 
	 * [两个POJO对象进行转换] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param t
	 * @param b
	 * @return <br>
	 */
	public static <T, B> B objectConvert(T t, B b) {
		if(t != null) {
			BeanUtils.copyProperties(t, b);
			return b;
		}
		return null;
	}
	
}

