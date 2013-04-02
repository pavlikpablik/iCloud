package com.manpowergroup.cn.icloud.socialbenefit.mapper;

import java.util.List;
import java.util.Map;

import com.manpowergroup.cn.core.orm.myBatis.MyBatisRepositorySqlserver;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandSsbModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandidateModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ChargeModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ClientModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.SsbItemModel;

/**
 * 
 * @author bob.chen
 *
 */
@MyBatisRepositorySqlserver
public interface SsbDataFromSqlServerMapper { 

	CandidateModel queryCandidateById(Integer candidateId);
	
	List<CandSsbModel> queryCandSsbByCandId(Integer candidateId);
	
	ChargeModel queryChargeRuleByChargeId(Integer chargeId);
	
	ClientModel queryClientByClientId(Integer clientId);
	
	List<SsbItemModel> querySsbItemBySsbId(Integer ssbId);
	
	CandSsbModel queryRuleId(CandSsbModel candSsb);
	
	CandidateModel queryCandidateByIdOrNo(Map<String, Object> parameters);
	
	Integer queryCompanyUnderByChargeId(Integer ruleId);
	
	 int insertChargeAjust(SsbAjust SsbAjust);

	 int insertChargeAjustDetail(List<SsbAjustDetail> list);
}
