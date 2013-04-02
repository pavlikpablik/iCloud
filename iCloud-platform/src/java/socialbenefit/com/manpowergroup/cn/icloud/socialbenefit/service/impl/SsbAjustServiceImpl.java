package com.manpowergroup.cn.icloud.socialbenefit.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.DateUtil;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.socialbenefit.dao.SsbAdjustDao;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;
import com.manpowergroup.cn.icloud.socialbenefit.mapper.SsbAjustDetailMapper;
import com.manpowergroup.cn.icloud.socialbenefit.mapper.SsbAjustMapper;
import com.manpowergroup.cn.icloud.socialbenefit.mapper.SsbDataFromSqlServerMapper;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandSsbModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandidateModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ChargeModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ClientModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.SsbItemModel;
import com.manpowergroup.cn.icloud.socialbenefit.service.SsbAjustService;
import com.manpowergroup.cn.icloud.socialbenefit.utils.SocialBenefitDataConvert;

@Service
@Transactional(rollbackFor=Exception.class)
public class SsbAjustServiceImpl implements SsbAjustService{

	@Autowired
	private SsbAjustMapper ssbAjustMapper;
	
	@Autowired
	private SsbAjustDetailMapper ssbAjustDetailMapper;
	
	@Autowired
	private SsbDataFromSqlServerMapper ssbDataFromSqlServerMapper;
	
   @Autowired
    private SsbAdjustDao ssbAdjustDao;
	
	@Transactional(readOnly=true)
	public Page<SsbAjust> queryCandAjustSsbByCondition(Map<String, Object> parameters,Page<SsbAjust> page) {
		//分页重载search方法
		List<SsbAjust> ssbAjusts = ssbAjustMapper.queryCandAjustSsbByCondition(parameters,ICloudDataUtil.getRowBounds(page));
		page.setTotalCount(ssbAjustMapper.count(parameters));
		page.setResult(ssbAjusts);
		return page;
	}
	
	@Transactional(readOnly=true)
	public CandidateModel queryCandidateById(Integer candidateId){
		if(candidateId==null)return null;
		return ssbDataFromSqlServerMapper.queryCandidateById(candidateId);
	}
	 
	public Integer queryCandByIdOrNo(Map<String, Object> parameters){
		CandidateModel candidate = ssbDataFromSqlServerMapper.queryCandidateByIdOrNo(parameters);
	return candidate.getId();
	}
	
