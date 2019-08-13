
package com.burylovehome.demo.entity.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * [用户实体类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.entity.po <br>
 */
@Data
@Table(name = "user")
@ApiModel
public class UserPO implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "主键")
	private Integer id;//主键
	
	@Column(name = "username")
	@ApiModelProperty(value = "用户名")
    private String username;//用户名
	
	@Column(name = "password")
	@ApiModelProperty(value = "密码")
    private String password;// 密码

	@Column(name = "name")
	@ApiModelProperty(value = "昵称")
    private String name;// 昵称
	
	@Column(name = "reg_time")
	@ApiModelProperty(value = "注册时间")
    private Date regTime;// 注册时间
	
	@Column(name = "mobile")
	@ApiModelProperty(value = "手机号码")
    private String mobile;// 手机号码

	@Column(name = "profile_photo")
	@ApiModelProperty(value = "头像图片地址")
    private String profilePhoto;// 头像图片地址
	
	@Column(name = "user_type")
	@ApiModelProperty(value = "用户类型 1：房买家，2：二手房业主，3：租房房东，4：租房业主，5：游客")
    private Integer userType;// 用户类型 1：房买家，2：二手房业主，3：租房房东，4：租房业主，5：游客
	
	@Column(name = "id_card")
	@ApiModelProperty(value = "用户的身份证号")
    private String idCard;//  用户的身份证号

	@Column(name = "id_card_front")
	@ApiModelProperty(value = "身份证正面图片")
    private String idCardFront;// 身份证正面图片
	
	@Column(name = "id_card_back")
	@ApiModelProperty(value = "身份证反面图片")
    private String idCardBack;// 身份证反面图片
	
	@Column(name = "real_name")
	@ApiModelProperty(value = "真实名称")
    private String realName;// 真实名称

	@Column(name = "user_no")
	@ApiModelProperty(value = "用户编号")
    private String userNo;// 用户编号
	
	@Column(name = "is_forbidden")
	@ApiModelProperty(value = "1：正常；0：禁掉")
    private Integer isForbidden;// 1：正常；0：禁掉
	
	@Column(name = "email")
	@ApiModelProperty(value = "邮箱")
    private String email;// 邮箱
}

