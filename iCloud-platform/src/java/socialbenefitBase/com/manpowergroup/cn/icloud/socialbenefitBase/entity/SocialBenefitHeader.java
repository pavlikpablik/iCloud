package com.manpowergroup.cn.icloud.socialbenefitBase.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.icloud.ssb.model.SocialBenefitEffective;


public class SocialBenefitHeader implements java.io.Serializable {

	private static final long serialVersionUID = 5741005175644487535L;

	private Integer id;

	private String name; // 社保名称

	private Integer cityId; // 社保所在市

	private String residency; // 户籍

	private Integer ajustTime; // 每天调整时间

	private Integer attributeMonth; // 社保所属月
	
	private Integer monthEffctive;//社保起缴月

	private Integer addLimitTime; // 加保截止日期
	
	private Integer ebbLimitTime; // 退保截止日期

	private String termMonth; // 支付月

	private String effective; // 开始或结束规则

	private String effectiveRemark; // 开始或结束规则备注

	private Integer status; // 状态有三种 -1: 逻辑已被删除 0：停用 1：启用 2:待审批

	private Integer createBy;

	private Date createDate;

	private Integer modifyBy;

	private Date modifyDate;

	private String description;
	private String remark;
	
	private String ssbFile; //上传附件
	
	//add by sundy 2012/06/27
	private Integer quitSsbStatus;//退保后是否需要费用的状态  0:无需费用  1:需要费用
	private String quitSsbStatusStr; //0:否 1:是
	

	private List<SocialBenefitDetail> socialBenefitDetails; // 所含险种类型
	
	@Transient
	private SocialBenefitEffective sbe = new SocialBenefitEffective(); // 开始或结束规则对象

	/*** 页面字段 ****/
	private Integer provinceId; // 省ID

	private String cityName;// 城市名称

	private BigDecimal personMonthPercent;// 个人月百分比

	private BigDecimal personMonthAmount;// 个人月固定金

	private BigDecimal companyMonthPercent;// 公司月百分比

	private BigDecimal companyMonthAmount;// 公司月固定金

	private BigDecimal personOtherPercent;// 个人其他百分比

	private BigDecimal personOtherAmount;// 其他固定金

	private BigDecimal companyOtherPercent;// 公司其他百分比

	private BigDecimal companyOtherAmount;// 公司其他固定金
	
	
	private String amString;// 社保所属月
	private String meString;// 社保起缴月
	
	public String getMeString() {
		return meString;
	}

	public void setMeString(String meString) {
		this.meString = meString;
	}

	private String everyMonth;//默认支付约-每月选中
	
	public String getEveryMonth() {
		return everyMonth;
	}

	public void setEveryMonth(String everyMonth) {
		this.everyMonth = everyMonth;
	}

	private List<String> termMonths = new ArrayList<String>();//支付月
	private List<String> residencys = new ArrayList<String>();//户籍
	
	private String effectiveStart;
	private String effectiveEnd;
	
	public List<String> getTermMonths() {
		List<String> resultList = new ArrayList<String>();
		if (this.termMonth == null || "".equals(this.termMonth)) {
			return termMonths;
		} else {
			for (String s : this.termMonth.split(",")) {
				resultList.add(s.trim());
			}
		}
		return resultList;
	}

	public void setTermMonths(List<String> termMonths) {
		this.termMonths = termMonths;
	}

	public List<String> getResidencys() {
		List<String> resultList = new ArrayList<String>();
		if (this.residency == null || "".equals(this.residency)) {
			return residencys;
		} else {
			for (String s : this.residency.split(",")) {
				resultList.add(s.trim());
			}
		}
		return resultList;
	}

	public void setResidencys(List<String> residencys) {
		this.residencys = residencys;
	}
	/*******************************************/

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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getResidency() {
		return residency;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}

	public Integer getAjustTime() {
		return ajustTime;
	}

	public void setAjustTime(Integer ajustTime) {
		this.ajustTime = ajustTime;
	}

	public Integer getAttributeMonth() {
		return attributeMonth;
	}

	public void setAttributeMonth(Integer attributeMonth) {
		this.attributeMonth = attributeMonth;
	}

	public Integer getAddLimitTime() {
		return addLimitTime;
	}

	public void setAddLimitTime(Integer addLimitTime) {
		this.addLimitTime = addLimitTime;
	}

