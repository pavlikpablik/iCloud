package com.manpowergroup.cn.icloud.sys.web;


import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.DESPlus;
import com.manpowergroup.cn.core.utils.ICloudCookie;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.SpringContextUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.service.LoginService;
import com.manpowergroup.cn.icloud.sys.service.UserService;

public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 4248442630020764571L;

	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	public String tologon() throws Exception {
		if(session.get(ICloudConst.LOGIN_USER) != null ){
		   return SUCCESS;
		}
		
		ICloudCookie icc = ICloudCookie.readICloudCookie(request.getCookies());
		if(icc == null || StringUtils.isBlank(icc.getStaffName()) || StringUtils.isBlank(icc.getStaffPassword())){
			return LOGIN;
		}
		User user = loginService.getUserByUsernameAndPassword(icc.getStaffName(), icc.getStaffPassword());
		if (user == null) {
			return LOGIN;
		} else {
			Map<String, Set<String>> map = ((LoginService)SpringContextUtil.getBean("loginServiceImpl")).getUserResource(user.getId());
			
			if (Integer.valueOf(1).equals(user.getIssys())) {
				map.get(ICloudConst.USER_ACTIONS).addAll(ICloudDataUtil.sysActionList);
				map.get(ICloudConst.USER_ROLES).addAll(ICloudDataUtil.sysResourceList);
			}
			
		     session.put(ICloudConst.LOGIN_USER, user);
		     session.put(ICloudConst.USER_ACTIONS, map.get(ICloudConst.USER_ACTIONS));
		     session.put(ICloudConst.USER_ROLES, map.get(ICloudConst.USER_ROLES));
			return SUCCESS;
		}
	}
	
	
	public String logon() throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			
			User user = loginService.getUserByUsernameAndPassword(username, DESPlus.encrypt(password));
			if (user==null) {
				addActionError("帐号或密码错误");
				return LOGIN;
			}else {
				Map<String, Set<String>> map = ((LoginService)SpringContextUtil.getBean("loginServiceImpl")).getUserResource(user.getId());
			   
				if (Integer.valueOf(1).equals(user.getIssys())) {
					map.get(ICloudConst.USER_ACTIONS).addAll(ICloudDataUtil.sysActionList);
					map.get(ICloudConst.USER_ROLES).addAll(ICloudDataUtil.sysResourceList);
				}
				session.put(ICloudConst.LOGIN_USER, user);
			    session.put(ICloudConst.USER_ACTIONS, map.get(ICloudConst.USER_ACTIONS));
			    session.put(ICloudConst.USER_ROLES, map.get(ICloudConst.USER_ROLES));
				return SUCCESS;
			}
		}else {
			addActionError("帐号或密码不能为空");
			return LOGIN;
		}
		
		
	}
	
	
	/**
	 * 退出系统，需要清空Cookie和Session
	 */
	public String logonOut() {
		
	    this.clearCookie();
	    //清除Session
		session.clear();
		return LOGIN;
	}
	
	private void clearCookie(){
		//清除cookie
		Cookie[] cs = request.getCookies();
	    if(cs != null){
	    	for(Cookie c:cs){
	    		if(StringUtils.equalsIgnoreCase(c.getName(), ICloudCookie.COOKIE)){
	    			c.setValue("");
	    			c.setMaxAge(0);
	    			response.addCookie(c);   
	    		}
	    	}
	    }
	}
	
	
	/**
	 * 默认Cookie为1个月
	 * @param icc
	 * @throws Exception
	 */
	private void writeICloudCookie(ICloudCookie icc) throws Exception{
		if(icc == null) return;
		
		try {
			Cookie c = new Cookie(ICloudCookie.COOKIE, XMLUtil.toXML(icc));
			c.setMaxAge(60*60*24*30);
			response.addCookie(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Cookie写入失败，需要修改浏览器设置");
		}
	}
	
	public String remember() throws Exception {
		writeCookie();
		Struts2Utils.renderText("success", headers);
		return null;
	}
	
	private void writeCookie() throws Exception{
		ICloudCookie icc = ICloudCookie.readICloudCookie(request.getCookies());
		if(icc == null){
			icc = new ICloudCookie();
		}
		User user = (User) session.get(ICloudConst.LOGIN_USER);
		if(user == null){
			throw new Exception("用户登录信息丢失，请重新登录");
		}
		icc.setStaffName(user.getLoginName());
		icc.setStaffPassword(user.getPassword());
		this.writeICloudCookie(icc);
	}
	
	
	/**
     * 初始化基础数据
     * @return
     * @throws Exception
     */
    public String init() throws Exception
    {
    	Map<String, Set<String>> map = ((LoginService)SpringContextUtil.getBean("loginServiceImpl")).getUserResource(this.getCurrOperatorId());
    	User user = userService.queryUserById(getCurrOperatorId());
		if (Integer.valueOf(1).equals(user.getIssys())) {
			map.get(ICloudConst.USER_ACTIONS).addAll(ICloudDataUtil.sysActionList);
			map.get(ICloudConst.USER_ROLES).addAll(ICloudDataUtil.sysResourceList);
		}
		
		 session.put(ICloudConst.LOGIN_USER, user);
		 session.put(ICloudConst.USER_ACTIONS, map.get(ICloudConst.USER_ACTIONS));
		 session.put(ICloudConst.USER_ROLES, map.get(ICloudConst.USER_ROLES));
		 return SUCCESS;
    }
	
}	
