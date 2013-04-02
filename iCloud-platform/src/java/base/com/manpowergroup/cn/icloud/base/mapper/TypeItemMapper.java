package com.manpowergroup.cn.icloud.base.mapper;

import java.util.List;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.base.entity.TypeItem;

@MyBatisRepositorySqlserver
public interface TypeItemMapper {

	List<TypeItem> queryTypeItem(Long id);
	
	void deleteTypeItem(Long id);
	
	int insert(TypeItem record);
	
}
