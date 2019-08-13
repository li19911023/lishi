
package com.frame.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * [该标识表示这个类属于过滤类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.annotation <br>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)//反射时可以获取
@Target(ElementType.TYPE)//属于类注解
public @interface DbFilter {

}

