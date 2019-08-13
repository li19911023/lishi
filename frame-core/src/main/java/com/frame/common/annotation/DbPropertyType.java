
package com.frame.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.frame.common.enums.db.FilterPropertyTypeEnum;



/**
 * 
 * [过滤条件类的属性注解] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.annotation <br>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbPropertyType {

	FilterPropertyTypeEnum type() default FilterPropertyTypeEnum.EQURE;//过滤条件默认是相等
	
	String name() default "";
	
	String[] orProperty() default "";
	
}

