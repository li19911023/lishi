
package com.burylovehome.demo.enums;

/**
 * 
 * [用户的状态枚举] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.enums <br>
 */
public enum UserStatusEnum {

	USE_STATUS(1,"使用"),
	NONUSE_STATUS(0,"不使用");
	
	public Integer status;
	public String name;
	
	private UserStatusEnum(Integer status, String name) {
		this.status = status;
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
	
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
}

