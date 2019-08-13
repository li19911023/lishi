package com.frame.common.util.common;

import java.io.Serializable;

import com.frame.common.enums.exception.ErrorCodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [回值返回说明类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
@Data
@ApiModel(value = "返回值返回说明类")
public class RespJson<V> implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	
	public final static int STATUS_SUCCESS = 0;
	public final static int STATUS_FAIL = 1012;
	public final static int STATUS_EXCEPTION = 1014;
	public final static int SESSION_EXCEPTION = 1015;
	public final static int AUTH_EXCEPTION = 1016;
	public final static int STATUS_FAIL2 = 1018;
    
	public final static String DESC_SUCCESS = "success";
	public final static String DESC_SERVER_FAIL = "参数缺失";
	public final static String DESC_SERVER_EXCEPTION = "服务异常,请重试.";
	public final static String DESC_SESSION_EXCEPTION = "用户信息失效,请重新登录.";
	public final static String DESC_AUTH_EXCEPTION = "您无权限操作.";
	public final static String DESC_SERVER_FAIL2 = "操作失败,请重试.";

	@ApiModelProperty(value = "状态码")
    private Integer errorCode;//状态码

	@ApiModelProperty(value = "状态描述")
	private String reason;//数据状态

	@ApiModelProperty(value = "数据体")
	private V data;//返回的数据

    /**
     * 默认是成功的
     */
    public RespJson() {
        this.errorCode = STATUS_SUCCESS;
        this.reason = DESC_SUCCESS;
    }
    
    public RespJson(int errorCode, String reason) {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public RespJson(int errorCode, String reason, V data) {
        this.errorCode = errorCode;
        this.reason = reason;
        this.data = data;
    }
    
    /**
     * 
     * [返回状态码和具体的数据] <br> 
     *  
     * @author [li.qiong]<br>
     * @param
     * @return <br>
     */
    public static <V> RespJson<V> success(V data) {
	    return new RespJson<V>(STATUS_SUCCESS, DESC_SUCCESS, data);
    }
    
    /**
	 * 
	 * [返回成功的状态] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
    public static <V> RespJson<V> success() {
    	return new RespJson<V>(STATUS_SUCCESS, DESC_SUCCESS);
    }

    /**
     * 
     * [操作失败] <br> 
     *  
     * @author [li.qiong]<br>
     * @param errorCode 状态码
     * @param reason 失败信息
     * @return <br>
     */
	public static <V> RespJson<V> fail() {
		return new RespJson<V>(STATUS_FAIL2 ,DESC_SERVER_FAIL2 );
    }

    /**
     * 
     * [自定义失败码和失败原因] <br> 
     *  
     * @author [li.qiong]<br>
     * @param errorCode 状态码
     * @param reason 失败信息
     * @return <br>
     */
	public static <V> RespJson<V> failed(int errorCode, String reason) {
		return new RespJson<V>(errorCode ,reason);
    }
	
	/**
	 * 
	 * [自定义失败码和失败原因] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param enumInfo ErrorCodeEnum 错误信息枚举
	 * @return <br>
	 */
	public static <V> RespJson<V> failed(ErrorCodeEnum enumInfo) {
		return new RespJson<V>(enumInfo.code(), enumInfo.message());
    }
	
	/**
	 * 
	 * [自定义失败码和失败原因] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param enumInfo ErrorCodeEnum 错误信息枚举
	 * @return <br>
	 */
	public static <V> RespJson<V> failed(ErrorCodeEnum enumInfo, String message) {
		return new RespJson<V>(enumInfo.code(), message);
    }
	
	/**
	 * 
	 * [参数信息缺失失败] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <V> RespJson<V> failed() {
		return new RespJson<V>(STATUS_FAIL, DESC_SERVER_FAIL);
    }

	/**
	 *
	 * [用户信息失效，返回错误信息] <br>
	 *
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static <V> RespJson<V> sessionFailed(){
		return new RespJson<V>(SESSION_EXCEPTION, DESC_SESSION_EXCEPTION);
	}

	/**
	 * 
	 * [异常返回] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
    public static <V> RespJson<V> exceptioned() {
        return new RespJson<V>(STATUS_EXCEPTION,DESC_SERVER_EXCEPTION);
    }
    
    /**
	 * 
	 * [无权限操作返回] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
    public static <V> RespJson<V> authFailed() {
        return new RespJson<V>(AUTH_EXCEPTION,DESC_AUTH_EXCEPTION);
    }
    
}
