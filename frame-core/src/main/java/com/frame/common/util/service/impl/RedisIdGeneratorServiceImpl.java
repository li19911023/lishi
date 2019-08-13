
package com.frame.common.util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.common.contant.RedisContants.UserIdent;
import com.frame.common.enums.id.IdGeneratorEnum;
import com.frame.common.util.redis.RedisUtils;
import com.frame.common.util.service.RedisIdGeneratorService;

/**
 * 
 * [编号服务] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.1<br>
 * @CreateDate 2018年12月17日 <br>
 * @since v1.0.1<br>
 * @see com.byjz.housecloud.common.util.service.impl <br>
 */
@Service
public class RedisIdGeneratorServiceImpl implements RedisIdGeneratorService {

	@Autowired
	private RedisUtils redisUtils;
	
	
	/**
	 * 
	 * [用户编号] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param generator
	 * @return <br>
	 */
	@Override
	public String generatorUserNo(IdGeneratorEnum generator) {
		int length = generator.getLength();
		long value = redisUtils.incrby(UserIdent.USER_ID_REDIS_KEY, 1);
		String val = String.format("%0" + length + "d", value);
		return new StringBuffer().append(generator.getPrefix()).append(val).toString();
	}

}

