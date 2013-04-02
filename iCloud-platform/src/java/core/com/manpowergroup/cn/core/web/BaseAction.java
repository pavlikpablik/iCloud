/*
 * Created on 2005-6-16 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpowergroup.cn.core.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.json.JsonMapper;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware
{

   private static final long serialVersionUID = 4867410373458725083L;
   
   protected static JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();

   protected HttpServletRequest request;

   protected HttpServletResponse response;

   protected Map< String, Object > session;

   protected transient Log logger = LogFactory.getLog( getClass() );
   
   protected String[] headers ={"encoding:utf-8", "no-cache:false"};
   
  

   
   public void setSession( Map< String, Object > session )
   {
      this.session = session;
   }

   public void setServletRequest( HttpServletRequest request )
   {
      this.request = request;
   }

   
   public void setServletResponse( HttpServletResponse response )
   {
      this.response = response;
   }

   
   @SuppressWarnings("rawtypes")
   public Page initPage(Page page){
	   	page = (Page) (page==null ? new Page():page);
		page.setOrderBy(StringUtils.isBlank(page.getOrderBy())?"id":page.getOrderBy());
		page.setOrder(StringUtils.isBlank(page.getOrder())?Page.ASC:page.getOrder());
		page.setPageSize(Page.PAGESIZE_15);
		page.setPageNo(page.getPageNo() == 0 ?Page.PAGENO_1 : page.getPageNo());
		return page;
   }

   /**
    * 页面传递过来的选择框的对象，为逗号分隔的String类型，在这里转换为整数list
    */
   public List<Integer> getChkboxChoosedIds(){
	   return StringUtils.stringIntegerListConvert(chkboxChoosed);
   }
   
    String chkboxChoosed;
    public String getChkboxChoosed() {
		return chkboxChoosed;
	}
	public void setChkboxChoosed(String chkboxChoosed) {
		this.chkboxChoosed = chkboxChoosed;
	}
	
	public Long getCurrOperatorId(){
		User user = (User)session.get(ICloudConst.LOGIN_USER);
		return user.getId();
	}
	
	public Long getCurrBranchId(){
		User user = (User)session.get(ICloudConst.LOGIN_USER);
		return user.getBranchId();
	}
	public Long getCurrVendorId(){
		User user = (User)session.get(ICloudConst.LOGIN_USER);
		return user.getVendorId();
	}
	
	
	
}