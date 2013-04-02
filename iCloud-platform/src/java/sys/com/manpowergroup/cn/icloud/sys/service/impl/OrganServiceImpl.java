package com.manpowergroup.cn.icloud.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.sys.entity.Organ;
import com.manpowergroup.cn.icloud.sys.mapper.OrganMapper;
import com.manpowergroup.cn.icloud.sys.model.Tree;
import com.manpowergroup.cn.icloud.sys.service.OrganService;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrganServiceImpl implements OrganService{

	@Autowired
	private OrganMapper organMapper;
	
	
	public Organ queryOrganById(Long id){
		return  organMapper.get(id);
	}
	
	
	public Organ getOrganById(Long id)
	{
		return organMapper.getOrganById(id);
	}
	
	
	
	 //组织架构的新建或保存
	public Result save(Organ organ , Long currOperatorId,Long currBranchId){
		Result result = new Result();
		
		//更新
		if(organ.getId()!=null){
			
			if(organ.getParentId()==null){
				organ.setParentId(new Long(0));
			}
			Organ tempOrgan=organMapper.get(organ.getId());
			if(tempOrgan==null)return null;
				organ.setBranchId(tempOrgan.getBranchId());
				organ.setCreateBy(tempOrgan.getCreateBy());
				organ.setCreateDate(tempOrgan.getCreateDate());
				organ.setStatus(tempOrgan.getStatus());
				organ.setModifyBy(currOperatorId);
				organ.setModifyDate(new Date());
			organMapper.updateByPrimaryKey(organ);
		}else{//新建
		//如果当前级别为1级,父ID为0
		if(organ.getParentId()==null || organ.getParentId().equals("")){
			organ.setParentId(new Long(0));
		}
		organ.setBranchId(currBranchId);
		organ.setCreateBy(currOperatorId);
		organ.setCreateDate(new Date());
		organMapper.insert(organ);
		     }
		result.setResult(true);
		result.setResultText("成功保存");
		
		return result;
	}
	
	 //具有分页功能的查询
	@Transactional(readOnly=true)
	public Page<Organ> queryProvinceByCondition(Map<String, Object> parameters,Page<Organ> page) {
		//分页重载search方法
		List<Organ> organ = organMapper.search(parameters,new RowBounds(page.getFirst()-1, page.getPageSize()));
		page.setTotalCount(organMapper.count(parameters));
		page.setResult(organ);
		return page;
	}
	
	
	
	//按公司查询组织架构并构建树形结构
	
	@Override
	public List<Tree> queryAllTreeByBranchId(Long currBranchId) {
		//return treeMapper.queryallOrganByBranchId(currBranchId);
		return organMapper.queryallOrganByBranchId(currBranchId);
	}	
	
	public Organ getOrganNameById(Long id){
		return organMapper.getNameById(id);
	}
	
	public List<Organ>queryOrganIdsById(Long id){
		return organMapper.queryOrganIdsById(id);
	}
}