	public SsbAjust queryCandAjustmentByCandidateIdOrNo(Map<String, Object> parameters, String monthFee) {
		SsbAjust dbCandAjustMentSsbHeader = new SsbAjust();
		CandidateModel candidate = ssbDataFromSqlServerMapper.queryCandidateByIdOrNo(parameters);
		if (candidate != null) {
			dbCandAjustMentSsbHeader.setCandidateId(candidate.getId());
			dbCandAjustMentSsbHeader.setCandidateName(candidate.getFnCn());
			dbCandAjustMentSsbHeader.setCandidateNo(candidate.getNo());
			dbCandAjustMentSsbHeader.setMonthFee(monthFee);
		}
		List<CandSsbModel> candSsbList=ssbDataFromSqlServerMapper.queryCandSsbByCandId(candidate.getId());
		StringBuffer _sb = null;
		Map<Integer, String> ssbChargeMap = new LinkedHashMap<Integer, String>();
		if(candSsbList.get(0)!=null ){
			//构建页面元素
			for (CandSsbModel candSsb : candSsbList) {
				if(candSsb != null &&ssbDataFromSqlServerMapper.queryChargeRuleByChargeId(candSsb.getChargeRuleId())!=null)
					_sb = new StringBuffer();
				_sb.append("(");
				ChargeModel charge=ssbDataFromSqlServerMapper.queryChargeRuleByChargeId(candSsb.getChargeRuleId());
				ClientModel client=ssbDataFromSqlServerMapper.queryClientByClientId(charge.getClientId());
				_sb.append(client.getClientId());
				_sb.append(")");
				_sb.append(client.getClientName());
				_sb.append("-(");
				_sb.append(charge.getId());
				_sb.append(")");
				_sb.append(charge.getName());
			
				ssbChargeMap.put(charge.getId(),  _sb.toString());
			
			}
			if(candSsbList!=null&&candSsbList.size()>1){
				CandSsbModel candSsb=new CandSsbModel();
				candSsb.setId(-1);
				candSsb.setName("请选择");
				candSsbList.add(0,candSsb);
			}
			
			dbCandAjustMentSsbHeader.setCandSsbList(candSsbList);
		}else{
			//dbCandAjustMentSsbHeader.setCandSsbList(null);
		}
		dbCandAjustMentSsbHeader.setSsbChargeMap(ssbChargeMap);
		return dbCandAjustMentSsbHeader;
	}
	public List<SsbItemModel> querySsbItemBySsbId(Integer ssbId){
		return ssbDataFromSqlServerMapper.querySsbItemBySsbId(ssbId);
	}

	
	/**
	 * 注：如果用户点击审批通过 step1: 更新ajustmentStatus 为 2 step2:
	 * 将通过的数据同步到billpay调整模块中
	 * @throws SQLException 
	 */
	public void changeStatusAll(String idsString, Integer status) throws SQLException 
	{
		/***********step1: 更新ajustmentStatus*************/
		List<Integer> list = StringUtils.stringIntegerListConvert(idsString);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", status);
		map.put("list", list);
		
		/************step2: 同步数据到icloud费用下**********/
		//点击通过是需要同步数据
		if(status != null && SocialBenefitDataConvert.CAND_SSB_AJUEST_OK.equals(status))
		{
			List<SsbAjust> ajustList = new ArrayList<SsbAjust>();
			for(int i = 0 ; list != null && i < list.size(); i++)
			{
				SsbAjust dbSsbAjust = ssbAjustMapper.querySsbAjust(list.get(i));
				if(dbSsbAjust!=null){
    				dbSsbAjust.setCompanyUndertake(ssbDataFromSqlServerMapper.queryCompanyUnderByChargeId(dbSsbAjust.getRuleId()));
    				ajustList.add(dbSsbAjust);
				}
			}
			this.saveSsbAdjustSqlserver(ajustList);
		}
		
		ssbAjustMapper.changeStatusAll(map);
	}
	
