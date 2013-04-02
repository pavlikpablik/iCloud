package com.manpowergroup.cn.icloud.sys.mapper;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.entity.Branch;
import java.util.List;

@MyBatisRepository
public interface BranchMapper {
    int deleteByPrimaryKey(Integer id);

    void  insert(Branch record);

    Branch selectByPrimaryKey(Integer id);

    List<Branch> selectAll();

    int updateByPrimaryKey(Branch record);
    
    Branch selectUserByVendorId(Long vendorId);
}