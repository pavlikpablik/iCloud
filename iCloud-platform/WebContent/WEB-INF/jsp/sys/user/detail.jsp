<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
ul.treedetail {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
div.userdetail {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>

	
<form class="form-horizontal" action="user_save.action"
	id="editForm" name="editForm" method="post">
	<s:hidden name="user.id"></s:hidden>
	<s:hidden name="user.createBy"></s:hidden>
	<s:hidden name="user.createDate"></s:hidden>
	<s:hidden name="user.status"></s:hidden>
	<div class="row-fluid">
		<div class="span4">
			<div class="control-group">
				<label class="control-label" for="loginName">用户名*</label>
				<div class="controls">
					<input type="text" disabled="disabled" class="{validate:{required:true,messages:{required:'用户名不能为空'}}} " id="loginName" value="${user.loginName}" name="loginName" placeholder="用户名">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">密码*</label>
				<div class="controls">
					<input type="password" disabled="disabled" class="{validate:{required:true,messages:{required:'密码不能为空'}}} " id="password" name="password" value="${user.password}"
						placeholder="密码">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="surePassword">确认密码*</label>
				<div class="controls">
					<input type="password"  disabled="disabled"
					class="{validate:{checkPassword:'#password',required:true,messages:{required:'密码输入不一致'}}}" id="surePassword" name="surePassword" value="${user.password}"
						placeholder="确认密码">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="trueName">真实姓名</label>
				<div class="controls">
					<input type="text" disabled="disabled" id="trueName" value="${user.trueName}" name="trueName" placeholder="真实姓名">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="idCard">身份证号码</label>
				<div class="controls">
					<input type="hidden" id="idCardType" value="1"> <input value="${user.idcard}" disabled="disabled"
						class="{validate:{checkIDCard:true,messages:{required:'身份证件号码不能为空'}}}"
						type="text" id="idCard" name="idCard" placeholder="身份证号码">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="trueName">组织结构</label>
				<div class="controls">
					<input id="pName" type="text" disabled="disabled"  disabled='disabled'  value="${user.orgName}" placeholder="组织结构">
					<input id="pId" type="hidden" name="organ.id"  value="${user.organId}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="mobile">手机</label>
				<div class="controls">
					<input type="text" disabled="disabled" id="mobile" class="{validate:{mobile:true,messages:{email:'手机号码输入错误'}}}"  value="${user.mobile}" name="mobile" placeholder="手机">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">电话</label>
				<div class="controls">
					<input type="text" disabled="disabled" class="{validate:{phone:true,messages:{email:'电话号码输入错误'}}}" id="phone" name="phone" value="${user.phone}" placeholder="电话">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">邮箱</label>
				<div class="controls">
					<input type="text" disabled="disabled" class="{validate:{email:true,messages:{email:'电子邮箱格式错误'}}}" id="email" name="email" value="${user.email}" placeholder="邮箱">
				</div>
			</div>
		</div>
		<div class="span4">
			<label class="control-label" for="name">权限组</label>
			<div class="controls">
				<ul id="treeRoleDetail" class="ztree treedetail"></ul>
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
					url : 'resource_queryRoleByUser.action?userId='+"${user.id}",
					cache : false,
					async : false,
					dataType : "json",
					success : function(josn) {
						zNodes = josn;
					}
				});  
				
				$.fn.zTree.init($("#treeRoleDetail"), setting, zNodes);
				$.fn.zTree.getZTreeObj("treeRoleDetail").expandAll(true);
			
		});
		//禁用所有单选并且给默认的当前选择的组织加上红色字体
		function setFontCss(treeId, treeNode) {
	        var treeObj = $.fn.zTree.getZTreeObj("treeRoleDetail");
	        treeObj.setChkDisabled(treeNode, true);
			return ;
		};
	</script>
