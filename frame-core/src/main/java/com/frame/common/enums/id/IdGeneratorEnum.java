
package com.frame.common.enums.id;

/**
 * 
 * [编号枚举] <br> 
 *   
 * @author [li.qiong]<br>
 * @version 1.0.1<br>
 * @CreateDate 2018年12月17日 <br>
 * @since v1.0.1<br>
 * @see com.byjz.housecloud.common.enums.number <br>
 */
public enum IdGeneratorEnum {
	
	USER_ID("UR",9),//用户编号
	;
	
	private String prefix;//前缀
	private final int length;//长度
	
	private IdGeneratorEnum(String prefix,int length) {
		this.prefix = prefix;
		this.length = length;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getLength() {
		return length;
	}

}

