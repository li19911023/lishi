
package com.frame.common.advice;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.common.enums.exception.ErrorCodeEnum;
import com.frame.common.exception.CommonException;
import com.frame.common.exception.InsideException;
import com.frame.common.util.common.CollectionListUtils;
import com.frame.common.util.common.RespJson;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * [统一异常处理] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @param <V>
 * @CreateDate 2019年7月24日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.advice <br>
 */
@ControllerAdvice // 全局处理异常的注解
@Slf4j
public class BaseControllerAdvice<V> {

    /**
     * 
     * [主要用于字段注解的@NotNull 和 @NotEmpty上的message信息] <br> 
     *  
     * @author [li.qiong]<br>
     * @param ex 后期可以自己做一个校验规则
     * @return <br>
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    @ResponseBody
    public RespJson<V> paramsExceptionHandler(MethodArgumentNotValidException ex ) {
    	log.info("参数绑定异常: 异常信息info ========== " + ex);
    	MethodArgumentNotValidException methodException = (MethodArgumentNotValidException) ex;
		BindingResult bindingResult = methodException.getBindingResult();
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		if(CollectionListUtils.isNotEmpty(allErrors)) {
			return RespJson.failed(ErrorCodeEnum.GL99990700, allErrors.get(0).getDefaultMessage());
		} else {
			return RespJson.fail();
		}
    }
    
    /**
     * 
     * [出现CommonException异常的时候] <br> 
     *  
     * @author [li.qiong]<br>
     * @param e CommonException 对外部展示异信息常
     * @return <br>
     */
    @ExceptionHandler(value = CommonException.class )
    @ResponseBody
    public RespJson<V> commonErrorHandle(CommonException e) {
        log.info("服务内部错误,并对外公布: 异常信息info ========== " + e);
        log.info("CommonException内部提示错误信息: 异常信息info ========== " + e.getMsg() );
        return RespJson.failed(e.getErrorCode(), e.getMsg());
    }
    
    /**
     * 
     * [该类异常信息,统一处理] <br> 
     *  
     * @author [li.qiong]<br>
     * @param e
     * @return <br>
     */
    @ExceptionHandler(value = InsideException.class )
    @ResponseBody
    public RespJson<V> insideErrorHandle(InsideException e) {
    	log.info("服务内部错误,不对外公布,属于代码级别错误：info ========== " + e);
    	log.info("InsideException内部提示错误信息: 异常信息info ========== " + e.getMsg() );
    	return RespJson.fail();
    }
    
    /**
     * 
     * [全局异常捕捉处理] <br> 
     *  
     * @author [li.qiong]<br>
     * @param ex
     * @return <br>
     */
    @ExceptionHandler( value = Exception.class )
    @ResponseBody
    public RespJson<V> errorHandler( Exception ex ) {
        log.error( "接口出现严重异常：info ========== ", ex );
        return RespJson.fail();
    }
}

