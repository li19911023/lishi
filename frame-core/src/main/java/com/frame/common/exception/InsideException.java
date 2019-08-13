
package com.frame.common.exception;

import com.frame.common.enums.exception.ErrorCodeEnum;
import com.frame.common.util.common.RespJson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * [内部异常,用于提示系统内部,不返回给前端] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.exception <br>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsideException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer errorCode;// 错误码
	
	private String msg;// 错误信息
	
	/**
	 * 
	 * @param errorInfo 错误枚举
	 */
	public InsideException(ErrorCodeEnum errorInfo) {
		this.errorCode = errorInfo.code();
		this.msg = errorInfo.message();
	}
	
	/**
	 * 
	 * @param respJson
	 */
	public InsideException(RespJson<Object> respJson){
	    this.errorCode = respJson.getErrorCode();
	    this.msg = respJson.getReason();
	}
	
}

