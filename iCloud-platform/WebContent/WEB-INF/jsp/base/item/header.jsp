<%@ page contentType="text/html;charset=utf-8"%>

<div class="row-fluid">
	<div class="span10">
		<h4>
			项目元素管理 <small>您可以创建或者修改项目元素。</small>
		</h4>
		<!-- 列表页面显示的按钮 -->
		<div class="btn-toolbar list_bottoms">
			<div class="btn-group">
				<a class="btn page_edit" href="item_edit.action"><i class="icon-pencil"></i>新建</a>
				<a class="btn" data-toggle="modal" href="#query"><i class="icon-search"></i>查询</a>
				<a class="btn" href="logon_init.action"><i class="icon-pencil"></i>初始化数据</a>
								
				
			</div>
		</div>
		
		<!-- 编辑页面显示的按钮 -->
		<div class="btn-toolbar edit_bottoms" style="display: none">
			<a class="btn page_save" href="#"><i class="icon-ok"></i>保存</a>
			<a class="btn page_back " href="#"><i class="icon-share-alt"></i>取消</a>
		</div>
		
		<!-- 详细页面显示的按钮 -->
		<div class="btn-toolbar detail_bottoms" style="display: none">
			<a class="btn page_back_detail" href="#"><i class="icon-share-alt"></i>返回</a>
		</div>
		
	</div>
</div>
