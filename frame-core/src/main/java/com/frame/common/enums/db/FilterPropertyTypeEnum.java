
package com.frame.common.enums.db;

/**
 * 
 * [过滤属性枚举类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.enums.db <br>
 */
public enum FilterPropertyTypeEnum {
	
	EQURE,//等于,默认是等于
	NOTEQURE,//不等于
	LIKE,//模糊查询
	RANGE,//范围(包含边界值)
	IGRANGE,//范围(不包含边界值)
	LIST,//集合
	NOCONDITION,//不做条件处理
	HOUSEAGE,//年代 例如:0,15年 :这个意思是15年之内
	ORPROPERTY,//or,并可以使用多个字段组成or语句
	ISNULL,
	ISNOTNULL,
	LISTANDRANGE,//此处要求数组里的元素对应的数据库字段是 数值 类型
	EACHORLIKE,//此处List每一个元素匹配，任1条件符合就可以
	ALL,//搜索服务用
	ARRAY,//搜索服务用
	INTERSEC,//搜索服务用
	LISTDATERANGE;//搜索服务用
}

