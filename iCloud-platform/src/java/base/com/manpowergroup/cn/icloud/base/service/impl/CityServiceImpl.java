package com.manpowergroup.cn.icloud.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.icloud.base.dao.CityDao;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.base.mapper.CityMapper;
import com.manpowergroup.cn.icloud.base.service.CityService;


@Service
@Transactional(rollbackFor=Exception.class)
@Qualifier(value="sqlserver")
public class CityServiceImpl implements CityService {
	
	@Resource
	private CityMapper cityMapper;
	
	@Resource
	private CityDao cityDao;
	
	

	@Transactional(readOnly=true)
	public Page<City> queryProvinceByCondition(Map<String, Object> parameters,Page<City> page) {
		//分页重载search方法
		List<City> citys = cityMapper.query(parameters,ICloudDataUtil.getRowBounds(page));
		/*List<City> citys = cityMapper.query(parameters);*/
		page.setTotalCount(cityMapper.count(parameters));
		page.setResult(citys);
		return page;
	}

	/**
	 * 查询省份，包含省份下面的城市。
	 */
	@Transactional(readOnly=true)
	public City queryCityById(Long id) {
		/*City city = cityMapper.get(id);
		Map< String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parentId", id);
		city.setCitys(cityMapper.search(parameters) );*/
		return cityMapper.get(id);
//		return cityDao.get(id);
	}

	@Override
	public Result save(City city, Long currOperatorId) throws Exception {
		
		//新增
		if (city.getId()==null) {
			Integer seq = cityMapper.maxSeq();
			if (seq==null) {
				seq = City.SEQ_CITY_BASE;
			}else {
				seq += City.SEQ_CITY_BASE;
			}
			
			city.setLevel(City.LEVEL_PROVINCE);
			city.setSeq(seq);
			city.setStatus(City.STATUS_START);
			city.setCreateBy(currOperatorId);
			city.setCreateDate(new Date());
			cityMapper.insert(city);
			if (city.getCitys()!=null) {
				for (City c : city.getCitys()) {
					c.setParentId(city.getId());
					c.setLevel(City.LEVEL_CITY);
					c.setSeq(++seq);
					c.setCreateBy(currOperatorId);
					c.setCreateDate(new Date());
					cityMapper.insert(c);
				}
			}
		}
		//修改
		else {
			
			Integer seq = city.getSeq();
			city.setLevel(City.LEVEL_PROVINCE);
			city.setModifyBy(currOperatorId);
			city.setModifyDate(new Date());
			cityMapper.update(city);
			
			if (city.getCitys()!=null) {
				for (City c : city.getCitys()) {
					c.setParentId(city.getId());
					c.setSeq(++seq);
					c.setLevel(City.LEVEL_CITY);
					if (c.getId()==null) {
						c.setCreateBy(currOperatorId);
						c.setCreateDate(new Date());
						cityMapper.insert(c);
					}else {
						c.setModifyBy(currOperatorId);
						c.setModifyDate(new Date());
						cityMapper.update(c);
					}
				}
			}
		}
		Result result = new Result(); 
		result.setResult(true);
		result.setResultText("保存成功");
		return result;
	}

	public List<City> findAll() {
		return cityMapper.findAll();
	}

	public List<City> provinceList() 
	{
		List<City> list = cityMapper.getProvinceList();
		if(list == null || list.size() == 0) return null;
		return  list;
	}

	
}
