package com.manpowergroup.cn.icloud.sys.service;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.sys.entity.Role;
import com.manpowergroup.cn.icloud.sys.model.Tree;

/**
 * 
 * @author Sundy.Sun
 * 权限组管理接口
 */
public interface RoleService {

	//通过id删除权限组信息
	int deleteByPrimaryKey(Long id);

	//新增权限组信息
    int insert(Role record);

    //通过id查询权限组信息
    Role selectByPrimaryKey(Long id);

    //查询全部权限信息
    List<Role> selectAll();

    //更新权限组信息
    int updateByPrimaryKey(Role record);

    //通过条件查询Role信息 并分页
	Page<Role> queryRoleByCondition(Map<String, Object> parameters,Page<Role> page);

	//更改权限状态
	void updateStatus(Role role, Long currOperatorId);

	//保存权限信息
	Result save(Role newRole, Long currOperatorId);
 
	//保存权限与资源信息的关系模型
	void saveRoleResource(Long roleId, String resourceIds);

	//查询权限组下面的所有员工
	List<Role> queryUserByRoleId(Long id);
	
	List<Tree> queryUserAllTree(Map<String, Object> parameters);
	
	void saveUserRole(Long roleId, String userIds);


}
