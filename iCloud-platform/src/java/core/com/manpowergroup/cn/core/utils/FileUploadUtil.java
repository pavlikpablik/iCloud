package com.manpowergroup.cn.core.utils;

import java.io.File;
import java.util.UUID;

/**
 * 文件操作工具类
* <p>Date : 2011-11-3</p>
* @author :lei.wang
* @url:
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
public final class FileUploadUtil extends org.apache.commons.io.FileUtils{

	/**
	 * 通过文件名称获取文件后缀
	* @author lei.wang
	* @param  fileName 文件名称
	* @return 
	* @throws
	 */
	public static String getFileSuffix(String fileName) {
		if (null == fileName || "".equals(fileName)) {
			return "";
		}
		if (fileName.lastIndexOf(".") <= 0
				|| fileName.lastIndexOf(".") == fileName.length() - 1) {
            return "";
		}
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}
	
	/**
	 * 获得一个不会重复的文件名称
	* @author lei.wang
	* @param  fileName 文件名称
	* @return 
	* @throws
	 */
	public static File getUUIDFileName(String filePath,String fileName) {   
		if(filePath == null){
			return null;
		}
		return new File(filePath + UUID.randomUUID().toString() + getFileSuffix(fileName));
	}

}