	/**
	 * 新增或更新社保调整
	 */
	@Override
	public Result save(SsbAjust ssbAjustMent, Long currOperatorId) throws Exception {
		//验证数据
		Result result = new Result();
		if (ssbAjustMent == null) {
			result.setResult(false);
			result.setResultText("员工的调整信息不存在,不能进行保存操作!");
			//result.setT(ssbAjustMent);
			return result;
		}
		/************** 验证员工编号是否合法 ***************/
		if (validateCandidate(ssbAjustMent.getCandidateId())) {
			result.setResult(false);
			result.setResultText("员工编号不合法请重新输入!");
			//result.setT(candAjustment);
			return result;
		}
		/***************** 如果添加险种重复提示错误信息 ******************/
		if (validateItem(ssbAjustMent)) {
			result.setResult(false);
			result.setResultText("员工的调整信息中存在重复的项目!");
			//result.setT(candAjustment);
			return result;
		}
		/***************** 验证险种的调整日期是否相同 *****************/
		if (validateItemMonth(ssbAjustMent)) {
			result.setResult(false);
			result.setResultText("员工的调整信息中社保所属月不一致!");
			//result.setT(candAjustment);
			return result;
		}
		
		
		
		
		if(ssbAjustMent.getId()==null){//新增
			ssbAjustMent.setVendorId(currOperatorId.intValue());
			ssbAjustMent.setCreateBy(currOperatorId.intValue());
			ssbAjustMent.setCreateDate(new Date());
			ssbAjustMapper.insertSsbAjustMent(ssbAjustMent);
			
			if(ssbAjustMent.getCandAjustmentssbDetails()!=null){
				List<SsbItemModel> ssbItemList=this.querySsbItemBySsbId(ssbAjustMent.getSsbId());
				for(SsbAjustDetail ssbAjustDetail : ssbAjustMent.getCandAjustmentssbDetails()){
					ssbAjustDetail.setAjustmentHeaderId(ssbAjustMent.getId());
					for(int i=0;i<ssbItemList.size();i++){
						if(ssbAjustDetail.getItemId().equals(ssbItemList.get(i).getId())){
							ssbAjustDetail.setItemName(ssbItemList.get(i).getName());
						}
					}
					ssbAjustDetail.setCreateBy(currOperatorId.intValue());
					ssbAjustDetail.setCreateDate(new Date());
					ssbAjustDetailMapper.insertSsbAjustDetail(ssbAjustDetail);
				}
			}
		}else{//修改
			List<SsbAjustDetail> lists=ssbAjustDetailMapper.querySsbDetailByAjustId(ssbAjustMent.getId());
			ssbAjustMent.setVendorId(currOperatorId.intValue());
			ssbAjustMent.setModifyBy(currOperatorId.intValue());
			ssbAjustMent.setModifyDate(new Date());
			ssbAjustMapper.updateSsbAjustMentById(ssbAjustMent);
			if(ssbAjustMent.getCandAjustmentssbDetails()!=null){
//				for(int i=0;i<lists.size();i++){
//					for(int j=0;j<ssbAjustMent.getCandAjustmentssbDetails().size();j++){
//						if (ssbAjustMent.getCandAjustmentssbDetails().get(j).getId().equals(lists.get(i).getId())){
//							ssbAjustMent.getCandAjustmentssbDetails().get(j).setModifyBy(currOperatorId.intValue());
//							ssbAjustMent.getCandAjustmentssbDetails().get(j).setModifyDate(new Date());
//							ssbAjustDetailMapper.updateSsbAjustDetail(ssbAjustMent.getCandAjustmentssbDetails().get(j));
//							flag=lists.get(i).getId()+flag;
//						}else{
//							ssbAjustDetailMapper
//							List<Integer> ids 
//						}
//					}
//				}
				List<Integer> ids = new ArrayList<Integer>();
				List<SsbAjustDetail> newSsbAjustDetailList = new ArrayList<SsbAjustDetail>();
				List<SsbAjustDetail> dbSsbAjustDetailList = new ArrayList<SsbAjustDetail>();
				         //筛选记录
				for(int j=0;j<ssbAjustMent.getCandAjustmentssbDetails().size();j++)
				{
					if(ssbAjustMent.getCandAjustmentssbDetails().get(j).getId()!= null)
					{
						ids.add(ssbAjustMent.getCandAjustmentssbDetails().get(j).getId());
						dbSsbAjustDetailList.add(ssbAjustMent.getCandAjustmentssbDetails().get(j));
					}
					else{
						//页面传过来的新的险种,此时数据库中不存在
						newSsbAjustDetailList.add(ssbAjustMent.getCandAjustmentssbDetails().get(j));
					}
				}
				//保存页面传过来的新险种
				if(newSsbAjustDetailList != null && newSsbAjustDetailList.size() > 0)
				{
					//ssbAjustDetailMapper.insertSsbAjustDetailList(newSsbAjustDetailList);
					List<SsbItemModel> ssbItemList=this.querySsbItemBySsbId(ssbAjustMent.getSsbId());
					for(SsbAjustDetail ssbAjustDetail : newSsbAjustDetailList){
						ssbAjustDetail.setAjustmentHeaderId(ssbAjustMent.getId());
						for(int i=0;i<ssbItemList.size();i++){
							if(ssbAjustDetail.getItemId().equals(ssbItemList.get(i).getId())){
								ssbAjustDetail.setItemName(ssbItemList.get(i).getName());
							}
						}
						ssbAjustDetail.setCreateBy(currOperatorId.intValue());
						ssbAjustDetail.setCreateDate(new Date());
						ssbAjustDetailMapper.insertSsbAjustDetail(ssbAjustDetail);
					}
				}
				
				//删除页面操作中删除的险种选项
				if(ids != null)
				{
					for(int i=0;i<lists.size();i++)
					{
						SsbAjustDetail dbSsbAjustDetail = lists.get(i);
						if(!ids.contains(dbSsbAjustDetail.getId()))ssbAjustDetailMapper.deleteByPrimaryKey(dbSsbAjustDetail.getId());
					}
				}
				
				//更新页面存在并且数据库存在的险种
				if(dbSsbAjustDetailList != null && dbSsbAjustDetailList.size() > 0)
				{
					//ssbAjustDetailMapper.updateList(dbSsbAjustDetailList);
					List<SsbItemModel> ssbItemList=this.querySsbItemBySsbId(ssbAjustMent.getSsbId());
					for(SsbAjustDetail ssbAjustDetail : dbSsbAjustDetailList){
						ssbAjustDetail.setAjustmentHeaderId(ssbAjustMent.getId());
						for(int i=0;i<ssbItemList.size();i++){
							if(ssbAjustDetail.getItemId().equals(ssbItemList.get(i).getId())){
								ssbAjustDetail.setItemName(ssbItemList.get(i).getName());
							}
						}
						ssbAjustDetail.setModifyBy(currOperatorId.intValue());
						ssbAjustDetail.setModifyDate(new Date());
						ssbAjustDetailMapper.updateSsbAjustDetail(ssbAjustDetail);
					}
				}
				
			}
			
			
			
		}
		
		
		 
		result.setResult(true);
		result.setResultText("保存成功");
		return result;
	}
	
