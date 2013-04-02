<%@ page contentType="text/html;charset=utf-8"%>

<div class="row-fluid">
	<div class="span12">
		<h4>
			员工社保调整管理 <small></small>
		</h4>
		<!-- 列表页面显示的按钮 -->
		<div class="btn-toolbar list_bottoms">
			<div class="btn-group">
				<a  class="btn " data-toggle="modal" href="#query"><i class="icon-search"></i>查询</a>
			</div>
			<div class="btn-group">
				<a  class="btn page_edit" href="ssbajustment_edit.action"><i class="icon-pencil"></i>新建</a>
			</div>
			<!-- 此功能暂时隐藏 
 		     <div class="btn-group">
				<a class="btn" id="page-passSychn"  href="#"><i class="icon-ok"></i>引入</a>
			</div> 
			<div class="btn-group">
				<a class="btn page_import" data-toggle="modal" url="candssbAjustMent_importFromExcel.action" href="#"><i class="icon-upload"></i>导入员工社保调整</a>
			</div>
-->
		</div>
		
		
		<!-- 编辑页面显示的按钮 -->
		<div class="btn-toolbar edit_bottoms" style="display: none">
			<a class="btn ssbAjust_save" href="#"><i class="icon-ok"></i>保存</a>
			<a class="btn page_back " href="#"><i class="icon-share-alt"></i>取消</a>
		</div>
		
		<!-- 详细页面显示的按钮 -->
		<div class="btn-toolbar detail_bottoms" style="display: none">
			<a class="btn page_back_detail" href="#"><i class="icon-share-alt"></i>返回</a>
		</div>
		
	</div>
	<!-- <div class="span2">
		<img src="resource/images/logo.jpg"
			style="height: 80px; width: 96px; float: right;">
	</div> -->
	
</div>
