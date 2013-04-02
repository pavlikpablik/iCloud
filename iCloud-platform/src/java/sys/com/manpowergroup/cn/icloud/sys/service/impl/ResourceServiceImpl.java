package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.mapper.ResourceMapper;
import com.manpowergroup.cn.icloud.sys.mapper.TreeMapper;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.ResourceService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private TreeMapper treeMapper;
	
	
	public Resource queryResourceById(Long id){
		return  resourceMapper.get(id);
	}
	
	public Resource getResourceById(Long id)
	{
		return resourceMapper.getResourceById(id);
	}
	
	public List< Resource > getResourceByLevel(Integer level )
	{
		return resourceMapper.getResourceByLevel(level);
	}
	
	
	public List<Resource> getResourceList(){
		return resourceMapper.getResourceList();
	}
	
	public Result save(Resource resource , Long currOperatorId){
		Result result = new Result();
		if(resource == null) 
		{
			result.setResult(false);
			result.setResultText("资源不存在，无法保存");
			return result;
		}
		
		if(resource.getId()!=null){
			List<Resource> perResource=new ArrayList<Resource>();
			List<Resource> perResource1=new ArrayList<Resource>();
			//第一层默认父ID为0
			if(resource.getParentId()==null || resource.getParentId().equals("")){
				resource.setParentId(new Long(0));
			}
			int flag=resourceMapper.queryOldSort(resource);
			//修改层级的情况
			if (flag==0){
				
				
				
			Resource pDBResource = resourceMapper.queryResourceByPid(resource.getParentId());
			
			//perResource=resourceMapper.queryByPid(resource);
			//有上层资源
			if(pDBResource != null){
				Integer step=resourceMapper.queryParentSort(resource);
				if(step==0){
					step=1;
				}else{
					step+=1;
				}
			Integer psort=pDBResource.getSort();
			Integer sort=resourceMapper.queryMaxSort();
				if(pDBResource.getSort()==0){
					if(sort==0){
						sort=Resource.RESOURCE_BASE;
					}else{
						int s=sort/Resource.RESOURCE_BASE;
						sort=s*Resource.RESOURCE_BASE+Resource.RESOURCE_BASE;
					}
				}else{
					sort=psort+step;
				}
				perResource=resourceMapper.queryByPid(resource);
			resource.setSort(sort);
			if(perResource.size()!=0 && perResource!=null){
				for(int i=0;i<perResource.size();i++){
					perResource.get(i).setSort(++sort);
					perResource.get(i).setModifyDate(new Date());
					perResource.get(i).setSourceLevel(resource.getSourceLevel()+1);
					
					perResource1=resourceMapper.queryByPid(perResource.get(i));
					if(perResource1.size()!=0 && perResource1!=null){
						for(int j=0;j<perResource1.size();j++){
							perResource1.get(j).setSort(++sort);
							perResource1.get(j).setModifyDate(new Date());
							perResource1.get(j).setSourceLevel(perResource.get(i).getSourceLevel()+1);
						}
					}
				}
			}
			resource.setRemark((StringUtils.isBlank(pDBResource.getRemark())?"":StringUtils.trimToEmpty(pDBResource.getRemark())+"->")+resource.getFullName());
			}else{
				Integer sort=resourceMapper.queryMaxSort();
				

				if(sort==null){
					sort=Resource.RESOURCE_BASE;
				}else{
					int s=sort/Resource.RESOURCE_BASE;
					sort=s*Resource.RESOURCE_BASE+Resource.RESOURCE_BASE;
				}
				
				perResource=resourceMapper.queryByPid(resource);
				resource.setSort(sort);
				if(perResource.size()!=0 && perResource!=null){
					for(int i=0;i<perResource.size();i++){
						perResource.get(i).setSort(++sort);
						perResource.get(i).setModifyDate(new Date());
						perResource.get(i).setSourceLevel(resource.getSourceLevel()+1);
						
						perResource1=resourceMapper.queryByPid(perResource.get(i));
						if(perResource1.size()!=0 && perResource1!=null){
							for(int j=0;j<perResource1.size();j++){
								perResource1.get(j).setSort(++sort);
								perResource1.get(j).setModifyDate(new Date());
								perResource1.get(j).setSourceLevel(perResource.get(i).getSourceLevel()+1);
							}
						}
					}
				}
				resource.setRemark(resource.getFullName());
			}
			//无上层资源
			//resource.setRemark(resource.getFullName());
			}else{
				Resource oldResource=resourceMapper.get(resource.getId());
				resource.setSort(oldResource.getSort());
				resource.setRemark(oldResource.getRemark());
				resource.setSourceLevel(oldResource.getSourceLevel());
				resource.setParentId(oldResource.getParentId());
				Resource pDBResource = resourceMapper.queryResourceByPid(resource.getParentId());
				if(pDBResource!=null){
				resource.setRemark((StringUtils.isBlank(pDBResource.getRemark())?"":StringUtils.trimToEmpty(pDBResource.getRemark())+"->")+resource.getFullName());
				}
			}
			resource.setStatus(1);
			//resource.setCreateBy(currOperatorId);
			resource.setCreateDate(new Date());
			//resource.setModifyBy(currOperatorId);
			resource.setModifyDate(new Date());
			resourceMapper.updateByPrimaryKey(resource);
			
			if(perResource.size()!=0 && perResource!=null){
				for(int i=0;i<perResource.size();i++){
					perResource.get(i).setRemark((StringUtils.isBlank(resource.getRemark())?"":StringUtils.trimToEmpty(resource.getRemark())+"->")+perResource.get(i).getFullName());
					resourceMapper.updateByPrimaryKey(perResource.get(i));	
					
					if(perResource1.size()!=0 && perResource1!=null){
						for(int j=0;j<perResource1.size();j++){
							perResource1.get(j).setRemark((StringUtils.isBlank(perResource.get(j).getRemark())?"":StringUtils.trimToEmpty(perResource.get(j).getRemark())+"->")+perResource1.get(j).getFullName());
							resourceMapper.updateByPrimaryKey(perResource1.get(j));	
						}
					}
					
				}
			}
			
			
			
		}else{
			
			if(resource.getParentId()==null || resource.getParentId().equals("")){
				resource.setParentId(new Long(0));
			}
			
			Resource pDBResource = resourceMapper.queryResourceByPid(resource.getParentId());
			if(pDBResource != null){
			Integer step=resourceMapper.queryParentSort(resource);
			if(step==0){
				step=1;
			}else{
				step=step+1;
			}
			Integer psort=pDBResource.getSort();
			Integer sort=resourceMapper.queryMaxSort();
				if(pDBResource.getSort()==0){
					if(sort==0){
						sort=Resource.RESOURCE_BASE;
					}else{
						int s=sort/Resource.RESOURCE_BASE;
						sort=s*Resource.RESOURCE_BASE+Resource.RESOURCE_BASE;
					}
					
					
				}else{
					sort=psort+step;
				}
				
			resource.setSort(sort);
			
			//dbMenu.setRemark((StringUtils.isBlank(pDBResource.getRemark())?"":StringUtils.trimToEmpty(pDBResource.getRemark())+"->")+resource.getFullName());
			
			resource.setRemark((StringUtils.isBlank(pDBResource.getRemark())?"":StringUtils.trimToEmpty(pDBResource.getRemark())+"->")+resource.getFullName());
			}else{
			//Integer step=1;	
			Integer sort=resourceMapper.queryMaxSort();
			

			if(sort==null){
				sort=Resource.RESOURCE_BASE;
			}else{
				int s=sort/Resource.RESOURCE_BASE;
				sort=s*Resource.RESOURCE_BASE+Resource.RESOURCE_BASE;
			}
			
			resource.setSort(sort);
			resource.setRemark(resource.getFullName());
			
			}
			
			resource.setStatus(1);
			resource.setCreateBy(currOperatorId);
			resource.setCreateDate(new Date());
			resource.setModifyBy(currOperatorId);
			resource.setModifyDate(new Date());
			resourceMapper.insert(resource);
		}
		result.setResult(true);
		result.setResultText("资源成功保存");
		//result.setT(resource);
		return result;
		
	}
	
	public Result changeStatus(Long id){
		Result result = new Result();
		Resource resource=resourceMapper.get(id);
		resource.setEnabled(resource.getEnabled().equals(Resource.STATUS_ABILITY)?Resource.STATUS_STOP:Resource.STATUS_ABILITY);
		resourceMapper.updateByPrimaryKey(resource);
		
		List<Resource> perResource=resourceMapper.queryByPid(resource);
		if(perResource.size()!=0 && perResource!=null){
			for(int i=0;i<perResource.size();i++){
				Resource pResource=resourceMapper.get(perResource.get(i).getId());
				pResource.setEnabled(pResource.getEnabled().equals(Resource.STATUS_ABILITY)?Resource.STATUS_STOP:Resource.STATUS_ABILITY);
				resourceMapper.updateByPrimaryKey(pResource);
				
				List<Resource> perResource1=resourceMapper.queryByPid(perResource.get(i));
				if(perResource1.size()!=0 && perResource1!=null){
				for(int j=0; j<perResource1.size();j++){
				Resource pResource1=resourceMapper.get(perResource1.get(j).getId());
				pResource1.setEnabled(pResource1.getEnabled().equals(Resource.STATUS_ABILITY)?Resource.STATUS_STOP:Resource.STATUS_ABILITY);
				resourceMapper.updateByPrimaryKey(pResource1);
				}
				}
			}
		}
		
		result.setResult(true);
		result.setResultText("操作成功");
		return result;
	}
	
	@Transactional(readOnly=true)
	public Page<Resource> queryProvinceByCondition(Page<Resource> page) {
		//分页重载search方法
		List<Resource> resources = resourceMapper.search(ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(resourceMapper.count());
		page.setResult(resources);
		return page;
	}

	@Override
	public List<Tree> queryAllTree(Long operatorId) {
		return treeMapper.queryallResource(operatorId);
	}

	/**
	 * 查询权限组与资源关系模型
	 */
	public List<Tree> queryRoleResourceTree(Long roleId) 
	{
		return treeMapper.queryAllResourceByRoleId(roleId);
	}

	@Override
	public void deleteById(Long id) {
		if (id!=null) {
			resourceMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<Tree> queryRoleByUser(Map<String, Object> parameters) 
	{
		return treeMapper.queryRoleByUser(parameters);
	}
	
}
