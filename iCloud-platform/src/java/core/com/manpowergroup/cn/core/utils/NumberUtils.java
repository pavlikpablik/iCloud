package com.manpowergroup.cn.core.utils;

import java.math.BigDecimal;


public class NumberUtils extends org.apache.commons.lang.math.NumberUtils{
       
	   
		/**
		 * 将String 转换为 Integer
		* @author leo
		* @param str
		* @return 
		* @throws
		 */
	   public static Integer convertStringToInteger(String str){
		   Integer integer = null;
		try {
			str = StringUtils.trimToNull(str);
			if (NumberUtils.isNumber(str)) {

				integer = NumberUtils.createInteger(str);
			}
		} catch (Exception e) {
			integer = null;
			e.printStackTrace();
		}
		   return integer;
	   }
	   
	/**
	 * 基数大于上限，取上限；基数小于下限，取下限；否则取本身
	 * @param cap上限
	 * @param floor下限
	 * @param base基数
	 * @author jiangpeng.sun
	 * @date 2012-2-16
	 */
	public static BigDecimal compromise(BigDecimal cap, BigDecimal floor,BigDecimal base) {
		BigDecimal result = null;// 基数
		
		//上限为0，取基数
		if (BigDecimal.ZERO.compareTo(cap)==0) {
			return  base;
		}
		
		// -1表示小于，0是等于，1是大于
		switch (base.compareTo(cap)) {
		case 1:
			result = cap;// 取上限
			break;
		case -1:
			if (base.compareTo(floor) == -1) {
				result = floor;// 取下限
			} else {
				result = base;
			}
			break;
		case 0:
			result = base;
			break;
		default:
			result = BigDecimal.ZERO;
			break;
		}
		return result;
	}
	
}
