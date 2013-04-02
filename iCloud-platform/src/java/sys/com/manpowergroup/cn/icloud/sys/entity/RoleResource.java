package com.manpowergroup.cn.icloud.sys.entity;

public class RoleResource implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Long roleId;
	private Long resourceId;
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
