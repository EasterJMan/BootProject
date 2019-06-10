package com.itheima.mapper;

import org.apache.ibatis.annotations.Param;

import com.itheima.domain.User;

public interface UserMapper {
	public User findUser(@Param("usercode") String usercode,
						 @Param("password") String password	);
	

}
