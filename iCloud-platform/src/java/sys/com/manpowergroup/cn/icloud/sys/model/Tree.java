package com.manpowergroup.cn.icloud.sys.model;

public class Tree implements java.io.Serializable{

	private static final long serialVersionUID = -7006946486642758114L;
	private Integer id;
	private Integer pId;
	private String name;

	private Boolean open;
	private Boolean checked;//checked:true 选中
	private Boolean doCheck;//doCheck:false 禁止勾选
	private Boolean nocheck;//nocheck:true 无勾选框

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getDoCheck() {
		return doCheck;
	}

	public void setDoCheck(Boolean doCheck) {
		this.doCheck = doCheck;
	}

}
