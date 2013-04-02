package com.manpowergroup.cn.icloud.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.base.entity.Item;
import com.manpowergroup.cn.icloud.base.entity.ItemType;
import com.manpowergroup.cn.icloud.base.entity.TypeItem;
import com.manpowergroup.cn.icloud.base.mapper.ItemMapper;
import com.manpowergroup.cn.icloud.base.mapper.TypeItemMapper;
import com.manpowergroup.cn.icloud.base.service.ItemService;

@Service
@Transactional(rollbackFor = Exception.class)
@Qualifier(value="sqlserver")
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemMapper baseItemMapper;
    
    @Autowired
    private TypeItemMapper typeItemMapper;

    @Transactional(readOnly = true)
    public Page<Item> queryListByCondition(Map<String, Object> parameters,
            Page<Item> page) {
        //分页重载search方法
        List<Item> baseItems = baseItemMapper.search(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(baseItemMapper.count(parameters));
		page.setResult(baseItems);
        return page;
    }
    
    @Override
    public Result save(Item baseItem, Long currOperatorId) throws Exception {
        
        //新增
        if (baseItem.getId()==null) {
            baseItem.setStatus(Item.BaseItemStatus.STATUS_START.getValue());
            baseItem.setCreateDt(new Date());
         //   baseItem.setCreateBy(currOperatorId.toString());
            baseItemMapper.insert(baseItem);
        }
        //修改
        else {
            baseItemMapper.updateByPrimaryKey(baseItem);
        }
        Result result = new Result(); 
        result.setResult(true);
        result.setResultText("保存成功");
        return result;
    }
    
  
    public Result save(Item item, Long currOperatorId, Integer[] ids) {
		
    	 Result result = new Result(); 
		if(item == null){
			result.setResult(false);
			result.setResultText("Item 项目元素不存在，无法保存");
			return result;
			//throw new RuntimeException("");
		}
		//更新
		if(item.getId() != null){
			Item dbitem =baseItemMapper.get(item.getId());
			dbitem.setName(StringUtils.trim(item.getName()));
			dbitem.setNameEn(StringUtils.trim(item.getNameEn()));
			dbitem.setDescribe(StringUtils.trim(item.getDescribe()));
			dbitem.setRemark(StringUtils.trim(item.getRemark()));
//			dbitem.setStatus(item.getStatus());
			dbitem.setModifyBy(currOperatorId);
			dbitem.setModifyDate(new Date());
			dbitem.setSeq(item.getSeq());
			dbitem.setPersonTax(item.getPersonTax());
			dbitem.setCompanyTax(item.getCompanyTax());
			dbitem.setIsNegative(item.getIsNegative());
			dbitem.setIsCompanyNegative(item.getIsCompanyNegative());
			dbitem.setType(item.getType());
			dbitem.setItemGroup(item.getItemGroup());
			dbitem.setTaxAble(item.getTaxAble());
			dbitem.setInOrOut(item.getInOrOut());
			dbitem.setServiceType(item.getServiceType());
			dbitem.setItemType(item.getItemType());
			//add
			dbitem.setIsPersonAmount(item.getIsPersonAmount());
			dbitem.setIsCompanyAmount(item.getIsCompanyAmount());
			dbitem.setIsPersonPay(item.getIsPersonPay());
			dbitem.setIsCompanyPay(item.getIsCompanyPay());
			dbitem.setTaxProvideBefore(item.getTaxProvideBefore());
			dbitem.setTaxDeductAfter(item.getTaxDeductAfter());
			
			
			baseItemMapper.updateByPrimaryKey(dbitem);
			result.setResultText("Item 项目元素已成功保存");
			
		}else{
			item.setCreateBy(currOperatorId);
			item.setCreateDate(new Date());
			item.setModifyBy(currOperatorId);
			item.setModifyDate(new Date());
			item.setStatus(item.STATUS_ABILITY1);
			baseItemMapper.insert(item);
			result.setResultText("Item 项目元素已成功保存");
		}
		
		result.setResult(true);
		//result.setT(item);
		/**************将item放到type中****************/
		this.saveItemToType(item, ids);
		return result;
	}
    
    
    public void saveItemToType(Item item, Integer[] ids)
	{
		if(item.getId()==null)return;
		List<TypeItem> typeItemList = typeItemMapper.queryTypeItem(item.getId());
		for(int i = 0 ; typeItemList != null && i < typeItemList.size(); i++)
		{
			typeItemMapper.deleteTypeItem(typeItemList.get(i).getId());
		}
		for(int i = 0 ; ids != null && i < ids.length; i++)
		{
			
			TypeItem newTypeItem = new TypeItem();
			newTypeItem.setItemId(item.getId());
			newTypeItem.setTypeId(ids[i].longValue());
			newTypeItem.setStatus(item.STATUS_ABILITY1);
			newTypeItem.setCreateBy(item.getCreateBy());
			newTypeItem.setCreateDate(new Date());
			typeItemMapper.insert(newTypeItem);
		}
	}
    
    
    
    
    
    
    
    
    
    

	@Override
	public List<Item> findAll() {
		return baseItemMapper.findAllItem();
	}
	
	public Item queryItemById(Long id){
		return baseItemMapper.get(id);
	}
    
	
	public List<TypeItem> queryTypeItemByItemId(Long id){
		return typeItemMapper.queryTypeItem(id);
	}
	
	public void updateStatus(Item baseItem,Long currOperatorId) {
		baseItemMapper.updateStatus(baseItem);
	}
}