	public Integer getEbbLimitTime() {
		return ebbLimitTime;
	}

	public void setEbbLimitTime(Integer ebbLimitTime) {
		this.ebbLimitTime = ebbLimitTime;
	}

	public String getTermMonth() {
		return termMonth;
	}

	public void setTermMonth(String termMonth) {
		this.termMonth = termMonth;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.sbe = (SocialBenefitEffective) XMLUtil.fromXML(effective);
		this.effective = effective;
	}
	

	public String getEffectiveRemark() {
		return effectiveRemark;
	}

	public void setEffectiveRemark(String effectiveRemark) {
		this.effectiveRemark = effectiveRemark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SocialBenefitDetail> getSocialBenefitDetails() {
		return socialBenefitDetails;
	}

	public void setSocialBenefitDetails(
			List<SocialBenefitDetail> socialBenefitDetails) {
		this.socialBenefitDetails = socialBenefitDetails;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public BigDecimal getPersonMonthPercent() {
		return personMonthPercent;
	}

	public void setPersonMonthPercent(BigDecimal personMonthPercent) {
		this.personMonthPercent = personMonthPercent;
	}

	public BigDecimal getPersonMonthAmount() {
		return personMonthAmount;
	}

	public void setPersonMonthAmount(BigDecimal personMonthAmount) {
		this.personMonthAmount = personMonthAmount;
	}

	public BigDecimal getCompanyMonthPercent() {
		return companyMonthPercent;
	}

	public void setCompanyMonthPercent(BigDecimal companyMonthPercent) {
		this.companyMonthPercent = companyMonthPercent;
	}

	public BigDecimal getCompanyMonthAmount() {
		return companyMonthAmount;
	}

	public void setCompanyMonthAmount(BigDecimal companyMonthAmount) {
		this.companyMonthAmount = companyMonthAmount;
	}

	public BigDecimal getPersonOtherPercent() {
		return personOtherPercent;
	}

	public void setPersonOtherPercent(BigDecimal personOtherPercent) {
		this.personOtherPercent = personOtherPercent;
	}

	public BigDecimal getPersonOtherAmount() {
		return personOtherAmount;
	}

	public void setPersonOtherAmount(BigDecimal personOtherAmount) {
		this.personOtherAmount = personOtherAmount;
	}

	public BigDecimal getCompanyOtherPercent() {
		return companyOtherPercent;
	}

	public void setCompanyOtherPercent(BigDecimal companyOtherPercent) {
		this.companyOtherPercent = companyOtherPercent;
	}

	public BigDecimal getCompanyOtherAmount() {
		return companyOtherAmount;
	}

	public void setCompanyOtherAmount(BigDecimal companyOtherAmount) {
		this.companyOtherAmount = companyOtherAmount;
	}

	public SocialBenefitEffective getSbe() {
		return sbe;
	}

	public void setSbe(SocialBenefitEffective sbe) {
		this.effective = XMLUtil.toXML(sbe);
		this.sbe = sbe;
	}
	public String getAmString() {
		return amString;
	}

	public void setAmString(String amString) {
		this.amString = amString;
	}

	public String getEffectiveStart() {
		return effectiveStart;
	}

	public void setEffectiveStart(String effectiveStart) {
		this.effectiveStart = effectiveStart;
	}

	public String getEffectiveEnd() {
		return effectiveEnd;
	}

	public void setEffectiveEnd(String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMonthEffctive() {
		return monthEffctive;
	}

	public void setMonthEffctive(Integer monthEffctive) {
		this.monthEffctive = monthEffctive;
	}

	public String getSsbFile() {
		return ssbFile;
	}

	public void setSsbFile(String ssbFile) {
		this.ssbFile = ssbFile;
	}

	public Integer getQuitSsbStatus() {
		return quitSsbStatus;
	}

	public void setQuitSsbStatus(Integer quitSsbStatus) {
		this.quitSsbStatus = quitSsbStatus;
	}

	public String getQuitSsbStatusStr() {
		if(quitSsbStatus != null && quitSsbStatus.equals(1))
		{
			return "是";
		}else{
			return "否";
		}
	}

	public void setQuitSsbStatusStr(String quitSsbStatusStr) {
		this.quitSsbStatusStr = quitSsbStatusStr;
	}
	
	
	
	

}