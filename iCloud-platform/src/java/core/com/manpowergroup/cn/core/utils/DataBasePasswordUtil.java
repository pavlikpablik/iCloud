package com.manpowergroup.cn.core.utils;



public class DataBasePasswordUtil {
	private static  String icloudp = "4a13cbcb9f901715";
//	private static String icloudSqlserver = "89824680f481ecd669bf049f1d0a07d7";
	public synchronized static String getICloudPassword() throws Exception{
		if(icloudp == null) throw new Exception("数据库密码只能获取一次");
		String p = DESPlus.decrypt(icloudp);
		icloudp = null;
		return p;
	}
	/*public synchronized static String getICloudPasswordSqlserver() throws Exception{
		if(icloudSqlserver == null) throw new Exception("数据库密码只能获取一次");
		String p = DESPlus.decrypt(icloudSqlserver);
		icloudSqlserver = null;
		return p;
	}*/
	
}
