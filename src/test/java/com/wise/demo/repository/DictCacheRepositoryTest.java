package com.wise.demo.repository;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.demo.BaseTest;
import com.wise.demo.entity.Dict;
import com.wise.demo.repository.DictCacheRepository;

/**
 * 
 * @author lingyuwang
 *
 */
public class DictCacheRepositoryTest extends BaseTest {

	@Autowired
	DictCacheRepository dictCacheRepository;

	@Before
	public void refreshDictAll() {
		dictCacheRepository.refreshDictAll();
	}
	
	@Test
	public void findByCode() {
		System.out.println();
		System.out.println("============================ findByCode start ============================");
		
		String code = "001";
		Dict dict = dictCacheRepository.findByCode(code);
		System.out.println("================== " + code + " result start ===================");
		System.out.println(dict);
		System.out.println("================== " + code + " result end ===================");
		
		code = "001001";
		dict = dictCacheRepository.findByCode(code);
		System.out.println("================== " + code + " result start ===================");
		System.out.println(dict);
		System.out.println("================== " + code + " result end ===================");
		
		System.out.println("============================ findByCode end ============================");
		System.out.println();
	}
	
	@Test
	public void findByParentCode() {
		System.out.println();
		System.out.println("============================ findByParentCode start ============================");
		
		String parentCode = "0";
		List<Dict> dictList = dictCacheRepository.findByParentCode(parentCode);
		System.out.println("================== " + parentCode + " result start ===================");
		System.out.println(dictList);
		System.out.println("================== " + parentCode + " result end ===================");
		
		parentCode = "001";
		dictList = dictCacheRepository.findByParentCode(parentCode);
		System.out.println("================== " + parentCode + " result start ===================");
		System.out.println(dictList);
		System.out.println("================== " + parentCode + " result end ===================");
		
		parentCode = "001001";
		dictList = dictCacheRepository.findByParentCode(parentCode);
		System.out.println("================== " + parentCode + " result start ===================");
		System.out.println(dictList);
		System.out.println("================== " + parentCode + " result end ===================");
		
		System.out.println("============================ findByParentCode end ============================");
		System.out.println();
	}
	
}
