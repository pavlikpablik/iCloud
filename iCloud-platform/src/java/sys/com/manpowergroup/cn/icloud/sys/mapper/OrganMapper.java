package com.manpowergroup.cn.icloud.sys.mapper;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.entity.Organ;
import com.manpowergroup.cn.icloud.sys.model.Tree;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
@MyBatisRepository
public interface OrganMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organ record);

    Organ selectByPrimaryKey(Integer id);

    List<Organ> selectAll();

    int updateByPrimaryKey(Organ record);
    
    Organ get(Long id);
    
    
    Organ getOrganById(Long id);
    
    List<Organ> search(Map<String, Object> parameters,RowBounds rowBounds);
	
	long count(Map<String, Object> parameters);
	
	Organ getNameById(Long id);
	
	List<Organ> queryOrganIdsById(Long id);
	
	List<Tree> queryallOrganByBranchId(Long currBranchId);
}