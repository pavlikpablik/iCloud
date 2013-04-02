package com.manpowergroup.cn.icloud.sys.entity;

public class UserResource implements java.io.Serializable {

	private static final long serialVersionUID = 737666072763616581L;

	private Long userId;
	private Long resourceId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
