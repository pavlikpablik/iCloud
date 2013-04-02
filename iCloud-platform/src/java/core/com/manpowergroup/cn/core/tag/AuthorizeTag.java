package com.manpowergroup.cn.core.tag;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.Collections3;


public class AuthorizeTag extends TagSupport {
	private static final long serialVersionUID = -2813304226856896439L;
	
	private String ifAny;//任意一个满足，显示
	private String ifAll;//全部满足，显示
	private String ifNot;//全部不满足，显示

	public int doStartTag() throws JspException {
		HttpSession session = (HttpSession) pageContext.getSession();
		
		/*User user = (User)session.getAttribute(ICloudConst.LOGIN_USER);
		if (Integer.valueOf(1).equals(user.getIssys())) {
			return EVAL_PAGE;
		}*/
		Set<String> roles = (Set<String>)session.getAttribute(ICloudConst.USER_ROLES);
		
		if (Collections3.isEmpty(roles)) {
			return SKIP_BODY;
		}
		
		Boolean AnyShow = null;
		if (StringUtils.isNotBlank(ifAny)) {
			AnyShow = false;
			String[] ifAnys = StringUtils.split(ifAny, ",");
			for (String any : ifAnys) {
				if (roles.contains(any)) {
					AnyShow = true;
					break;
				}
			}
		}
		Boolean AllShow = null;
		if (StringUtils.isNotBlank(ifAll)) {
			AllShow = true;
			String[] ifAlls = StringUtils.split(ifAll, ",");
			for (String all : ifAlls) {
				if (!roles.contains(all)) {
					AllShow = false;
					break;
				}
			}
		}
		Boolean notShow = null;
		if (StringUtils.isNotBlank(ifNot)) {
			AllShow = true;
			String[] ifNots = StringUtils.split(ifNot, ",");
			for (String not : ifNots) {
				if (roles.contains(not)){
					notShow = false;
					break;
				}
			}
		}
		if( (AnyShow==null?true:AnyShow) && (AllShow==null?true:AllShow) && (notShow==null?true:notShow)){
			return EVAL_PAGE;
	    }
		return SKIP_BODY;
	}
	

	public void setIfAny(String ifAny) {
		this.ifAny = ifAny;
	}

	public void setIfAll(String ifAll) {
		this.ifAll = ifAll;
	}

	public void setIfNot(String ifNot) {
		this.ifNot = ifNot;
	}
}
