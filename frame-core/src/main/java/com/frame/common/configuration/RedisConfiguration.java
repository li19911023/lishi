package com.frame.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.frame.common.util.redis.RedisUtils;

/**
 * 
 * [redis模板配置] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.configuration <br>
 */
@Configuration
public class RedisConfiguration<V> {

	@Bean
	public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

		RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		//设置key的序列化方式
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.afterPropertiesSet();

		return redisTemplate;

	}
	
	//redis的工具类
	@Bean
	public RedisUtils getRedisUtils() {
		return new RedisUtils();
	}

}
