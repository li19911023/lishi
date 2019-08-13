
package com.frame.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.frame.common.util.config.SensitiveConfigUtils;

/**
 * 
 * [对于util下的工具类进行注入] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.configuration <br>
 */
@Configuration
public class UtilsConfiguration {

	// 敏感字过滤
	@Bean
	public SensitiveConfigUtils getSensitiveConfigUtils() {
		return new SensitiveConfigUtils();
	}
}

