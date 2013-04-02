package com.manpowergroup.cn.icloud.socialbenefitBase.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.json.JsonMapper;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitDetail;
import com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitHeader;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.BaseCode;
import com.manpowergroup.cn.icloud.socialbenefitBase.model.Item;
import com.manpowergroup.cn.icloud.socialbenefitBase.service.SocialBenefitHeaderService;


public class SocialBenefitBaseAction extends BaseAction {
	
	private static final long serialVersionUID = -2622195268814511086L;

	@Autowired
	private SocialBenefitHeaderService socialBenefitService;
	
	protected static JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
	
	private SocialBenefitHeader socialBenefit;

	private List<City> provinceList;

	private List<City> cityList = new ArrayList<City>();
	private City city;
	private List<BaseCode> residencyList = new ArrayList<BaseCode>();// 户籍
	private List<BaseCode> effecitveList = new ArrayList<BaseCode>();// 开始/结束规则
	private List<BaseCode> attributeMonthList = new ArrayList<BaseCode>();// 社保所属月
	private List<BaseCode> monthEffctiveList = new ArrayList<BaseCode>();// 社保起缴月
	

	private List<Item> itemList = new ArrayList<Item>();//险种

	private Page<SocialBenefitHeader> page;

	
	
	public String page() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "manage";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}

	public String prepaging() throws Exception {
		querySsbByCondition();
		return "list";
	}

	public String query() throws Exception {
		querySsbByCondition();
		return "list";
	}

	@SuppressWarnings("unchecked")
	public void querySsbByCondition() throws Exception {
		page = this.initPage(page);
		page.setResult(new ArrayList<SocialBenefitHeader>());
		if (socialBenefit==null ) {
			socialBenefit = new SocialBenefitHeader();
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("name", request.getParameter("name"));
		parameters.put("provinceName", request.getParameter("provinceName"));
		parameters.put("cityName", request.getParameter("cityName"));
		List<String> list = StringUtils.stringStringListConvert(request.getParameter("residency"));
		parameters.put("residencylist", list);
		parameters.put("status", request.getParameter("status"));
		page = socialBenefitService.querySsbByCondition(parameters,page);
	}

	public String edit() throws Exception {
		if (socialBenefit != null && socialBenefit.getId() != null
				&& socialBenefit.getId() > 0) {
			socialBenefit = socialBenefitService.querySocialBenefitById(this.socialBenefit.getId());
			//只获取启用的城市 status = 1
			cityList = socialBenefitService.cityList(socialBenefit.getProvinceId());
			if (socialBenefit.getTermMonth() != null && StringUtils.stringConvertWithCommaAndSortBack(socialBenefit.getTermMonth()).length()==26) {
				socialBenefit.setEveryMonth("true");//默认支付月-每月选中
			}
			for (SocialBenefitDetail detail : socialBenefit.getSocialBenefitDetails()) {
				if (detail.getTermMonth() != null && StringUtils.stringConvertWithCommaAndSortBack(detail.getTermMonth()).length()==26) {
					detail.setEveryMonth("true");//默认支付月-每月选中
				}
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
		} else {
			socialBenefit = new SocialBenefitHeader();
			//默认12个月全部选中
			socialBenefit.setTermMonths(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12"));
			socialBenefit.setEveryMonth("true");
			
			//社保开始月缴纳规则:选项分别为“按日历日”和“按工作日”，默认选择“按日历日”
			socialBenefit.getSbe().setStartType(33);
			socialBenefit.getSbe().setEndType(33);
			
			// 默认的6个险种
			itemList = socialBenefitService.findItemDefault();
			List<SocialBenefitDetail> socialBenefitDetails = new ArrayList<SocialBenefitDetail>();
			for (Item item : itemList) {
				SocialBenefitDetail sbd = new SocialBenefitDetail();
				sbd.setItemId(Integer.parseInt(item.getId().toString()));
				sbd.setTermMonths(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12"));
				sbd.setCompanyPerency(BigDecimal.ZERO);
				sbd.setPersonPerency(BigDecimal.ZERO);
				sbd.setCompanyCap(BigDecimal.ZERO);
				sbd.setCompanyFloor(BigDecimal.ZERO);
				sbd.setPersonCap(BigDecimal.ZERO);
				sbd.setPersonFloor(BigDecimal.ZERO);
				sbd.setCompanyFixAmount(BigDecimal.ZERO);
				sbd.setPersonFixAmount(BigDecimal.ZERO);
				sbd.setEveryMonth("true");
				sbd.setPersonPayOffCapAmount(BigDecimal.ZERO);
				sbd.setPersonPayOffFloorAmount(BigDecimal.ZERO);
				sbd.setCompanyPayOffCapAmount(BigDecimal.ZERO);
				sbd.setCompanyPayOffFloorAmount(BigDecimal.ZERO);
				
				//社保开始月缴纳规则:选项分别为“按日历日”和“按工作日”，默认选择“按日历日”
				sbd.getSbe().setStartType(33);
				sbd.getSbe().setEndType(33);
				socialBenefitDetails.add(sbd);
			}
			socialBenefit.setSocialBenefitDetails(socialBenefitDetails);
		}
		//全部险种
		itemList = socialBenefitService.findAllItemOfSocialBenfit();
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		effecitveList = socialBenefitService.getEffecitveList();
		attributeMonthList = socialBenefitService.getAttributeMonthList();
		monthEffctiveList = socialBenefitService.getMonthEffctiveList();
		
		request.setAttribute( "mp",creatMonthMap());
		return "edit";
	}

	public String queryCityByProvince() throws Exception {

		if (city != null && city.getId() != null) {
			cityList = socialBenefitService.cityList(city.getId().intValue());
		} else {
			cityList = new ArrayList<City>();
		}
		Struts2Utils.renderJson(jsonMapper.toJson(cityList),
				"encoding:utf-8", "no-cache:false");
		return null;
	}

	/**
	 * 添加detail
	 * @return
	 * @throws Exception
	 * @author jiangpeng.sun
	 * @date 2011-11-23
	 */
	public String addItemNew() throws Exception {
	
		// 移除掉页面中删掉的数据
 		for (int i = 0; socialBenefit.getSocialBenefitDetails() != null
				&& i < socialBenefit.getSocialBenefitDetails().size(); i++) {
			if (socialBenefit.getSocialBenefitDetails().get(i) == null) {
				socialBenefit.getSocialBenefitDetails().remove(i);
				i--;
			}
		}
		if (socialBenefit.getSocialBenefitDetails()==null) {
			socialBenefit.setSocialBenefitDetails(new ArrayList<SocialBenefitDetail>());
		}
		for (SocialBenefitDetail detail : socialBenefit
				.getSocialBenefitDetails()) {
			detail.setTermMonths(arrayToList(new ArrayList<String>(),detail.getTermMonth()));// 支付月
			
			BigDecimal percent = BigDecimal.valueOf(100);
            BigDecimal companyPayOffCapAmount = detail.getCompanyPerency().divide(percent).multiply(detail.getCompanyCap()).add(detail.getCompanyFixAmount());
            BigDecimal companyPayOffFloorAmount = detail.getCompanyPerency().divide(percent).multiply(detail.getCompanyFloor()).add(detail.getCompanyFixAmount());
            BigDecimal personPayOffCapAmount = detail.getPersonPerency().divide(percent).multiply(detail.getPersonCap()).add(detail.getPersonFixAmount());
            BigDecimal personPayOffFloorAmount = detail.getPersonPerency().divide(percent).multiply(detail.getPersonFloor()).add(detail.getPersonFixAmount());
            detail.setCompanyPayOffCapAmount(companyPayOffCapAmount);
            detail.setCompanyPayOffFloorAmount(companyPayOffFloorAmount);
            detail.setPersonPayOffCapAmount(personPayOffCapAmount);
            detail.setPersonPayOffFloorAmount(personPayOffFloorAmount);
            
//			detail.setResidencys(arrayToList(new ArrayList<String>(),detail.getResidency()));// 户籍
		}

		SocialBenefitDetail sbd = new SocialBenefitDetail();
		sbd.setCompanyPerency(BigDecimal.ZERO);
		sbd.setPersonPerency(BigDecimal.ZERO);
		sbd.setCompanyCap(BigDecimal.ZERO);
		sbd.setCompanyFloor(BigDecimal.ZERO);
		sbd.setPersonCap(BigDecimal.ZERO);
		sbd.setPersonFloor(BigDecimal.ZERO);
		sbd.setCompanyFixAmount(BigDecimal.ZERO);
		sbd.setPersonFixAmount(BigDecimal.ZERO);
		sbd.setTermMonths(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12"));
		sbd.setEveryMonth("true");//默认支付月-每月选中
		sbd.setCompanyPayOffCapAmount(BigDecimal.ZERO);
        sbd.setCompanyPayOffFloorAmount(BigDecimal.ZERO);
        sbd.setPersonPayOffCapAmount(BigDecimal.ZERO);
        sbd.setPersonPayOffFloorAmount(BigDecimal.ZERO);
		
		//社保开始月缴纳规则:选项分别为“按日历日”和“按工作日”，默认选择“按日历日”
		sbd.getSbe().setStartType(33);
		sbd.getSbe().setEndType(33);
		
		
		
		socialBenefit.getSocialBenefitDetails().add(sbd);

		// 重新查询下，要不添加detail的时候会丢失
		residencyList = socialBenefitService.getResidencyList();
		effecitveList = socialBenefitService.getEffecitveList();
		itemList = socialBenefitService.findAllItemOfSocialBenfit();
		attributeMonthList = socialBenefitService.getAttributeMonthList();
		monthEffctiveList = socialBenefitService.getMonthEffctiveList();
		request.setAttribute( "mp",creatMonthMap());
		return "addItemNew";
	}
	/**
	 * 合并socialBenefit里的信息到detail
	 * @return
	 * @throws Exception
	 * @author jiangpeng.sun
	 * @date 2011-11-23
	 */
	public String merge() throws Exception {
		if (socialBenefit == null)
			socialBenefit = new SocialBenefitHeader();
		if (socialBenefit.getSocialBenefitDetails() == null)
			socialBenefit.setSocialBenefitDetails(new ArrayList<SocialBenefitDetail>());
		
		//合并一个
		if (request.getParameter("mergeIndex")!=null && !"".equals(request.getParameter("mergeIndex"))) {
		    
		    if(socialBenefit.getSocialBenefitDetails()!=null){
                for (SocialBenefitDetail detail : socialBenefit.getSocialBenefitDetails()) {
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
		    
			SocialBenefitDetail detail = socialBenefit.getSocialBenefitDetails().get(Integer.valueOf(request.getParameter("mergeIndex")));
//			detail.setResidency(socialBenefit.getResidency());//户籍
//			detail.setResidencys(arrayToList(new ArrayList<String>(),detail.getResidency()));// 户籍
			
			
			detail.setTermMonth(socialBenefit.getTermMonth());// 支付月
			detail.setEveryMonth(socialBenefit.getEveryMonth());
			detail.setTermMonths(arrayToList(new ArrayList<String>(),detail.getTermMonth()));// 支付月
			
			//开始结束规则
			detail.getSbe().setEndDay(socialBenefit.getSbe().getEndDay());
			detail.getSbe().setEndType(socialBenefit.getSbe().getEndType());
			detail.getSbe().setStartDay(socialBenefit.getSbe().getStartDay());
			detail.getSbe().setStartType(socialBenefit.getSbe().getStartType());
			
			detail.setAjustTime(socialBenefit.getAjustTime());//每年调整时间
			detail.setAttributeMonth(socialBenefit.getAttributeMonth());//社保所属月
			detail.setMonthEffctive(socialBenefit.getMonthEffctive());//社保起缴月
			detail.setAddLimitTime(socialBenefit.getAddLimitTime());//加保截止日期
			detail.setEbbLimitTime(socialBenefit.getEbbLimitTime());//退保截止日期
			detail.setQuitSsbStatus(socialBenefit.getQuitSsbStatus());//退保是否产生费用
			
			
		}else {//全部合并
			// 移除掉页面中删掉的数据
			for (int i = 0; socialBenefit.getSocialBenefitDetails() != null
					&& i < socialBenefit.getSocialBenefitDetails().size(); i++) {
				if (socialBenefit.getSocialBenefitDetails().get(i) == null) {
					socialBenefit.getSocialBenefitDetails().remove(i);
					i--;
				}
			}
			for (SocialBenefitDetail detail : socialBenefit.getSocialBenefitDetails()) {
//				detail.setResidency(socialBenefit.getResidency());//户籍
				detail.setTermMonth(socialBenefit.getTermMonth());// 支付月
				detail.setEveryMonth(socialBenefit.getEveryMonth());
				//开始结束规则
				detail.getSbe().setEndDay(socialBenefit.getSbe().getEndDay());
				detail.getSbe().setEndType(socialBenefit.getSbe().getEndType());
				detail.getSbe().setStartDay(socialBenefit.getSbe().getStartDay());
				detail.getSbe().setStartType(socialBenefit.getSbe().getStartType());
				
				detail.setAjustTime(socialBenefit.getAjustTime());//每年调整时间
				detail.setAttributeMonth(socialBenefit.getAttributeMonth());//社保所属月
				detail.setMonthEffctive(socialBenefit.getMonthEffctive());//社保起缴月
				detail.setAddLimitTime(socialBenefit.getAddLimitTime());//加保截止日期
				detail.setEbbLimitTime(socialBenefit.getEbbLimitTime());//退保截止日期
				detail.setQuitSsbStatus(socialBenefit.getQuitSsbStatus());//退保是否产生费用
				
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
		
		// 重新查询下，要不添加detail的时候会丢失
		residencyList = socialBenefitService.getResidencyList();
		effecitveList = socialBenefitService.getEffecitveList();
		itemList = socialBenefitService.findAllItemOfSocialBenfit();
		attributeMonthList =socialBenefitService.getAttributeMonthList();
		monthEffctiveList =socialBenefitService.getMonthEffctiveList();
	
		request.setAttribute( "mp",creatMonthMap());
		
		return "addItemNew";
	}

	/**
	 * 创建12个月的map
	 * @return
	 * @author jiangpeng.sun
	 * @date 2011-11-25
	 */
	public Map<String, String> creatMonthMap() {
		Map<String,String> mp = new LinkedHashMap<String,String>();
		mp.put("1", "01月");
		mp.put("2", "02月");
		mp.put("3", "03月");
		mp.put("4", "04月");
		mp.put("5", "05月");
		mp.put("6", "06月");
		mp.put("7", "07月");
		mp.put("8", "08月");
		mp.put("9", "09月");
		mp.put("10", "10月");
		mp.put("11", "11月");
		mp.put("12", "12月");
		
		return mp;
	}
	
	/**
	 * 把逗号分隔的字符串转换成list
	 * 
	 * @param resultList
	 * @param arrayString
	 * @return List
	 * @author jiangpeng.sun
	 * @date 2011-11-22
	 */
	public List<String> arrayToList(List<String> resultList, String arrayString) {
		if (arrayString == null || "".equals(arrayString)) {
			return resultList;
		} else {
			for (String s : arrayString.split(",")) {
				resultList.add(s.trim());
			}
		}
		return resultList;
	}
	/**
	 * 跳转到导入页面
	 * @return
	 * @throws Exception
	 */
	public String importpre()throws Exception{
		return "import";
	}
	
	public String save() throws Exception {
		// 移除掉页面中删掉的数据
		for (int i = 0; socialBenefit.getSocialBenefitDetails() != null
				&& i < socialBenefit.getSocialBenefitDetails().size(); i++) {
			if (socialBenefit.getSocialBenefitDetails().get(i) == null) {
				socialBenefit.getSocialBenefitDetails().remove(i);
				i--;
			}
		}
		
		socialBenefit.setEffective (XMLUtil.toXML(socialBenefit.getSbe()));
		/**把逗号分隔的字符串转成需要的格式，便于搜素**/
		socialBenefit.setTermMonth(StringUtils.stringConvertWithCommaAndSort(socialBenefit.getTermMonth()));//支付月
		socialBenefit.setResidency(StringUtils.stringConvertWithCommaAndSort(socialBenefit.getResidency()));//户籍
		if(null != socialBenefit.getSocialBenefitDetails()){
		for (SocialBenefitDetail sbfd : socialBenefit.getSocialBenefitDetails()) {
			sbfd.setTermMonth(StringUtils.stringConvertWithCommaAndSort(sbfd.getTermMonth()));
//			sbfd.setResidency(StringUtils.stringConvertWithCommaAndSort(sbfd.getResidency()));
			sbfd.setEffective(XMLUtil.toXML(sbfd.getSbe()));
		}
		}
		/******************************************/
		Result result = socialBenefitService.save(socialBenefit, this.getCurrOperatorId().intValue());
		String[] filters= {"t"};//不需要转json的字段
		Struts2Utils.renderJsonFilter(result,filters, "encoding:utf-8","no-cache:false");
		return null;
	}

	
	public String detail() throws Exception {
		this.socialBenefit = socialBenefitService.querySocialBenefitFullInfoById(socialBenefit.getId());
		return "detail";
	}
	
	public String changeStatus()
	{
		if(socialBenefit.getId()==null)return null;
		socialBenefitService.changeStatus(socialBenefit.getId(),socialBenefit.getStatus());
		//Struts2Utils.renderJson("{\"success\":\"success\"}",  "encoding:utf-8", "no-cache:false");
		Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		return null;
	}
	
	public String batchAudit() throws Exception
	{
		
		String ssbIds = request.getParameter("ssbIds");
		
		socialBenefitService.batchChangeStatus(ssbIds);
		//Struts2Utils.renderJson("{\"success\":\"success\"}",  "encoding:utf-8", "no-cache:false");
		Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		return null;
	}
	
	


	public List<City> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<City> provinceList) {
		this.provinceList = provinceList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public SocialBenefitHeader getSocialBenefit() {
		return socialBenefit;
	}

	public void setSocialBenefit(SocialBenefitHeader socialBenefit) {
		this.socialBenefit = socialBenefit;
	}

	public List<BaseCode> getResidencyList() {
		return residencyList;
	}

	public void setResidencyList(List<BaseCode> residencyList) {
		this.residencyList = residencyList;
	}

	public List<BaseCode> getEffecitveList() {
		return effecitveList;
	}

	public void setEffecitveList(List<BaseCode> effecitveList) {
		this.effecitveList = effecitveList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Page<SocialBenefitHeader> getPage() {
		return page;
	}

	public void setPage(Page<SocialBenefitHeader> page) {
		this.page = page;
	}
	public List<BaseCode> getAttributeMonthList() {
		return attributeMonthList;
	}

	public void setAttributeMonthList(List<BaseCode> attributeMonthList) {
		this.attributeMonthList = attributeMonthList;
	}
	public List<BaseCode> getMonthEffctiveList() {
		return monthEffctiveList;
	}
	public void setMonthEffctiveList(List<BaseCode> monthEffctiveList) {
		this.monthEffctiveList = monthEffctiveList;
	}
	
	
	/******************************多项二级菜单*********************************/
	private String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	/**
	 * 社保类型查看（仅可导出）
	 */
	public String view() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "view";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}
	
	/**
	 * 社保类型新增（仅可导入新增）
	 */
	public String create() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "create";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}
	
	/**
	 * 社保类型修改（仅可导入修改）
	 */
	public String modify() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "modify";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}
	
	/**
	 * 社保类型状态维护
	 */
	public String status() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "status";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}
	
	/**
	 * 社保类型操作审批
	 */
	public String audit() throws Exception {
		provinceList = socialBenefitService.provinceList();
		residencyList = socialBenefitService.getResidencyList();
		//querySsbByCondition();
		method = "audit";
		request.setAttribute("fromMenuLink", true);
		return "page";
	}
	
	
	/**
	 * 跳转到上传附件页面
	 * @return
	 * @throws Exception
	 */
	public String toUploadFile() throws Exception 
	{
		return "uploadFile";
	}
}