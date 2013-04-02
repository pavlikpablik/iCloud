package com.manpowergroup.cn.icloud.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Resource implements java.io.Serializable {

	private static final long serialVersionUID = -1251674039799225402L;

	private Long id;

	private String name;

	private String url;

	private String fullName;

	private BigDecimal type;

	private Integer issys;

	private Integer sort;

	private Integer enabled;

	private Date createDate;

	private Long createBy;

	private Date modifyDate;

	private Long modifyBy;

	private Integer status;

	private String remark;

	private Integer sourceLevel;

	private Long parentId;
	
	private List<Resource> cResource;
	
	
    public List<Resource> getcResource() {
		return cResource;
	}

	public void setcResource(List<Resource> cResource) {
		this.cResource = cResource;
	}

	private Integer maxLevel;
	
    public static final Integer RESOURCE_DEFAULT_LEVEL = 1; //新建菜单时默认为一级菜单
    
    public static final Integer RESOURCE_MAX_LEVEL = 4;//菜单最大为两级
    public static final Integer STATUS_STOP = 0; //停用状态
	public static final Integer STATUS_ABILITY = 1; //有效的或已审批状态
    
    public static final Integer RESOURCE_BASE=100000;
    public static final Long RESOURCE_FACTOR = 1000L;
	public Long getFactorNumber(Integer sourceLevel){
		if(sourceLevel == null) return 0L;
		return (long) Math.pow(RESOURCE_FACTOR, RESOURCE_MAX_LEVEL-this.getSourceLevel());
	}
    
	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
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
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName == null ? null : fullName.trim();
	}

	public BigDecimal getType() {
		return type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public Integer getIssys() {
		return issys;
	}

	public void setIssys(Integer issys) {
		this.issys = issys;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getSourceLevel() {
		return sourceLevel;
	}

	public void setSourceLevel(Integer sourceLevel) {
		this.sourceLevel = sourceLevel;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}