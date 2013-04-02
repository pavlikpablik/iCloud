package com.manpowergroup.cn.icloud.socialbenefit.model;

public class ClientModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4068458287351753100L;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	private String clientId;
	
	private String clientName;
}
