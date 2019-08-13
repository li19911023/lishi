package com.burylovehome.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burylovehome.demo.entity.dto.RegistUserDTO;
import com.burylovehome.demo.entity.filter.UserPageFilter;
import com.burylovehome.demo.entity.vo.UserVO;
import com.burylovehome.demo.service.UserService;
import com.frame.common.util.common.RespJson;
import com.frame.common.wrapper.FilterWrapper;
import com.frame.common.wrapper.PageWrapper;
import com.frame.common.wrapper.PagingInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * [以用户为案例] <br> 
   *   访问  localhost:8082/swagger-ui.html
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.controller <br>
 */
@RestController
@RequestMapping(value = "/web/users/")
@Slf4j
@Api(tags = {"用户案例"})
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * [用户注册] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param user
	 * @return <br>
	 */
	@PostMapping(value = "user")
	@ApiOperation(value = "用户注册")
	public RespJson<Object> addUserInfo(@RequestBody @Valid RegistUserDTO user){
		try {
			//判断当前用户是否已经注册了
			String mobile = user.getMobile();
			//查询的数据库中是否有这个手机号码
			int row = userService.queryUserPOByMobile(mobile);
			if(row > 0) {
				return RespJson.failed(1023,"您手机号码已经注册了");//TODO 最好做一个枚举,返回数据格式一致
			}
			//注册
			Boolean flag = userService.addUserInfo(user);
			return flag ? RespJson.success() : RespJson.fail();
		} catch (Exception e) {
			log.error("用户注册异常", e);
			return RespJson.exceptioned();
		}
	}
	
	/**
	 * 
	 * [通过用户id查看用户信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param id
	 * @return <br>
	 */
	@GetMapping(value = "user/{id}")
	@ApiOperation(value = "通过用户id查看用户信息")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
	public RespJson<Object> queryUserInfo(
			@PathVariable(value = "id" ,required = true) Integer id){
		UserVO user = userService.queryUserVoById(id);
		return RespJson.success(user);
	}
	
	/**
	 * 
	 * [根据条件分页查询用户的信息] <br> 
	
		userPageFilter 条件组装
		{
		  "data": {
		    "filter": {
		      "name": "",
		      "registerTime": "2018-10-19,2019-04-19",
		      "userNo": ""
		    },
		    "orders": [
		      {
		        "orderName": "regTime",
		        "orderType": "desc"
		      }
		    ]
		  },
		  "pageNum": 1,
		  "pageSize": 10
		}
	
	
	 *  
	 * @author [li.qiong]<br>
	 * @param id
	 * @return <br>
	 */
	@PostMapping(value = "user/list")
	@ApiOperation(value = "根据条件分页查询用户的信息")
	public RespJson<Object> queryUserInfoByPage(@RequestBody PageWrapper<FilterWrapper<UserPageFilter>> userPageFilter){
		PagingInfo<UserVO> user = userService.queryUserInfoByPage(userPageFilter);
		return RespJson.success(user);
	}
}
