package com.manpowergroup.cn.icloud.socialbenefit.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.DateUtil;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.service.SsbAjustService;

/**
 * 
 * @author Sundy.Sun
 * 社保调整审批管理
 */
public class SsbAjustApproveAction extends BaseAction{

	private static final long serialVersionUID = 7255559824308163292L;

	@Autowired
	private SsbAjustService ssbAjustService;
	
	private SsbAjust candAjustMent;
	private Page<SsbAjust> page;
	
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
	
	public String query() throws Exception{
		querySsbAjustByCondition();
		return "list"; 
	}

	/**
	 * 跳转到社保调整管理页面
	 * @return
	 */
	public String page() throws Exception{
		querySsbAjustByCondition();
		return "page";
	}
	
	/**
	 * 跳转到修改、新增页面
	 * @return
	 */
	public String edit() throws Exception{
		Integer key = 0;
		if(candAjustMent == null || candAjustMent.getId()==null)//条件成立时为新增调整
		{
			String monthFee = DateUtil.getDateFormat(new Date(), "yyyy/MM");
			candAjustMent = new SsbAjust();
			candAjustMent.setMonthFee(monthFee);
			return "edit";
		}
		
		return "edit";
	}
	
	
	@SuppressWarnings("unchecked")
	public void querySsbAjustByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("candidateId", request.getParameter("pname"));
		parameters.put("candidateName", request.getParameter("pname_en"));
		parameters.put("candidateNo", request.getParameter("cname"));
		parameters.put("monthFee", request.getParameter("cname_en"));
		parameters.put("ajuststatus", request.getParameter("ajuststatus"));
		parameters.put("vendorId", this.getCurrOperatorId());
		
		page = ssbAjustService.queryCandAjustSsbByCondition(parameters,page);
	}
	
	public String prepaging() throws Exception
	{
		querySsbAjustByCondition();
		return "list";
	}
	
	/**
	 * 状态的更改
	 * @return
	 */
	public String changeStatusAll()throws Exception
	{
		Integer status = Integer.valueOf(request.getParameter("status"));
		String idsString = request.getParameter("ids");
		if(StringUtils.isBlank(idsString) || status==null)return null;
		ssbAjustService.changeStatusAll(idsString,status);
		Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
		return null;
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

	
}