	public SsbAjust queryCandAjustmentById(Integer id) 
	{
		SsbAjust dbSsbAjust = ssbAjustMapper.querySsbAjust(id);
		return dbSsbAjust;
	}
	
	/** 保存社保信息连接sqlserver
	 * <p> 描述	:  方法的主要功能和使用场合</p>
	 * <p> 备注	:  其他对方法的说明信息</p>
	 * @param headerList
	 * @throws SQLException
	 */
    public void saveSsbAdjustSqlserver(List<SsbAjust> headerList) throws SQLException {
        /******* 通过调整社保所属月构造candAjustmentssbHeader集合进去批量插入 **********/
        List ssbAdjustList = new ArrayList<SsbAjust>();
        for (SsbAjust ssbAjust:headerList) {
            ssbAdjustList.addAll(this.buildChargeAjustHeaderList(ssbAjust));
//          for(SsbAjust ssbAjustNew:this.buildChargeAjustHeaderList(ssbAjust)){
//              ssbAdjustDao.saveSsbAdjustment(ssbAjustNew);
//              //批量的方法
////              ssbDataFromSqlServerMapper.insertChargeAjust(ssbAjustNew);
////              ssbDataFromSqlServerMapper.insertChargeAjustDetail(ssbAjustNew.getSsbDetailList());
//          }
        }
        ssbAdjustDao.saveSsbAdjustment(ssbAdjustList);
        
    }
//	@Override
//	 public void saveSsbAdjust(List<SsbAjust> headerList) throws SQLException {
//	        /******* 通过调整社保所属月构造candAjustmentssbHeader集合进去批量插入 **********/
//	        for (SsbAjust ssbAjust:headerList) {
//	          for(SsbAjust ssbAjustNew:this.buildChargeAjustHeaderList(ssbAjust)){
//	              ssbAdjustDao.saveSsbAdjustment(ssbAjustNew);
//	              ssbDataFromSqlServerMapper.
//	          }
//	        }
//	    }

    /**
     * 通过调整社保所属月构造candAjustmentssbHeader集合进去批量插入
     */
    public List<SsbAjust> buildChargeAjustHeaderList(
            SsbAjust header) {
        // 设置header type
        header.setType(1);
        header.setId(null);
        header.setStatus(2);
        header.setCreateDate(new Date());
        List<SsbAjust> headerList = new ArrayList<SsbAjust>();
        if (header == null||header.getCandAjustmentssbDetails()==null||header.getCandAjustmentssbDetails().size()==0) {
            return headerList;
        }
        Map<String, List<SsbAjustDetail>> monthDetail = new HashMap<String, List<SsbAjustDetail>>();
        for (SsbAjustDetail detail: header.getCandAjustmentssbDetails()) {
            String month = detail.getMonthAttribute();
            detail.setType(1);
            detail.setCreateDate(new Date());
            //社保个人部分公司承担
            if((new Integer(1)).equals(header.getCompanyUndertake())){
                detail.setCompanyPay(detail.getCompanyAmount());
                detail.setPersonPay(BigDecimal.ONE);
            }else{
                detail.setCompanyPay(detail.getCompanyAmount());
                detail.setCompanyPay(detail.getPersonAmount());
            }
            if (monthDetail.containsKey(month)) {
                monthDetail.get(month).add(detail);
            } else {
                List<SsbAjustDetail> detailList = new ArrayList<SsbAjustDetail>();
                detailList.add(detail);
                monthDetail.put(month, detailList);
            }
        }
        
        
        for (Map.Entry<String, List<SsbAjustDetail>> entry : monthDetail.entrySet()) {
            List<SsbAjustDetail> dList = entry.getValue();
            SsbAjust headerTemp = new SsbAjust();
            BeanUtils.copyProperties(header, headerTemp);
            headerTemp.setMonthAttribute(entry.getKey());
            headerTemp.setSsbDetailList(dList);
            headerList.add(headerTemp);
        }

        return headerList;
    }
    
