package com.manpowergroup.cn.icloud.base.mapper;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author Jack
 */
@MyBatisRepositorySqlserver
public interface CityMapper {

	City get(Long id);

	City selectCitysByProvince(Long id);

	List<City> query(Map<String, Object> parameters, RowBounds rowBounds);

	List<City> query(Map<String, Object> parameters);

	void insert(City city);

	void update(City city);

	void delete(Long id);

	long count(Map<String, Object> parameters);

	Integer maxSeq();

	List<City> findAll();

	/**
	 * 获取省份list
	 * @return
	 */
	List<City> getProvinceList();

	/**
	 * 通过省份id获取城市列表
	 * @param pervinceId
	 * @param status
	 * @return
	 */
	List<City> getCityList(Integer pervinceId);

	/**
	 * 获取城市信息
	 * @param string
	 * @param id
	 * @param b
	 * @return
	 */
	City findUniqueBy(String string, Integer id, boolean b);
	
	City selectProvinceByCityId(Long parentId);
}
