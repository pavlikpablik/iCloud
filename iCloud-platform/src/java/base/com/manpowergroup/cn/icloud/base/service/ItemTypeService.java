package com.manpowergroup.cn.icloud.base.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.ItemType;

public interface ItemTypeService {

	Page<ItemType> queryProvinceByCondition(Map<String, Object> parameters,Page<ItemType> page);
	
	
	//Result delete(Long id);
    
    ItemType queryItemTypeById(Long id);
    
    Result save(ItemType itemType, Long currOperatorId);
    
    void updateStatus(ItemType itemType, Long currOperatorId);
    
    List<ItemType> findAll();
    
    List<ItemType> queryItemTypeListById(Long id);
}
