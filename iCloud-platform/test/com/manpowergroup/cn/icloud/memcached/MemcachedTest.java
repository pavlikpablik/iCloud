package com.manpowergroup.cn.icloud.memcached;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;
/**
 * @description: TODO
 * @author jiangpeng.sun
 * @date 2013-3-27
 */
public class MemcachedTest {

	@Test
	/**
	 * @description: TODO
	 * @user: jiangpeng.sun
	 * @date: 2013-3-27
	 */
	public void test() throws IOException  {
		System.out.println(showMemoryUsage()+"初始内存占用");
		
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("10.86.15.241:11211"));
		builder.setConnectionPoolSize(1);//启用连接池（默认为1）
		builder.setCommandFactory(new BinaryCommandFactory());
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());//一致性哈希
		
		builder.getConfiguration().setStatisticsServer(false);
		
		MemcachedClient memcachedClient = builder.build();
		memcachedClient.setMergeFactor(50);   //默认是150，缩小到50
	    memcachedClient.setOptimizeMergeBuffer(false);  //关闭合并buffer的优化
	    memcachedClient.setEnableHeartBeat(false);
		try {
			/*Tree tree = new Tree();
			tree.setName("aaa");
			tree.setId(1);
			memcachedClient.set("tree", 0, tree);
			System.out.println("TreeName=" + ((Tree)memcachedClient.get("tree")).getName());
			
			assertEquals("aaa", ((Tree)memcachedClient.get("tree")).getName());

			
			memcachedClient.set("hello", 0, "Hello,xmemcached");
			String value = memcachedClient.get("hello");
			System.out.println("hello=" + value);
			memcachedClient.delete("hello");
			value = memcachedClient.get("hello");
			System.out.println("hello=" + value);*/
			Long start = System.currentTimeMillis();
			Map<String, SsbAjust> map = new HashMap<String, SsbAjust>();
			for (int i = 0; i < 10000; i++) {
				SsbAjust ajust = new SsbAjust();
				ajust.setId(i);
				ajust.setAjustmentStatus(1111111);
				ajust.setCandAjustmentssbDetails(Lists.newArrayList(new SsbAjustDetail(),new SsbAjustDetail(),new SsbAjustDetail(),new SsbAjustDetail()));
				ajust.setCompanyAmount(BigDecimal.ZERO);
				ajust.setCandidateId(100000000);
				ajust.setCandidateName("哈哈啊哈哈");
				ajust.setCandidateNo("21028219851542632");
				ajust.setCreateDate(new Date());
				ajust.setModifyBy(111);
				ajust.setCreateBy(2222);
				ajust.setRemark("dddddddddddddddddddddddddddddddddddddddddddddddddddd");
				map.put("SsbAjust-"+i, ajust);
			}
			
			System.out.println(showMemoryUsage()+"构造数据后内存占用");
			
			String key = UUID.randomUUID().toString();
			System.out.println(key);
			memcachedClient.set(key, 0, map);
			
			map.clear();
			map =null;
			System.out.println(showMemoryUsage()+"清除数据后内存占用");
			
			Map<String, SsbAjust> reMap = memcachedClient.get("4125d5b9-cd3b-455e-b67c-e94c10c42a7e");
			System.out.println(reMap.size());
			for (Entry<String, SsbAjust> re : reMap.entrySet()) {
				/*System.out.println(re.getKey());*/
				String key11 = re.getKey();
			}
			memcachedClient.delete(key);
			
			Long end = System.currentTimeMillis();
			System.out.println((end-start)/1000/60);
			System.out.println(showMemoryUsage()+"取出数据后内存占用");
			
			
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
		System.out.println(showMemoryUsage()+"结束程序后内存占用");
	}
	
	public static String showMemoryUsage() {
		long memory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		return String.format("%.1f MB", (memory / (1024.0 * 1024.0)));
	}

}
