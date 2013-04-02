package com.manpowergroup.cn.icloud.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemType {
	
	/**
	 * 状态 1 启用BDSTATUS_START，0停用
	 */
	public static final Integer BDSTATUS_START = 1;
	/**
	 * 状态 1 启用，0停用BDSTATUS_STOP
	 */
	public static List<String> itemsTypeList;
	
	/**
	 *Title:this
	 *@Param: 
	 *@Author: bob
	 *@Description: 项目初始化
	 */
	public static List<String> getItemsTypeList(){
				itemsTypeList = new ArrayList<String>();
				itemsTypeList.add("import");
				itemsTypeList.add("un-normal");
				itemsTypeList.add("no-calculate");
				itemsTypeList.add("normal");
				return itemsTypeList;
	}
	
	private Long id;
	private String name;
	private String nameEn;
	private Integer status;
	private Long createBy;
	private Date createDate;
	private Long modifyBy;
	private Date modifyDate;
	private String isUse;
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
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
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
