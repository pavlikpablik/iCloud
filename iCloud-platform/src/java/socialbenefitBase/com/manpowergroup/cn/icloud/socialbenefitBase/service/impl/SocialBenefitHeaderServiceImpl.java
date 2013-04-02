package com.manpowergroup.cn.icloud.socialbenefitBase.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.base.mapper.CityMapper;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitDetail;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitHeader;
import com.manpowergroup.cn.icloud.socialbenefitBase.mapper.SocialBenefitHeaderMapper;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.BaseCode;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.Item;
import com.manpowergroup.cn.icloud.socialbenefitBase.service.SocialBenefitHeaderService;
import com.manpowergroup.cn.icloud.socialbenefitBase.utils.SocialBenefitDataConvert;

@Service
@Transactional(rollbackFor=Exception.class)
@Qualifier(value="sqlserver")
public class SocialBenefitHeaderServiceImpl implements SocialBenefitHeaderService
{
	
	@Autowired
	private SocialBenefitHeaderMapper socialBenefitHeaderMapper;
	
	@Autowired
	private CityMapper cityMapper;
	
	public List< SocialBenefitHeader > list()
	{
		return socialBenefitHeaderMapper.list();
	}

	public List<City> provinceList()
	{
		return cityMapper.getProvinceList();
	}

	public List<City> cityList(Integer pervinceId) 
	{
		return cityMapper.getCityList(pervinceId);
	}

	public SocialBenefitHeader findSocialBenefitHeaderByCondition(
			SocialBenefitHeader header) {
		
		return null;
	}

	public List<SocialBenefitDetail> findDetailListByHeaderID(Integer headerid) {
		
		return socialBenefitHeaderMapper.findDetailListByHeaderID(headerid);
	}

	public SocialBenefitDetail findSocialBenefitDetailByCondition(
			SocialBenefitDetail detail) {
		
		return null;
	}

	public List<Item> findItemsByType(Integer id) {
		if(id == null) return null;
		return socialBenefitHeaderMapper.getItemListByType(id);
	}

