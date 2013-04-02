package com.manpowergroup.cn.icloud.socialbenefit.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.DateUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;
import com.manpowergroup.cn.icloud.socialbenefit.model.CandSsbModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ChargeModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.ClientModel;
import com.manpowergroup.cn.icloud.socialbenefit.model.SsbItemModel;
import com.manpowergroup.cn.icloud.socialbenefit.service.SsbAjustService;

public class SsbAjustMentAction extends BaseAction{

	@Autowired
	private SsbAjustService ssbAjustService;
	
	private SsbAjust candAjustMent;
	private Page<SsbAjust> page;
	private List<SsbItemModel> ssbItemList;
	private List<CandSsbModel> candSsbList;
	
	public List<SsbItemModel> getSsbItemList() {
		return ssbItemList;
	}

	public void setSsbItemList(List<SsbItemModel> ssbItemList) {
		this.ssbItemList = ssbItemList;
	}

	public Page<SsbAjust> getPage() {
		return page;
	}

	public void setPage(Page<SsbAjust> page) {
		this.page = page;
	}

	public SsbAjust getCandAjustMent() {
		return candAjustMent;
	}

	public void setCandAjustMent(SsbAjust candAjustMent) {
		this.candAjustMent = candAjustMent;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824884519710673028L;

	/**
	 * 跳转到社保调整管理页面
	 * @return
	 */
	public String page() throws Exception{
		queryProvinceByCondition();
		return "page";
	}
	
	public String query() throws Exception{
		queryProvinceByCondition();
		return "list"; 
	}
	
	/**
	 * 跳转到修改、新增页面
	 * @return
	 */
	public String edit() throws Exception{
		Map<Integer , CandSsbModel>  candssbmap = new HashMap<Integer , CandSsbModel>();
		Integer key = 0;
		if(candAjustMent == null || candAjustMent.getId()==null)//条件成立时为新增调整
		{
			String monthFee = DateUtil.getDateFormat(new Date(), "yyyy/MM");
			candAjustMent = new SsbAjust();
			candAjustMent.setMonthFee(monthFee);
			return "edit";
		}
		candAjustMent=ssbAjustService.queryCandAjustById(candAjustMent.getId());
		
		if(candAjustMent.getCandidate()!=null && ssbAjustService.queryCandSsbById(candAjustMent.getCandidateId())!=null){
			candSsbList=ssbAjustService.queryCandSsbById(candAjustMent.getCandidateId());
			Map<Integer, String> ssbChargeMap = new LinkedHashMap<Integer, String>();
			StringBuffer _sb = null;
			for(CandSsbModel candSsb:ssbAjustService.queryCandSsbById(candAjustMent.getCandidateId())){
				if(candSsb != null &&ssbAjustService.queryChargeRuleByChargeId(candSsb.getChargeRuleId())!= null)
					_sb = new StringBuffer();
					_sb.append("(");
					ChargeModel charge=ssbAjustService.queryChargeRuleByChargeId(candSsb.getChargeRuleId());
					ClientModel client=ssbAjustService.queryClientByClientId(charge.getClientId());
					_sb.append(client.getClientId());
					_sb.append(")");
					_sb.append(client.getClientName());
					_sb.append("-(");
					_sb.append(charge.getId());
					_sb.append(")");
					_sb.append(charge.getName());
				
					ssbChargeMap.put(charge.getId(),  _sb.toString());
			}
			candAjustMent.setCandSsbList(candSsbList);
		
			candAjustMent.setSsbChargeMap(ssbChargeMap);
		
			ssbItemList=ssbAjustService.querySsbItemBySsbId(candAjustMent.getSsbId());
		
		}
		
		return "edit";
	}
	
	
	@SuppressWarnings("unchecked")
	public void queryProvinceByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("candidateId", request.getParameter("candidateId"));
		parameters.put("candidateName", request.getParameter("candidateName"));
		parameters.put("candidateNo", request.getParameter("candidateNo"));
		parameters.put("monthFee", request.getParameter("monthFee"));
		parameters.put("vendorId", this.getCurrOperatorId());
		
		page = ssbAjustService.queryCandAjustSsbByCondition(parameters,page);
	}
	
	public String prepaging() throws Exception{
		queryProvinceByCondition();
		return "list";
	}
	/**
	 * 页面显示detail方法
	 * @return
	 */
	public String detail()
	{
		if(candAjustMent == null || candAjustMent.getId()==null)return "detail";
		candAjustMent = ssbAjustService.queryCandAjustmentById(candAjustMent.getId());
		return "detail";
	}
	
