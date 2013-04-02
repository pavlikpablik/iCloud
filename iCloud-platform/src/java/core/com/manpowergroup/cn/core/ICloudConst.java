package com.manpowergroup.cn.core;

public class ICloudConst {
	public static final String LOGIN_USER = "iCloudPlatformLogonUser";
	public static final String USER_ROLES = "userRoles";
	public static final String USER_ACTIONS = "userActions";
	
	//导入导出的文件路径配置
	public static final String EXCEL_EXPORT = "classpath:/excelexport.properties"; 
	
	//webservice 超时设置
	public static final Integer WEBSERVICE_CONNECTION_TIMEOUT = 60000; 
	public static final Integer WEBSERVICE_RECEIVE_TIMEOUT = 360000; 
}
