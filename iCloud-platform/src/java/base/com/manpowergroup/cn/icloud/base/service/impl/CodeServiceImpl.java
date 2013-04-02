package com.manpowergroup.cn.icloud.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.icloud.base.entity.Code;
import com.manpowergroup.cn.icloud.base.mapper.CodeMapper;
import com.manpowergroup.cn.icloud.base.service.CodeService;


@Service
@Transactional(rollbackFor=Exception.class)
@Qualifier(value="sqlserver")
public class CodeServiceImpl implements CodeService{
	@Autowired
	private CodeMapper codeMapper;
	
	@Transactional(readOnly=true)
	public Page<Code> queryProvinceByCondition(Map<String, Object> parameters,Page<Code> page) {
		//分页重载search方法
		List<Code> codes = codeMapper.search(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(codeMapper.count(parameters));
		page.setResult(codes);
		return page;
	}
	
	public Result  delete(Long id){
		codeMapper.delete(id);
		Result result = new Result(); 
		result.setResult(true);
		result.setResultText("操作成功");
		return result;
	}
	
	
	public Code queryCodeById(Long id){
		return codeMapper.get(id);
	}
		
	public Result save(Code code, Long currOperatorId) {
		Result result = new Result();
		if(code == null) {
			result.setResult(false);
			result.setResultText("Code 键值不存在，无法保存");
			//result.setT(code);
			return result;
		}
		//更新
		if(code.getId() != null){
			//code.setCreateBy(currOperatorId);
			//code.setCreateDate(new Date());
			code.setModifyBy(currOperatorId);
			code.setModifyDate(new Date());
			codeMapper.update(code);
			
			result.setResultText("键值对象已成功保存");
		}else{
			code.setCreateBy(currOperatorId);
			code.setCreateDate(new Date());
			//code.setModifyBy(currOperatorId);
			//code.setModifyDate(new Date());
			codeMapper.insert(code);
			
			result.setResultText("键值对象已成功保存");
		}
		result.setResult(true);
		//result.setT(code);
		return result;
	
	}
}
