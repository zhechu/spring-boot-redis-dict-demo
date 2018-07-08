package com.wise.hello.component;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.hello.BaseTest;
import com.wise.hello.entity.DictDetail;

/**
 * 
 * @author lingyuwang
 *
 */
public class DictCacheComponentTest extends BaseTest {

	@Autowired
	DictCacheComponent dictCacheComponent;
	
	@Test
	public void findById() {
		List<DictDetail> dictDetail = dictCacheComponent.findById("2");
		System.out.println(dictDetail);
	}
	
	@Test
	public void refreshDictAll() {
		dictCacheComponent.refreshDictAll();
	}
	
	@Test
	public void refreshDict() {
		dictCacheComponent.refreshDict("2");
	}
	
}
