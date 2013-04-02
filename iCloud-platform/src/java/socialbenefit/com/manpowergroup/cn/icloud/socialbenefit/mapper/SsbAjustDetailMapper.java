package com.manpowergroup.cn.icloud.socialbenefit.mapper;

import java.util.List;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;

@MyBatisRepository
public interface SsbAjustDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsbAjustDetail record);

    SsbAjustDetail selectByPrimaryKey(Integer id);

    List<SsbAjustDetail> selectAll();

    int updateByPrimaryKey(SsbAjustDetail record);
    
    void insertSsbAjustDetail(SsbAjustDetail ssbAjustDetail);
    
    List<SsbAjustDetail> querySsbDetailByAjustId(Integer id);
    
    void updateSsbAjustDetail(SsbAjustDetail ssbAjustDetail);
    
    void  deleteAjustMentDetail(Integer id);
}