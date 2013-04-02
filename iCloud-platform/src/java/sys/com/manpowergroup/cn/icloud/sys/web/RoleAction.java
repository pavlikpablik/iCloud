package com.manpowergroup.cn.icloud.sys.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.entity.Role;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.RoleService;

/**
 * 
 * @author Sundy.Sun
 * 权限组管理模块
 */
public class RoleAction extends BaseAction{
	
	private static final long serialVersionUID = 213461508804142509L;
	
	@Autowired
    private RoleService roleService;
	
	private Role role;
	
    private Page<Role> page;
    
	private List<Role> roleList;
	
	private List<Role> userList;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public List<Role> getUserList() {
		return userList;
	}

	public void setUserList(List<Role> userList) {
		this.userList = userList;
	}

	/**
	 * 页面菜单跳转接受方法
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception 
	{
		queryRoleByCondition();
		return "page";
	}
	
	/**
	 * 详细页面跳转方法
	 * @return
	 * @throws Exception
	 */
	public String prepaging() throws Exception
	{
		queryRoleByCondition();
		return "list";
	}
	
	/**
	 * 查询跳转方法
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception
	{
		queryRoleByCondition();
		return "list"; 
	}
	
	/*
	 * 查询方法
	 */
	public void queryRoleByCondition() throws Exception
	{
		page = this.initPage(page);
		Map<String, Object> parameters = new HashMap<String, Object>();
		//查询条件
		parameters.put("name", request.getParameter("name"));
		parameters.put("enabled", request.getParameter("enabled"));
		parameters.put("branchId", this.getCurrBranchId());
		page = roleService.queryRoleByCondition(parameters,page);
	}
	
	/**
	 * 更新权限状态
	 * @return
	 * @throws Exception
	 */
	public String updateStatus() throws Exception
	{
		   roleService.updateStatus(role,this.getCurrOperatorId());
		   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		   return null;
	}
	
	/**
	 * 新增或修改权限
	 * @return
	 * @throws Exception
	 */
	public String edit()  throws Exception 
	{
		
		if (role != null && role.getId() != null && role.getId() > 0) {
			role = this.roleService.selectByPrimaryKey(this.role.getId());
		}
		return "edit";
	}
	
	/**
	 * 权限信息详细页面
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception 
	{
		if (role != null && role.getId() != null && role.getId() > 0) {
			this.role = this.roleService.selectByPrimaryKey(this.role.getId());
			userList = this.roleService.queryUserByRoleId(role.getId());
			
		}
		return "detail";
	}
	
	/**
	 * 保存权限信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception
	{
       Role newRole = new Role();
       newRole.setName(role.getName());
       newRole.setSort(role.getSort());
	   newRole.setEnabled(role.getEnabled());
	   newRole.setModifyBy(this.getCurrOperatorId());
	   newRole.setModifyDate(new Date());
	   newRole.setRemark(role.getRemark());
	   newRole.setStatus(role.getStatus());
	   newRole.setBranchId(getCurrBranchId());
	   newRole.setCreateBy(role.getCreateBy());
	   newRole.setModifyBy(role.getModifyBy());
       if(role.getId()!=null)
       {
    	   newRole.setId(role.getId());
       }else{
    	   newRole.setStatus(0);
    	   newRole.setCreateBy(getCurrOperatorId());
    	   newRole.setCreateDate(new Date());
       }
       Result result = roleService.save(newRole,this.getCurrOperatorId());
       Struts2Utils.renderJson(result, headers);
       return null;
	       
	}
	
	/**
	 * 创建权限与资源信息的关系模型
	 * @return
	 * @throws Exception
	 */
	public String authzAssign() throws Exception 
	{
	   if (role != null && role.getId() != null && role.getId() > 0) {
		   this.role = this.roleService.selectByPrimaryKey(this.role.getId());
	   }
	   return "authzAssign";
	}
	
	/**
	 * 保存权限与资源信息的关系模型
	 * @return
	 * @throws Exception
	 */
	public String saveRoleResource() throws Exception 
	{
	   Long roleId = Long.valueOf(request.getParameter("roleId").trim());
	   String resourceIds = request.getParameter("rsourceIds").trim();
	   this.roleService.saveRoleResource(roleId,resourceIds);
	   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(), headers);
	   return null;
	}

	/**
	 * 用户页面跳转用来展示树形结构
	 * @return
	 * @throws Exception
	 */
	public String user() throws Exception{
		return "user";
	}
	
	/**
	 * 查找用户信息用来构建树形结构
	 * @return
	 * @throws Exception
	 */
	public String queryUserAllTree() throws Exception{
		//Long userId = Long.valueOf(request.getParameter("userId").trim());
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		//查询条件
		parameters.put("roleId", role.getId());
		parameters.put("branchId", this.getCurrBranchId());
		
		
		List<Tree> trees= roleService.queryUserAllTree(parameters);
		String treejosn = jsonMapper.toJson(trees);
		Struts2Utils.renderText(treejosn, headers);
		return null;
	}
	
	/**
	 * 保存或修改组权限下面的用户信息
	 * @return
	 * @throws Exception
	 */
	public String saveUserRole() throws Exception {
		   Long roleId = Long.valueOf(request.getParameter("roleId").trim());
		   String userIds = request.getParameter("userIds").trim();
		   this.roleService.saveUserRole(roleId, userIds);
		   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(), headers);
		   return null;
	   }
	       
}

