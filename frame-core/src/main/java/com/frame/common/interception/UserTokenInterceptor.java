package com.frame.common.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.frame.common.util.redis.RedisUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * [用户信息处理拦截器] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.interception <br>
 */
@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {
	
//	@Autowired
//	private RedisUtils redisUtils;
	
//	private static final String NONAUTH_PATH = "/nonauth"; // 不需要认证
//	private static final String ERROR_PATH = "/error"; // 错误信息
//	private static final String DICT_PATH = "/dict"; // 字典接口
//	
//	private static final String SWAGGER_PATH = "/"; // swagger接口api

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
//		String url = request.getRequestURI();
//		// 配置URL不拦截
//		if(url.startsWith(NONAUTH_PATH) || url.startsWith(ERROR_PATH) || url.startsWith(DICT_PATH) 
//				){
//			log.info("<== preHandle - 配置URL不走认证.  url={}", url);
//			return true;
//		}
//		
//		if(url.equals(SWAGGER_PATH)) {
//			return true;
//		}
//		
//		// 其他的接口是需要进行认证的
//		String frontUserToken = FrontContants.FRONT_USER_TOKEN;
//		String uuid = request.getHeader(frontUserToken);
//		if(StringUtils.isNotEmpty(uuid)) {
//			String userNo = redisUtils.getString(UserIdent.WECHAT_USER_LOGIN_REDIS_KEY + uuid);
//			if(StringUtils.isEmpty(userNo)) {
//				throw new CommonException(RespJson.sessionFailed());
//			} else {
//				return true;
//			}
//		} else {
//			throw new CommonException(RespJson.failed(ErrorCodeEnum.GL99990100));
//		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
	
}
