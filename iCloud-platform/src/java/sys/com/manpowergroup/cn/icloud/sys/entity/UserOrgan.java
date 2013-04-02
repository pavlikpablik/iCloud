package com.manpowergroup.cn.icloud.sys.entity;

public class UserOrgan implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -931587597543152707L;

	private Long userId;
	
	private Long organId;
	
	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
