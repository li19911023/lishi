
package com.burylovehome.demo.util;

import org.springframework.beans.BeanUtils;

/**
 * 
 * [POJO转换] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.util <br>
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

