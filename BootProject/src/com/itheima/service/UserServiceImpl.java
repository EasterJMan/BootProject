package com.itheima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUser(String usercode, String password) {
		User user = this.userMapper.findUser(usercode, password);
		return user;
	}
	

}
