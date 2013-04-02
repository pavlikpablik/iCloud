package com.manpowergroup.cn.icloud.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.model.VendorModel;
import com.manpowergroup.cn.icloud.sys.entity.Branch;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.model.Vendor;
import com.manpowergroup.cn.icloud.sys.service.RegisterService;
import com.manpowergroup.cn.icloud.webservice.VendorService;

public class RegisterAction extends BaseAction{
	
    /**
     * Create date:2013-2-5
     * Describe   :TODO
     */
    private static final long serialVersionUID = -7052874146685156189L;

    @Autowired
    private RegisterService registerService;
    
    
    
    private List<VendorModel> vendorList;
    
    public List<VendorModel> getVendorList() {
		return vendorList;
	}


	public void setVendorList(List<VendorModel> vendorList) {
		this.vendorList = vendorList;
	}






	private User user ;
    
    
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    /** 跳转到注册页面
     * <p> 描述	:  方法的主要功能和使用场合</p>
     * <p> 备注	:  其他对方法的说明信息</p>
     * @return
     * @throws Exception
     */
    public String toRegister()throws Exception{
    	if (ICloudDataUtil.vendorList==null || ICloudDataUtil.vendorList.size()==0){
    	vendorList=	new ArrayList<VendorModel>();
    	}else{
    	vendorList=ICloudDataUtil.vendorList;
    	}
        return "register";
    }
    
   
   public String checkLoginName()throws Exception{
       
       String loginName = request.getParameter("loginName");
     //保存验证页面数据自身是否重复
       Result result = new Result();
       boolean isValidity = registerService.checkLoginNameValidity(loginName);
       result.setResult(isValidity);
       Struts2Utils.renderJson(result, headers);
       return null;
   }
   
   /**
    * 2013-03-07 add by bob 
    * 判断供应商是否存在
    * @return
    * @throws Exception
    */
   public String checkVendor() throws Exception{
	   String vendorId=request.getParameter("vendorId");
	   Result result=new Result();
	   boolean isValidity=registerService.checkVendorIdValidity(Long.parseLong(vendorId));
	   result.setResult(isValidity);
	   Struts2Utils.renderJson(result, headers);
	   return null;
   }
   
   public String register()throws Exception{
      // Map<String, Object> parameters = new HashMap<String, Object>();
       
       //查询条件
       String loginName =  request.getParameter("loginName");
       String password =  request.getParameter("password");
       String surePassword =  request.getParameter("surePassword");
       String trueName =  request.getParameter("trueName");
       String idcard =  request.getParameter("idCard");
       //String company =  request.getParameter("company");
       //供应商名称
       String companyName=request.getParameter("vendorName");
       //供应商ID
       String companyId=request.getParameter("company");
       String mobile =  request.getParameter("mobile");
       String phone =  request.getParameter("phone");
       String email =  request.getParameter("email");
       String checkCode =  request.getParameter("checkCode");
       String checkCodeOrg = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
       User user = new User();
       user.setLoginName(loginName);
       user.setPassword(password);
       user.setSurePassword(surePassword);
       user.setTrueName(trueName);
       user.setIdcard(idcard);
       Branch branch= new Branch();
       branch.setName(companyName);
       branch.setVendorId(Long.valueOf(companyId));
       user.setBranch(branch);
       user.setMobile(mobile);
       user.setPhone(phone);
       user.setEmail(email);
       
     //保存注册信息
       Result result = registerService.register(user,checkCode,checkCodeOrg);
       Struts2Utils.renderJson(result, headers);
         return null;
         
         
     }
	
}
