package com.manpowergroup.cn.icloud.socialbenefit.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.icloud.socialbenefit.model.CandSsbModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandidateModel;

public class SsbAjust implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer candidateId;

    private Integer ajustmentStatus;

    private String monthFee;

    private Integer ssbId;

    private String ssbName;

    private String candidateName;

    private Integer ruleId;

    private String candidateNo;

    private Integer vendorId;

    private Integer createBy;

    private Date createDate;

    private Integer status;

    private Integer modifyBy;

    private Date modifyDate;

    private String remark;
    
    private Integer companyUndertake;
    
    private List<SsbAjustDetail> ssbDetailList;
    
    private String monthAttribute;
    
    private Integer type;
    
    
    
    
    //自定义字段
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private CandidateModel candidate;
    
    public CandidateModel getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateModel candidate) {
		this.candidate = candidate;
	}

	private List<SsbAjustDetail> candAjustmentssbDetails;// 调整详细内容
    
	public List<SsbAjustDetail> getCandAjustmentssbDetails() {
		return candAjustmentssbDetails;
	}

	public void setCandAjustmentssbDetails(
			List<SsbAjustDetail> candAjustmentssbDetails) {
		this.candAjustmentssbDetails = candAjustmentssbDetails;
	}

	private List<CandSsbModel> candSsbList = new ArrayList<CandSsbModel>();

	public List<CandSsbModel> getCandSsbList() {
		return candSsbList;
	}

	public void setCandSsbList(List<CandSsbModel> candSsbList) {
		this.candSsbList = candSsbList;
	}
    
    private Map<Integer, String> ssbChargeMap;
	
	public Map<Integer, String> getSsbChargeMap() {
		return ssbChargeMap;
	}

	public void setSsbChargeMap(Map<Integer, String> ssbChargeMap) {
		this.ssbChargeMap = ssbChargeMap;
	}

	public Integer getCompanyUndertake() {
		return companyUndertake;
	}

	public void setCompanyUndertake(Integer companyUndertake) {
		this.companyUndertake = companyUndertake;
	}

	private BigDecimal personAmount;// 供页面 显示用 个人总金额

	private BigDecimal companyAmount;// 供页面显示用 公司总金额
	
	

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

    public List<SsbAjustDetail> getSsbDetailList() {
        return ssbDetailList;
    }

    public void setSsbDetailList(List<SsbAjustDetail> ssbDetailList) {
        this.ssbDetailList = ssbDetailList;
    }

    public String getMonthAttribute() {
        return monthAttribute;
    }

    public void setMonthAttribute(String monthAttribute) {
        this.monthAttribute = monthAttribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getAjustmentStatus() {
        return ajustmentStatus;
    }

    public void setAjustmentStatus(Integer ajustmentStatus) {
        this.ajustmentStatus = ajustmentStatus;
    }

    public String getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(String monthFee) {
        this.monthFee = monthFee == null ? null : monthFee.trim();
    }

    public Integer getSsbId() {
        return ssbId;
    }

    public void setSsbId(Integer ssbId) {
        this.ssbId = ssbId;
    }

    public String getSsbName() {
        return ssbName;
    }

    public void setSsbName(String ssbName) {
        this.ssbName = ssbName == null ? null : ssbName.trim();
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName == null ? null : candidateName.trim();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getCandidateNo() {
        return candidateNo;
    }

    public void setCandidateNo(String candidateNo) {
        this.candidateNo = candidateNo == null ? null : candidateNo.trim();
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
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