	/**
	 * 点击确认加载员工社保数据
	 * @return
	 * @throws Exception
	 */
	public String editCand() throws Exception{
		//if(candAjustMent == null || candAjustMent.getCandidateId()==null)return "edit";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		if(request.getParameter("noOrId").length()!=18)
		{
			parameters.put("candidateId", request.getParameter("noOrId"));
		}
		parameters.put("candidateNo", request.getParameter("noOrId"));
		//parameters.put("candidateNo", "'441221197803114966'");
		
		
		String monthFee = DateUtil.getDateFormat(new Date(), "yyyy/MM");
		candAjustMent = ssbAjustService.queryCandAjustmentByCandidateIdOrNo(parameters,monthFee);
		
		
		return "edit";
	}
	
	
	/**
	 * 初始化设置页面的员工id
	 * @return
	 * @throws Exception
	 */
	public String initCandidateId() throws Exception{
    Map<String, Object> parameters = new HashMap<String, Object>();
		//查询条件
        if(request.getParameter("noOrId").length()!=18)
	    {
		parameters.put("candidateId", request.getParameter("noOrId"));
	    }
		parameters.put("candidateNo", request.getParameter("noOrId"));
		//parameters.put("candidateNo", "'441221197803114966'");
		Integer dbCandId=null;
		dbCandId = ssbAjustService.queryCandByIdOrNo(parameters);
		Struts2Utils.renderJson(dbCandId.toString(),headers);
		return null;
	}
	
	
	/**
	 * 添加detail
	 * @return
	 * @throws Exception
	 */
	public String addItemNew() throws Exception 
	{
		for (int i = 0; candAjustMent.getCandAjustmentssbDetails() != null
				&& i < candAjustMent.getCandAjustmentssbDetails().size(); i++) 
		{
			if (candAjustMent.getCandAjustmentssbDetails().get(i) == null) {
				candAjustMent.getCandAjustmentssbDetails().remove(i);
				i--;
			}
		}
		
		if (candAjustMent.getCandAjustmentssbDetails()==null) 
		{
			candAjustMent.setCandAjustmentssbDetails(new ArrayList<SsbAjustDetail>());
		}
		SsbAjustDetail candAjustmentssb = new SsbAjustDetail();
		candAjustMent.getCandAjustmentssbDetails().add(candAjustmentssb);
		ssbItemList=ssbAjustService.querySsbItemBySsbId(candAjustMent.getSsbId());
		//itemList = iCloudDataUtil.getAllAdjustItem();
		return "addItemNew";
	}
	
	/**
	 * 社保类型变更的险种联动
	 * @return
	 * @throws Exception
	 */
	public String changeSsb() throws Exception
	{
			candAjustMent.setCandAjustmentssbDetails(null);
		//ssbItemList=ssbAjustService.querySsbItemBySsbId(candAjustMent.getSsbId());
		return "addItemNew";
	}
	
	
	/**
	 * 保存员工社保调整信息
	 * @return
	 * @throws Exception 
	 */
	public String save() throws Exception
	{
		Result result=new Result();
		if(candAjustMent.getCandAjustmentssbDetails() == null ){
			result.setResult(false);
			result.setResultText("至少添加一个调整项目!");
			Struts2Utils.renderJson(result, headers);
			return null;
		}
		
		/**************移除页面中删除的item选项****************/
		for (int i = 0; candAjustMent != null && candAjustMent.getCandAjustmentssbDetails() != null && i < candAjustMent.getCandAjustmentssbDetails().size(); i++) 
		{
			if(candAjustMent.getCandAjustmentssbDetails().get(i) == null) 
			{
				candAjustMent.getCandAjustmentssbDetails().remove(i);
				i--;
			}
		}
		/************整理后的数据进行save or update***************/
		 result = ssbAjustService.save(candAjustMent, this.getCurrOperatorId());
		Struts2Utils.renderJson(result, headers);
		return null;
	}
	
	public String changeStatus()throws Exception
	{
		if(candAjustMent.getId() == null || candAjustMent.getAjustmentStatus()==null)return null;
		Result result = ssbAjustService.changeStatus(candAjustMent);
		Struts2Utils.renderJson(result, headers);
		return null;
	}
	
	public String delete() throws Exception
	{
		if(candAjustMent.getId() == null )return null;
		Result result = ssbAjustService.deleteHeadDetail(candAjustMent.getId());
		Struts2Utils.renderJson(result,headers );
		return null;
	}
	
    public String changeRuleId(){
    	if(candAjustMent.getSsbId().equals(-1))
    	{
    		Struts2Utils.renderJson("",headers);
    		 return null;
    	}
	    CandSsbModel candSsb=new CandSsbModel();
	    candSsb.setCandidateId(candAjustMent.getCandidateId());
	    candSsb.setSsbId(candAjustMent.getSsbId());
	    CandSsbModel dbCandSsb=new CandSsbModel();
	    dbCandSsb=ssbAjustService.queryRuleId(candSsb);
	    Struts2Utils.renderJson(dbCandSsb.getChargeRuleId().toString(),headers);
	    return null;
	}
}
