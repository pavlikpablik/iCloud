package com.manpowergroup.cn.icloud.socialbenefitBase.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitDetail;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitHeader;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.BaseCode;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.Item;

/**
 * 
 * @author sundy.sun
 *
 */
@MyBatisRepositorySqlserver
public interface SocialBenefitHeaderMapper {

	/**
	 * 获取社保list集合
	 * @return
	 */
	List<SocialBenefitHeader> list();

	/**
	 * 通过社保id获取detail信息
	 * @param headerid
	 * @return
	 */
	List<SocialBenefitDetail> findDetailListByHeaderID(Integer headerid);

	/**
	 * 通过类型id获取item集合
	 * @param id
	 * @return
	 */
	List<Item> getItemListByType(Integer id);

	/**
	 * 分页查询社保基础信息
	 * @param parameters
	 * @param rowBounds
	 * @return
	 */
	List<SocialBenefitHeader> query(Map<String, Object> parameters,RowBounds rowBounds);

	/**
	 * 查询页数
	 * @param parameters
	 * @return
	 */
	long count(Map<String, Object> parameters);

	/**
	 * 通过社保id获取社保基础信息
	 * @param id
	 * @return
	 */
	SocialBenefitHeader getSocialBenefitHeader(Integer id);

	/**
	 * 保存社保基础信息
	 * @param socialBenefit
	 * @return
	 */
	SocialBenefitHeader saveSocialBenefit(SocialBenefitHeader socialBenefit);

	/**
	 * 保存社保detail信息
	 * @param dbSocialBenefitDetail
	 */
	void saveSocialBenefitDetail(SocialBenefitDetail dbSocialBenefitDetail);

	/**
	 * 获取默认Item集合
	 * @return
	 */
	List<Item> getItemListDefault();

	/**
	 * 获取户籍baseCode集合
	 * @param socialbenefitResidency
	 * @return
	 */
	List<BaseCode> getBaseCodeListByType(String socialbenefitResidency);

	/**
	 * 保险是否已存在
	 * @param sbname
	 * @param insurancename
	 * @return
	 */
	boolean isExistInsurance(Map<String, Object> parameters);

	/**
	 * 通过社保编号、名称，险种编号、名称 验证该险种是否已存在
	 * @param headid
	 * @param headname
	 * @param detailid
	 * @param itemid
	 * @param detailname
	 * @return
	 */
	boolean isExistChild(Map<String, Object> parameters);

	/**
	 * 获取所有的ITEM集合
	 * @return
	 */
	List<Item> getAllItemListOfSocialBenefit();

	/**
	 * 验证社保类型是否重复 and 支付月是否为空
	 * @param socialBenefitName
	 * @param socialBenefitId
	 * @return
	 */
	Long isExsitSocialBenefit(Map<String, Object> parameters);

	/**
	 * 获取社保单个险种信息
	 * @param headid
	 * @param detailid
	 * @return
	 */
	Item quaryItemByHeadIdAndDetailId(Map<String, Object> parameters);

	/**
	 * 获取社保基础信息
	 * @param headId
	 * @param headName
	 * @return
	 */
	SocialBenefitHeader querySocialBenefitByNameOrID(Map<String, Object> parameters);

	/**
	 * 获取basecode集合
	 * @param stringIntegerListConvert
	 * @return
	 */
	List<BaseCode> findByIds(List<Integer> list);

	/**
	 * 获取基础数据basecode
	 * @param string
	 * @param startType
	 * @param b
	 * @return
	 */
	BaseCode findUniqueBy(Integer id);

	/**
	 * 获取险种信息
	 * @param string
	 * @param itemId
	 * @param b
	 * @return
	 */
	Item findItemUniqueBy(Integer id);

	/**
	 * 获取basecode信息并封装为map
	 * @param socialbenefitTermmonth
	 * @return
	 */
	Map<String, String> getBaseCodesByType(String socialbenefitTermmonth);

	/**TODO:方法已被重写]
	 * 获取basecode基础数据信息
	 * @param string
	 * @param termname
	 * @param b
	 * @return
	 *//*
	BaseCode findUniqueBy(Map<String, Object> parameters);*/

	/**
	 * 获取basecode集合
	 * @param stringIntegerListConvert
	 * @return
	 */
	List<BaseCode> findByIds(Map<String, Object> parameters);

	/**
	 * 获取所有社保信息
	 * @return
	 */
	List<SocialBenefitHeader> findAllSocialBenefitHeader();

	/**TODO:  update hibernate ceritor
	 * 待审批 如果不是待审批状态，不能审批
	 * @param string
	 * @param id
	 * @return
	 *//*
	SocialBenefitHeader findUniqueBy(Map<String, Object> parameters);*/

	/**
	 * 同过城市获取社保集合
	 * @param cityId
	 * @return
	 */
	List<SocialBenefitHeader> querySocialBenefitListByCity(Integer cityId);

	/**
	 * 通过itmeid获取险种信息
	 * @param itemId
	 * @return
	 */
	Item getItemById(Integer itemId);

	BaseCode findUniqueByBDName(String termname);

	/**
	 * 更新社保基础数据状态
	 * @param parameters
	 */
	void changeStatus(Map<String, Object> parameters);

	/**
	 * 新增社保基础信息
	 * @param socialBenefit
	 * @return
	 */
	void insertSocialBenefit(SocialBenefitHeader socialBenefit);
	
	/**
	 * 更新社保基础信息
	 * @param dbSocialBenefitHeader
	 */
	void updateSocialBenefit(SocialBenefitHeader dbSocialBenefitHeader);

	/**
	 * 批量新增detail信息
	 * @param dbSocialBenefitDetail
	 */
	void insertSocialBenefitDetailList(List<SocialBenefitDetail> list);

	/**
	 * 批量更新detail信息
	 * @param list
	 */
	void updateSocialBenefitDetailList(List<SocialBenefitDetail> list);

	/**
	 * 批量删除detail信息
	 * @param list
	 */
	void deleteSocialBenefitDetailList(List<Integer> list);

	/**
	 * 批量更新申报状态
	 * @param parameters
	 */
	void updateBatchHeader(Map<String, Object> parameters);

	
}
