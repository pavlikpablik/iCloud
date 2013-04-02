package com.manpowergroup.cn.icloud.sys.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.sys.entity.Organ;
import com.manpowergroup.cn.icloud.sys.model.Tree;


public interface OrganService {
  
	Organ queryOrganById(Long id);
	
	
	Organ getOrganById(Long id);
	
	Result save(Organ organ,Long currOperatorId,Long currBranchId);
	
	List<Tree> queryAllTreeByBranchId(Long currBranchId);
	
	Page<Organ> queryProvinceByCondition(Map<String, Object> parameters,Page<Organ> page);
	
	Organ getOrganNameById(Long id);
	
	List<Organ> queryOrganIdsById(Long id);
}
