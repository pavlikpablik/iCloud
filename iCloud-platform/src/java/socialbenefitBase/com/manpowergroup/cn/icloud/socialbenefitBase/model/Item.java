package com.manpowergroup.cn.icloud.socialbenefitBase.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{

	private static final long serialVersionUID = 6982456446843616105L;
	private Integer id;
	private String name;
	private String nameEn;
	private String describe;
	private String remark;
	private Integer status;
	private Integer createBy;
	private Date createDate;
	private Integer modifyBy;
	private Date modifyDate;
	private Integer seq;
	//add by dianqing
	private Integer type;
	//add to Leo
	private Integer personTax; //个人是否计税 0 不计税 1 计税
	private Integer companyTax;//公司是否计税 0 不计税 1 计税
	
	private Integer isNegative;//是否是负数 1 负数(个人扣除)
	private Integer isCompanyNegative;//是否是负数 1 负数（公司扣除）
	
	private String itemGroup;
	private String inOrOut;// pay,bill
	private String taxable;//import,system
	private String serviceType;//type
	private String itemsType;
	
	private Integer isPersonAmount;//是否个人发放
	private Integer isCompanyAmount;//是否公司费用
	private Integer isPersonPay;//是否个人扣除
   private Integer isCompanyPay;//是否公司成本
   
   //add by sundy
   private Integer taxProvideBefore;//是否税前发放
   private Integer taxDeductAfter;//是否税后扣除
	
	
	public String getItemsType() {
		return itemsType;
	}

	public void setItemsType(String itemsType) {
		this.itemsType = itemsType;
	}

	public Integer getIsCompanyNegative() {
		return isCompanyNegative;
	}

	public void setIsCompanyNegative(Integer isCompanyNegative) {
		this.isCompanyNegative = isCompanyNegative;
	}

	public Integer getIsNegative() {
		return isNegative;
	}

	public void setIsNegative(Integer isNegative) {
		this.isNegative = isNegative;
	}

	private String itemType;
	
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getPersonTax() {
		return personTax;
	}

	public void setPersonTax(Integer personTax) {
		this.personTax = personTax;
	}

	public Integer getCompanyTax() {
		return companyTax;
	}

	public void setCompanyTax(Integer companyTax) {
		this.companyTax = companyTax;
	}


	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

   public String getServiceType()
   {
      return serviceType;
   }

   public void setServiceType( String serviceType )
   {
      this.serviceType = serviceType;
   }

   public Integer getIsPersonAmount()
   {
      return isPersonAmount;
   }

   public void setIsPersonAmount( Integer isPersonAmount )
   {
      this.isPersonAmount = isPersonAmount;
   }

   public Integer getIsCompanyAmount()
   {
      return isCompanyAmount;
   }

   public void setIsCompanyAmount( Integer isCompanyAmount )
   {
      this.isCompanyAmount = isCompanyAmount;
   }

   public Integer getIsPersonPay()
   {
      return isPersonPay;
   }

   public void setIsPersonPay( Integer isPersonPay )
   {
      this.isPersonPay = isPersonPay;
   }

   public Integer getIsCompanyPay()
   {
      return isCompanyPay;
   }

   public void setIsCompanyPay( Integer isCompanyPay )
   {
      this.isCompanyPay = isCompanyPay;
   }

	public Integer getTaxProvideBefore() {
		return taxProvideBefore;
	}
	
	public void setTaxProvideBefore(Integer taxProvideBefore) {
		this.taxProvideBefore = taxProvideBefore;
	}
	
	public Integer getTaxDeductAfter() {
		return taxDeductAfter;
	}
	
	public void setTaxDeductAfter(Integer taxDeductAfter) {
		this.taxDeductAfter = taxDeductAfter;
	}

	
	
	
	

}
