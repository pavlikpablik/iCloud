<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="az" uri="/WEB-INF/tld/authz.tld"%>

<div class="navbar navbar-fixed-top">
		<div class="navbar-inner" >
			<div class="container-fluid" >
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="layout_layout.action">iCloud</a> 
				<div class="nav-collapse" >
					<ul class="nav">
						<az:authorize ifAny="R_BASE_WH">
						 <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" >公司<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<az:authorize ifAny="R_SYS_ORGAN">
								<li class=""><a href="organ_page.action"><i class="icon-align-justify"></i> 组织架构</a>
								</li>
								</az:authorize>
								<az:authorize ifAny="R_USER_M">
								<li class=""><a href="user_page.action"><i class="icon-user"></i> 用户管理</a>
								</li>
								</az:authorize>
								<az:authorize ifAny="R_ROLE_M">
								<li class=""><a href="role_page.action"><i class="icon-briefcase"></i> 权限组管理</a>
								</li>
								</az:authorize>
							</ul>
						</li>
						</az:authorize>
						
						<az:authorize ifAny="R_SSB_WH">
						<li class="dropdown"><a href="#" class=" dropdown-toggle" data-toggle="dropdown" >社保配置<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li class=""><a href="socialBenefitBase_page.action"><i class="icon-check"></i> 社保类型管理</a>
								<li class=""><a href="socialBenefitBaseApply_page.action"><i class="icon-check"></i> 社保类型操作审批</a>
							</ul>
						</li>
						</az:authorize>
					
						<az:authorize ifAny="R_SSB_WH">
						<li class="dropdown"><a href="#" class=" dropdown-toggle" data-toggle="dropdown" >社保<b class="caret"></b> </a>
							<ul class="dropdown-menu">
							<az:authorize ifAny="R_SSB_D">
								<li class=""><a href="socialbenefit_page.action"><i class="icon-leaf"></i> 社保申报</a>
							</az:authorize>
							<az:authorize ifAny="R_SSB_DM">
								<li class=""><a href="ssbdeclarationconfirmation_page.action"><i class="icon-check"></i> 社保申报确认</a>
							</az:authorize>
								<li class=""><a href="ssbajustment_page.action"><i class="icon-check"></i> 社保调整管理</a>
								<li class=""><a href="candSsbAjustMentCheck_page.action"><i class="icon-check"></i> 社保申报调整审批</a>
							</ul>
						</li>
						</az:authorize>
						
						<az:authorize ifAny="R_SSB_CAL">
						<li class="dropdown"><a href="#" class=" dropdown-toggle" data-toggle="dropdown" >结算<b class="caret"></b> </a>
							<ul class="dropdown-menu">
							<az:authorize ifAny="R_SSB_CAL_FEE">
								<li class=""><a href=""><i class="icon-fire"></i>费用计算</a>
							</az:authorize>
							</ul>
						</li>
						</az:authorize>
						
						<li class="dropdown"><a href="#" class=" dropdown-toggle" data-toggle="dropdown" >系统<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li class=""><a href="resource_page.action">资源管理</a>
								</li>
								<li class=""><a href="city_page.action">省份城市</a>
								</li>
								<li class=""><a href="itemtype_page.action">项目元素类型</a>
								</li>
								<li class=""><a href="item_page.action">项目元素</a>
								</li>
								<li class=""><a href="code_page.action">键值对象</a>
								</li>
								
							</ul>
						</li>
					</ul>
					<p class="navbar-text pull-right"  >
						欢迎 ${sessionScope.iCloudPlatformLogonUser.loginName}&nbsp;
						<a href="logon_logonOut.action"  target="_self"  >退出</a>&nbsp;
						<a href="user_updatePassword.action" class="page_update_password"  target="_self" >修改密码</a>&nbsp;
						<c:if test="${empty cookie.iCloud_platform.value}">
						<a id="remeberme"  href="#" target="_self"  >记住我 </a>
						</c:if>
					</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 密码修改 -->
	<div class="modal hide fade" id="updatePassword"></div>
