<%@ page contentType="text/html;charset=utf-8"%>

<div class="row-fluid">
	<div class="span12">
		<h4>
			员工社保调整审核 <small></small>
		</h4>
		<!-- 列表页面显示的按钮 -->
		<div class="btn-toolbar list_bottoms">
			<div class="btn-group">
				<a  class="btn " data-toggle="modal" href="#query"><i class="icon-search"></i>查询</a>
			</div>
			<div class="btn-group">
				<a  class="btn " id="page-passStatus" href="#"><i class="icon-ok"></i>通过</a>
			</div>
			<div class="btn-group">
				<a  class="btn " id="page-turnStatus" href="#"><i class="icon-remove"></i>退回</a>
			</div>
		</div>
		
		<!-- 详细页面显示的按钮 -->
		<div class="btn-toolbar detail_bottoms" style="display: none">
			<a class="btn page_back_detail" href="#"><i class="icon-share-alt"></i>返回</a>
		</div>
	</div>
	
</div>
