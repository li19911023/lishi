package com.frame.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.frame.common.interception.UserTokenInterceptor;

/**
 * 
 * [拦截器配置类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.configuration <br>
 */
@Configuration
public class InterceptorConfiguration {

	@Bean
	public UserTokenInterceptor tokenInterceptor(){
		return new UserTokenInterceptor();
	}

}
