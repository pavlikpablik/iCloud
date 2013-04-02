package com.manpowergroup.cn.core.memcached;

import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemcachedCache implements Cache {
	// Sf4j logger reference
	private static Logger logger = LoggerFactory.getLogger(MemcachedCache.class);

	/** The cache service reference. */
	protected static final CacheService CACHE_SERVICE = createMemcachedService();

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;
	private LinkedList<String> cacheKeys = new LinkedList<String>();

	public MemcachedCache(String id) {
		this.id = id;
	}

	// 创建缓存服务类，基于xmemcached
	protected static CacheService createMemcachedService() {
		JMemcachedClientAdapter memcachedAdapter;
		try {
			memcachedAdapter = new JMemcachedClientAdapter();
		} catch (Exception e) {
			String msg = "Initial the JMmemcachedClientAdapter Error.";
			logger.error(msg, e);
			throw new RuntimeException(msg);
		}
		return new MemcachedService(memcachedAdapter);
	}

	@Override
	public String getId() {
		return this.id;
	}

	// 根据key从缓存中获取数据
	@Override
	public Object getObject(Object key) {
		String cacheKey = String.valueOf(key.hashCode());
		Object value = CACHE_SERVICE.get(cacheKey);
		if (!cacheKeys.contains(cacheKey)) {
			cacheKeys.add(cacheKey);
		}
		return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	// 设置数据至缓存中
	@Override
	public void putObject(Object key, Object value) {
		String cacheKey = String.valueOf(key.hashCode());
		if (!cacheKeys.contains(cacheKey)) {
			cacheKeys.add(cacheKey);
		}
		CACHE_SERVICE.put(cacheKey, value);
	}

	// 从缓存中删除指定key 数据
	@Override
	public Object removeObject(Object key) {
		String cacheKey = String.valueOf(key.hashCode());
		cacheKeys.remove(cacheKey);
		return CACHE_SERVICE.delete(cacheKey);
	}

	// 清空当前 Cache实例中的所有缓存数据
	@Override
	public void clear() {
		for (int i = 0; i < cacheKeys.size(); i++) {
			String cacheKey = cacheKeys.get(i);
			CACHE_SERVICE.delete(cacheKey);
		}
		cacheKeys.clear();
	}

	@Override
	public int getSize() {
		return cacheKeys.size();
	}
}
