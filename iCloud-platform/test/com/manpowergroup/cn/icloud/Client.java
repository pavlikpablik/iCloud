/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.manpowergroup.cn.icloud;



public final class Client {

	private Client() {
	}

	public static void main(String args[]) throws Exception {
		// START SNIPPET: client 结合spring的写法
		
		 /* ClassPathXmlApplicationContext context = new
		  ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});
		 
		  HelloWorld client = (HelloWorld)context.getBean("client");
		  
		  String response = client.sayHi("Joe");
		  System.out.println("Response: " + response); System.exit(0);*/
		// END SNIPPET: client

		// 正常写法
		/*JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(HelloWorld.class);
		proxyFactory.setAddress("http://10.86.36.23:8080/iCloud-platform/services/HelloWorld");
		HelloWorld hello = (HelloWorld) proxyFactory.create();*/
		
		/*ClassPathXmlApplicationContext context = new
				  ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});
				 
				  HelloWorld hello = (HelloWorld)context.getBean("client");
		

		org.apache.cxf.endpoint.Client client1 = ClientProxy.getClient(hello);
		HTTPConduit http = (HTTPConduit) client1.getConduit();

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

		httpClientPolicy.setConnectionTimeout(600000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(600000);

		http.setClient(httpClientPolicy);
		System.out.println("Invoke sayHi()....");
		System.out.println(hello.sayHi(System.getProperty("user.name")));*/

	}
}
