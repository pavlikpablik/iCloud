<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="container">
<form action="organ_save.action"  id="editForm" name="editForm" class="form-horizontal" >
<input type="hidden"  name="organ.id" value="${organ.id}"/>
<div id="editDiv">
	<p style="cursor: pointer;">基本信息</p>
	
		<div class="control-group">
			<label class="control-label">名称</label>
			<div class="controls">
			<input class="{validate:{required:true,messages:{required:'名称不能为空'}}} text-input ui-widget-content" type="text" id="organ_title"name="organ.name" value="${organ.name}" placeholder="名称"/>&nbsp;</td>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
		<textarea name="organ.remark" class="ui-widget-content"  >${organ.remark}</textarea></td>
		</div>
		</div>
	<p style="cursor: pointer;">组织架构</p>
		<div class="control-group">
			<label class="control-label">上层</label>
			<div class="controls" id="selectlevel">
				<input id="pName" type="text" class="span2" disabled='disabled' value="${organ.parentName}" >
				<a class="page_level btn  btn-primary" href="organ_level.action?organ.branchId=${organ.branchId}&organ.id=${organ.id}">选择</a>	
				<input id="pId" type="hidden" name="organ.parentId"  value="${organ.parentId}">
			</div>
		</div>
</div>
</form>
</div>
