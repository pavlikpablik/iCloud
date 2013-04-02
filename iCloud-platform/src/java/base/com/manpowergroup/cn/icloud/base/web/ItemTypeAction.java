package com.manpowergroup.cn.icloud.base.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.ItemType;
import com.manpowergroup.cn.icloud.base.service.ItemTypeService;

public class ItemTypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7151694218429360766L;

	@Autowired
	private ItemTypeService itemTypeService;
	
	private Page<ItemType> page;
	public Page<ItemType> getPage() {
		return page;
	}

	public void setPage(Page<ItemType> page) {
		this.page = page;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	private ItemType itemType;
	
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
		parameters.put("name", request.getParameter("name"));
		
		page = itemTypeService.queryProvinceByCondition(parameters,page);
	}
	
	public String detail()throws Exception{
		this.itemType = this.itemTypeService.queryItemTypeById(this.itemType.getId());
		return "detail";
	}
	
	
	public String edit() throws Exception {
		if (itemType != null && itemType.getId() != null && itemType.getId() > 0) {
			this.itemType = this.itemTypeService.queryItemTypeById(this.itemType.getId());
		}
		return "edit";
	}
	
	public String save()throws Exception{
		this.itemType.setStatus(itemType.BDSTATUS_START);
		Result result = this.itemTypeService.save(itemType, this.getCurrOperatorId());
		Struts2Utils.renderJson(result, headers);
		return null;
	}
	public String changeStatus() throws Exception{
		   itemTypeService.updateStatus(itemType,this.getCurrOperatorId());
		   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		   return null;
	   }
	
}
