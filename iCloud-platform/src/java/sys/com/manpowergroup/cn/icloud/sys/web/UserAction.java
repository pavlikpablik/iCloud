package com.manpowergroup.cn.icloud.sys.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.entity.Organ;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.service.UserService;

/**
 * 
 * @author Sundy.Sun
 * 用户管理
 */
public class UserAction extends BaseAction{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7678487241722264008L;

	@Autowired
    private UserService userService;
    
    private User user ;
    private Organ organ;
    private Page<User> page;
	private List<User> cityList;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public Page<User> getPage() {
		return page;
	}


	public void setPage(Page<User> page) {
		this.page = page;
	}


	public List<User> getCityList() {
		return cityList;
	}


	public void setCityList(List<User> cityList) {
		this.cityList = cityList;
	}

	public String page() throws Exception {
		queryUserByCondition();
		return "page";
	}
	
	
	public String prepaging() throws Exception{
		queryUserByCondition();
		return "list";
	}
	public String query() throws Exception{
		queryUserByCondition();
		return "list"; 
	}
	
	public void queryUserByCondition() throws Exception{
		page = this.initPage(page);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//查询条件
		parameters.put("loginName", request.getParameter("loginName"));
		parameters.put("trueName", request.getParameter("trueName"));
		parameters.put("idcard", request.getParameter("idcard"));
		parameters.put("companyName", request.getParameter("companyName"));
		parameters.put("enabled", request.getParameter("enabled"));
		parameters.put("branchId", this.getCurrBranchId());
		
		page = userService.queryUserByCondition(parameters,page);
	}
	
    
   
   public String checkLoginName()throws Exception{
       
       String loginName = request.getParameter("loginName");
     //保存验证页面数据自身是否重复
       Result result = new Result();
       boolean isValidity = userService.checkLoginNameValidity(loginName);
       result.setResult(isValidity);
       Struts2Utils.renderJson(result, headers);
       return null;
   }
   
   public String save()throws Exception{
      // Map<String, Object> parameters = new HashMap<String, Object>();
       
       //查询条件
       String loginName =  request.getParameter("loginName");
       String password =  request.getParameter("password");
       String surePassword =  request.getParameter("surePassword");
       String trueName =  request.getParameter("trueName");
       String idcard =  request.getParameter("idCard");
       String company =  request.getParameter("company");
       String mobile =  request.getParameter("mobile");
       String phone =  request.getParameter("phone");
       String email =  request.getParameter("email");
       String orgId=request.getParameter("orgId");
       String roleIds = request.getParameter("userRoleId");
       List<Integer> ids = StringUtils.stringIntegerListConvert(roleIds);
       User newUser = new User();
       if(user.getId()!=null)
       {
    	   newUser.setId(user.getId());
    	   newUser.setCreateBy(user.getCreateBy());
    	   newUser.setCreateDate(user.getCreateDate());
    	   newUser.setStatus(user.getStatus());
       }else{
    	   newUser.setCreateDate(new Date());
       }
       newUser.setIds(ids);
       newUser.setLoginName(loginName);
       newUser.setPassword(password);
       newUser.setSurePassword(surePassword);
       newUser.setTrueName(trueName);
       newUser.setOrgId(orgId);
       newUser.setIdcard(idcard);
       newUser.setBranchId(this.getCurrBranchId());
       newUser.setMobile(mobile);
       newUser.setPhone(phone);
       newUser.setEmail(email);
       
     //保存注册信息
       Result result = userService.save(newUser,this.getCurrOperatorId());
       Struts2Utils.renderJson(result, headers);
       return null;
         
         
     }
   
   public String edit()  throws Exception {
		if (user != null && user.getId() != null && user.getId() > 0) {
			this.user = this.userService.queryUserById(this.user.getId());
			if(this.userService.queryOrgNameByUserId(user.getId())!=null){
				this.user.setOrgName(this.userService.queryOrgNameByUserId(user.getId()).getOrgName());
				this.user.setOrgId(this.userService.queryOrgNameByUserId(user.getId()).getOrgId());
			}
		}
		return "edit";
	}
   
   public String detail() throws Exception {
		if (user != null && user.getId() != null && user.getId() > 0) {
			this.user = this.userService.queryUserById(this.user.getId());
			if(this.userService.queryOrgNameByUserId(user.getId())!=null){
				this.user.setOrgName(this.userService.queryOrgNameByUserId(user.getId()).getOrgName());
				this.user.setOrgId(this.userService.queryOrgNameByUserId(user.getId()).getOrgId());
			}
		}
		return "detail";
	}
   
   public String authzAssign() throws Exception {
	   if (user != null && user.getId() != null && user.getId() > 0) {
		   this.user = this.userService.queryUserById(this.user.getId());
	   }
	   return "authzAssign";
   }
   
   public String updatePassword() throws Exception {
	   if (this.getCurrOperatorId() != null ) {
		   this.user = this.userService.queryUserById(this.getCurrOperatorId());
	   }
	   return "updatePassword";
   }
   
   public String savePassword() throws Exception{
	   if(user.getId() == null) return null;
	   String password =  request.getParameter("password");
       String surePassword =  request.getParameter("surePassword");
       User newUser = new User();
       newUser.setId(user.getId());
       newUser.setPassword(password);
       newUser.setSurePassword(surePassword);
	   Result result = userService.savePassword(newUser,this.getCurrOperatorId());
       Struts2Utils.renderJson(result, headers);
	   return null;
   }
   
   public String saveUserResource() throws Exception {
	   Long userId = Long.valueOf(request.getParameter("userId").trim());
	   String resourceIds = request.getParameter("rsourceIds").trim();
	   this.userService.saveUserResource(userId,resourceIds);
	   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(), headers);
	   return null;
   }
   
   public String updateStatus() throws Exception{
	   userService.updateStatus(user,this.getCurrOperatorId());
	   Struts2Utils.renderJson(ICloudDataUtil.getdefaultResult(),headers);
	   return null;
   }
   
   public String checkLoginPassword() throws Exception{
	   Result result = new Result();
       String password =  request.getParameter("oldPassword");
       Long id =  Long.valueOf(request.getParameter("userId"));
       boolean isValidity = userService.checkLoginPasswordValidity(id,password);
       result.setResult(isValidity);
       Struts2Utils.renderJson(result, headers);
	   return null;
   }
   
   public String showOrgan() throws Exception
   {
	   return "showOrgan";
   }
   
   public String showRole() throws Exception
   {
	   return "showRole";
   }
	
}
