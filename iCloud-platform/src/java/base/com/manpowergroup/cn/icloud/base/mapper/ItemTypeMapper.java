package com.manpowergroup.cn.icloud.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.base.entity.ItemType;


@MyBatisRepositorySqlserver
public interface ItemTypeMapper {

	List<ItemType> query(Map<String, Object> parameters, RowBounds rowBounds);
	
	long count(Map<String, Object> parameters);
	
	ItemType get(Long id);
	
	void insert(ItemType itemType);

	void update(ItemType itemType);

	void updateStatus(ItemType itemType);
	
	List<ItemType> findAll();
	
	List<ItemType> queryItemTypeById(Long id);
}
