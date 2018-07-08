package com.wise.hello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wise.hello.component.UserCacheComponent;
import com.wise.hello.entity.User;
import com.wise.hello.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserCacheComponent userCacheComponent;
	
	@Override
	public User findById(String id) {
		System.out.println("=================================");
		System.out.println("从缓存中获取用户，并返回");
		System.out.println("=================================");
		User user = userCacheComponent.findById(id);
		if (user != null) {
			return user;
		}
		System.out.println("=================================");
		System.out.println("从数据库获取用户，并返回");
		System.out.println("=================================");
		return new User(id);
	}

	@Override
	public User add(User user) {
		System.out.println("=================================");
		System.out.println("添加用户，并更新缓存");
		System.out.println("=================================");
		userCacheComponent.refreshUser(user.getId());
		return user;
	}

}
