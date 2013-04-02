package com.manpowergroup.cn.icloud.base.web;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.Item;
import com.manpowergroup.cn.icloud.base.entity.ItemType;
import com.manpowergroup.cn.icloud.base.entity.TypeItem;
import com.manpowergroup.cn.icloud.base.model.BaseItemTypeEnum;
import com.manpowergroup.cn.icloud.base.service.ItemService;
import com.manpowergroup.cn.icloud.base.service.ItemTypeService;

public class ItemAction extends BaseAction {
    
    private static final long serialVersionUID = -7872270951525919673L;
    


    private Page<Item> page;
    
    
    private Item baseItem;
    
    EnumSet<BaseItemTypeEnum> typeSet;
    
    private List<ItemType> typeList;
    
    public  List<String> itemsTypeList;
    
    public List<String> getItemsTypeList() {
		return itemsTypeList;
	}

	public void setItemsTypeList(List<String> itemsTypeList) {
		this.itemsTypeList = itemsTypeList;
	}

	public List<ItemType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<ItemType> typeList) {
		this.typeList = typeList;
	}

	public Item getBaseItem() {
        return baseItem;
    }

    public void setBaseItem(Item baseItem) {
        this.baseItem = baseItem;
    }


    public EnumSet<BaseItemTypeEnum> getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(EnumSet<BaseItemTypeEnum> typeSet) {
        this.typeSet = typeSet;
    }

    public Page<Item> getPage() {
        return page;
    }

    public void setPage(Page<Item> page) {
        this.page = page;
    }
    @Autowired
    private ItemService baseItemService;
    
    @Autowired
    private ItemTypeService itemTypeService;
    
    public String page() throws Exception {
    	itemsTypeList = ItemType.getItemsTypeList();
    	typeList = itemTypeService.findAll();
        queryListByCondition();
        return "page";
    }
    
    public String prepaging() throws Exception {
        queryListByCondition();
        return "list";
    }
    
    public String query() throws Exception {
        queryListByCondition();
        return "list";
    }
    
    @SuppressWarnings("unchecked")
    public void queryListByCondition() throws Exception {

        page = this.initPage(page);
        Map<String, Object> parameters = new HashMap<String, Object>();
        //查询条件
        parameters.put("name", request.getParameter("name"));
        parameters.put("nameEn", request.getParameter("nameEn"));
        parameters.put("itemType", request.getParameter("itemType"));
        parameters.put("type", request.getParameter("type"));
        parameters.put("itemGroup", request.getParameter("itemGroup"));
        parameters.put("itemsType", request.getParameter("itemsType"));
        page = baseItemService.queryListByCondition(parameters, page);
    }
    
 
    
   /* public String save() throws Exception {
        //保存验证页面数据自身是否重复and页面数据和数据库比对是否重复
        Result result = baseItemService.save(baseItem,this.getCurrOperatorId());
        Struts2Utils.renderJson(result, headers);
        return null;
    }*/
    

    
    public String detail() throws Exception {
        if (baseItem != null && baseItem.getId() != null && baseItem.getId() > 0) {
        	baseItem = this.baseItemService.queryItemById(this.baseItem.getId());
    		typeList = this.itemTypeService.queryItemTypeListById(this.baseItem.getId());
        }
        return "detail";
    }
    
    
    /*public String edit() throws Exception {
        if (baseItem != null && baseItem.getId() != null && baseItem.getId() > 0) {
            baseItem = this.baseItemService.queryItemById(this.baseItem.getId());
        }
        typeSet = EnumSet.allOf(BaseItemTypeEnum.class);  
        return "edit";
    }*/
    
    
    public String edit() throws Exception {
		//ssbDesList = ICloudDataUtil.getSsbDesList();
		itemsTypeList = ItemType.getItemsTypeList();
		//itemsSalaryTypeList =  ICloudDataUtil.getSalaryItemsTypeList();
		if (baseItem != null && baseItem.getId() != null && baseItem.getId() > 0) {
		
			baseItem = baseItemService.queryItemById(baseItem.getId());
			String xx = baseItem.getServiceType();
			typeList = itemTypeService.findAll();
			List<TypeItem> typeItemListByItem = baseItemService.queryTypeItemByItemId(baseItem.getId());
			for(int i = 0 ; typeList != null && i < typeList.size(); i++)
			{
				for(int j = 0 ; typeItemListByItem != null && j < typeItemListByItem.size(); j++)
				{
					Long typeId = typeList.get(i).getId();
					Long typeIdByItem = typeItemListByItem.get(j).getTypeId();
					if(typeId.equals(typeIdByItem))
					{
						typeList.get(i).setIsUse("isUse");
						break;
					}
				}
			}
			
		}else{
			typeList = itemTypeService.findAll();
		}
		return "edit";
	}
    
    public String save() throws Exception {
		/*if(this.item == null){
			this.logger.error("没有项目元素可以保存");
			Struts2Utils.renderText("没有项目元素可以保存", "encoding:utf-8", "no-cache:false");
			return null;
		}*/
//		item.setStatus(1);
		//保存验证页面数据和数据库比对是否重复
//		String result = "";
//		result = this.itemService.validateItem(item);
//		if(StringUtils.isNotBlank(result))
//		{
//			Struts2Utils.renderText(result, "encoding:utf-8", "no-cache:false");
//			return null;
//		}
		String[] typeIds = request.getParameterValues("typeIds");
	    Integer[] typeIntIds = null;
	    if(typeIds!=null)
	    {
	    	typeIntIds = new Integer[typeIds.length];
	       for(int i = 0; typeIds!=null && i < typeIds.length; i++)
	       {
	    	   typeIntIds[i] = new Integer(typeIds[i]);
	       }
	    }
		Result result = baseItemService.save(baseItem, this.getCurrOperatorId(),typeIntIds);
		 Struts2Utils.renderJson(result, headers);
		return null;
	}
    
    
    public String changeStatus() throws Exception{
		   baseItemService.updateStatus(baseItem,this.getCurrOperatorId());
		   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		   return null;
	   }
}
