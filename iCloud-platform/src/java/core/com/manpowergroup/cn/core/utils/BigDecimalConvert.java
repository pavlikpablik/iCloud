package com.manpowergroup.cn.core.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**brief description.
 * <p>Date: 2011-9-19</p>
 * <p>Module : </p>
 * <p>Description: </p>
 * <p>Remark : </p>
 * @author jiangpeng.sun
 * @version :
 * @bugId :
 * <p>------------------------------------------------------------</p>
 * <p> 修改历史</p>
 * <p> 序号 日期 修改人 修改原因</p>
 * <p> 1 </p>
 */
public class BigDecimalConvert extends StrutsTypeConverter{

	
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map map, String[] values, Class toClass) {
		if (BigDecimal.class == toClass) {
			String decimalStr = values[0];
			BigDecimal d = null;
			if (!decimalStr.equals("")) {
				d = new BigDecimal(decimalStr);
			}
			return d;
		}
		return 0;
	}

	
	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
		if (o instanceof BigDecimal) {
			BigDecimal d = (BigDecimal)o;
			//保留两位，四舍五入
			BigDecimal b = d.setScale(2,BigDecimal.ROUND_HALF_UP);
			return b.toString();
		}
		return o.toString();
	}

}