    public SsbAjust queryCandAjustById(Integer id){
    	SsbAjust dbSsbAjust=ssbAjustMapper.queryByPrimaryKey(id);
    	if (dbSsbAjust == null)
			return null;
    	CandidateModel candidate=ssbDataFromSqlServerMapper.queryCandidateById(dbSsbAjust.getCandidateId());
    	
    	List<SsbAjustDetail> lists=ssbAjustDetailMapper.querySsbDetailByAjustId(dbSsbAjust.getId());
    	dbSsbAjust.setCandAjustmentssbDetails(lists);
    	/****** 设置调整费用月为当月 供查询当月调整次数 ******/
		String monthFee = DateUtil.getDateFormat(new Date(), "yyyy/MM");
		dbSsbAjust.setMonthFee(monthFee);
		dbSsbAjust.setCandidate(candidate);
    	
    	return dbSsbAjust;
    }
    
    public List<CandSsbModel> queryCandSsbById(Integer id){
    	return ssbDataFromSqlServerMapper.queryCandSsbByCandId(id);
    }
    
    public ChargeModel queryChargeRuleByChargeId(Integer id){
    	return ssbDataFromSqlServerMapper.queryChargeRuleByChargeId(id);
    }
    
    public ClientModel queryClientByClientId(Integer id){
    	return ssbDataFromSqlServerMapper.queryClientByClientId(id);
    }
    
    public Result changeStatus(SsbAjust ssbAjustMent) throws Exception{
    	ssbAjustMapper.updateStatus(ssbAjustMent);
    	Result result = new Result(); 
		result.setResult(true);
		result.setResultText("操作成功");
		return result;
    }
    
    public Result deleteHeadDetail(Integer id) throws Exception{
    	ssbAjustDetailMapper.deleteAjustMentDetail(id);
    	ssbAjustMapper.deleteAjustMent(id);
    	Result result = new Result(); 
		result.setResult(true);
		result.setResultText("操作成功");
		return result;
    }
    
    public Boolean validateCandidate(Integer candidateId) {
		boolean falg = true;
		CandidateModel dbCandidate = ssbDataFromSqlServerMapper.queryCandidateById(candidateId);
		if (dbCandidate != null && dbCandidate.getId() != null)
			falg = false;
		return falg;
	}
    
    public Boolean validateItem(SsbAjust candAjustment) {
		boolean falg = false;
		List<Integer> integerList = new ArrayList<Integer>();
		for (int i = 0; candAjustment != null
				&& candAjustment.getCandAjustmentssbDetails() != null
				&& candAjustment.getCandAjustmentssbDetails().size() != 0
				&& i < candAjustment.getCandAjustmentssbDetails().size(); i++) {
			SsbAjustDetail detai = candAjustment
					.getCandAjustmentssbDetails().get(i);
			if (integerList.contains(detai.getItemId())) {
				falg = true;
				break;
			}
			integerList.add(detai.getItemId());
		}
		return falg;
	}
    
    public Boolean validateItemMonth(SsbAjust candAjustment) {
		boolean falg = false;
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; candAjustment != null
				&& candAjustment.getCandAjustmentssbDetails() != null
				&& candAjustment.getCandAjustmentssbDetails().size() != 0
				&& i < candAjustment.getCandAjustmentssbDetails().size(); i++) {
			SsbAjustDetail detai = candAjustment
					.getCandAjustmentssbDetails().get(i);
			if (stringList != null && stringList.size() != 0
					&& !stringList.contains(detai.getMonthAttribute().trim())) {
				falg = true;
			}
			stringList.add(detai.getMonthAttribute().trim());
		}
		return falg;
	}
    
    public CandSsbModel queryRuleId(CandSsbModel candSsb){
    	return ssbDataFromSqlServerMapper.queryRuleId(candSsb);
    }
    
  
}
