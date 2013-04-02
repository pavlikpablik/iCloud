package com.manpowergroup.cn.icloud.socialbenefit.service;

import java.util.List;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandSsbModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandidateModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ChargeModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ClientModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.SsbItemModel;

public interface SsbAjustService {

	Page<SsbAjust> queryCandAjustSsbByCondition(Map<String, Object> parameters,Page<SsbAjust> page);

	/**
	 * 通过or退回
	 * @param idsString
	 * @param status
	 * @throws SQLException 
	 */
	void changeStatusAll(String idsString, Integer status) throws SQLException;

	/**
	 * 查看员工社保调整信息
	 * @param id
	 * @return
	 */
	SsbAjust queryCandAjustmentById(Integer id);
	
	CandidateModel queryCandidateById(Integer candidateId);
	
	SsbAjust queryCandAjustmentByCandidateIdOrNo(Map<String, Object> parameters, String monthFee);
	
	Integer queryCandByIdOrNo(Map<String, Object> parameters);
	
	List<SsbItemModel> querySsbItemBySsbId(Integer ssbId);
	
	Result save(SsbAjust ssbAjustMent, Long currOperatorId) throws Exception;
	
	 
    SsbAjust queryCandAjustById(Integer id);
    
    List<CandSsbModel> queryCandSsbById(Integer id);
    
    ChargeModel queryChargeRuleByChargeId(Integer id);
    
    ClientModel queryClientByClientId(Integer id);
    
    Result changeStatus(SsbAjust ssbAjustMent) throws Exception;
    
    Result deleteHeadDetail(Integer id) throws Exception;
    
    CandSsbModel queryRuleId(CandSsbModel candSsb);
	
//    public void saveSsbAdjust(List<SsbAjust> headerList) throws SQLException;
    
    public void saveSsbAdjustSqlserver(List<SsbAjust> headerList) throws SQLException;

    
}
