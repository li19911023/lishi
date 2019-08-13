package com.frame.common.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * [对日期进行处理] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月1日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	
	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {}

	public JsonDateValueProcessor(String format) {
		this.format = format;
	}

	/**
	 * 对数组的处理
	 */
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}

	/**
	 * 对对象的处理
	 */
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return process(value, jsonConfig);
	}

	/**
	 * 日期处理，如果是日期类型数据则格式化为日期字符串，否则字符串返回
	 * 
	 * @param value
	 * @param jsonConfig
	 * @return
	 */
	private Object process(Object value, JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		} else {
			return value == null ? null : value.toString();
		}
	}

}
