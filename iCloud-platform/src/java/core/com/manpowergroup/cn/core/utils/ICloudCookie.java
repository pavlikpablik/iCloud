package com.manpowergroup.cn.core.utils;

import java.io.Serializable;

import javax.servlet.http.Cookie;

import com.manpowergroup.cn.core.utils.StringUtils;

public class ICloudCookie implements Serializable{
	private static final long serialVersionUID = -1618787450431533722L;
	private String staffName;
	private String staffPassword;
	public static final String COOKIE = "iCloud-platform"; //cookie信息的KEY
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPassword() {
		return staffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	
	public static ICloudCookie readICloudCookie(Cookie[] cs){
		if(cs == null) return null;
		for(Cookie c : cs){
			if(StringUtils.equalsIgnoreCase(c.getName(), ICloudCookie.COOKIE)){
				String cookieStr = c.getValue();
				try {
					ICloudCookie icc = (ICloudCookie) XMLUtil.fromXML(cookieStr);
					return icc;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
			}
		}
		return null;
	}
	
}
