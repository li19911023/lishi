
package com.burylovehome.demo.service;

import com.burylovehome.demo.entity.dto.RegistUserDTO;
import com.burylovehome.demo.entity.filter.UserPageFilter;
import com.burylovehome.demo.entity.vo.UserVO;
import com.frame.common.wrapper.FilterWrapper;
import com.frame.common.wrapper.PageWrapper;
import com.frame.common.wrapper.PagingInfo;

public interface UserService {

	/**
	 * 
	 * [通过手机号码查看用户信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param mobile 手机号码
	 * @return <br>
	 */
	public abstract int queryUserPOByMobile(String mobile);

	/**
	 * 
	 * [用户注册] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param user
	 * @return <br>
	 */
	public abstract Boolean addUserInfo(RegistUserDTO user);

	/**
	 * 
	 * [通过用户id查看用户信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param id
	 * @return <br>
	 */
	public abstract UserVO queryUserVoById(Integer id);

	/**
	 * 
	 * [根据条件分页查询用户的信息] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param userPageFilter
	 * @return <br>
	 */
	public abstract PagingInfo<UserVO> queryUserInfoByPage(PageWrapper<FilterWrapper<UserPageFilter>> userPageFilter);

}

