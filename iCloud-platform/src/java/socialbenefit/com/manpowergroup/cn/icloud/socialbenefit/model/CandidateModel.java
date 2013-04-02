package com.manpowergroup.cn.icloud.socialbenefit.model;


public class CandidateModel {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getFnCn() {
		return fnCn;
	}

	public void setFnCn(String fnCn) {
		this.fnCn = fnCn;
	}

	private Integer id;
	
	private String no; //证件编号
	
	private String fnCn; //中文名
	

}
