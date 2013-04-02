package com.manpowergroup.cn.core.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * 将逗号分隔的字符串转换为整型的List
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> stringIntegerListConvert(String str) {
		String[] t = StringUtils.split(str, ",");
		if (t == null || t.length == 0)
			return null;
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; t != null && i < t.length; i++) {
			if (StringUtils.isNotBlank(t[i])
					&& StringUtils.isNumeric(StringUtils.trim(t[i]))) {
				Integer id = Integer.valueOf(StringUtils.trim(t[i]));
				ids.add(id);
			}
		}
		return ids;
	}
	
	/**
	 * 将逗号分隔的字符串转换为String的List
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> stringStringListConvert(String str) {
		String[] t = StringUtils.split(str, ",");
		if (t == null || t.length == 0)
			return null;
		List<String> ids = new ArrayList<String>();
		for (int i = 0; t != null && i < t.length; i++) {
			if (StringUtils.isNotBlank(t[i])
					&& StringUtils.isNumeric(StringUtils.trim(t[i]))) {
				ids.add(StringUtils.trim(t[i]));
			}
		}
		return ids;
	}

	/**
	 * 将"1,2,3,4...n"转换成",01,",",2,"...,",n,"的list
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> stringStringListConvertWithComma(String str) {
		List<String> t = stringStringListConvert(str);
		for (int i = 0; t != null && i < t.size(); i++) {
			t.set(i, "," + t.get(i) + ",");
		}
		return t;
	}

	

	/**
	 * 将 1,4,2,3...n 转换成 ,1,2,3,4...n, 并且排序
	 * @param str
	 * @return String
	 * @author jiangpeng.sun
	 * @date 2011-11-23
	 */
	public static String stringConvertWithCommaAndSort(String str) {
		String result = "";
		if (str == null || "".equals(str)) {
			return result;
		} 
		try {
			String[] strs = str.split(",");
			Set<Integer> resultSet = new HashSet<Integer>();
			for (String s : strs) {
				if (!"".equals(s.trim())) {
					resultSet.add(Integer.valueOf(s.trim()));
				}
			}
			// set排序
			TreeSet<Integer> ts = new TreeSet<Integer>(resultSet);
			for (Integer rs : ts) {
				result = result + rs + ",";
			}
		} catch (Exception e) {
			// 如果字符串不能转成整数，原样返回
			return str;
		}

		return ","+result;
	}
	
	/**
	 * 将 String[] str 转换成 ,1,2,3,4...n, 并且排序
	 * @param str
	 * @return String
	 * @author jiangpeng.sun
	 * @date 2011-11-23
	 */
	public static String stringConvertWithCommaAndSort(String[] strs)
	{
		if(strs==null || strs.length==0)return null;
		String result = "";
		try {
			Set<Integer> resultSet = new HashSet<Integer>();
			for (String s : strs) {
				if (!"".equals(s.trim())) {
					resultSet.add(Integer.valueOf(s.trim()));
				}
			}
			// set排序
			TreeSet<Integer> ts = new TreeSet<Integer>(resultSet);
			for (Integer rs : ts) {
				result = result + rs + ",";
			}
		} catch (Exception e) {
			// 如果字符串不能转成整数，原样返回
			return null;
		}

		return ","+result;
	}
	
	/**
	 * 将 ,4,1,3,2...n, 转换成 1,2,3,4...n
	 * 
	 * @param str
	 * @return String
	 * @author jiangpeng.sun
	 * @date 2011-11-23
	 */
	public static String stringConvertWithCommaAndSortBack(String str) {
		String result = "";
		if (str == null || "".equals(str)) {
			return result;
		} 
		try {
			String[] strs = str.split(",");
			Set<Integer> resultSet = new HashSet<Integer>();
			for (String s : strs) {
				if (!"".equals(s.trim())) {
					resultSet.add(Integer.valueOf(s.trim()));
				}
			}
			// set排序
			TreeSet<Integer> ts = new TreeSet<Integer>(resultSet);
			for (Integer rs : ts) {
				result = result + rs + ",";
			}
		} catch (Exception e) {
			// 如果字符串不能转成整数，原样返回
			return str;
		}
		return result.substring(0, result.length() - 1);
	}
	
	/**
	 * 根据参数产生唯一序号，参数可以为null
	 * @return 
	 * @author jiangpeng.sun
	 * @date 2012-1-11
	 */
	public static String generateSequence(){
		return UUID.randomUUID().toString() ;
		
	}
	
	/**
	 * 把list转换成以‘分隔符号’分隔的字符串，支持中文
	 * @param strs
	 * @param regular 分隔符号
	 * @return
	 * @author jiangpeng.sun
	 * @date 2012-2-24
	 */
	public static String listConvertToString(List<String> strs,String regular) {
		String result = "";
		if (strs == null || strs.size()==0) {
			return result;
		} 
		
		for (String s : strs) {
			if (s!=null && !"".equals(s.trim())) {
				result = result+s+regular;
			}
		}
		return result.substring(0, result.length() - regular.length());
	}

	/**
	 * 把list转换成以‘分隔符号’分隔的字符串，支持中文
	 * @param strs
	 * @param regular 分隔符号
	 * @return
	 * @author Bruce.xing
	 * @date 2012-3-13
	 */
	public static String listIntegerConvertToString(List<Integer> strs,String regular) {
		String result = "";
		if (strs == null || strs.size()==0) {
			return result;
		} 
		
		for (Integer s : strs) {
			if (s!=null ) {
				if(StringUtils.isNotBlank(result)){
					result = result+regular+s;
				}else{
					result = s+"";
				}
			}
		}
		return result;
	}

	
}
