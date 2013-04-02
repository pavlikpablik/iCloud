package com.manpowergroup.cn.icloud.socialbenefit.model;

public class SsbItemModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2187943462659590483L;

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
