package com.manpowergroup.cn.icloud.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepository;
import com.manpowergroup.cn.icloud.sys.entity.Role;
import com.manpowergroup.cn.icloud.sys.entity.RoleResource;
import com.manpowergroup.cn.icloud.sys.entity.UserRole;
@MyBatisRepository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    
    void deleteUserRole(Long roleId);
    
    //成批插入用户
	void batchInsertUserRole(List<UserRole> list);

	List<Role> query(Map<String, Object> parameters, RowBounds rowBounds);

	long count(Map<String, Object> parameters);

	void updateStatus(Role role);

	void deleteRoleResource(Long roleId);
	
	void deleteRoleByUserId(Long userId);

	void batchInsertRoleResources(List<RoleResource> list);

	Role selectRoleByName(Map<String, Object> parameters);

	List<Role> queryUserByRoleId(Map<String, Object> parameters);
}