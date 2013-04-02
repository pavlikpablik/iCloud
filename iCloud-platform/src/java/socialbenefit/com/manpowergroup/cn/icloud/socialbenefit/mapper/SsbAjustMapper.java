package com.manpowergroup.cn.icloud.socialbenefit.mapper;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

@MyBatisRepository
public interface SsbAjustMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SsbAjust record);

    SsbAjust selectByPrimaryKey(Long id);

    List<SsbAjust> selectAll();

    int updateByPrimaryKey(SsbAjust record);
    
    List<SsbAjust> queryCandAjustSsbByCondition(Map<String, Object> parameters, RowBounds rowBounds);
    
    long count(Map<String, Object> parameters);
    
    void insertSsbAjustMent(SsbAjust ssbAjust);

	SsbAjust querySsbAjust(Integer id);
	
	SsbAjust queryByPrimaryKey(Integer id);
	
	void changeStatusAll(Map<String, Object> map);
	
	void updateSsbAjustMentById(SsbAjust ssbAjust);
	
	void updateStatus(SsbAjust ssbAjustMent);
	
	void deleteAjustMent(Integer id);
}

