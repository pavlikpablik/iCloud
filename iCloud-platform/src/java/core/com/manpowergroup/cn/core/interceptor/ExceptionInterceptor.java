package com.manpowergroup.cn.core.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义异常拦截
 * @author jiangpeng.sun
 */
public class ExceptionInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;
	 static String regEx = "[\\u4e00-\\u9fbb]+";   
	 static Pattern pat = Pattern.compile(regEx);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		try {
			result = invocation.invoke();
		} 
		catch (Exception exception) {
			String actionName = invocation.getProxy().getActionName();
//			String actionMethod = invocation.getProxy().getMethod();
			ExceptionResult<Exception> executeResult  = new ExceptionResult<Exception>();
			if(exception.getMessage().contains("java")){
				executeResult.setResultText(actionName.replaceAll("_", ":")+"失败。");
			}else {
				executeResult.setResultText(exception.getMessage());
			}
			executeResult.setResult(false);
			executeResult.setT(exception);
			executeResult.setExceptionText(exception.toString());
			exception.printStackTrace();//方便开发人员调试，后期去掉
			try {
				ServletActionContext.getResponse().setStatus(444);
				Struts2Utils.renderJson(executeResult, "encoding:utf-8", "no-cache:false");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 取异常信息
	 * @param exception
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-12-13
	 */
	/*private String getExceptionInfo(Exception exception) {
		StringBuffer bExceptionInfo = new StringBuffer();
		bExceptionInfo.append(exception.toString());
		bExceptionInfo.append("\n\t");

		StackTraceElement[] stackTraceElements = exception.getStackTrace();
		for (int i = 0; i < stackTraceElements.length; i++) {
			bExceptionInfo.append("at "+ stackTraceElements[i].toString() + "\n\t");
		}
		return bExceptionInfo.toString();
	}*/

	/**
	 * 判断字符串是否包含中文
	 * @param str
	 * @return Boolean
	 * @author jiangpeng.sun
	 * @date 2012-1-16
	 */
	private Boolean hasChineseCharacters(String str){
		
		if (str==null || "".equals(str)) {
			return false;
		}
	   Matcher matcher = pat.matcher(str);     
        boolean flg = false;  
        if (matcher.find())    {    
            flg = true;   
        }     
        return flg;     
	}

	

	

}