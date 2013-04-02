package com.manpowergroup.cn.icloud.socialbenefit.model;

public class ChargeModel implements java.io.Serializable{

	/**收费规则基本信息
	 * bob.chen
	 */
	private static final long serialVersionUID = 6180888590639868053L;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Integer id;
	
	private Integer clientId;
	
	private String name;
	
}
