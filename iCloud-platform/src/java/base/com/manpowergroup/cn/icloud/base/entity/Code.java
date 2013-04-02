package com.manpowergroup.cn.icloud.base.entity;

import java.util.Date;

public class Code implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9064989613994857407L;

	/**
	 * 状态 1 启用BDSTATUS_START，0停用
	 */
	public static final Integer BDSTATUS_START = 1;
	/**
	 * 状态 1 启用，0停用BDSTATUS_STOP
	 */
	public static final Integer BDSTATUS_STOP = 0;
	
	private Long id;
	private String bdCode;
	private String bdName;
	private String bdType;
	private Integer bdStatus;
	private String bdRemark;
	private Long createBy;
	private Date createDate;
	private Long modifyBy;
	private Date modifyDate;
	private Integer seq;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBdCode() {
		return bdCode;
	}
	public void setBdCode(String bdCode) {
		this.bdCode = bdCode;
	}
	public String getBdName() {
		return bdName;
	}
	public void setBdName(String bdName) {
		this.bdName = bdName;
	}
	public String getBdType() {
		return bdType;
	}
	public void setBdType(String bdType) {
		this.bdType = bdType;
	}
	public Integer getBdStatus() {
		return bdStatus;
	}
	public void setBdStatus(Integer bdStatus) {
		this.bdStatus = bdStatus;
	}
	public String getBdRemark() {
		return bdRemark;
	}
	public void setBdRemark(String bdRemark) {
		this.bdRemark = bdRemark;
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
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bdCode == null) ? 0 : bdCode.hashCode());
		result = prime * result + ((bdName == null) ? 0 : bdName.hashCode());
		result = prime * result
				+ ((bdRemark == null) ? 0 : bdRemark.hashCode());
		result = prime * result
				+ ((bdStatus == null) ? 0 : bdStatus.hashCode());
		result = prime * result + ((bdType == null) ? 0 : bdType.hashCode());
		result = prime * result
				+ ((createBy == null) ? 0 : createBy.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifyBy == null) ? 0 : modifyBy.hashCode());
		result = prime * result
				+ ((modifyDate == null) ? 0 : modifyDate.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Code other = (Code) obj;
		if (bdCode == null) {
			if (other.bdCode != null)
				return false;
		} else if (!bdCode.equals(other.bdCode))
			return false;
		if (bdName == null) {
			if (other.bdName != null)
				return false;
		} else if (!bdName.equals(other.bdName))
			return false;
		if (bdRemark == null) {
			if (other.bdRemark != null)
				return false;
		} else if (!bdRemark.equals(other.bdRemark))
			return false;
		if (bdStatus == null) {
			if (other.bdStatus != null)
				return false;
		} else if (!bdStatus.equals(other.bdStatus))
			return false;
		if (bdType == null) {
			if (other.bdType != null)
				return false;
		} else if (!bdType.equals(other.bdType))
			return false;
		if (createBy == null) {
			if (other.createBy != null)
				return false;
		} else if (!createBy.equals(other.createBy))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifyBy == null) {
			if (other.modifyBy != null)
				return false;
		} else if (!modifyBy.equals(other.modifyBy))
			return false;
		if (modifyDate == null) {
			if (other.modifyDate != null)
				return false;
		} else if (!modifyDate.equals(other.modifyDate))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
	
	
	


}
