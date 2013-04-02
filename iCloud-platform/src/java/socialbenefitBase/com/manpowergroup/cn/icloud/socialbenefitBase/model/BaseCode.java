/**
 * 
 */
package com.manpowergroup.cn.icloud.socialbenefitBase.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bruce.Xing
 *
 */
public class BaseCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9064989613994857407L;
	//private static final long serialVersionUID = -4593387701036428845L;
													//4593387701036428845L
	/**
	 * 状态 1 启用BDSTATUS_START，0停用
	 */
	public static final Integer BDSTATUS_START = 1;
	/**
	 * 状态 1 启用，0停用BDSTATUS_STOP
	 */
	public static final Integer BDSTATUS_STOP = 0;
	
	private Integer id;
	private String bdcode;
	private String bdname;
	private String bdtype;
	private Integer bdstatus;
	private String bdremark;
	private Integer createBy;
	private Date createDate;
	private Integer modifyBy;
	private Date modifyDate;
	private Integer seq;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBdcode() {
		return bdcode;
	}
	public void setBdcode(String bdcode) {
		this.bdcode = bdcode;
	}
	public String getBdname() {
		return bdname;
	}
	public void setBdname(String bdname) {
		this.bdname = bdname;
	}
	public String getBdtype() {
		return bdtype;
	}
	public void setBdtype(String bdtype) {
		this.bdtype = bdtype;
	}
	public Integer getBdstatus() {
		return bdstatus;
	}
	public void setBdstatus(Integer bdstatus) {
		this.bdstatus = bdstatus;
	}
	public String getBdremark() {
		return bdremark;
	}
	public void setBdremark(String bdremark) {
		this.bdremark = bdremark;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
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
		result = prime * result + ((bdcode == null) ? 0 : bdcode.hashCode());
		result = prime * result + ((bdname == null) ? 0 : bdname.hashCode());
		result = prime * result
				+ ((bdremark == null) ? 0 : bdremark.hashCode());
		result = prime * result
				+ ((bdstatus == null) ? 0 : bdstatus.hashCode());
		result = prime * result + ((bdtype == null) ? 0 : bdtype.hashCode());
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
		BaseCode other = (BaseCode) obj;
		if (bdcode == null) {
			if (other.bdcode != null)
				return false;
		} else if (!bdcode.equals(other.bdcode))
			return false;
		if (bdname == null) {
			if (other.bdname != null)
				return false;
		} else if (!bdname.equals(other.bdname))
			return false;
		if (bdremark == null) {
			if (other.bdremark != null)
				return false;
		} else if (!bdremark.equals(other.bdremark))
			return false;
		if (bdstatus == null) {
			if (other.bdstatus != null)
				return false;
		} else if (!bdstatus.equals(other.bdstatus))
			return false;
		if (bdtype == null) {
			if (other.bdtype != null)
				return false;
		} else if (!bdtype.equals(other.bdtype))
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
