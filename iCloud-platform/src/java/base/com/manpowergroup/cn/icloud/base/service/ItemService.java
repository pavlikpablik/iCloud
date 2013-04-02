package com.manpowergroup.cn.icloud.base.service;


import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.Item;
import com.manpowergroup.cn.icloud.base.entity.ItemType;
import com.manpowergroup.cn.icloud.base.entity.TypeItem;

public interface ItemService {
	
	Page<Item> queryListByCondition(Map<String, Object> parameters,Page<Item> page);

    public Result save(Item baseItem, Long currOperatorId) throws Exception ;
    
    public Result save(Item baseItem, Long currOperatorId,Integer[] ids) throws Exception ;

	List<Item> findAll();
	
	Item queryItemById(Long id);
    
    public List<TypeItem> queryTypeItemByItemId(Long id);
    
    void updateStatus(Item baseItem, Long currOperatorId);
    
}
