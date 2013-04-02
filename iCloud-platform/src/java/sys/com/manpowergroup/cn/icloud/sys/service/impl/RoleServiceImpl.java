package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.sys.entity.Role;
import com.manpowergroup.cn.icloud.sys.entity.RoleResource;
import com.manpowergroup.cn.icloud.sys.entity.UserRole;
import com.manpowergroup.cn.icloud.sys.mapper.RoleMapper;
import com.manpowergroup.cn.icloud.sys.mapper.TreeMapper;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.RoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
	private TreeMapper treeMapper;

    //通过id删除权限组信息
	public int deleteByPrimaryKey(Long id) 
	{
		return roleMapper.deleteByPrimaryKey(id);
	}

	//新增权限组信息
	public int insert(Role record) 
	{
		return roleMapper.insert(record);
	}

	//通过id查询权限组信息
	@Transactional(readOnly=true)
	public Role selectByPrimaryKey(Long id) 
	{
		return roleMapper.selectByPrimaryKey(id);
	}

	//查询全部权限信息
	@Transactional(readOnly=true)
	public List<Role> selectAll() 
	{
		return roleMapper.selectAll();
	}

	//更新权限组信息
	public int updateByPrimaryKey(Role record) 
	{
		return roleMapper.updateByPrimaryKey(record);
	}

	//通过条件查询Role信息 并分页
	public Page<Role> queryRoleByCondition(Map<String, Object> parameters,Page<Role> page) 
	{
		List<Role> roles = roleMapper.query(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(roleMapper.count(parameters));
		page.setResult(roles);
		return page;
	}

	//更改权限状态
	public void updateStatus(Role role, Long currOperatorId) 
	{
		roleMapper.updateStatus(role);
	}

	//保存权限信息
	public Result save(Role newRole, Long currOperatorId)
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		//查询条件
		parameters.put("name", newRole.getName());
		parameters.put("branchId", newRole.getBranchId());
		if(newRole.getId()==null&&StringUtils.isNotBlank(newRole.getName())
	    		&&roleMapper.selectRoleByName(parameters)!=null)
		{
            return ICloudDataUtil.getResult("该权限名已经被注册。", false);
        }
		if(newRole.getId() == null)
		{
			roleMapper.insert(newRole);
		}else{
			roleMapper.updateByPrimaryKey(newRole);
		}
		return ICloudDataUtil.getResult("保存成功", true);
	}

	//保存权限与资源信息的关系模型
	public void saveRoleResource(Long roleId, String resourceIds) 
	{
		if (roleId==null) 
		{
			throw new RuntimeException("权限组信息不存在，请重试");
		}
		
		List<RoleResource> list = Lists.newArrayList();
		if (StringUtils.isNotBlank(resourceIds)) {
			String[] array = StringUtils.split(resourceIds,",");
			
			for (String r : array) {
				if (StringUtils.isBlank(r)) {
					continue;
				}
				RoleResource rr = new RoleResource();
				rr.setResourceId(Long.valueOf(r));
				rr.setRoleId(roleId);
				list.add(rr);
			}
		}
		roleMapper.deleteRoleResource(roleId);
		if (list!=null && list.size()>0) {
			roleMapper.batchInsertRoleResources(list);
		}
	}

	@Override
	public List<Role> queryUserByRoleId(Long id) 
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("roleId", id);
		return roleMapper.queryUserByRoleId(parameters);
	}
    
	//保存权限下的用户信息
	public void saveUserRole(Long roleId, String userIds){
		if (roleId==null) {
			throw new RuntimeException("组权限信息不存在，请重试");
		}
		List<UserRole> list=Lists.newArrayList();
		
		if (StringUtils.isNotBlank(userIds)) {
			String[] array = StringUtils.split(userIds,",");
			
			for (String r : array) {
				if (StringUtils.isBlank(r)) {
					continue;
				}
				UserRole ur=new UserRole();
				
				ur.setUserId(Long.valueOf(r));
				ur.setRoleId(roleId);
				list.add(ur);
			}
		}
		roleMapper.deleteUserRole(roleId);
		if (list!=null && list.size()>0) {
			roleMapper.batchInsertUserRole(list);
		}
	}
	
	//查询所有用户
	@Override
	public List<Tree> queryUserAllTree(Map<String, Object> parameters) {
		return treeMapper.queryallUserByBranchId(parameters);
	}
}
