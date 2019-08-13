
package com.frame.common.enums;

/**
 * 
 * [状态枚举] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月4日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.enums <br>
 */
public enum StatusEnum {

	USE_STATUS(1,"使用"),
	NONUSE_STATUS(0,"不使用");
	
	public Integer status;
	public String name;
	
	private StatusEnum(Integer status, String name) {
		this.status = status;
		this.name = name;
	}
}

