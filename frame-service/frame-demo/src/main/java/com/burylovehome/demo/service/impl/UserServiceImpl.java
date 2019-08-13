
package com.burylovehome.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.burylovehome.demo.entity.dto.RegistUserDTO;
import com.burylovehome.demo.entity.filter.UserPageFilter;
import com.burylovehome.demo.entity.po.UserPO;
import com.burylovehome.demo.entity.vo.UserVO;
import com.burylovehome.demo.enums.UserStatusEnum;
import com.burylovehome.demo.enums.UserTypeEnum;
import com.burylovehome.demo.mapper.UserMapper;
import com.burylovehome.demo.service.UserService;
import com.burylovehome.demo.util.MD5Utils;
import com.burylovehome.demo.util.ObjectConvertUtils;
import com.frame.common.enums.exception.ErrorCodeEnum;
import com.frame.common.exception.CommonException;
import com.frame.common.exception.InsideException;
import com.frame.common.handle.DbFilterHandle;
import com.frame.common.util.common.CollectionListUtils;
import com.frame.common.util.common.RespJson;
import com.frame.common.wrapper.FilterWrapper;
import com.frame.common.wrapper.PageWrapper;
import com.frame.common.wrapper.PagingInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * [用户服务] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.service.impl <br>
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private static final String mobileInfo = "mobile";
	
	@Autowired
	private UserMapper userMapper;

	//通过手机号码查看用户信息
	@Override
	public int queryUserPOByMobile(String mobile) {
		int row = DbFilterHandle.getIdentInfoNum(mobileInfo, mobile, userMapper, UserPO.class);
		return row;
	}
	
	//用户注册
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean addUserInfo(RegistUserDTO user) {
		user.setProfilePhoto("图片");
		//设置用户类型
		user.setUserType(UserTypeEnum.TOURIST_TYPE.type);
		//设置当前的时间
		user.setRegTime(new Date());
		//密码加密
		String password = user.getPassword();
		if(StringUtils.isNotEmpty(password)) {
			user.setPassword(MD5Utils.md5(password));
		}
		//启用当前用户
		user.setIsForbidden(UserStatusEnum.USE_STATUS.status);
		//对象转换
		UserPO userPO = ObjectConvertUtils.objectConvert(user, new UserPO());
		Optional.ofNullable(userPO).orElseThrow(() -> new InsideException(ErrorCodeEnum.IL99990200));
		int row = userMapper.insertSelective(userPO);
		if(row > 1) {
			log.error("添加用户信息不止一条,不符合实际");
			throw new RuntimeException("添加用户信息不止一条,不符合实际");
		}
		return row == 1 ? true : false ;
		
	}

	//通过用户id查看用户信息
	@Override
	public UserVO queryUserVoById(Integer id) {
		UserPO userPO = userMapper.selectByPrimaryKey(id);
		UserVO userVO = ObjectConvertUtils.objectConvert(userPO, new UserVO());
		//测试出现问题,拦截器错误信息是否会返回给前端(返回前端的是json格式的字符串(可以强转成对象))
		if(userVO == null) {
			throw new CommonException(RespJson.failed(2012, "没有该用户信息"));
		}
		return userVO;
	}

	//根据条件分页查询用户的信息
	@Override
	public PagingInfo<UserVO> queryUserInfoByPage(PageWrapper<FilterWrapper<UserPageFilter>> userPageFilter) {
		List<UserPO> filterInfoPageList = DbFilterHandle.getFilterInfoPageList(userPageFilter, userMapper, UserPO.class, true);
		List<UserVO> userVOList = new ArrayList<>();
		if(CollectionListUtils.isNotEmpty(filterInfoPageList)) {
			for (UserPO userPO : filterInfoPageList) {
				UserVO userVO = ObjectConvertUtils.objectConvert(userPO, new UserVO());
				userVOList.add(userVO);
			}
		}
		return PagingInfo.getNewPage(PagingInfo.of(filterInfoPageList), userVOList);
		
	}

}

