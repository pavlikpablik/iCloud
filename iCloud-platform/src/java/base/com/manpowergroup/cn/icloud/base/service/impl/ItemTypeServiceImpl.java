package com.manpowergroup.cn.icloud.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.icloud.base.entity.ItemType;
import com.manpowergroup.cn.icloud.base.mapper.ItemTypeMapper;
import com.manpowergroup.cn.icloud.base.service.ItemTypeService;

@Service
@Transactional(rollbackFor=Exception.class)
@Qualifier(value="sqlserver")
public class ItemTypeServiceImpl implements ItemTypeService{

	@Resource
	private ItemTypeMapper itemTypeMapper;
	
	@Transactional(readOnly=true)
	public Page<ItemType> queryProvinceByCondition(Map<String, Object> parameters,Page<ItemType> page) {
		//分页重载search方法
		List<ItemType> types = itemTypeMapper.query(parameters,ICloudDataUtil.getRowBounds(page));
		/*List<City> citys = cityMapper.query(parameters);*/
		page.setTotalCount(itemTypeMapper.count(parameters));
		page.setResult(types);
		return page;
	}
	
	public ItemType queryItemTypeById(Long id){
		return itemTypeMapper.get(id);
	}
	
	
	public Result save(ItemType itemType, Long currOperatorId) {
		Result result = new Result();
		if(itemType == null) {
			result.setResult(false);
			result.setResultText(" 项目元素类型不存在，无法保存");
			//result.setT(code);
			return result;
		}
		//更新
		if(itemType.getId() != null){
			//code.setCreateBy(currOperatorId);
			//code.setCreateDate(new Date());
			itemType.setModifyBy(currOperatorId);
			itemType.setModifyDate(new Date());
			itemTypeMapper.update(itemType);
			
			result.setResultText("项目元素类型已成功保存");
		}else{
			itemType.setCreateBy(currOperatorId);
			itemType.setCreateDate(new Date());
			//itemType.setId(new Long(100));
			//code.setModifyBy(currOperatorId);
			//code.setModifyDate(new Date());
			itemTypeMapper.insert(itemType);
			
			result.setResultText("项目元素类型已成功保存");
		}
		result.setResult(true);
		//result.setT(code);
		return result;
	
	}
	
	public void updateStatus(ItemType itemType,Long currOperatorId) {
		itemTypeMapper.updateStatus(itemType);
	}
	
	public List<ItemType> findAll()
	{
		return itemTypeMapper.findAll();
	}
	
	public List<ItemType>queryItemTypeListById(Long id)
	{
		return itemTypeMapper.queryItemTypeById(id);
	}
}
