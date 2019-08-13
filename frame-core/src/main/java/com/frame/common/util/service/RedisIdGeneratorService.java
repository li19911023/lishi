
package com.frame.common.util.service;

import com.frame.common.enums.id.IdGeneratorEnum;

/**
 * 
 * [编号生成工具类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.1<br>
 * @CreateDate 2018年12月17日 <br>
 * @since v1.0.1<br>
 * @see com.byjz.housecloud.common.util.number <br>
 */
public interface RedisIdGeneratorService {
	
	/**
	 * 
	 * [用户编号] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param generator
	 * @return <br>
	 */
	public abstract String generatorUserNo(IdGeneratorEnum generator);
	
}