	public Page<SocialBenefitHeader> querySsbByCondition(Map<String, Object> parameters,Page<SocialBenefitHeader> page)
	{
		List<SocialBenefitHeader> Users = socialBenefitHeaderMapper.query(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(socialBenefitHeaderMapper.count(parameters));
		page.setResult(Users);
		return page;
	}



	public SocialBenefitHeader querySocialBenefitById(Integer id) {
		SocialBenefitHeader dbSocialBenefitHeader = null;
		dbSocialBenefitHeader =  socialBenefitHeaderMapper.getSocialBenefitHeader(id);
		City dbCity = cityMapper.get(dbSocialBenefitHeader.getCityId().longValue());
		
		dbSocialBenefitHeader.setProvinceId((cityMapper.selectProvinceByCityId(dbCity.getParentId().longValue()).getId().intValue()));
		return dbSocialBenefitHeader;
	}

	public Result save(SocialBenefitHeader socialBenefit , Integer staffId) {
		Result result = new Result();
		if(socialBenefit == null) 
		{
			result.setResult(false);
			result.setResultText("socialBenefit 不存在，无法保存");
			return result;
		}
		//验证socialbenefit是否重复
		boolean flag = false;
		flag = validateSocialBenefit(socialBenefit,result);
		if(!flag)return result;
		//保存或更新socialbenefit
		SocialBenefitHeader dbSocialBenefitHeader = null;
		if(socialBenefit.getId()==null)
		{
			socialBenefit.setStatus(SocialBenefitDataConvert.STATUS_STAY);
			socialBenefit.setCreateBy(staffId);
			socialBenefit.setCreateDate(new Date());
			socialBenefit.setModifyBy(staffId);
			socialBenefit.setModifyDate(new Date());
			socialBenefitHeaderMapper.insertSocialBenefit(socialBenefit);
			
		}else{
			socialBenefit.setModifyBy(staffId);
			socialBenefit.setModifyDate(new Date());
			//修改后为待审批状态
			socialBenefit.setStatus(SocialBenefitDataConvert.STATUS_STAY);
			//文件上传
			//dbSocialBenefitHeader.setSsbFile(socialBenefit.getSsbFile());
			socialBenefitHeaderMapper.updateSocialBenefit(socialBenefit);
			dbSocialBenefitHeader = socialBenefitHeaderMapper.getSocialBenefitHeader(socialBenefit.getId());
		}
		List<Integer> delIdsList = new ArrayList<Integer>();
		List<Integer> dbIdsList = new ArrayList<Integer>();
		List<SocialBenefitDetail> newSocialBenefitDetailList = new ArrayList<SocialBenefitDetail>();
		List<SocialBenefitDetail> dbSocialBenefitDetailList = new ArrayList<SocialBenefitDetail>();
		for( int i = 0 ; socialBenefit != null && socialBenefit.getSocialBenefitDetails() != null && i < socialBenefit.getSocialBenefitDetails().size(); i++)
		{
			SocialBenefitDetail pageSocialBenefitDetail = socialBenefit.getSocialBenefitDetails().get(i);
			pageSocialBenefitDetail.setHeaderId(socialBenefit.getId());
			if(pageSocialBenefitDetail.getId() != null)
			{
				dbIdsList.add(pageSocialBenefitDetail.getId());
				pageSocialBenefitDetail.setModifyBy(staffId);
				pageSocialBenefitDetail.setModifyDate(new Date());
				pageSocialBenefitDetail.setStatus(pageSocialBenefitDetail.getStatus()==null?SocialBenefitDataConvert.STATUS_ABILITY:pageSocialBenefitDetail.getStatus());
				dbSocialBenefitDetailList.add(pageSocialBenefitDetail);
			}else{
				pageSocialBenefitDetail.setCreateBy(staffId);
				pageSocialBenefitDetail.setCreateDate(new Date());
				pageSocialBenefitDetail.setModifyBy(staffId);
				pageSocialBenefitDetail.setModifyDate(new Date());
				pageSocialBenefitDetail.setStatus(SocialBenefitDataConvert.STATUS_ABILITY);
				newSocialBenefitDetailList.add(pageSocialBenefitDetail);
			}
		}
		for( int i = 0 ; dbSocialBenefitHeader != null && 
							dbSocialBenefitHeader.getSocialBenefitDetails() != null && i< dbSocialBenefitHeader.getSocialBenefitDetails().size(); i++)
		{
			SocialBenefitDetail dbSocialBenefitDetail = dbSocialBenefitHeader.getSocialBenefitDetails().get(i);
			if(!dbIdsList.contains(dbSocialBenefitDetail.getId()))delIdsList.add(dbSocialBenefitDetail.getId()); 
		}
		if(dbSocialBenefitDetailList != null && dbSocialBenefitDetailList.size() > 0)socialBenefitHeaderMapper.updateSocialBenefitDetailList(dbSocialBenefitDetailList);
		if(newSocialBenefitDetailList != null && newSocialBenefitDetailList.size() > 0)socialBenefitHeaderMapper.insertSocialBenefitDetailList(newSocialBenefitDetailList);
		if(delIdsList != null && delIdsList.size() > 0)socialBenefitHeaderMapper.deleteSocialBenefitDetailList(delIdsList);
		result.setResult(true);
		result.setResultText("该社保类型保存成功");
		return result;
	}

	private Result save(SocialBenefitHeader socialBenefit , Integer staff,Map<String,Boolean> columnexist) {
		Result result = new Result();
		if(socialBenefit == null) 
		{
			result.setResult(false);
			result.setResultText("socialBenefit 不存在，无法保存");
			return result;
		}
		//验证socialbenefit是否重复
		boolean flag = false;
		flag = validateSocialBenefit(socialBenefit,result);
		if(!flag)return result;
		//保存或更新socialbenefit
		SocialBenefitHeader dbSocialBenefitHeader = null;
		if(socialBenefit.getId()==null)
		{
			socialBenefit.setStatus(2);
			socialBenefit.setCreateBy(staff);
			socialBenefit.setCreateDate(new Date());
			socialBenefit.setModifyBy(staff);
			socialBenefit.setModifyDate(new Date());
			dbSocialBenefitHeader = socialBenefitHeaderMapper.saveSocialBenefit(socialBenefit);
		}else{
			dbSocialBenefitHeader = socialBenefitHeaderMapper.getSocialBenefitHeader(socialBenefit.getId());
			if(socialBenefit.getId() != null && dbSocialBenefitHeader == null){
				result.setResultText("要更新的社保类型对象不存在");
				return result;
			}
			dbSocialBenefitHeader.setName(socialBenefit.getName());
			dbSocialBenefitHeader.setCityId(socialBenefit.getCityId());
			dbSocialBenefitHeader.setResidency(socialBenefit.getResidency());
			dbSocialBenefitHeader.setTermMonth(socialBenefit.getTermMonth());
			dbSocialBenefitHeader.setAttributeMonth(socialBenefit.getAttributeMonth());
			dbSocialBenefitHeader.setMonthEffctive(socialBenefit.getMonthEffctive());
			dbSocialBenefitHeader.setAjustTime(socialBenefit.getAjustTime());
			dbSocialBenefitHeader.setAddLimitTime(socialBenefit.getAddLimitTime());
			dbSocialBenefitHeader.setEbbLimitTime(socialBenefit.getEbbLimitTime());
			dbSocialBenefitHeader.setEffective(socialBenefit.getEffective());
			dbSocialBenefitHeader.setDescription(socialBenefit.getDescription());
			dbSocialBenefitHeader.setEffectiveRemark(socialBenefit.getEffectiveRemark());
			dbSocialBenefitHeader.setModifyBy(staff);
			dbSocialBenefitHeader.setModifyDate(new Date());
			dbSocialBenefitHeader.setStatus(2);
		}
		if(dbSocialBenefitHeader.getId()==null)return null;
		for( int i = 0 ; socialBenefit != null && socialBenefit.getSocialBenefitDetails() != null && i < socialBenefit.getSocialBenefitDetails().size(); i++)
		{
			SocialBenefitDetail pageSocialBenefitDetail = socialBenefit.getSocialBenefitDetails().get(i);
			if(pageSocialBenefitDetail!=null && pageSocialBenefitDetail.getId() != null)
			{
				for(int j = 0 ; dbSocialBenefitHeader != null && dbSocialBenefitHeader.getSocialBenefitDetails() != null && j < dbSocialBenefitHeader.getSocialBenefitDetails().size(); j++)
				{
					SocialBenefitDetail dbSocialBenefitDetail = dbSocialBenefitHeader.getSocialBenefitDetails().get(j);
					if(dbSocialBenefitDetail.getId().equals(pageSocialBenefitDetail.getId()))
					{
						dbSocialBenefitDetail.setItemId(pageSocialBenefitDetail.getItemId());
						dbSocialBenefitDetail.setCompanyPerency(pageSocialBenefitDetail.getCompanyPerency());
						dbSocialBenefitDetail.setPersonPerency(pageSocialBenefitDetail.getPersonPerency());
						dbSocialBenefitDetail.setCompanyFixAmount(pageSocialBenefitDetail.getCompanyFixAmount());
						dbSocialBenefitDetail.setPersonFixAmount(pageSocialBenefitDetail.getPersonFixAmount());
						dbSocialBenefitDetail.setCompanyCap(pageSocialBenefitDetail.getCompanyCap());
						dbSocialBenefitDetail.setPersonCap(pageSocialBenefitDetail.getPersonCap());
						dbSocialBenefitDetail.setCompanyFloor(pageSocialBenefitDetail.getCompanyFloor());
						dbSocialBenefitDetail.setPersonFloor(pageSocialBenefitDetail.getPersonFloor());
						dbSocialBenefitDetail.setTermMonth(pageSocialBenefitDetail.getTermMonth());
						dbSocialBenefitDetail.setAjustTime(pageSocialBenefitDetail.getAjustTime());
						dbSocialBenefitDetail.setAttributeMonth(pageSocialBenefitDetail.getAttributeMonth());
						dbSocialBenefitDetail.setMonthEffctive(pageSocialBenefitDetail.getMonthEffctive());
						dbSocialBenefitDetail.setAddLimitTime(pageSocialBenefitDetail.getAddLimitTime());
						dbSocialBenefitDetail.setEbbLimitTime(pageSocialBenefitDetail.getEbbLimitTime());
						dbSocialBenefitDetail.setDescription(pageSocialBenefitDetail.getDescription());
						dbSocialBenefitDetail.setStatus(pageSocialBenefitDetail.getStatus());
						dbSocialBenefitDetail.setModifyBy(staff);
						dbSocialBenefitDetail.setModifyDate(new Date());
						dbSocialBenefitDetail.setQuitSsbStatus(pageSocialBenefitDetail.getQuitSsbStatus());
						dbSocialBenefitDetail.setSocialBenefitHeader(dbSocialBenefitHeader);
						socialBenefitHeaderMapper.saveSocialBenefitDetail(dbSocialBenefitDetail);
					}
				}
			}else{
				SocialBenefitDetail newSocialBenefitDetail = new SocialBenefitDetail();
				newSocialBenefitDetail.setItemId(pageSocialBenefitDetail.getItemId());
				newSocialBenefitDetail.setCompanyPerency(pageSocialBenefitDetail.getCompanyPerency());
				newSocialBenefitDetail.setPersonCap(pageSocialBenefitDetail.getPersonCap());
				newSocialBenefitDetail.setPersonPerency(pageSocialBenefitDetail.getPersonPerency());
				newSocialBenefitDetail.setCompanyCap(pageSocialBenefitDetail.getCompanyCap());
				newSocialBenefitDetail.setCompanyFloor(pageSocialBenefitDetail.getCompanyFloor());
				newSocialBenefitDetail.setPersonFloor(pageSocialBenefitDetail.getPersonFloor());
				newSocialBenefitDetail.setCompanyFixAmount(pageSocialBenefitDetail.getCompanyFixAmount());
				newSocialBenefitDetail.setPersonFixAmount(pageSocialBenefitDetail.getPersonFixAmount());
				newSocialBenefitDetail.setTermMonth(pageSocialBenefitDetail.getTermMonth());
				newSocialBenefitDetail.setMonthEffctive(pageSocialBenefitDetail.getMonthEffctive());
				//newSocialBenefitDetail.setResidency(pageSocialBenefitDetail.getResidency());
				newSocialBenefitDetail.setAjustTime(pageSocialBenefitDetail.getAjustTime());
				newSocialBenefitDetail.setAddLimitTime(pageSocialBenefitDetail.getAddLimitTime());
				newSocialBenefitDetail.setEbbLimitTime(pageSocialBenefitDetail.getEbbLimitTime());
				newSocialBenefitDetail.setAttributeMonth(pageSocialBenefitDetail.getAttributeMonth());
				newSocialBenefitDetail.setTermMonth(pageSocialBenefitDetail.getTermMonth());
				newSocialBenefitDetail.setEffective(pageSocialBenefitDetail.getEffective());
				newSocialBenefitDetail.setEffectiveRemark(pageSocialBenefitDetail.getEffectiveRemark());
				newSocialBenefitDetail.setDescription(pageSocialBenefitDetail.getDescription());
				newSocialBenefitDetail.setStatus(pageSocialBenefitDetail.getStatus());
				newSocialBenefitDetail.setCreateBy(staff);
				newSocialBenefitDetail.setCreateDate(new Date());
				newSocialBenefitDetail.setModifyBy(staff);
				newSocialBenefitDetail.setModifyDate(new Date());
				newSocialBenefitDetail.setQuitSsbStatus(pageSocialBenefitDetail.getQuitSsbStatus());
				newSocialBenefitDetail.setSocialBenefitHeader(dbSocialBenefitHeader);
				socialBenefitHeaderMapper.saveSocialBenefitDetail(newSocialBenefitDetail);
			}
		}
		result.setResult(true);
		result.setResultText("该社保类型保存成功");
		return result;
	}
	
	
	
	public List<Item> findItemDefault()
	{
		return socialBenefitHeaderMapper.getItemListDefault();
	}
	
	public List<BaseCode> getResidencyList()
	{
		return socialBenefitHeaderMapper.getBaseCodeListByType(SocialBenefitDataConvert.SOCIALBENEFIT_RESIDENCY);
	}
	
	public boolean isExsitInsurance(String sbname, String insurancename) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("sbname", sbname);
			parameters.put("insurancename", insurancename);
			return this.socialBenefitHeaderMapper.isExistInsurance(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<BaseCode> getEffecitveList()
	{
		return socialBenefitHeaderMapper.getBaseCodeListByType(SocialBenefitDataConvert.SOCIALBENEFIT_EFFCTIVE);
	}
	
	
	public boolean validateSBDetail(Integer headid,String headname,Integer detailid,Integer itemid,String detailname) throws Exception{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("headid", headid);
		parameters.put("headname", headname);
		parameters.put("detailid", detailid);
		parameters.put("itemid", itemid);
		parameters.put("detailname", detailname);
		return this.socialBenefitHeaderMapper.isExistChild(parameters);
	}
	
	public List<Item> findAllItemOfSocialBenfit()
	{
		return socialBenefitHeaderMapper.getAllItemListOfSocialBenefit();
	}
	/**
	 * 验证社保类型是否重复 and 支付月是否为空
	 * @return
	 */
	public Boolean validateSocialBenefit(SocialBenefitHeader socialBenefit,Result result)
	{
		result = (result == null?new Result():result);
		String socialBenefitName = StringUtils.trimToNull(socialBenefit.getName());
		Integer socialBenefitId = socialBenefit.getId();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("socialBenefitName", socialBenefitName);
		parameters.put("socialBenefitId", socialBenefitId);
		Long count =  socialBenefitHeaderMapper.isExsitSocialBenefit(parameters);
		if(count != null && count > 0) 
		{
			result.setResult(false);
			result.setResultText("当前保存的社保类型已存在");
			return false;
		}
	/*	if(StringUtils.isBlank(socialBenefit.getTermMonth()))
		{
			result.setResult(false);
			result.setResultText("当前社保支付月不能为空");
			return false;
		}*/
		List<Integer> socialBenefitItemIds = new ArrayList<Integer>();
		for(int i = 0 ; socialBenefit.getSocialBenefitDetails() != null && i < socialBenefit.getSocialBenefitDetails().size();i++)
		{
			if(socialBenefitItemIds.contains(socialBenefit.getSocialBenefitDetails().get(i).getItemId()))
			{
				result.setResult(false);
				result.setResultText("当前保存的社保类型中的险种存在重复");
				return false;
			}
			socialBenefitItemIds.add(socialBenefit.getSocialBenefitDetails().get(i).getItemId());
		}
		return true;
	}
	
	public Item quaryItemByHeadIdAndDetailId(Integer headid, Integer detailid) 
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("headid", headid);
		parameters.put("detailid", detailid);
		return socialBenefitHeaderMapper.quaryItemByHeadIdAndDetailId(parameters);
	}
	
	public SocialBenefitHeader querySocialBenefitByNameOrID(Integer headId,String headName) 
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("headId", headId);
		parameters.put("headName", headName);
		return socialBenefitHeaderMapper.querySocialBenefitByNameOrID(parameters);
	}

	
	
