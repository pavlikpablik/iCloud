package com.manpowergroup.cn.core.utils;

import com.thoughtworks.xstream.XStream;

/**
 * XML工具类
* <p>Date : 2011-11-22</p>
* <p>Module : Log</p>
* @author :lei.wang
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
public class XMLUtil {
	   
	   /**
	    *将一个序列化的对象转换为xml
	   * @author lei.wang
	   * @bugId
	   * @summary:
	   * @url:
	   * @param obj 将要被转换的对象
	   * @return  如果obj为空返回null
	   * @throws
	    */
	   private static XStream xs = new XStream(); //提高系统性能，只实例化一次
	   public static String toXML(Object obj){
		      if(obj == null){
		    	  return null;
		      }
		      
		      return xs.toXML(obj);
	   }
	   
	   /**
	    *将一个序列化的对象转换为xml
	   * @author lei.wang
	   * @bugId
	   * @summary:
	   * @url:
	   * @param obj 将要被转换的对象
	   * @param alias 别名
	   * @return  如果obj为空返回null
	   * @throws
	    */
	   public static String toXML(Object obj , String alias){
		      if(obj == null){
		    	  return null;
		      }
		      if(alias != null && !"".equals(alias)){
		    	  xs.alias(alias, obj.getClass());
		      }
		      return xs.toXML(obj);
	   }
	   
	   /**
	    *将一个xml文件转换为对象
	   * @author lei.wang
	   * @bugId
	   * @summary:
	   * @url:
	   * @param xmlStr xml信息
	   * @return  Object 由xml文件解析的对象
	   * @throws
	    */
	   public static Object fromXML(String xmlStr){
		      if(xmlStr == null || "".equals(xmlStr.trim())){
		    	  return null;
		      }
		      return xs.fromXML(xmlStr);
	   }
	
}
