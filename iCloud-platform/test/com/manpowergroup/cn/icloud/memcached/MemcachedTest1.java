package com.manpowergroup.cn.icloud.memcached;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

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
public class MemcachedTest1 {

	@Test
	/**
	 * @description: TODO
	 * @user: jiangpeng.sun
	 * @date: 2013-3-27
	 */
	public void test() throws IOException  {
		System.out.println(showMemoryUsage()+"初始内存占用");
		
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("10.86.36.23:11211"));
				
				//将10.86.36.23:11211的备份节点设置为10.86.36.22:11211 使用备份节点的前提是启用failure模式
				//AddrUtil.getAddressMap("10.86.36.23:11211,10.86.36.22:11211"));
		
		//启用failure模式(默认不启用failure模式)
	    builder.setFailureMode(true);//所谓failure模式是指，当一个memcached节点down掉的时候，发往这个节点的请求将直接失败，而不是发送给下一个有效的memcached节点
				
		
		builder.setConnectionPoolSize(1);//启用连接池（默认为1）
		builder.setCommandFactory(new BinaryCommandFactory());//二进制协议
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());//一致性哈希
		
		
		//统计连接是否空闲，禁止统计
		builder.getConfiguration().setStatisticsServer(false);
		
		MemcachedClient memcachedClient = builder.build();
		/*try {
			memcachedClient.flushAll();
		} catch (TimeoutException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (MemcachedException e1) {
			e1.printStackTrace();
		}*/
		
		
	    memcachedClient.setOptimizeMergeBuffer(false);  //开启合并buffer的优化（响应时间比较在意，设置为false）
	    memcachedClient.setEnableHeartBeat(false);//关闭心跳检测，减小系统开销
	    
	    //xmemcached会将连续的单个get请求合并成一个multi get请求作批量获取，提高效率
	    memcachedClient.setOptimizeGet(false);
	   //memcachedClient.setMergeFactor(50);   //默认是150，缩小到50（将连续的请求合并成socket发送缓冲区大小的buffer发送）
	    
	   memcachedClient.getTranscoder().setCompressionThreshold(1024*32);//大小阀值默认是16K.设置为1K,大约1K的数据将被压缩
	    
	  /*  builder.setSocketOption(StandardSocketOption.SO_RCVBUF, 32 * 1024); // 设置接收缓存区为32K，默认16K
        builder.setSocketOption(StandardSocketOption.SO_SNDBUF, 32 * 1024); // 设置发送缓冲区为32K，默认为8K
        builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false); // 启用nagle算法，提高吞吐量，默认关闭
*/        
		try {
			
			/*int DYNAMIC_MAX_QUEUED_NOPS = (int)(40000D * ((double)Runtime.getRuntime().maxMemory() / 1024D / 1024D / 1024D));
			int DEFAULT_MAX_QUEUED_NOPS = DYNAMIC_MAX_QUEUED_NOPS <= 40000 ? DYNAMIC_MAX_QUEUED_NOPS : 40000;
			System.out.println(DYNAMIC_MAX_QUEUED_NOPS);
			System.out.println(DEFAULT_MAX_QUEUED_NOPS);*/
			int count = 1;
			int count2 = 1;
			Long start = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				SsbAjust ajust = new SsbAjust();
				ajust.setId(i);
				ajust.setAjustmentStatus(1111111);
				ajust.setCandAjustmentssbDetails(Lists.newArrayList(new SsbAjustDetail(),new SsbAjustDetail(),new SsbAjustDetail(),new SsbAjustDetail()));
				ajust.setCompanyAmount(BigDecimal.TEN);
				ajust.setCandidateId(100000000);
				ajust.setCandidateName("哈哈啊哈哈");
				ajust.setCandidateNo("21028219851542632");
				ajust.setCreateDate(new Date());
				ajust.setModifyBy(111);
				ajust.setCreateBy(2222);
				ajust.setRemark("dddddddddddddddddddddddddddddddddddddddddddddddddddd");
				//map.put("SsbAjust-"+i, ajust);
				
				if (memcachedClient.set("SsbAjust11-"+i, 3000, ajust)) {
					count++;
				}
			}
			System.out.println(count+"add总数量");
			
			System.out.println(showMemoryUsage()+"构造数据后内存占用");
			Long middle = System.currentTimeMillis();
			System.out.println((middle-start)/1000);
		
			/*for (int i = 0; i < 1000; i++) {
			
				if (memcachedClient.delete("SsbAjust11-"+i)) {
					count2++;
				}
			}
			
			System.out.println(count2+"delete总数量");*/
			
			
			
			/*System.out.println(showMemoryUsage()+"取出数据后内存占用");*/
			
			Long end = System.currentTimeMillis();
			System.out.println((end-start)/1000);
			
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		}  catch (InterruptedException e) {
		}
		catch (Exception e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
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
