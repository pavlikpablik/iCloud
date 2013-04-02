package com.manpowergroup.cn.icloud.base.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.manpowergroup.cn.icloud.base.entity.City;

@Repository
public class CityDaoImpl  implements CityDao {

	@Autowired
	@Qualifier(value="sqlserver")
	private SqlSessionTemplate sqlSession;  
	
	@Override
	public City get(Long id) {
	 	/*Connection conn = this.getSqlSession().getConnection();*/
		return sqlSession.selectOne("com.manpowergroup.cn.icloud.base.mapper.CityMapper.get",id);
	}

}
