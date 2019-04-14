package com.wise.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.wise.demo.entity.Dict;

/**
 * 数据字典数据缓存 组件类
 * @author lingyuwang
 *
 */
@Repository
public class DictCacheRepository {

	/**
	 * 锁
	 */
	Lock lock = new ReentrantLock();

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, String, Dict> dictOpsForHash;

	private HashOperations<String, String, List<Dict>> dictParentOpsForHash;
	
	@PostConstruct
	private void init() {
		dictOpsForHash = redisTemplate.opsForHash();
		dictParentOpsForHash = redisTemplate.opsForHash();
	}

	/**
	 * 字典数据
	 */
	private final static String DICT_DETAIL_KEY = "dict:detail";
	private final static String DICT_LIST_KEY = "dict:list";

	/**
	 * 获取字典
	 * @param code 字典编码
	 * @return
	 */
	public Dict findByCode(String code) {
		return dictOpsForHash.get(DICT_DETAIL_KEY, code);
	}

	/**
	 * 获取子字典列表
	 * @param parentCode 父字典编码
	 * @return
	 */
	public List<Dict> findByParentCode(String parentCode) {
		return dictParentOpsForHash.get(DICT_LIST_KEY, parentCode);
	}
	
	/**
	 * 刷新缓存的所有数据字典数据
	 */
	public void refreshDictAll() {
		// 模拟数据准备
		List<Dict> dictList = getDictList();

		// list 转 map 方便调用 redisTemplate 接口批量插入
		Map<String, Dict> dictMap = dictList.stream().collect(Collectors.toMap(Dict::getCode, dict -> dict));
		
		// 按父字典编码进行分组（生产环境不建议使用递归查询组装数据，可以批量查询后使用 Java8 集合的分组功能）
		Map<String, List<Dict>> dictParentMap = dictList.stream().collect(Collectors.groupingBy(Dict::getParentCode));

		// 加锁，分布式下需使用分布式锁保证数据一致性
		lock.lock();

		// 先删除旧的缓存
		redisTemplate.delete(DICT_DETAIL_KEY);
		redisTemplate.delete(DICT_LIST_KEY);
		
		// 缓存 detail，底层调用的是 redis 的 hmset 命令
		dictOpsForHash.putAll(DICT_DETAIL_KEY, dictMap);
		
		// 缓存 list，底层调用的是 redis 的 hmset 命令
		dictParentOpsForHash.putAll(DICT_LIST_KEY, dictParentMap);

		// 解锁
		lock.unlock();
	}

	/**
	 * 模拟数据准备，实际应从数据库查询获取数据
	 * @return
	 */
	private List<Dict> getDictList() {
		List<Dict> dictList = new ArrayList<>();
		dictList.add(new Dict("001", "0"));
		dictList.add(new Dict("002", "0"));
		dictList.add(new Dict("003", "0"));
		dictList.add(new Dict("004", "0"));
		dictList.add(new Dict("005", "0"));
		dictList.add(new Dict("001001", "001"));
		dictList.add(new Dict("001002", "001"));
		dictList.add(new Dict("002001", "002"));
		dictList.add(new Dict("002002", "002"));
		dictList.add(new Dict("003001", "003"));
		dictList.add(new Dict("003002", "003"));
		dictList.add(new Dict("001001001", "001001"));
		dictList.add(new Dict("001001002", "001001"));
		return dictList;
	}

}
