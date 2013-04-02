package com.manpowergroup.cn.icloud.memcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Test;
/**
 * @description: TODO
 * @author jiangpeng.sun
 * @date 2013-3-27
 */
public class MemcachedTest2 {

	@Test
	/**
	 * @description: TODO
	 * @user: jiangpeng.sun
	 * @date: 2013-3-27
	 */
	public void test() throws IOException  {
		
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("10.86.36.22:11211"));
		builder.setConnectionPoolSize(1);//启用连接池（默认为1）
		builder.setCommandFactory(new BinaryCommandFactory());//二进制协议
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());//一致性哈希
		
		//统计连接是否空闲，禁止统计
		builder.getConfiguration().setStatisticsServer(false);
		
		MemcachedClient memcachedClient = builder.build();
		try {
			memcachedClient.flushAll();
		} catch (TimeoutException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (MemcachedException e1) {
			e1.printStackTrace();
		}
		
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
		
	}
	
	public static String showMemoryUsage() {
		long memory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		return String.format("%.1f MB", (memory / (1024.0 * 1024.0)));
	}

}
