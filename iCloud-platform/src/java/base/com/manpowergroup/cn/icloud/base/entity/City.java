package com.manpowergroup.cn.icloud.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class City implements java.io.Serializable {
	private static final long serialVersionUID = -4593387701036428845L;
	/**
	 * 状态 1 启用STATUS_START，0停用
	 */
	public static final Integer STATUS_START = 1;
	/**
	 * 状态 1 启用，0停用STATUS_STOP
	 */
	public static final Integer STATUS_STOP = 0;
	
	/**
	 * 层级关系 1 ：省 LEVEL_PROVINCE 2：市
	 */
	public static final Integer LEVEL_PROVINCE = 1;
	/**
	 * 层级关系 1 ：省 2：市  LEVEL_CITY
	 */
	public static final Integer LEVEL_CITY = 2;
	
	/**
	 * 省级序列增加基数，每新建一个省份，序列增加基数加10w
	 */
	public static final Integer SEQ_PROVINCE_BASE = 1000000;
	
	/**
	 * 市级序列增加基数，没新建一个城市，序列增加的基数为1W
	 */
	public static final Integer  SEQ_CITY_BASE = 10000;
	
	//org.apache.ibatis.type.JdbcType.TIMESTAMP
	
	private Long id; 
	private String name;
	private String nameEn;
	private Integer seq;
	private Integer level;
	private String remark;
	private Integer status;
	private City parentCity;
	private Long createBy;
	private Date createDate;
	private Long modifyBy;
	private Date modifyDate;
	private List<City> citys = new ArrayList<City>();
	
	private Long parentId;
	
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 不对应到数据库
	 */
	private String parentName;

	
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public City getParentCity() {
		return parentCity;
	}

	public void setParentCity(City parentCity) {
		this.parentCity = parentCity;
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}