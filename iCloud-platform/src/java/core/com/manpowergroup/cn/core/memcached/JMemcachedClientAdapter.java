package com.manpowergroup.cn.core.memcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.manpowergroup.cn.core.utils.SpringContextUtil;

public class JMemcachedClientAdapter {
	private MemcachedClient memcachedClient;
	/*private MemcachedClient mc;*/
	/*private String servers =  "10.86.15.241:11211";

	public void setServers(String servers) {
		this.servers = servers;
	}*/

	public JMemcachedClientAdapter() throws IOException {
		/*MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses(servers));
		//builder.setConnectionPoolSize(5);//启用连接池会有线程安全性问题（默认为1）
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());//一致性哈希
		mc = builder.build();*/
		
		memcachedClient = (MemcachedClient)SpringContextUtil.getBean("memcachedClient");
		
	}

	public Object get(String cacheKey) throws TimeoutException, InterruptedException, MemcachedException {
		return memcachedClient.get(cacheKey);
	}

	public void put(String cacheKey, Object value) throws TimeoutException, InterruptedException, MemcachedException {
		memcachedClient.set(cacheKey,0, value);
	}

	public Object delete(String cacheKey) throws TimeoutException, InterruptedException, MemcachedException {
		return memcachedClient.delete(cacheKey);
	}
}