package com.manpowergroup.cn.icloud.socialbenefitBase.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitDetail;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitHeader;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.BaseCode;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.Item;


public interface SocialBenefitHeaderService 
{
	List< SocialBenefitHeader > list();

	/**
	 * 通过传入的Header的某个属性或某几个属性查询Header对象（不要用模糊查询）
	 * @param header
	 * @return
	 */
	SocialBenefitHeader findSocialBenefitHeaderByCondition(SocialBenefitHeader header);
	/**
	 * 根据headerid查询所属的险种集合
	 * @param headerid
	 * @return
	 */
	List<SocialBenefitDetail> findDetailListByHeaderID(Integer headerid);
	
	/**
	 * 通过传入的detail的某个属性或某几个属性查询Detail对象（不要用模糊查询）
	 * @param detail
	 * @return
	 */
	SocialBenefitDetail findSocialBenefitDetailByCondition(SocialBenefitDetail detail);

	/**
	 * 获取省list列表
	 * @return
	 */
	List<City> provinceList();
	
	/**
	 * 通过省编号获取他下面的所有城市
	 * @param pervinceId
	 * @return
	 */
	List<City> cityList(Integer pervinceId);
	
	/**
	 * to Sundy ，type是什么字符，你自己定，到时候告诉我。
	 * 根据类型查询Item (包括户籍，一月到十二月，开始结束规则等)
	 * @param type
	 * @return List
	 */
	List<Item> findItemsByType(Integer id);
	
	/**
	 * 新建社保时默认的item列表 add by sundy
	 * @return
	 */
	List<Item> findItemDefault();
	
	List<Item> findAllItemOfSocialBenfit();
	
	/**
	 * 获取户籍列表 
	 * @return
	 */
	List<BaseCode> getResidencyList();
	/**
	 * 获取开始或结束规则信息
	 * @return
	 */
	List<BaseCode> getEffecitveList();

	/**
	 * 根据ID查询SocialBenefitHeader
	 * @param id
	 * @return SocialBenefitHeader
	 */
	SocialBenefitHeader querySocialBenefitById(Integer id);

	/**
	 * 保存社保类型，包括detail
	 * @param socialBenefit
	 */
	Result save(SocialBenefitHeader socialBenefit, Integer staffId);
	
	/**
	 * 根据社保类型名称和险种名称检查险种在此社保类型中是否已存在
	 * @param sbname
	 * @param insurancename
	 * @return
	 */
	boolean isExsitInsurance(String sbname,String insurancename);
	
	/**
	 * /**
	 * 根据查询页面的条件查询（条件：城市、户籍、名称）
	 * @param page
	 * @param cityId 城市
	 * @param provinceId 
	 * @param name 名称
	 * @param residency 户籍, 逗号分隔id字符串
	 * @return
	 */
	Page<SocialBenefitHeader> querySsbByCondition(Map<String, Object> parameters,Page<SocialBenefitHeader> page);
	/**
	 * 根据传入的参数查询社保类型和险种是否重复
	 * @param headid 社保类型编号
	 * @param headname 社保类型名称
	 * @param detailid 险种编号
	 * @param itemid 险种所属项目元素编号
	 * @param detailname 险种名称
	 * @return
	 * @throws Exception
	 */
	boolean validateSBDetail(Integer headid,String headname,Integer detailid,Integer itemid,String detailname) throws Exception;
	/**
	 * 根据社保类型编号和险种类型 查询对应的Item的对象 
	 * @param headid 社保类型ID
	 * @param detailid 险种编号
	 * @return
	 */
	Item quaryItemByHeadIdAndDetailId(Integer headid,Integer detailid);
	/**
	 * 根据社保类型ID或名称，查询社保类型对象
	 * @param headid 社保类型ID
	 * @param headname 社保类型名称
	 * @return
	 */
	SocialBenefitHeader querySocialBenefitByNameOrID(Integer headid,String headname);

	/**
	 * 查询详细页面所需数据
	 * @param id
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-11-25
	 */
	SocialBenefitHeader querySocialBenefitFullInfoById(Integer id);

	/**
	 * 社保所属于月
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-11-25
	 */
	List<BaseCode> getAttributeMonthList();
	
	void changeStatus(Integer id,Integer status);

	Map<String,String> getTermMonth();

	List<BaseCode> getMonthEffctiveList();
	
	List<SocialBenefitHeader> findAllSocialBenefitHeader();

	/**
	 * 批量审批
	 * @param ssbIds
	 * @author jiangpeng.sun
	 * @throws Exception 
	 * @date 2012-1-16
	 */
	void batchChangeStatus(String ssbIds) throws Exception;

	/**
	 * 通过城市查找社保
	 * @param cityId
	 * @return
	 * @author jiangpeng.sun
	 * @date 2012-1-19
	 */
	List<SocialBenefitHeader> querySocialBenefitListByCity(Integer cityId);
	
	public List<Item> findItemListByHeaderID(Integer id);
}
