package com.wise.hello.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wise.hello.entity.DictDetail;

/**
 * 数据字典数据缓存 组件类
 * @author lingyuwang
 *
 */
@Component
public class DictCacheComponent {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 用户数据
     */
    public final static String CACHE_PREFIX = "dict:id:";
    public final static String CACHE_SUFFIX = ":detail";

    /**
     * 从缓存查询
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<DictDetail> findById(String id) {
    	return (List<DictDetail>) redisTemplate.opsForValue().get(CACHE_PREFIX + id + CACHE_SUFFIX);
	}

    /**
     * 刷新缓存的所有数据字典数据
     */
    public void refreshDictAll() {
    	// 先删除
        Set<String> keys = redisTemplate.keys(CACHE_PREFIX + "*");
        redisTemplate.delete(keys);
    	
        // 缓存(模拟从数据库获取信息，若数据库没有用户数据，则不需缓存)
        String dictId = "1"; // 字典ID
        String id = "1";
        String label = "1";
        String value = "1";
        List<DictDetail> dictDetail = new ArrayList<>();
        for (int d = 1; d < 10; d++) {
        	dictId = d + "";
        	dictDetail.clear();
            for (int i = 1; i <= 10; i++) {
            	id = d + ":" + i;
            	label = d + ":" + i;
            	value = d + ":" + i;
            	dictDetail.add(new DictDetail(id, label, value));
    		}
            redisTemplate.opsForValue().set(CACHE_PREFIX + dictId + CACHE_SUFFIX, dictDetail);
		}
    }
    
    /**
     * 刷新单个数据字典数据
     * @param id
     */
    public void refreshDict(String dictId) {
    	// 先删除
    	Set<String> keys = redisTemplate.keys(CACHE_PREFIX + dictId);
    	redisTemplate.delete(keys);
    	
    	// 缓存(模拟从数据库获取信息，若数据库没有用户数据，则不需缓存)
    	String id = "1";
        String label = "1";
        String value = "1";
        List<DictDetail> dictDetail = new ArrayList<>();
    	for (int i = 1; i <= 10; i++) {
        	id = dictId + ":" + i;
        	label = dictId + ":" + i;
        	value = dictId + ":" + i + "refresh";
        	dictDetail.add(new DictDetail(id, label, value));
		}
    	redisTemplate.opsForValue().set(CACHE_PREFIX + dictId + CACHE_SUFFIX, dictDetail);
    }
    
}
