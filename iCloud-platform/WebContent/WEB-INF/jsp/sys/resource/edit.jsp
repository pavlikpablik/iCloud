<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">
<form action="resource_save.action"  id="editForm" name="editForm" class="form-horizontal" >
<input type="hidden" name="resource.id" value="${resource.id}"/>
<div id="editDiv">
	<p style="cursor: pointer;">基本信息</p>
	
		<div class="control-group">
			<label class="control-label">资源名称</label>
			<div class="controls">
			<input placeholder="资源名称" class="{validate:{required:true,messages:{required:'资源名称不能为空'}}} text-input ui-widget-content" type="text" id="resource_title"name="resource.fullName" value="${resource.fullName}" />&nbsp;</td>
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">资源别名</label>
			<div class="controls">
			<input placeholder="资源别名" class="{validate:{required:true,messages:{required:'资源别名不能为空'}}} text-input ui-widget-content" type="text" id="resource_title"name="resource.name" value="${resource.name}" />&nbsp;</td>
		</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">类型</label>
			<div class="controls">
			<s:select list="#{1:'按钮',0:'菜单'}" name="resource.type" listKey="key" listValue="value" /> 
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">超级用户</label>
			<div class="controls">
			<s:select list="#{1:'是',0:'否'}" name="resource.issys" listKey="key" listValue="value" /> 
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态</label>
			<div class="controls">
			<s:select list="#{1:'启用',0:'停用'}" name="resource.enabled" listKey="key" listValue="value" /> 
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接</label>
			<div class="controls">
				<input placeholder="链接" class="text-input ui-widget-content" type="text" id="resource_url" name="resource.url" value="${resource.url}" >
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
		<textarea name="resource.remark" class="ui-widget-content"  disabled="disabled">${resource.remark}</textarea></td>
		</div>
		</div>
	</table>
	<p style="cursor: pointer;">层级关系</p>
	<table> 
		<div class="control-group">
			<label class="control-label">层级关系</label>
			<div class="controls">
				<select id="resource_sourceLevel" name="resource.sourceLevel" class="ui-widget-content">
				<c:forEach begin="1" end="${max_level}" step="1" var="i">
					<option value="${i}" <c:if test="${resource.sourceLevel == i}">selected="selected"</c:if> >第${i}层</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级资源</label>
			<div class="controls">
				<select class='ui-widget-content' id="resource_parentId" name="resource.parentId">
				</select>
				</div>
		</div>
		
	
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	$('#resource_sourceLevel').change(function(){
		renderresourceParentByLevel();
	});
	function renderresourceParentByLevel(){
		if($('#resource_sourceLevel').val() != ''){
			var val1 = $('#resource_sourceLevel').val();
			var val2 = $.ajax({
					url:'resource_renderresourceParentByLevel.action?resource.sourceLevel='+val1+'&resource.id=${resource.id}',
					type:'POST',
					async: false
		   		}).responseText;
			$('#resource_parentId').empty();
			$('#resource_parentId').append(val2);
		}
	}
	renderresourceParentByLevel();
	
	
	//图标展示 开始
	var iconval = $("#icon").val();
	if(iconval != ""){
		$("#iconPicture").addClass("resource-icon-item").addClass("resource-icon").addClass(iconval);
	}
	
	$("#icon").change(
	   		function(){
	   			$("#iconPicture").attr("class" , "");
	   			iconval = $(this).val();
	   			if(iconval != null && iconval != ""){
	   				$("#iconPicture").addClass("resource-icon-item").addClass("resource-icon").addClass(iconval);
	   			}
	   		}
	);
	//图标展示 结束
}); 
</script>