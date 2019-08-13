
package com.burylovehome.demo.enums;

/**
 * 
 * [用户类型枚举] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.enums <br>
 */
public enum UserTypeEnum {

	NEW_HOUSE_BUYER_TYPE(1,"新房买家"),
	SECOND_HOUSE_OWNER_TYPE(2,"二手房业主"),
	RENTING_LANDLORD_TYPE(3,"租房房主"),
	RENTING_OWMNER_TYPE(4,"租房业主"),
	TOURIST_TYPE(5,"游客");
	
	public Integer type;
	public String name;
	
	private UserTypeEnum(Integer type, String name) {
		this.type = type;
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
	
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
}

