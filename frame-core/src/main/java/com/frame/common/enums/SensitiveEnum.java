
package com.frame.common.enums;

/**
 * 
 * [敏感字类型] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月25日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.enums <br>
 */
public enum SensitiveEnum {

	CHINA_TYPE(1,"汉字"),
	PINYIN_TYPE(2,"拼音"),
	ENGLIST_TYPE(3, "英语"),
	;
	
	public Integer type;
	public String name;
	
	private SensitiveEnum(Integer type, String name) {
		this.type = type;
		this.name = name;
	}
}

