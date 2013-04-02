package com.manpowergroup.cn.icloud.base.mapper;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.base.entity.Item;
import com.manpowergroup.cn.icloud.base.entity.ItemType;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

@MyBatisRepositorySqlserver
public interface ItemMapper {

    int insert(Item record);

    int updateByPrimaryKey(Item record);
	
	List<Item> search(Map<String, Object> parameters, RowBounds rowBounds);
	
	long count(Map<String, Object> parameters);
	
	Item get(Long id);
	
	List<Item> findAllItem();
	
	void updateStatus(Item baseItem);
}