package com.manpowergroup.cn.icloud.base.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.manpowergroup.cn.icloud.base.service.CityService;

public class CityServiceImplTest {

	CityService cityService;
	DruidDataSource datasource;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		cityService = (CityService) ac.getBean("cityServiceImpl");
		datasource = (DruidDataSource)ac.getBean("dataSource");
	
	}

	@After
	public void tearDown() throws Exception {
		
	}

	/*@Test
	public void testSaveOrUpdate() {
		City city1 = new City(); 
		city1.setName("大连-2");
		city1.setNameEn("LiaoNing");
		cityService.savetest(city1);
	}*/
	
	
	

}
