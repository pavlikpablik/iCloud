package com.manpowergroup.cn.icloud.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.ResourceService;

public class ResourceAction extends BaseAction{
	
	private static final long serialVersionUID = -7872270951525919673L;
	
	
	@Autowired
	private ResourceService resourceService;
	
	public Resource getresource() {
		return resource;
	}

	

	public void setresource(Resource resource) {
		this.resource = resource;
	}


	public List<Resource> getresourceList() {
		return resourceList;
	}


	public void setresourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}


	private Resource resource;
	   
    private List<Resource> resourceList;
    private Page<Resource> page;


	public Page<Resource> getPage() {
		return page;
	}



	public void setPage(Page<Resource> page) {
		this.page = page;
	}



	public String page() throws Exception {
		//resourceList=resourceService.getResourceList();
		queryProvinceByCondition();
		return "page";
	}
	
	
	
	
	public String edit()
	{
		request.setAttribute("max_level", resource.RESOURCE_MAX_LEVEL);
		if(resource!=null && resource.getId()!=null)
		{
			resource = resourceService.queryResourceById(resource.getId());
		}else{
			resource = new Resource();
			resource.setSourceLevel(resource.RESOURCE_DEFAULT_LEVEL);//新建菜单时默认级别为一级菜单
		}
		
		return "edit";
	}
	
	/**
	 * 保存更新后的资源信息
	 * @return
	 */
	public String save() throws Exception
	{

			Result result = resourceService.save(resource,this.getCurrOperatorId());
			Struts2Utils.renderJson(result, headers);
			//resourceList=resourceService.getResourceList();
			return null;
	}
	
	
	
	
	
	
	/**
	 * 根据层级参数然会上层级resource供用户选择
	 * @return
	 */
	public String renderresourceParentByLevel()
	{
		if(resource.getSourceLevel()==null)return null;
		int resourceLevel = resource.getSourceLevel() -1 < 0 ? 0:resource.getSourceLevel() -1;
		StringBuffer sb = new StringBuffer();
		//实际参数传过来的是当前层级，要求找的resource位于父层级
		List<Resource> presources = resourceService.getResourceByLevel(resourceLevel);
		if(resource.getId()!=null)
		{
			resource = resourceService.getResourceById(resource.getId());
			for(int i = 0 ; presources!=null && i < presources.size(); i++)
			{
				if(presources.get(i).getId().equals(resource.getParentId()))
				{
					sb.append( "<option value='"+presources.get(i).getId()+"' selected='selected'>"+presources.get(i).getRemark()+"</option>" );
					continue;
				}
				if(presources.get(i).getId()!=null && StringUtils.isNotBlank(presources.get(i).getFullName()))
				{
					sb.append( "<option  value='"+presources.get(i).getId()+"'>"+presources.get(i).getRemark()+"</option>" );
				}
			}
		}else{
			for(int i = 0 ; presources!=null && i < presources.size(); i++)
			{
				if(presources.get(i).getId()!=null && StringUtils.isNotBlank(presources.get(i).getFullName()))
				{
					sb.append( "<option value='"+presources.get(i).getId()+"'>"+presources.get(i).getFullName()+"</option>" );
				}
			}
		}
		Struts2Utils.renderHtml( sb.toString(), "encoding:UTF-8","no-cache:false" );
		return null;
	}
	
	public String changeStatus() throws Exception
	{
		if(resource.getId()==null)return null;
		Result result =resourceService.changeStatus(resource.getId());
		//Struts2Utils.renderJson(result,  "encoding:utf-8", "no-cache:false");
		//resourceList=resourceService.getResourceList();
		queryProvinceByCondition();
		return "page"; 
	}
	
	@SuppressWarnings("unchecked")
	public void queryProvinceByCondition() throws Exception{
		page = this.initPage(page);
		page = resourceService.queryProvinceByCondition(page); 
	}
	
	public String prepaging() throws Exception{
		queryProvinceByCondition();
		return "list";
	}
	
	
	
	public String queryAllTree() throws Exception{
		Long userId = Long.valueOf(request.getParameter("userId").trim());
		List<Tree> trees= resourceService.queryAllTree(userId);
		String treejosn = jsonMapper.toJson(trees);
		Struts2Utils.renderText(treejosn, headers);
		return null;
	}
	
	public String queryRoleByUser() throws Exception
	{
		Long userId = Long.valueOf(request.getParameter("userId").trim());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("userId", userId);
		parameters.put("branchId", this.getCurrBranchId());
		List<Tree> trees= resourceService.queryRoleByUser(parameters);
		String treejosn = jsonMapper.toJson(trees);
		Struts2Utils.renderText(treejosn, headers);
		return null;
	}
	
	/**
	 * 查询权限组与资源关系模型
	 * @return
	 * @throws Exception
	 */
	public String queryRoleResourceTree() throws Exception{
		Long roleId = Long.valueOf(request.getParameter("roleId").trim());
		List<Tree> trees= resourceService.queryRoleResourceTree(roleId);
		String treejosn = jsonMapper.toJson(trees);
		Struts2Utils.renderText(treejosn, headers);
		return null;
	}
	
	
	public String delete(){
		resourceService.deleteById(resource.getId());
		Struts2Utils.renderJson(ICloudDataUtil.getResult("删除成功", true), headers);
		return null;
	}
	
	

	
	
}

