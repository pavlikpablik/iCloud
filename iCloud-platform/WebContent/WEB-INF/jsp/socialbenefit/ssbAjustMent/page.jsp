<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<link rel="stylesheet" href="resource/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<div class="pageContainer">

<jsp:include page="header.jsp"></jsp:include>

<div class="query modal hide fade" id="query">
		<form class="queryForm form-horizontal" name="queryForm" id="queryForm"
			action="ssbajustment_query.action">
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

		$("a.ssbAjust_save").live("click",function(){
			var _editForm  = $("#editForm");
			var _action = _editForm.attr("action");
			var ssbName=$("#ssbId").find('option:selected').text();
			var candName=$("#candidateName").val();
			var candNo=$("#candidateNo").val();
			var monthFee=$("#monthFee").val();
			//alert(candName);
			var _data = _editForm.serialize()+"&candAjustMent.ssbName="+ssbName;
			//alert(_data);
			//return ;
			$.ajax({
				url : _action,
				type : "POST",
				data : _data,
				async : false,
				cache : false,
				dataType : "json",
				success : function(json) {
					if(!json.result)
					{	failNotify(json,$("#debug"));
						return;
					}else{
						successNotify(json);
						$("div.list_bottoms").show();
						$("div.edit_bottoms").hide();
						$("div.detail_bottoms").hide();
						$("div.pageContainer>div.list").show();
						$("div.pageContainer>div.edit").hide();
						$("div.pageContainer>div.detail").hide();
						$("a.prepaginggo").trigger("click");
					}
				}
			});
		});
	</script>
</div>
