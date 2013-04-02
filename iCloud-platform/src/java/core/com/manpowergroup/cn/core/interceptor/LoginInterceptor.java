package com.manpowergroup.cn.core.interceptor;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.ICloudCookie;
import com.manpowergroup.cn.core.utils.ICloudDataUtil;
import com.manpowergroup.cn.core.utils.SpringContextUtil;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.icloud.sys.entity.User;
import com.manpowergroup.cn.icloud.sys.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录验证拦截器.
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String actionName = invocation.getProxy().getActionName();
		String namespace = invocation.getProxy().getNamespace();
		if (session.get(ICloudConst.LOGIN_USER) == null ||  session.get(ICloudConst.USER_ACTIONS) == null ) {
			if (!(actionName.contains("logon") || actionName.contains("register") )) {
				
				ICloudCookie icc = ICloudCookie.readICloudCookie(ServletActionContext.getRequest().getCookies());
				if(icc == null || StringUtils.isBlank(icc.getStaffName()) || StringUtils.isBlank(icc.getStaffPassword())){
					ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_FORBIDDEN);
					return "login";
				}
				
				User user  = ((LoginService)SpringContextUtil.getBean("loginServiceImpl")).getUserByUsernameAndPassword(icc.getStaffName(), icc.getStaffPassword());
				if (user == null) {
					ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_FORBIDDEN);
					return "login";
				}else {
					
					Map<String, Set<String>> map = ((LoginService)SpringContextUtil.getBean("loginServiceImpl")).getUserResource(user.getId());
					if (Integer.valueOf(1).equals(user.getIssys())) {
						map.get(ICloudConst.USER_ACTIONS).addAll(ICloudDataUtil.sysActionList);
						map.get(ICloudConst.USER_ROLES).addAll(ICloudDataUtil.sysResourceList);
					}
					session.put(ICloudConst.LOGIN_USER, user);
				    session.put(ICloudConst.USER_ACTIONS, map.get(ICloudConst.USER_ACTIONS));
				    session.put(ICloudConst.USER_ROLES, map.get(ICloudConst.USER_ROLES));
					
					if (!ICloudDataUtil.hasRoles((Set<String>)session.get(ICloudConst.USER_ACTIONS),actionName,namespace)) {
						ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_FORBIDDEN);
						return "login";
					}
					
				} 
			}
		}else{
			Set<String> roles = (Set<String>)session.get(ICloudConst.USER_ACTIONS);
		
			if (!ICloudDataUtil.hasRoles(roles,actionName,namespace)) {
				ServletActionContext.getResponse().setStatus(HttpServletResponse.SC_FORBIDDEN);
				return "login";
			}
		}
		return invocation.invoke();
	}
	
}
