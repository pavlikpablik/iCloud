package com.manpowergroup.cn.icloud.sys.mapper;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.model.Tree;

@MyBatisRepository
public interface TreeMapper {
	
	List<Tree> queryallResource(Long operatorId);
	
	List<Tree> queryallUserByBranchId(Map<String, Object> parameters);

	//查询权限组与资源的关系模型
	List<Tree> queryAllResourceByRoleId(Long roleId);

	List<Tree> queryRoleByUser(Map<String, Object> parameters);
}
