
package com.burylovehome.demo.entity.filter;

import java.io.Serializable;

import com.frame.common.annotation.DbFilter;
import com.frame.common.annotation.DbPropertyType;
import com.frame.common.enums.db.FilterPropertyTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [根据条件查询用户信息] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年5月5日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.entity.filter <br>
 */
@Data
@DbFilter //告诉dbhandle这个是条件类
@ApiModel(value = "UserPageFilter,查询用户条件类",description = "查询用户条件类")
public class UserPageFilter implements Serializable {
	
	@DbPropertyType(type = FilterPropertyTypeEnum.NOCONDITION)//不做条件处理
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户昵称")
	@DbPropertyType(type = FilterPropertyTypeEnum.LIKE)//模糊查询
	private String name;//用户昵称
	
	@ApiModelProperty(value = "用户编号")
	@DbPropertyType(type = FilterPropertyTypeEnum.EQURE)//全等查询
	private String userNo;//用户编号
	
	@ApiModelProperty(value = "用户注册时间")
	@DbPropertyType(type = FilterPropertyTypeEnum.RANGE, name = "regTime")//全等查询
	private String registerTime;//根据注册时间进行查询,这个和PO的字段不匹配,这个时候要使用name属性
	//还有时间一般都是开始和结束,但是必须用,号隔开

}

