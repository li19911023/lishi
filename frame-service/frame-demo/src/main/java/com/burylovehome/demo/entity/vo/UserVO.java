
package com.burylovehome.demo.entity.vo;

import com.burylovehome.demo.entity.po.UserPO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * [返回给前端的用户信息] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.entity.vo <br>
 */
@Setter
@Getter
@NoArgsConstructor
@ApiModel(value = "UserVO.返回给前端的用户信息", description = "返回给前端的用户信息")
@JsonIgnoreProperties(value = {"password"})//密码不能返回给前端展示,所以禁止序列化
public class UserVO extends UserPO {
	 
	private static final long serialVersionUID = 1L;

}

