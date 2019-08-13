
package com.frame.common.enums.exception;

/**
 * 
 * [错误信息枚举] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.enums.exception <br>
 */
public enum ErrorCodeEnum {

	// GL开头表示全局对外错误码；
	GL99990100(99990100, "参数异常"),
	GL99990600(99990600, "参数信息没有传递"),
	GL99990700(99990700, ""),// 主要用于MethodArgumentNotValidException异常,字段值的校验
	GL99990800(99990800, "获取微信的签名信息失败"),
	
	// IL全局开头对内部提示错误信息,不对外提供错误信息
	IL99990200(99990200, "对象转换导致信息丢失"),
	IL99990300(99990300, "过滤类为空"),
	IL99990400(99990400, "该类不是过滤类"),
	IL99990500(99990500, "权限匹配的url为多个。url：%s"),
	
	IL90021034(90021034, "[内部]找不到该属性"),
	IL90021020(90021020, "[内部]非法访问异常"),
	
	;
	private int code;

	private String message;

	public int code() {
		return code;
	}

	public String message() {
		return message;
	}

	private ErrorCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}

