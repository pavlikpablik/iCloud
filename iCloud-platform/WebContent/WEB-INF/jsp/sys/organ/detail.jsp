<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
ul.treedetail {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>

<input type="hidden" id="organId" name="organ.id" value="${organ.id}"/>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span4">
		<div id="editDiv">
	<p style="cursor: pointer;">基本信息</p>
	
		<div class="control-group">
			<label class="control-label">名称</label>
			<div class="controls">
			<input  type="text" id="organ_title" disabled='disabled' name="organ.name" value="${organ.name}" />&nbsp;</td>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
		<textarea name="organ.remark" disabled='disabled'  >${organ.remark}</textarea></td>
		</div>
		</div>
	<p style="cursor: pointer;">组织架构</p>
		<div class="control-group">
			<label class="control-label">上层</label>
			<div class="controls" id="selectlevel">
				<input id="pName" type="text"  disabled='disabled' value="${organ.parentName}">
				<input id="pId" type="hidden" name="organ.parentId"  value="${organ.parentId}">
			</div>
		</div>
</div>
    </div>
    <div class="span8">
		<div class=" control-group">
			<input type="hidden"  name="organ.id" value="${organ.id}"/>
		</div>
		
		<div class=" control-group">
				<ul id="treeDetail" class="ztree treedetail"></ul>
		</div>
    </div>
  </div>
</div>
<script type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false,
				fontCss: setFontCss
			},
			check: {
				enable: false,
				chkStyle: "radio" 
				/*  chkboxType: { "Y" : "ps", "N" : "s" } */
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		setting.check.radioType = "all"; 
		$(function(){
			//alert(${organ.id});
			 	$.ajax({
					url : 'organ_queryAllTreeByBranchId.action?organ.id='+"${organ.id}",
					cache : false,
					async : false,
					dataType : "json",
					success : function(josn) {
						zNodes = josn;
					}
				});  
				$.fn.zTree.init($("#treeDetail"), setting, zNodes);
				$.fn.zTree.getZTreeObj("treeDetail").expandAll(true);
			
		});
//禁用所有单选并且给默认的当前选择的组织加上红色字体
		function setFontCss(treeId, treeNode) {
	        var id=$("#organId").val();
	        var treeObj = $.fn.zTree.getZTreeObj("treeDetail");
	        treeObj.setChkDisabled(treeNode, true);
			return treeNode.id == id ? {color:"red"} : {};
		};
	</script>

