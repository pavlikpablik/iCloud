package com.manpowergroup.cn.icloud.sys.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 在Excel导入导出时产生的信息
* <p>Date : 2011-11-7</p>
* <p>Module : Excel 导入导出</p>
* @author :lei.wang
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
public class ExcelImg implements Serializable{

	private static final long serialVersionUID = -3093139607301407030L;

	public static final String OK = "成功";
	public static final String FAILD = "失败";
	public static final String SEPARATOR = "<br/>";
		
	public ExcelImg(String isOK,String result) {
		this.result = result;
		this.isOK = isOK;
		this.results.add(result + this.getResultSeparator());
	}
	
	public ExcelImg(String isOK, String result, String resultSeparator , boolean isAdd) {
		this.resultSeparator = resultSeparator;
		this.isOK = isOK;
		if(isAdd){
		 this.result = result;
		 this.results.add(result + this.getResultSeparator());
		}
	}

	

	public ExcelImg(String isOK) {
		this.isOK = isOK;
	}
	
   public ExcelImg() {
    }



	private String result="";
	private String resultSeparator = SEPARATOR;
	private List<String> results = null;
	private String isOK = ExcelImg.OK;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIsOK() {
		return isOK;
	}

	public void setIsOK(String isOK) {
		this.isOK = isOK;
	}

	public List<String> getResults() {
		return results;
	}



	public String getResultSeparator() {
		if(resultSeparator == null)
		return SEPARATOR;
		return resultSeparator;
	}

	public void setResultSeparator(String resultSeparator) {
		this.resultSeparator = resultSeparator;
	}
	
	
}
