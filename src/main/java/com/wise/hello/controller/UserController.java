package com.wise.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wise.hello.entity.User;
import com.wise.hello.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	User findById(@PathVariable("id") String id) {
		return userService.findById(id);
	}
	
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	User add(@RequestBody User user) {
		return userService.add(user);
	}
	
}