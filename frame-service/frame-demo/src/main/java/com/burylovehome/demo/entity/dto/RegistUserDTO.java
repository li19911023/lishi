
package com.burylovehome.demo.entity.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [用户注册] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.1.0<br>
 * @CreateDate 2019年3月13日 <br>
 * @since v1.1.0<br>
 * @see com.byjz.housecloud.microservice.user.entity.user.dto <br>
 */
@Data
@ApiModel(value = "RegistUserDTO.用户注册",description = "用户注册")
public class RegistUserDTO implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "手机号码不能为null")
	@NotEmpty(message = "手机号码不能为空")
	@ApiModelProperty(value = "手机号码", required = true)
    private String mobile;// 手机号码
	
	@NotNull(message = "密码不能为null")
	@NotEmpty(message = "密码不能为空")
	@ApiModelProperty(value = "密码", required = true)
	private String password;// 密码
	
	@ApiModelProperty(hidden = true)
	private String userNo;// 用户编号
	
	@ApiModelProperty(hidden = true)
	private String profilePhoto;// 头像图片地址
	
	@ApiModelProperty(hidden = true)
	private Date regTime;// 注册时间
	
	@ApiModelProperty(hidden = true)
	private Integer userType;// 用户类型 1：房买家，2：二手房业主，3：租房房东，4：租房业主，5：游客
	
	@ApiModelProperty(hidden = true)
	private Integer isForbidden;// 1：正常；0：禁掉
}

