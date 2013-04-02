package com.manpowergroup.cn.icloud.sys.entity;

import java.util.Date;

public class Organ implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1042345612655809328L;

	private Long id;

    private String name;

    private Long parentId;
    private String parentName;

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	private Long branchId;

    private Date createDate;

    private Long createBy;

    private Date modifyDate;

    private Long modifyBy;

    private Integer status;

    private String remark;
    
    
    
    public static final Integer ORGAN_DEFAULT_LEVEL = 1; //新建时默认为一级
    
    public static final Integer RESOURCE_MAX_LEVEL = 10;//最大级数


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

  
}