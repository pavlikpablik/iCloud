package com.manpowergroup.cn.icloud.base.service;

import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.Code;

public interface CodeService {

	Page<Code> queryProvinceByCondition(Map<String, Object> parameters,Page<Code> page);
	
	
    Result delete(Long id);
    
    Code queryCodeById(Long id);
    
    Result save(Code code, Long currOperatorId);
}
