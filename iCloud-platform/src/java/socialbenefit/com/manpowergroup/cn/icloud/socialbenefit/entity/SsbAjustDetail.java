package com.manpowergroup.cn.icloud.socialbenefit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SsbAjustDetail implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -439708078364156765L;

	private Integer id;

    private Integer ajustmentHeaderId;

    private Integer itemId;

    private BigDecimal personAmount;
    
    private BigDecimal personPay;

    private BigDecimal companyAmount;

    private BigDecimal companyPay;
    
    private String monthAttribute;

    private String itemName;

    private Integer createBy;

    private Date createDate;

    private Integer status;

    private Integer modifyBy;

    private Date modifyDate;

    private String remark;
    
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPersonPay() {
        return personPay;
    }

    public void setPersonPay(BigDecimal personPay) {
        this.personPay = personPay;
    }

    public BigDecimal getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(BigDecimal companyPay) {
        this.companyPay = companyPay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAjustmentHeaderId() {
        return ajustmentHeaderId;
    }

    public void setAjustmentHeaderId(Integer ajustmentHeaderId) {
        this.ajustmentHeaderId = ajustmentHeaderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPersonAmount() {
        return personAmount;
    }

    public void setPersonAmount(BigDecimal personAmount) {
        this.personAmount = personAmount;
    }

    public BigDecimal getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(BigDecimal companyAmount) {
        this.companyAmount = companyAmount;
    }

    public String getMonthAttribute() {
        return monthAttribute;
    }

    public void setMonthAttribute(String monthAttribute) {
        this.monthAttribute = monthAttribute == null ? null : monthAttribute.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}