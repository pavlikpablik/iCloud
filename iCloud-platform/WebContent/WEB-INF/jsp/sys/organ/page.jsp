<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<link rel="stylesheet" href="resource/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<div class="pageContainer">

<jsp:include page="header.jsp"></jsp:include>

<div class="query modal hide fade" id="query">
		<form class="queryForm form-horizontal" name="queryForm" id="queryForm"
			action="organ_query.action">
			<jsp:include page="query.jsp"></jsp:include>
		</form>
	</div>

	<div class="editMenu"></div>
	
	<!-- 定义页面查询结果及分页类 -->
	<div class="list">
		<jsp:include page="list.jsp"></jsp:include>
	</div>
	<!-- 编辑页面 -->
	<div class="edit"></div>
	
	<!-- 详细页面 -->
	<div class="detail"></div>
	
	<!-- 分配组织架构 -->
	<div class="modal hide fade" id="level"></div>
	
	<!-- 导入 -->
	<div class="modal fade import" id="excel_import">
		
	</div>

	<!-- 用于保存 -->
	<input type="hidden" class="queryCondition" />
	<!-- 保存页面分页页面checkbox条件类 -->
	<input type="hidden" class="chkboxChoosed" />


	<script type="text/javascript">
		$(function() {

		});
	</script>
</div>
<script type="text/javascript">

	
	$(function(){
		$("a.page_level").live("click",function(){
			var _url = $(this).attr("href") || '';
			var div_authrAssign = $( "#level");
			$.ajax({
				url : _url,
				cache : false,
				dataType : "html",
				async : false,
				success : function(html) { 
					div_authrAssign.empty();
					div_authrAssign.html(html);
					$('#level').modal('show');
				}
			}); 
			return false;
		});
		$("#page_level_save").live("click",function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		        var nodes = treeObj.getCheckedNodes(true);  
		        var ids = "";  
		        var names = "";  
		        for (var i = 0; i < nodes.length; i++) {  
		        	if(i == nodes.length-1){
		        		ids += nodes[i].id;
		        		names += nodes[i].name;
		        	}else{
		        		ids += nodes[i].id + ",";  
		        		names += nodes[i].name + ",";  
		        	}
		        }  
		        $("#pId").val(ids);
		        $("#pName").val(names);
		});


		$("a.page_searchUser").live("click",function(){

			var _url = $(this).attr("href") || '';
			var div_authrAssign = $( "#level");
			$.ajax({
				url : _url,
				cache : false,
				dataType : "html",
				async : false,
				success : function(html) { 
					div_authrAssign.empty();
					div_authrAssign.html(html);
					$('#level').modal('show');
				}
			}); 
			return false;
		});

		
		
	});
	
</script>