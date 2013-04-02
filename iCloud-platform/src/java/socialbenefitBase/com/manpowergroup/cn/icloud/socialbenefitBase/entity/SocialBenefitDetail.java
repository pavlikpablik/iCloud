package com.manpowergroup.cn.icloud.socialbenefitBase.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.icloud.ssb.model.SocialBenefitEffective;


public class SocialBenefitDetail implements java.io.Serializable 
{
	private static final long serialVersionUID = 3170134568228565731L;
	
	
	/**
	 * 状态 启用
	 */
	public static final Integer STATUS_START = 1;
	/**
	 * 状态 停用
	 */
	public static final Integer STATUS_STOP = 0;
	/**
	 * 状态 删除
	 */
	public static final Integer STATUS_DELETE = -1;
	
	
	private Integer id;
	
	private Integer itemId; //险种类型
	
	private Integer headerId;
	
	private String itemName;//险种名称 （临时属性，不保存到数据库）
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	private SocialBenefitHeader socialBenefitHeader;//所属社保类型
	
	private BigDecimal companyPerency; //公司比例
	
	private BigDecimal personPerency; //个人比例
	
	private BigDecimal companyCap; //公司上限  （不用。跟个人上限保持一致）
	
	private BigDecimal companyFloor; //公司下限  （不用。跟个人下限保持一致）
	
	private BigDecimal personCap; //个人上限
	 
	private BigDecimal personFloor; //个人下限
	
	private BigDecimal companyFixAmount; //公司固定金
	
	private BigDecimal personFixAmount; //个人固定金
	
//	private String residency; //户籍
	
	private Integer ajustTime; //每天调整时间
	
	private Integer attributeMonth; //社保所属月
	
	private Integer monthEffctive;//社保生效月
	
	private Integer addLimitTime; //加保截止日期
	
	private Integer ebbLimitTime; //退保截止日期
	
	private String termMonth; //支付月
	
	private String effective; //开始或结束规则
	
	private String effectiveRemark; //开始或结束规则备注
	
	private Integer status;
	
	private Integer createBy;
	
	private Date createDate;
	
	private Integer modifyBy;
	
	private Date modifyDate;
	
	private String description;
	
	private String remark;
	
	
	/*****************页面字段 不保存到数据库******/
	private String amString;//社保起始月
	private String meString;// 社保起缴月
	private BigDecimal companyPayOffCapAmount;
	private BigDecimal companyPayOffFloorAmount;
	private BigDecimal personPayOffCapAmount;
	private BigDecimal personPayOffFloorAmount;
	
	private Integer quitSsbStatus;//退保后是否需要费用的状态  0:无需费用  1:需要费用
	
	
	/**
	 * 公司部分缴存额上限
	 * 单位缴存额上限（=单位缴费基数上限*单位缴费比例+固定金）
	 * @return 
	 */
	public BigDecimal getCompanyPayOffCapAmount() {
		//this.companyPayOffCapAmount = this.getCompanyCap().multiply(this.getCompanyPerency()).add(this.getCompanyFixAmount());
		return companyPayOffCapAmount;
	}
	/**
	 * 公司缴存额下限
	 * 单位缴存额下限（=单位缴费基数下限*个人缴费比例+固定金）
	 * @return
	 */
	public BigDecimal getCompanyPayOffFloorAmount() {
		//this.companyPayOffFloorAmount = this.getCompanyFloor().multiply(this.getCompanyPerency()).add(this.getCompanyFixAmount());
		return companyPayOffFloorAmount;
	}
	/**
	 * 个人缴存额上限
	 * 个人缴存额上限（=个人缴费基数上限*个人缴费比例+固定金）
	 * @return
	 */
	public BigDecimal getPersonPayOffCapAmount() {
		//this.personPayOffCapAmount = this.getPersonCap().multiply(this.getPersonPerency()).add(this.getPersonFixAmount());
		return personPayOffCapAmount;
	}
	/**
	 * 个人缴存额下限
	 * 个人缴存额下限（=个人缴费基数下限*个人缴费比例+固定金）
	 * @return
	 */
	
	public BigDecimal getPersonPayOffFloorAmount() {
		//this.personPayOffFloorAmount = this.getPersonFloor().multiply(this.getPersonPerency()).add(this.getPersonFixAmount());
		return personPayOffFloorAmount;
	}

