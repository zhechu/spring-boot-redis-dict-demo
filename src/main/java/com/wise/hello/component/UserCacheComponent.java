package com.wise.hello.component;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wise.hello.entity.User;

/**
 * 用户数据缓存 组件类
 * @author lingyuwang
 *
 */
@Component
public class UserCacheComponent {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 用户数据
     */
    public final static String CACHE_PREFIX = "user:id:";

    /**
     * 从缓存查询
     * @param id
     * @return
     */
    public User findById(String id) {
    	return (User) redisTemplate.opsForValue().get(CACHE_PREFIX + id);
	}

    /**
     * 刷新缓存的所有用户数据
     */
    public void refreshUserAll() {
    	// 先删除
        Set<String> keys = redisTemplate.keys(CACHE_PREFIX + "*");
        redisTemplate.delete(keys);
    	
        // 缓存(模拟从数据库获取信息，若数据库没有用户数据，则不需缓存)
        String id = "1";
        String name = "1";
        for (int i = 1; i <= 10; i++) {
        	id = i + "";
        	name = i + "";
        	redisTemplate.opsForValue().set(CACHE_PREFIX + id, new User(id, name));
		}
    }
    
    /**
     * 刷新单个用户数据
     * @param id
     */
    public void refreshUser(String id) {
    	// 先删除
    	Set<String> keys = redisTemplate.keys(CACHE_PREFIX + id);
    	redisTemplate.delete(keys);
    	
    	// 缓存(模拟从数据库获取信息，若数据库没有用户数据，则不需缓存)
    	String name = "refresh";
    	redisTemplate.opsForValue().set(CACHE_PREFIX + id, new User(id, name));
    }
    
}
