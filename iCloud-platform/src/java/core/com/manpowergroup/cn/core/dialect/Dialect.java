package com.manpowergroup.cn.core.dialect;

public abstract class Dialect {

	public static enum Type{
		MYSQL,
		ORACLE,
		SQLSERVER
	}
	
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
	
}
