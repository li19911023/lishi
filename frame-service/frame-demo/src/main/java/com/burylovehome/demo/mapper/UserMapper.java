
package com.burylovehome.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.burylovehome.demo.entity.po.UserPO;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<UserPO> {

	//通过用户id查看用户信息
	UserPO queryUserVoById(@Param("id") Integer id);

}

