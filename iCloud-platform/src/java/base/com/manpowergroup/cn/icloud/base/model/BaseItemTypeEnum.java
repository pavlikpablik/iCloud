package com.manpowergroup.cn.icloud.base.model;

import java.util.EnumSet;

public enum BaseItemTypeEnum {
	SALARY(1, "社保"),
	SOCIAL_SECURITY(2, "服务费"), 
	SERVICE_FEE(3, "工资"), 
	BUSINESS_SECURITY(3, "商保"), 
	OTHERS(4, "其他");

	private final int value;
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static BaseItemTypeEnum getEnumByValue(int value) {
		EnumSet<BaseItemTypeEnum> stateSet = EnumSet
				.allOf(BaseItemTypeEnum.class);
		for (BaseItemTypeEnum s : stateSet) {
			if (s.getValue() == value)
				return s;
		}
		return null;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	BaseItemTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

}
