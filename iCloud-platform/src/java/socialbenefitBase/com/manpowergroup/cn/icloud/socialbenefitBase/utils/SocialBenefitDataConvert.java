package com.manpowergroup.cn.icloud.socialbenefitBase.utils;

/**
 * 社保基础模块模块的常量
 * @author jiangpeng.sun
 */
public class SocialBenefitDataConvert {
	
	public static final Integer SOCIALBENEFIT_DEFAULT = 1;//默认社保类型
	public static final Integer SOCIALBENEFIT_ALL = 2; //获取所有的SocialBenefit的险种
	public static final Integer CAND_SALARY_ITEM_ALL = 4; //获取所有的工资类型
	public static final Integer CAND_COINSUR_ITEM_ALL = 5; //获取所有的商保类型
	public static final Integer ADJUST_ITEM_ALL=7;//所有的调整项目
	public static final Integer CHARGE_ROLE_TYPE = 8; //获取所有的服务费项目
	public static final Integer CHARGE_OTHERITEM_TYPE = 9; //获取收费规则中其他费用	
	public static final Integer CHARGEONCE_SUCCESS = 0;
	public static final Integer CHARGEONCE_FAIL_BEGINENDTIME = 1;
	public static final Integer CHARGEONCE_FAIL_BEGINTIME = 2;
	public static final Integer CHARGEONCE_FAIL_ENDTIME = 3;
	public static final String SOCIALBENEFIT_RESIDENCY = "SSB_SB_户籍";//通过社保模块中户籍获取相应的list
	public static final String CANDIDATE_RESIDENCY = "员工户籍性质";//通过社保模块中户籍获取相应的list
	public static final String SOCIALBENEFIT_EFFCTIVE = "SSB_SB_规则";//通过社保模块中开始后结束规则
	public static final String SOCIALBENEFIT_MONTH_ATT = "SSB_SB_社保所属月";//通过社保类型中社保所属月获取相应的Map
	public static final String SOCIALBENEFIT_MONTH_ITERM = "SSB_SB_支付月";//通过社保类型中支付月获取相应的Map
	public static final String SOCIALBENEFIT_ARRRIBUTEMONTH = "SSB_SB_社保所属月";//社保所属月
	public static final String SOCIALBENEFIT_TERMMONTH = "termMonth";//社保支付月
	public static final String SOCIALBENEFIT_MONTHEFFECTIVE = "monthEffctive";//社保起缴月
	
	
	public static final String MENU_AUTHORITY_TYPE ="AuthorityType";//菜单模块中的类别
	
	public static final String MENU_ICON_CLASS = "IconClass";
	public static final Integer STATUS_DEL = -1; //已删除状态
	public static final Integer STATUS_STOP = 0; //停用状态
	public static final Integer STATUS_ABILITY = 1; //有效的或已审批状态
	public static final Integer STATUS_STAY = 2; //待审批状态
	
	
}
