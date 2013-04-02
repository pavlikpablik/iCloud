package com.manpowergroup.cn.icloud.base.service;


import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.City;

public interface CityService {
	
	Page<City> queryProvinceByCondition(Map<String, Object> parameters,Page<City> page);

	City queryCityById(Long id);

	Result save(City city, Long currOperatorId) throws Exception;

	List<City> findAll();
	
	/**
	 * 获取省list列表
	 * @return
	 */
	List<City> provinceList();


}
