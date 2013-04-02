package com.manpowergroup.cn.icloud.sys.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.model.Tree;

public interface ResourceService {

	Resource queryResourceById(Long id);

	Result save(Resource resource,Long currOperatorId);
	
	List< Resource > getResourceByLevel(Integer level );
	
	Resource getResourceById(Long id);
	
	List<Resource> getResourceList();
	
	Result changeStatus(Long id);
	
	Page<Resource> queryProvinceByCondition(Page<Resource> page);

	List<Tree> queryAllTree(Long operatorId);

	//查询权限组与资源关系模型
	List<Tree> queryRoleResourceTree(Long roleId);

	void deleteById(Long id) ;

	List<Tree> queryRoleByUser(Map<String, Object> parameters);

}
