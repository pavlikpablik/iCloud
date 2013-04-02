package com.manpowergroup.cn.icloud.base.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.Code;
import com.manpowergroup.cn.icloud.base.service.CodeService;


public class CodeAction extends BaseAction{

	private static final long serialVersionUID = -7872270951525919673L;
	
	public Page<Code> getPage() {
		return page;
	}


	public void setPage(Page<Code> page) {
		this.page = page;
	}


	public List<Code> getCodeList() {
		return codeList;
	}


	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}


	public Code getCode() {
		return code;
	}


	public void setCode(Code code) {
		this.code = code;
	}


	private Page<Code> page;
	private List<Code> codeList;
	private Code code;
	//private Code province;
	
	@Autowired
	private CodeService codeService;
	
	public String page() throws Exception {
		queryProvinceByCondition();
		return "page";
	}
	
	
	public String query() throws Exception{
		queryProvinceByCondition();
		return "list"; 
	}
	
	public String delete() throws Exception{
		Long id=code.getId();
		Result result = codeService.delete(id);
		queryProvinceByCondition();
		//Struts2Utils.renderJson(result, headers);
		return "page"; 
	}
	
	public String detail()throws Exception{
		this.code = this.codeService.queryCodeById(this.code.getId());
		return "detail";
	}
	
	
	public String edit() throws Exception {
		if (code != null && code.getId() != null && code.getId() > 0) {
			this.code = this.codeService.queryCodeById(this.code.getId());
		}
		return "edit";
	}
	
	public String save()throws Exception{
		//保存验证页面数据和数据库比对是否重复
//		String result = "";
//		result = this.baseCodeService.validateBaseCode(baseCode);
//		if(StringUtils.isNotBlank(result))
//		{
//			Struts2Utils.renderText(result, "encoding:utf-8", "no-cache:false");
//			return null;
//		}
		this.code.setBdStatus(code.BDSTATUS_START);
		Result result = this.codeService.save(code, this.getCurrOperatorId());
		Struts2Utils.renderJson(result, headers);
		return null;
	}
	
	public String prepaging() throws Exception{
		queryProvinceByCondition();
		return "list";
	}
	
	@SuppressWarnings("unchecked")
	public void queryProvinceByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("bdname", request.getParameter("bdname"));
		parameters.put("bdtype", request.getParameter("bdtype"));
		
		
		page = codeService.queryProvinceByCondition(parameters,page);
	}
	
}
