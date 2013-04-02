package com.manpowergroup.cn.icloud.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.base.entity.Code;


/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author Bob
 */
@MyBatisRepositorySqlserver

public interface CodeMapper {
	
	Code get(Long id);


	List<Code> search(Map<String, Object> parameters, RowBounds rowBounds);


	void insert(Code code);

	void update(Code code);

	void delete(Long id);

	long count(Map<String, Object> parameters);

	Long maxId();
}
