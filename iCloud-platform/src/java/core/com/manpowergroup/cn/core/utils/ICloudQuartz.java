package com.manpowergroup.cn.core.utils;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.icloud.webservice.VendorService;

public class ICloudQuartz {

	  // @Autowired
	   private VendorService vendorService;
	   
	   /**
	    * 2013-03-11 add by bob 定时更新供应商脚本
	    * @return
	    * @throws Exception
	    */
	 public String updateVendor() throws Exception{
		//手动注入
			ApplicationContext ctx = new ClassPathXmlApplicationContext(  
	        "applicationContext-webservice.xml");  
			vendorService = (VendorService) ctx.getBean("vendorService");  
			//设置超时时间
			org.apache.cxf.endpoint.Client client1 = ClientProxy.getClient(vendorService);
	        HTTPConduit http = (HTTPConduit) client1.getConduit();
	        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	        httpClientPolicy.setConnectionTimeout(ICloudConst.WEBSERVICE_CONNECTION_TIMEOUT);
	        httpClientPolicy.setAllowChunking(false);
	        httpClientPolicy.setReceiveTimeout(ICloudConst.WEBSERVICE_RECEIVE_TIMEOUT);
	        http.setClient(httpClientPolicy);
		 ICloudDataUtil.vendorList=vendorService.getVendorAllList();
		 return null;
	 }
	 
	 public String updateScript() throws Exception{
		 updateVendor();
		 return null;
	 }
	
}
