<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<form class="form-horizontal" action="user_save.action"
	id="editForm" name="editForm" method="post">
	<s:hidden name="user.id"></s:hidden>
	<s:hidden name="user.createBy"></s:hidden>
	<s:hidden name="user.createDate"></s:hidden>
	<s:hidden name="user.status"></s:hidden>
	<div>
		<div class="control-group">
			<label class="control-label" for="loginName">用户名*</label>
			<div class="controls">
				<input type="text" class="{validate:{required:true,messages:{required:'用户名不能为空'}}} " id="loginName" value="${user.loginName}" name="loginName" placeholder="用户名">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">密码*</label>
			<div class="controls">
				<input type="password" class="{validate:{required:true,messages:{required:'密码不能为空'}}} " id="password" name="password" value="${user.password}"
					placeholder="密码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="surePassword">确认密码*</label>
			<div class="controls">
				<input type="password"  
				class="{validate:{checkPassword:'#password',required:true,messages:{required:'密码输入不一致'}}}" id="surePassword" name="surePassword" value="${user.password}"
					placeholder="确认密码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="trueName">真实姓名</label>
			<div class="controls">
				<input type="text" id="trueName" value="${user.trueName}" name="trueName" placeholder="真实姓名">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="idCard">身份证号码</label>
			<div class="controls">
				<input type="hidden" id="idCardType" value="1"> <input value="${user.idcard}"
					class="{validate:{checkIDCard:true,messages:{required:'身份证件号码不能为空'}}}"
					type="text" id="idCard" name="idCard" placeholder="身份证号码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="trueName">组织结构</label>
			
				<div class="controls">
				<textarea id="organName" class="span2" disabled="disabled" class="ui-widget-content"  placeholder="组织结构">${user.orgName}</textarea>
				<input id="organId" type="hidden" name="orgId" value="${user.orgId}"">
				<a class="page_organ btn btn-primary" href="user_showOrgan.action?organ.branchId=${user.branchId}&user.orgId=${user.orgId}">设置</a>	
			</div>
		</div>
		<c:if test="${user.id != null }">
		<div class="control-group">
			<label class="control-label" for="trueName">权限组</label>
			<div class="controls">
				<textarea id="userRoleName" class="span2" disabled="disabled" class="ui-widget-content"  placeholder="权限">${user.roleNames }</textarea>
				<input id="userRoleId" type="hidden" name="userRoleId" value="${user.roleIds }">
				<a class="page_assign_role btn btn-primary" href="user_showRole.action?user.id=${user.id}">设置</a>	
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="mobile">手机</label>
			<div class="controls">
				<input type="text" id="mobile" class="{validate:{mobile:true,messages:{email:'手机号码输入错误'}}}"  value="${user.mobile}" name="mobile" placeholder="手机">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="phone">电话</label>
			<div class="controls">
				<input type="text" class="{validate:{phone:true,messages:{email:'电话号码输入错误'}}}" id="phone" name="phone" value="${user.phone}" placeholder="电话">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="email">邮箱</label>
			<div class="controls">
				<input type="text" class="{validate:{email:true,messages:{email:'电子邮箱格式错误'}}}" id="email" name="email" value="${user.email}" placeholder="邮箱">
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
			
	</script>
