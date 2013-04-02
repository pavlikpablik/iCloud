package com.manpowergroup.cn.icloud.sys.entity;

public class UserRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1750037783085104919L;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	private Long userId;
	
	private Long roleId;
}
