package com.manpowergroup.cn.core.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

public class MemcachedService implements CacheService {
	private JMemcachedClientAdapter memcachedAdapter;

	public MemcachedService(JMemcachedClientAdapter memcachedAdapter) {
		this.memcachedAdapter = memcachedAdapter;
	}

	@Override
	public Object get(String cacheKey) {
		try {
			return memcachedAdapter.get(cacheKey);
		} catch (TimeoutException  e) {
			e.printStackTrace();
		}
		catch ( InterruptedException  e) {
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(String cacheKey, Object value) {
		try {
			memcachedAdapter.put(cacheKey, value);
		}  catch (TimeoutException  e) {
			e.printStackTrace();
		}
		catch ( InterruptedException  e) {
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object delete(String cacheKey) {
		try {
			return memcachedAdapter.delete(cacheKey);
		}  catch (TimeoutException  e) {
			e.printStackTrace();
		}
		catch ( InterruptedException  e) {
			e.printStackTrace();
		}
		catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