	/**
	 * 算出支付月，用来显示
	 * @param termMonths
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-12-16
	 */
	public String convertTermMonth(List<String> termMonths) {
		String termMonth = "";
		if (termMonths == null) {
			return termMonth;
		}
		if(termMonths.size()==12){
			termMonth = "每月";
			return termMonth;
		}else if (termMonths.size()<7) {
			for (Iterator<String> iterator = termMonths.iterator(); iterator.hasNext();) {
				String s = iterator.next();
				termMonth = termMonth +s+"月，";
			}
			
			termMonth = termMonth.length()>0?termMonth.substring(0, termMonth.length()-1):termMonth;
		}else if (termMonths.size()>6) {
//			termMonth = "每月（除*月，*月）";
			termMonth = "每月（除";
			List<String> fullTermMonths = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12"); 
			Collection<String> result =	CollectionUtils.subtract(fullTermMonths, termMonths);
//			Collections.sort((List)result);
			for (String s : result) {
				termMonth = termMonth +s+"月，";
			}
			termMonth = termMonth.substring(0, termMonth.length()-1) +"）";
		}
		return termMonth;
	}
	
	/**
	 * 查询详细页面所需数据
	 * @param id
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-11-25
	 */
	@Transactional(readOnly=true)
	public SocialBenefitHeader querySocialBenefitFullInfoById(Integer id) {
		
		SocialBenefitHeader header = socialBenefitHeaderMapper.getSocialBenefitHeader(id);
//		SocialBenefitEffective a = (SocialBenefitEffective) XMLUtil.fromXML(header.getEffective());
//		header.setSbe((SocialBenefitEffective) XMLUtil.fromXML(header.getEffective()));
		if (header!=null) {
			
			
			City city = cityMapper.get(header.getCityId().longValue());
			//省市名
			String cityName =cityMapper.selectProvinceByCityId(city.getParentId()).getName()+"-"+city.getName();
			//户籍
			String residency = "";
			if (header.getResidency()!=null &&!"".equals(header.getResidency()) ) {
				List<BaseCode> bsList = socialBenefitHeaderMapper.findByIds(com.manpowergroup.cn.core.utils.StringUtils.stringIntegerListConvert(header.getResidency()));
				for (BaseCode baseCode : bsList) {
					residency = residency+baseCode.getBdcode()+"，";
				}
			}
			residency = residency.length()>0?residency.substring(0, residency.length()-1):residency;
			
//			Map<String, Object> parameters1 = new HashMap<String, Object>();
//			parameters1.put("id", "id");
//			parameters1.put("sbeType", header.getSbe().getStartType());
//			parameters1.put("flag", true);
			BaseCode	start	= socialBenefitHeaderMapper.findUniqueBy(header.getSbe().getStartType());
//			Map<String, Object> parameters2 = new HashMap<String, Object>();
//			parameters2.put("id", "id");
//			parameters2.put("sbeType", header.getSbe().getEndType());
//			parameters2.put("flag", true);
			BaseCode	end	= socialBenefitHeaderMapper.findUniqueBy(header.getSbe().getEndType());
			
			String s1 =  "";
			String s2 =  "";
			if (start!=null && new Integer(32).equals(start.getId())) {
				s1="工作天数少于"+header.getSbe().getStartDay()+"天，入职当月需缴纳社保";
			}else if(start!=null && new Integer(33).equals(start.getId())){
				s1="早于"+header.getSbe().getStartDay()+"日入职，入职当月需缴纳社保";
			}
			
			if (end!=null && new Integer(32).equals(end.getId())) {
				s2="工作天数大于"+header.getSbe().getStartDay()+"天，离职当月需缴纳社保";
			}else if(end!=null && new Integer(33).equals(end.getId())){
				s2="晚于"+header.getSbe().getStartDay()+"日离职，离职当月需缴纳社保";
			}
			
			
			//开始规则
			String effectiveStart  = start==null?"":start.getBdcode()+"  "+s1+"";
			//结束规则
			String effectiveEnd = end==null?"":end.getBdcode()+"   "+s2+"";
			
			//支付月
			List<String> termMonths = com.manpowergroup.cn.core.utils.StringUtils.stringStringListConvert(header.getTermMonth());
			String termMonth= convertTermMonth(termMonths);
			
			//社保所属月
//			Map<String, Object> parameters3 = new HashMap<String, Object>();
//			parameters3.put("id", "id");
//			parameters3.put("sbeType", header.getAttributeMonth());
//			parameters3.put("flag", true);
			BaseCode amCode	= socialBenefitHeaderMapper.findUniqueBy(header.getAttributeMonth());
//			Map<String, Object> parameters4 = new HashMap<String, Object>();
//			parameters4.put("id", "id");
//			parameters4.put("sbeType", header.getMonthEffctive());
//			parameters4.put("flag", true);
			BaseCode meCode	= socialBenefitHeaderMapper.findUniqueBy(header.getMonthEffctive());
			
			header.setTermMonth(termMonth);
			header.setCityName(cityName);
			header.setResidency(residency);
			
			header.setEffectiveStart(effectiveStart);//开始规则
			header.setEffectiveEnd(effectiveEnd);//结束规则
			
			header.setAmString(amCode==null?"":amCode.getBdcode());
			header.setMeString(meCode==null?"":meCode.getBdcode());
			
			
			/************************detail信息**************************/
			for (SocialBenefitDetail detail : header.getSocialBenefitDetails()) {
				
				//险种
//				Map<String, Object> parameters5 = new HashMap<String, Object>();
//				parameters5.put("id", "id");
//				parameters5.put("sbeType", detail.getItemId());
//				parameters5.put("flag", true);
				Item item =  socialBenefitHeaderMapper.findItemUniqueBy(detail.getItemId());
				//户籍
				/*String residency_detail = "";
				if (detail.getResidency()!=null &&!"".equals(detail.getResidency()) ) {
					List<BaseCode> bs_detailList = baseCodeDao.findByIds(com.manpowergroup.cn.core.utils.StringUtils.stringIntegerListConvert(detail.getResidency()));
					for (BaseCode baseCode : bs_detailList) {
						residency_detail = residency_detail+baseCode.getBdcode()+"，";
					}
				}
				
				residency_detail = residency_detail.length()>0?residency_detail.substring(0, residency_detail.length()-1):residency_detail;*/
				
//				detail.setSbe((SocialBenefitEffective) XMLUtil.fromXML(detail.getEffective()));
//				Map<String, Object> parameters6 = new HashMap<String, Object>();
//				parameters6.put("id", "id");
//				parameters6.put("sbeType", detail.getSbe().getStartType());
//				parameters6.put("flag", true);
				BaseCode	start_detail	= socialBenefitHeaderMapper.findUniqueBy(detail.getSbe().getStartType());
//				Map<String, Object> parameters7 = new HashMap<String, Object>();
//				parameters7.put("id", "id");
//				parameters7.put("sbeType", detail.getSbe().getEndType());
//				parameters7.put("flag", true);
				BaseCode	end_detail	= socialBenefitHeaderMapper.findUniqueBy(detail.getSbe().getEndType());
				
				String detail_s1 =  "";
				String detail_s2 =  "";
				if (start_detail!=null && new Integer(32).equals(start_detail.getId())) {
					detail_s1="工作天数少于"+header.getSbe().getStartDay()+"天，入职当月需缴纳社保";
				}else if(start_detail!=null && new Integer(33).equals(start_detail.getId())){
					detail_s1="早于"+header.getSbe().getStartDay()+"日入职，入职当月需缴纳社保";
				}
				
				if (end_detail!=null && new Integer(32).equals(end_detail.getId())) {
					detail_s2="工作天数大于"+header.getSbe().getStartDay()+"天，离职当月需缴纳社保";
				}else if(end_detail!=null && new Integer(33).equals(end_detail.getId())){
					detail_s2="晚于"+header.getSbe().getStartDay()+"日离职，离职当月需缴纳社保";
				}
				
				
				//开始规则
				String effectiveStart_detail  =  start_detail==null?"":start_detail.getBdcode()+"  "+detail_s1+"";
				//结束规则
				String effectiveEnd_detail =  end_detail==null?"":end_detail.getBdcode()+"   "+detail_s2+"";
				
				//支付月
				List<String> termMonths_detail = com.manpowergroup.cn.core.utils.StringUtils.stringStringListConvert(detail.getTermMonth());
				String termMonth_detail= convertTermMonth(termMonths_detail);
			
				
				//社保所属月
//				Map<String, Object> parameters8 = new HashMap<String, Object>();
//				parameters8.put("id", "id");
//				parameters8.put("sbeType", detail.getAttributeMonth());
//				parameters8.put("flag", true);
				BaseCode amCode_detail	= socialBenefitHeaderMapper.findUniqueBy(detail.getAttributeMonth());
//				Map<String, Object> parameters9 = new HashMap<String, Object>();
//				parameters9.put("id", "id");
//				parameters9.put("sbeType", detail.getMonthEffctive());
//				parameters9.put("flag", true);
				BaseCode meCode_detail	= socialBenefitHeaderMapper.findUniqueBy(detail.getMonthEffctive());
				
				detail.setTermMonth(termMonth_detail);
				detail.setItemName(item.getName());
//				detail.setResidency(residency_detail);
				detail.setEffectiveStart(effectiveStart_detail);//开始规则
				detail.setEffectiveEnd(effectiveEnd_detail);//结束规则
				
				detail.setAmString(amCode_detail==null?"":amCode_detail.getBdcode());
				detail.setMeString(meCode_detail==null?"":meCode_detail.getBdcode());
				
				// 临时属性，在页面提示用
				//计算规则：比例*基数+固定金
				BigDecimal percent = BigDecimal.valueOf(100);
				BigDecimal companyPayOffCapAmount = detail.getCompanyPerency().divide(percent).multiply(detail.getCompanyCap()).add(detail.getCompanyFixAmount());
				BigDecimal companyPayOffFloorAmount = detail.getCompanyPerency().divide(percent).multiply(detail.getCompanyFloor()).add(detail.getCompanyFixAmount());
				BigDecimal personPayOffCapAmount = detail.getPersonPerency().divide(percent).multiply(detail.getPersonCap()).add(detail.getPersonFixAmount());
				BigDecimal personPayOffFloorAmount = detail.getPersonPerency().divide(percent).multiply(detail.getPersonFloor()).add(detail.getPersonFixAmount());
				detail.setCompanyPayOffCapAmount(companyPayOffCapAmount);
				detail.setCompanyPayOffFloorAmount(companyPayOffFloorAmount);
				detail.setPersonPayOffCapAmount(personPayOffCapAmount);
				detail.setPersonPayOffFloorAmount(personPayOffFloorAmount);
			}
		}
		
		return header;
	}

