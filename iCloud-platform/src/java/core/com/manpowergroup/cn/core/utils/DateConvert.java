package com.manpowergroup.cn.core.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 简单日期转换
 * @author alan.xiao
 *
 */
public class DateConvert extends StrutsTypeConverter {



	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values[0] == null || values[0].trim().equals(""))
			return null;
		try {
			Date date = DateUtils.parseDate(values[0], new String[]{"yyyy-MM-dd"});
			return date;
		} catch (ParseException e) {
			return null;
		}
	}


	public String convertToString(Map context, Object o) {   
        // 格式化为date格式的字符串   
        Date date = (Date) o;   
        try {
			String dateString=DateFormatUtils.format(date, "yyyy-MM-dd");
			return dateString;
		} catch (RuntimeException e) {
			return null;
		} 
    }  
}