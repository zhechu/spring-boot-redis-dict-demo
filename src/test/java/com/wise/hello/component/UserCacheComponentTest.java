package com.wise.hello.component;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.hello.BaseTest;
import com.wise.hello.entity.User;

/**
 * 
 * @author lingyuwang
 *
 */
public class UserCacheComponentTest extends BaseTest {

	@Autowired
	UserCacheComponent userCacheComponent;
	
	@Test
	public void findById() {
		User user = userCacheComponent.findById("1");
		System.out.println(user);
	}
	
	@Test
	public void refreshUserAll() {
		userCacheComponent.refreshUserAll();
	}
	
	@Test
	public void refreshUser() {
		userCacheComponent.refreshUser("1");
	}
	
}
