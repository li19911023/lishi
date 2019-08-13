
package com.frame.common.contant;

/**
 * 
 * [redis的一些键常量信息] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月23日 <br>
 * @since v1.0.0<br>
 * @see com.runka.community.contant <br>
 */
public class RedisContants {

	/**
	 * 
	 * [微信的静态常量数据] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @version 1.0.0<br>
	 * @CreateDate 2019年7月25日 <br>
	 * @since v1.0.0<br>
	 * @see com.runka.common.contant <br>
	 */
	public static final class WechatContants {
		
		private WechatContants () {}
		
		// 把微信调用接口的accesstoken放在redis中的键
		public static final String ACCESS_TOKEN_REDIS_KEY_INFO = "ACCESS_TOKEN_REDIS_KEY_INFO";
		
		// 把微信前端调用接口的jsapi_ticket放在redis中的键
		public static final String JSAPI_TICKET_REDIS_KEY_INFO = "JSAPI_TICKET_LOGIN_REDIS_KEY";
		
	}
	
	/**
	 * 
	 * [传递给前端的静态常量] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @version 1.0.0<br>
	 * @CreateDate 2019年7月25日 <br>
	 * @since v1.0.0<br>
	 * @see com.runka.common.contant <br>
	 */
	public static final class FrontContants {
		
		private FrontContants () {}
		
		// 用户登录之后把用户的token信息返回给前端的字段名称
		public static final String FRONT_USER_TOKEN = "userToken";
	}
	
	/**
	 * 
	 * [redis的字典静态常量] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @version 1.0.0<br>
	 * @CreateDate 2019年7月25日 <br>
	 * @since v1.0.0<br>
	 * @see com.runka.common.contant <br>
	 */
	public static final class RedisDictContants {
		
		private RedisDictContants () {}
		
		// 用于过滤文字的拦截器
		public static final String SENSITIVE_REDIS_KEY = "SENSITIVE_REDIS_KEY";
	}
	
	/**
	 * 
	 * [用于用户端的redis的静态常量] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @version 1.0.0<br>
	 * @CreateDate 2019年8月2日 <br>
	 * @since v1.0.0<br>
	 * @see com.runka.common.contant <br>
	 */
	public static final class UserIdent {
		
		// 用户微信登录之后,放入redis中的键
		public static final String WECHAT_USER_LOGIN_REDIS_KEY = "wechat_user_login_redis_key:";
		
		//用户编号前缀
		public static final String USER_ID_REDIS_KEY = "USER_ID_REDIS_KEY";
	}
}

