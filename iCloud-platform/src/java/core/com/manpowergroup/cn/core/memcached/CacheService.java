package com.manpowergroup.cn.core.memcached;

public interface CacheService {
	Object get(String cacheKey);

	void put(String cacheKey, Object value);

	Object delete(String cacheKey);
}
