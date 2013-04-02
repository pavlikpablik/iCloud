package com.manpowergroup.cn.icloud.ssb.model;

import java.io.Serializable;

public class SocialBenefitEffective implements Serializable{

	private static final long serialVersionUID = -6400893846413281584L;
	private Integer startType;
	private Integer startDay;
	private Integer endType;
	private Integer endDay;
	
	
	public Integer getStartType() {
		return startType;
	}
	public void setStartType(Integer startType) {
		this.startType = startType;
	}
	public Integer getStartDay() {
		return startDay;
	}
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}
	public Integer getEndType() {
		return endType;
	}
	public void setEndType(Integer endType) {
		this.endType = endType;
	}
	public Integer getEndDay() {
		return endDay;
	}
	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}
	
	
}
