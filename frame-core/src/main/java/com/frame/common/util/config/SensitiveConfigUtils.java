
package com.frame.common.util.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.frame.common.contant.RedisContants.RedisDictContants;
import com.frame.common.entity.SensitiveInfoSO;
import com.frame.common.enums.SensitiveEnum;
import com.frame.common.util.common.CollectionListUtils;
import com.frame.common.util.redis.RedisUtils;

/**
 * 
 * [对象需要先进行注入    用于敏感字的过滤] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.config <br>
 */
public class SensitiveConfigUtils {

	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 
	 * [过滤敏感字] <br> 
	 * 	 
	 * @author [li.qiong]<br>
	 * @param sensitiveStr
	 * @return <br>
	 */
	public String filterSensitiveInfo(String sensitiveStr) {
		
		if(StringUtils.isEmpty(sensitiveStr)) {
			return sensitiveStr;
		}
		
		List<SensitiveInfoSO> list = redisUtils.getList(RedisDictContants.SENSITIVE_REDIS_KEY, SensitiveInfoSO.class);
		if(CollectionListUtils.isEmpty(list)) {
			return sensitiveStr;
		}
		
		for (SensitiveInfoSO sensitiveInfoSO : list) {
			
			String sensitiveText = sensitiveInfoSO.getSensitiveText();
			String replaceText = sensitiveInfoSO.getReplaceText();
			if(!sensitiveText.contains(replaceText)) {
				continue ;
			}
			
			// 中文和拼音
			if(sensitiveInfoSO.getTextType() == SensitiveEnum.CHINA_TYPE.type || sensitiveInfoSO.getTextType() == SensitiveEnum.PINYIN_TYPE.type) {
				String replaceStr = replaceStr(replaceText, sensitiveText.length());
				sensitiveStr = sensitiveStr.replaceAll(sensitiveText, replaceStr);
			}
			
			// 英语
			if(sensitiveInfoSO.getTextType() == SensitiveEnum.ENGLIST_TYPE.type) {
				sensitiveText = sensitiveInfoSO.getSensitiveText() + " ";
				String replaceStr = replaceStr(replaceText, sensitiveText.length());
				sensitiveStr = sensitiveStr.replaceAll(sensitiveText, replaceStr);
			}
		}
		
		return sensitiveStr;
		
	}
	
	/**
	 * 
	 * [替换敏感文字] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param replace
	 * @param length
	 * @return <br>
	 */
	private static String replaceStr(String replace, int length) {
		if(StringUtils.isEmpty(replace)) {
			replace = "*";
		}
		
		StringBuffer str = new StringBuffer();// 线程安全的
		replace = replace.trim();
		if(replace.length() == 1) {
			for(int i = 0; i < length; i++) {
				str.append(replace);
			}
			return str.toString();
		}
		return replace; 
	}
}

