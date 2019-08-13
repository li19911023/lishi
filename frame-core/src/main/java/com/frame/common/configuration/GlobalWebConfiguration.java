package com.frame.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.frame.common.interception.UserTokenInterceptor;

/**
 * 
 * [跨域处理] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.configuration <br>
 */
@Configuration
public class GlobalWebConfiguration {


	@Autowired
	private UserTokenInterceptor userTokenInterceptor;
	
	@Bean
	public WebMvcConfigurer webConfigurer(){


		return new WebMvcConfigurer() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {

				registry.addInterceptor(userTokenInterceptor).addPathPatterns("/**")
					.excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html", "/webjars/**", "/csrf/**");

			}	
		};
	}


	@Bean
	public CorsFilter corsFilter(){
		//1.添加CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		//放行哪些原始域
		config.addAllowedOrigin("*");
		//是否发送Cookie信息
		config.setAllowCredentials(true);
		//放行哪些原始域(请求方式)
		config.addAllowedMethod("*");
		//放行哪些原始域(头部信息)
		config.addAllowedHeader("*");
		//暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
		//config.addExposedHeader("*");
		config.addExposedHeader("Cache-Control,Content-Language, Content-Type, Expires, Last-Modified,Pragma");

		//2.添加映射路径
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);

		//3.返回新的CorsFilter.
		return new CorsFilter(configSource);
	}


}
