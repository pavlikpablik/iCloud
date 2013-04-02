package com.manpowergroup.cn.icloud.sys.model;

public class Vendor implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 587586707943393133L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Integer id;
	
	private String name;
}
