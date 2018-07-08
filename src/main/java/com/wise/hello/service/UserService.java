package com.wise.hello.service;

import com.wise.hello.entity.User;

public interface UserService {

	User findById(String id);
	
	User add(User user);
	
}