	/**
	 * 社保所属月
	 * @return
	 * author:jiangpeng.sun
	 * date:2011-11-25
	 */
	public List<BaseCode> getAttributeMonthList() {
		return socialBenefitHeaderMapper.getBaseCodeListByType(SocialBenefitDataConvert.SOCIALBENEFIT_ARRRIBUTEMONTH);
	}
	/**
	 * 社保起缴月
	 * @return
	 * author:jiangpeng.sun
	 * date:2011-12-8
	 */
	public List<BaseCode> getMonthEffctiveList() {
		return socialBenefitHeaderMapper.getBaseCodeListByType(SocialBenefitDataConvert.SOCIALBENEFIT_MONTHEFFECTIVE);
	}
	
	public void changeStatus(Integer id ,Integer status)
	{
//		SocialBenefitHeader sb = socialBenefitHeaderMapper.getSocialBenefitHeader(id);
//		sb.setStatus(status);
		if(id == null || status == null) return;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		parameters.put("status", status);
		socialBenefitHeaderMapper.changeStatus(parameters);
		
	}
	public Page<SocialBenefitHeader> querySsbByCondition(
			Page<SocialBenefitHeader> page, Integer provinceId, Integer cityId,
			String name, String residency) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String,String> getTermMonth()
	{
		return socialBenefitHeaderMapper.getBaseCodesByType(SocialBenefitDataConvert.SOCIALBENEFIT_TERMMONTH);
	}
	
