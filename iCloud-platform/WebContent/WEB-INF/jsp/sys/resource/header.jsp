<%@ page contentType="text/html;charset=utf-8"%>

<div class="row-fluid">
	<div class="span10">
		<h4>
			资源管理 <small>您可以创建或者修改资源。</small>
		</h4>
		<!-- 列表页面显示的按钮 -->
		<div class="btn-toolbar list_bottoms">
			<div class="btn-group">
				<a class="btn  page_edit"  href="resource_edit.action"><i class="icon-pencil"></i>新建</a>
				</div>
		</div>
		
		<!-- 编辑页面显示的按钮 -->
		<div class="btn-toolbar edit_bottoms" style="display: none">
			<a class="btn   page_save" ><i class="icon-ok"></i>保存</a>
			<a class="btn   page_back " ><i class="icon-remove"></i>取消</a>
		</div>
		
		<!-- 详细页面显示的按钮 -->
		<div class="btn-toolbar detail_bottoms" style="display: none">
			<a class="btn  page_back_detail" ><i class="icon-share-alt"></i>返回</a>
		</div>
		
	</div>
	<div class="span2">
		<!-- <img src="resource/images/logo.jpg"
			style="height: 80px; width: 96px; float: right;"> -->
	</div>
</div>