package com.manpowergroup.cn.icloud.base.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.base.service.CityService;

public class CityAction extends BaseAction {

	private static final long serialVersionUID = -7872270951525919673L;
	
	private Page<City> page;
	private List<City> cityList;
	private City city;
	private City province;
	
	@Autowired
	private CityService cityService;
	
	
	
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;

	
	public String page() throws Exception {
		queryProvinceByCondition();
		return "page";
	}
	
	
	public String prepaging() throws Exception{
		queryProvinceByCondition();
		return "list";
	}
	public String query() throws Exception{
		queryProvinceByCondition();
		return "list"; 
	}
	
	@SuppressWarnings("unchecked")
	public void queryProvinceByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("pname", request.getParameter("pname"));
		parameters.put("pname_en", request.getParameter("pname_en"));
		parameters.put("cname", request.getParameter("cname"));
		parameters.put("cname_en", request.getParameter("cname_en"));
		parameters.put("status", request.getParameter("status"));
		parameters.put("level", City.LEVEL_PROVINCE);
		
		page = cityService.queryProvinceByCondition(parameters,page);
	}
	
	
	public String edit()  throws Exception {
		
		if (city != null && city.getId() != null && city.getId() > 0) {
			this.city = this.cityService.queryCityById(this.city.getId());
		}
		return "edit";
	}
	
	public String addCityItem() throws Exception {

		if(city == null) city = new City();
		if(city.getCitys() == null) city.setCitys(new ArrayList<City>());
		
		city.getCitys().add(new City());
		
		request.setAttribute("index", city.getCitys().size()-1);
		return "addCityItem";
	}
	
	public String save() throws Exception {
		//移除掉页面中删掉的数据
		for(int i = 0; city.getCitys() != null && i < city.getCitys().size();i++){
			if(city.getCitys().get(i) == null){
				city.getCitys().remove(i);
				i--;
			}
		}
		//保存验证页面数据自身是否重复and页面数据和数据库比对是否重复
		Result result = cityService.save(city,this.getCurrOperatorId());
		Struts2Utils.renderJson(result, headers);
		return null;
	}
	
	public String detail() throws Exception {
		if (city != null && city.getId() != null && city.getId() > 0) {
			this.city = this.cityService.queryCityById(this.city.getId());
		}
		return "detail";
	}
	
	
	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public Page<City> getPage() {
		return page;
	}

	public void setPage(Page<City> page) {
		this.page = page;
	}
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	public City getProvince() {
		return province;
	}
	public void setProvince(City province) {
		this.province = province;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	
	private String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

}