	public void setCompanyPayOffCapAmount(BigDecimal companyPayOffCapAmount) {
		this.companyPayOffCapAmount = companyPayOffCapAmount;
	}

	public void setCompanyPayOffFloorAmount(BigDecimal companyPayOffFloorAmount) {
		this.companyPayOffFloorAmount = companyPayOffFloorAmount;
	}

	public void setPersonPayOffCapAmount(BigDecimal personPayOffCapAmount) {
		this.personPayOffCapAmount = personPayOffCapAmount;
	}

	public void setPersonPayOffFloorAmount(BigDecimal personPayOffFloorAmount) {
		this.personPayOffFloorAmount = personPayOffFloorAmount;
	}

	public String getMeString() {
		return meString;
	}

	public void setMeString(String meString) {
		this.meString = meString;
	}

	private String everyMonth;//每月(true表示12个月全选中)
	public String getEveryMonth() {
		return everyMonth;
	}

	public void setEveryMonth(String everyMonth) {
		this.everyMonth = everyMonth;
	}

	private List<String> termMonths = new ArrayList<String>();//支付月
//	private List<String> residencys = new ArrayList<String>();//户籍
	
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

	/*public List<String> getResidencys() {
		List<String> resultList = new ArrayList<String>();
		if (this.residency == null || "".equals(this.residency)) {
			return resultList;
		} else {
			for (String s : this.residency.split(",")) {
				resultList.add(s.trim());
			}
		}
		residencys = resultList;
		return residencys;
	}

	public void setResidencys(List<String> residencys) {
		this.residencys = residencys;
	}*/
	/*******************************************/
	
	@Transient
	private SocialBenefitEffective sbe = new SocialBenefitEffective(); //开始或结束规则对象


	public SocialBenefitEffective getSbe() {
		return sbe;
	}

	public void setSbe(SocialBenefitEffective sbe) {
		this.effective = XMLUtil.toXML(sbe);
		this.sbe = sbe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public SocialBenefitHeader getSocialBenefitHeader() {
		return socialBenefitHeader;
	}

	public void setSocialBenefitHeader(SocialBenefitHeader socialBenefitHeader) {
		this.socialBenefitHeader = socialBenefitHeader;
	}

	public BigDecimal getCompanyPerency() {
		return companyPerency;
	}

	public void setCompanyPerency(BigDecimal companyPerency) {
		this.companyPerency = companyPerency;
	}

	public BigDecimal getPersonPerency() {
		return personPerency;
	}

	public void setPersonPerency(BigDecimal personPerency) {
		this.personPerency = personPerency;
	}

	public BigDecimal getCompanyCap() {
		return companyCap;
	}

	public void setCompanyCap(BigDecimal companyCap) {
		this.companyCap = companyCap;
	}

	public BigDecimal getCompanyFloor() {
		return companyFloor;
	}

	public void setCompanyFloor(BigDecimal companyFloor) {
		this.companyFloor = companyFloor;
	}

	public BigDecimal getPersonCap() {
		return personCap;
	}

	public void setPersonCap(BigDecimal personCap) {
		this.personCap = personCap;
	}

	public BigDecimal getPersonFloor() {
		return personFloor;
	}

	public void setPersonFloor(BigDecimal personFloor) {
		this.personFloor = personFloor;
	}

	public BigDecimal getCompanyFixAmount() {
		return companyFixAmount;
	}

	public void setCompanyFixAmount(BigDecimal companyFixAmount) {
		this.companyFixAmount = companyFixAmount;
	}

	public BigDecimal getPersonFixAmount() {
		return personFixAmount;
	}

	public void setPersonFixAmount(BigDecimal personFixAmount) {
		this.personFixAmount = personFixAmount;
	}

	/*public String getResidency() {
		return residency;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}*/

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getMonthEffctive() {
		return monthEffctive;
	}

	public void setMonthEffctive(Integer monthEffctive) {
		this.monthEffctive = monthEffctive;
	}

   public Integer getQuitSsbStatus()
   {
      return quitSsbStatus;
   }

   public void setQuitSsbStatus( Integer quitSsbStatus )
   {
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
   
   public void setQuitSsbStatusStr() {
      
   }
	
	public Integer getHeaderId() {
		return headerId;
	}
	
	public void setHeaderId(Integer headerId) {
		this.headerId = headerId;
	}
	   
   
   
	
}