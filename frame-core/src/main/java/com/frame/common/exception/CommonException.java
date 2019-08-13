
package com.frame.common.exception;

import com.frame.common.enums.exception.ErrorCodeEnum;
import com.frame.common.util.common.RespJson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * [公共异常处理] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.exception <br>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException {
	 
	private static final long serialVersionUID = 1L;
	
	private Integer errorCode;// 错误码
	
	private String msg;// 错误信息
	
	/**
	 * 
	 * @param errorInfo 错误枚举
	 */
	public CommonException(ErrorCodeEnum errorInfo) {
		this.errorCode = errorInfo.code();
		this.msg = errorInfo.message();
	}
	
	/**
	 * 
	 * @param respJson
	 */
	public CommonException(RespJson<Object> respJson){
	    this.errorCode = respJson.getErrorCode();
	    this.msg = respJson.getReason();
	}
	
	public CommonException(ErrorCodeEnum errorInfo, Object... args){
	     super(String.format(errorInfo.message(),args));
	     this.errorCode = errorInfo.code();
	     this.msg = errorInfo.message();
	}

    public CommonException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
        this.msg = message;
    }

    public CommonException(Integer code,String msgFormat,Object... args){
        super(String.format(msgFormat,args));
        this.errorCode = code;
    }

}

