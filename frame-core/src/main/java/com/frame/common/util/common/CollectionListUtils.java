
package com.frame.common.util.common;

import java.util.Collection;

/**
 * 
 * [list集合处理类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class CollectionListUtils {

	/**
	 * 
	 * [判断集合为空] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param collection
	 * @return <br>
	 */
	public static <T> Boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * 
	 * [判断集合不为空] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param collection
	 * @return <br>
	 */
	public static <T> Boolean isNotEmpty(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}
}

