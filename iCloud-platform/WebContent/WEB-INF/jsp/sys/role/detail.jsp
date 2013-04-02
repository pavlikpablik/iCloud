<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
ul.treedetail {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
div.userdetail {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>

	
<form class="form-horizontal" action=""
	id="editForm" name="editForm" method="post">
	<input type="hidden" id="roleId" name="organ.id" value="${role.id}"/>
	<div class="row-fluid" >
		<div class="span4">
			<div class="control-group">
			<label class="control-label" for="name">权限名称</label>
			<div class="controls">
				<input type="text" disabled="disabled"  id="RoleName" value="${role.name}" name="role.name" placeholder="权限名称">
			</div>
			</div>
			<div class="control-group">
				<label class="control-label">描述</label>
				<div class="controls">
					<textarea name="role.remark" disabled="disabled" class="ui-widget-content"  >${role.remark}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">状态</label>
				<div class="controls">
				<s:select list="#{1:'启用',0:'停用'}" disabled="true" name="role.enabled" listKey="key" listValue="value" /> 
				</div>
			</div>
		</div>
		
		<div class="span4">
			<label class="control-label" for="name">员工</label>
			<table>
			<c:forEach items="${userList}" var="user">
					<div class="control-group">
					<div class="controls">
						<input type="text" disabled="disabled" value="${user.userName }" >
				</div>
				</div>
				</c:forEach>
			</table>
		</div>
		<div class="span4">
			<label class="control-label" for="name">资源</label>
			<div class="controls">
				<ul id="treeUser" class="ztree treedetail"></ul>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false,
				fontCss: setFontCss
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		setting.check.radioType = "all"; 
		$(function(){
			
			 	$.ajax({
					url : 'resource_queryRoleResourceTree.action?roleId='+"${role.id}",
					cache : false,
					async : false,
					dataType : "json",
					success : function(josn) {
						zNodes = josn;
					}
				});  
				
				$.fn.zTree.init($("#treeUser"), setting, zNodes);
				$.fn.zTree.getZTreeObj("treeUser").expandAll(true);
			
		});
		//禁用所有单选并且给默认的当前选择的组织加上红色字体
		function setFontCss(treeId, treeNode) {
	        var treeObj = $.fn.zTree.getZTreeObj("treeUser");
	        treeObj.setChkDisabled(treeNode, true);
			return ;
		};
	</script>