	/**
	 * 取省市名称
	 * @param id
	 * @param type
	 * @return
	 */
	private String findNameByCityIdOrProvinceID(Integer id,String type){
		String rtnname = "";
		City city = this.cityMapper.findUniqueBy("id", id,true);
		if(city != null && type.equals("city")){
			rtnname = city.getName();
		}else if(city != null && type.equals("province")){
			rtnname = city.getParentCity().getName();
		}
		return rtnname;
	}
	/**
	 * 状态转换
	 * @param status
	 * @return
	 */
	private String convertStatus(Integer status,String type){
		String rtnstatus = "";
		if(status != null){
			if(status == 0 && "header".equals(type)){
				rtnstatus = "停用";
			}else if(status == 1 && "header".equals(type)){
				rtnstatus = "启用";
			}else if(status == 2 && "header".equals(type)){
				rtnstatus = "待审批";
			}else if(status == 0 && "detail".equals(type)){
				rtnstatus = "停用";
			}else if(status == 1 && "detail".equals(type)){
				rtnstatus = "启用";
			}
		}else{
			rtnstatus = "";
		}
		return rtnstatus;
	}
	/**
	 * 户籍转换查询
	 * @param residencyids
	 * @return
	 */
	private String convertResidency(String residencyids){
		String residency_detail = "";
		if (residencyids!=null &&!"".equals(residencyids.trim()) ) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("flag", true);
			parameters.put("list", StringUtils.stringIntegerListConvert(residencyids));
			List<BaseCode> bs_detailList = socialBenefitHeaderMapper.findByIds(parameters);
			for (BaseCode baseCode : bs_detailList) {
				if(residency_detail != null && !"".equals(residency_detail))
					residency_detail = residency_detail+","+baseCode.getBdcode();
				else
					residency_detail = baseCode.getBdcode();
			}
		}
		//residency_detail = residency_detail.length()>0?residency_detail.substring(0, residency_detail.length()-1):residency_detail;
		return residency_detail;
	}
	/**
	 * 根据ItemID查询名称 
	 * @param itemid
	 * @return
	 */
	private String findItemNameById(Integer itemid){
		String itemname = "";
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("id", "id");
//		parameters.put("itemid", itemid);
//		parameters.put("flag", true);
		Item item = this.socialBenefitHeaderMapper.findItemUniqueBy(itemid);
		if(item != null){
			itemname = item.getName();
		}
		return itemname;
	}
	
	private String convertTermMonth(String termname){
		String termMonth_detail = "";
		if(termname != null){
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			parameters.put("bdname", "bdname");
//			parameters.put("termname", termname);
//			parameters.put("flag", true);
			BaseCode code = this.socialBenefitHeaderMapper.findUniqueByBDName(termname);
			if(code != null){
				termMonth_detail = code.getBdcode();
			}else{
				List<String> termMonths_detail = StringUtils.stringStringListConvert(termname);
				if(null != termMonths_detail){
					for (Iterator iterator = termMonths_detail.iterator();  iterator.hasNext();) {
						String s = (String) iterator.next();
						if(termMonth_detail != null && !"".equals(termMonth_detail)){
							termMonth_detail = termMonth_detail+","+s+"月";
						}else{
							termMonth_detail = s+"月";
						}					
					}
				}
			}
		}
		return termMonth_detail;
	}
	
	public List<SocialBenefitHeader> findAllSocialBenefitHeader(){
		return this.socialBenefitHeaderMapper.findAllSocialBenefitHeader();
	}

	/**
	 * 批量审批
	 * @param ssbIds
	 * author:jiangpeng.sun
	 * date:2012-1-16
	 * @throws Exception 
	 */
	public void batchChangeStatus(String ssbIds) throws Exception {
		List<Integer> ids = StringUtils.stringIntegerListConvert(ssbIds);
		
		if(ids == null) return;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", 1);
		parameters.put("list", ids);
		parameters.put("comstatus", 2);
		socialBenefitHeaderMapper.updateBatchHeader(parameters);
		
	}

	@Override
	public List<SocialBenefitHeader> querySocialBenefitListByCity(Integer cityId) {
		return socialBenefitHeaderMapper.querySocialBenefitListByCity(cityId);
	}

	/**
	 * 根据社保类型取得所有险种
	 * @param id
	 * @return
	 * author:jiangpeng.sun
	 * date:2012-2-22
	 */
	public List<Item> findItemListByHeaderID(Integer id) {
		List<SocialBenefitDetail> detailList = socialBenefitHeaderMapper.findDetailListByHeaderID(id);
		if (detailList == null) {
			detailList = new ArrayList<SocialBenefitDetail>();
		}

		List<Item> items = new ArrayList<Item>();

		for (SocialBenefitDetail detail : detailList) {
			items.add(socialBenefitHeaderMapper.getItemById(detail.getItemId()));
		}
		return items;
	}
}
