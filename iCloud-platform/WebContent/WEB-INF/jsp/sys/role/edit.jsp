<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<form class="form-horizontal" action="role_save.action"
	id="editForm" name="editForm" method="post">
	<s:hidden name="role.id"></s:hidden>
	<s:hidden name="role.createBy"></s:hidden>
	<s:hidden name="role.createDate"></s:hidden>
	<s:hidden name="role.modifyBy"></s:hidden>
	<s:hidden name="role.modifyDate"></s:hidden>
	<s:hidden name="role.status"></s:hidden>
	<div>
		<div class="control-group">
			<label class="control-label" for="name">权限组名称*</label>
			<div class="controls">
				<input type="text" class="{validate:{required:true,messages:{required:'权限名称不能为空'}}} " id="loginName" value="${role.name}" name="role.name" placeholder="权限组名称">
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="sort">排序</label>
			<div class="controls">
				<input type="text" id="sort" name="role.sort" value="${role.sort}"
					placeholder="排序">
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
				<textarea name="role.remark" class="ui-widget-content"  >${role.remark}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态</label>
			<div class="controls">
			<s:select list="#{1:'启用',0:'停用'}" name="role.enabled" listKey="key" listValue="value" /> 
			</div>
		</div>
		
	</div>
</form>
