package com.manpowergroup.cn.icloud.sys.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
@MyBatisRepository
public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectAll();

    int updateByPrimaryKey(Resource record);
    
    Resource get(Long id);
	
	//void insert(Resource reource);
	
	List<Resource> getResourceByLevel(Integer level);
	
	Resource getResourceById(Long id);
	
	List<Resource> getResourceList();
	
	List<Resource> queryByPid(Resource resource);
	
	Resource queryResourceByPid(Long pid);
	
	Integer queryMaxSort();
	
	int queryParentSort(Resource resource);
	
	int queryOldSort(Resource resource);
	
	List<Resource> search(RowBounds rowBounds);
	
	long count();
}