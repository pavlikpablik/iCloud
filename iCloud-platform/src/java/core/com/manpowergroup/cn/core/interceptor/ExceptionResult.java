package com.manpowergroup.cn.core.interceptor;

import java.io.Serializable;

public class ExceptionResult<T> implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T t;
	private boolean result;
	private int resultCode;
	private String resultText="";
	private String exceptionText="";
	


	/**
	 * 在处理的过程中用来交互的数据对象
	 * @return
	 */
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
	/**
	 * 标识本行为是否成功
	 * @return
	 */
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	/**
	 * 返回一个行为结果编号，用于后续集成，例如用来传递错误信息的编号
	 * @return
	 */
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	/**
	 * 返回一个行为结果，用于后续集成，例如用来传递错误信息
	 * @return
	 */
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	public String getExceptionText() {
		return exceptionText;
	}
	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
}
