package com.manpowergroup.cn.icloud.sys.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.manpowergroup.cn.core.utils.DataBasePasswordUtil;

public class DataBasePasswordUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetICloudPassword() {
		try {
			assertEquals("Manpower1", DataBasePasswordUtil.getICloudPassword());
		} catch (Exception e) {
		}
		try {
			DataBasePasswordUtil.getICloudPassword();
		} catch (Exception e) {
			assertEquals("数据库密码只能获取一次", e.getMessage());
		}
		
	}

	

}
