<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="row-fluid">
	<div class="span10 ">
		<h4>
			<c:if test="${method == 'manage'}">社保类型管理<small></small></c:if> 
			<c:if test="${method == 'create'}">社保类型新增<small></small></c:if> 
			<c:if test="${method == 'view'}">社保类型查询<small></small></c:if> 
			<c:if test="${method == 'modify'}">社保类型修改<small></small></c:if> 
			<c:if test="${method == 'audit'}">社保类型操作审批<small></small></c:if> 
			<c:if test="${method == 'status'}">社保类型状态维护<small></small></c:if> 
		</h4>
		<!-- 列表页面显示的按钮 -->
		<div class="btn-toolbar list_bottoms">
			<div class="btn-group">
				<c:if test="${method == 'manage' || method == 'create' }">
				<a id="page-create" class="btn page_edit" href="socialBenefitBaseApply_edit.action"><i class="icon-pencil"></i>新建</a>
				</c:if>
				
				<a id="page-query" class="btn" data-toggle="modal" href="#query"><i class="icon-search"></i>查询</a>
			</div>
			<%-- <div class="btn-group">
				<c:if test="${method == 'manage' || method == 'create' || method == 'modify' }">
					<AT:AuthorityTag actionName="socialBenefitBaseApply_importfile.action">
					<a class="btn page_import" url="socialBenefitBaseApply_importpre.action?method=${method}" href="#"><i class="icon-upload"></i>导入</a>
					</AT:AuthorityTag>
				</c:if>
				<c:if test="${method == 'manage' || method == 'modify' || method == 'view'}">
					<AT:AuthorityTag actionName="socialBenefitBaseApply_export.action">
						<a class="btn page_export" url="socialBenefitBaseApply_export.action" href="#"><i class="icon-download"></i>导出</a>
					</AT:AuthorityTag>
				</c:if>
			</div> --%>
			<c:if test="${method == 'audit'}">
				<div class="btn-group">
					<a id="page-batchAudit" class="btn" url="socialBenefitBaseApply_batchAudit.action" ><i class="icon-check"></i>批量审批</a>
				</div>
			</c:if>
		</div>
		
		<!-- 编辑页面显示的按钮 -->
		<div class="btn-toolbar edit_bottoms" style="display: none">
			<a class="btn  page_save" href="#"><i class="icon-ok"></i>保存</a>
			<a class="btn  page_back " href="#"><i class="icon-remove"></i>取消</a>
		</div>
		
		<!-- 详细页面显示的按钮 -->
		<div class="btn-toolbar detail_bottoms" style="display: none">
			<a class="btn page_back_detail" href="#"><i class="icon-share-alt"></i>返回</a>
		</div>
		
	</div>
	<div class="span2">
		<!-- <img src="resource/images/logo.jpg"
			style="height: 80px; width: 96px; float: right;"> -->
	</div>
</div>
