package com.manpowergroup.cn.icloud.socialbenefit.model;


public class CandSsbModel implements java.io.Serializable{

	/**员工社保信息
	 * bob.chen
	 */
	private static final long serialVersionUID = -6434957282509030372L;

	public static final Integer ITEM_VENDOR_SERVERFEE = 178; // 社保供应商管理费
	
	public static final Integer ITEM_EMPLOYMENT_SECURITY = 64;//残疾人保障金 
	
	private Integer chargeRuleId;
	
	private String name;
	
	private Integer id;
	
	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public Integer getSsbId() {
		return ssbId;
	}

	public void setSsbId(Integer ssbId) {
		this.ssbId = ssbId;
	}

	private Integer candidateId;
	
	private Integer ssbId;

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

	public Integer getChargeRuleId() {
		return chargeRuleId;
	}

	public void setChargeRuleId(Integer chargeRuleId) {
		this.chargeRuleId = chargeRuleId;
	}
	
	

}
