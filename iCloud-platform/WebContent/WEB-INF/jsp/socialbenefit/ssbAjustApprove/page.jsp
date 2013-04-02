<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<link href="resource/javascript/sys/uploadfile/upload.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.js"></script>
    <script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.queue.js"></script>
    <script type="text/javascript" src="resource/javascript/sys/uploadfile/fileprogress.js"></script>
<div class="pageContainer">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 用于保存 -->
		<input type="hidden" class="queryCondition"/>
		<!-- 保存页面分页页面checkbox条件类 -->
		<input type="hidden" class="chkboxChoosed" id="chkboxChoosedId"/>
		<!-- 定义页面内部查询条件类 -->
		
		<div class="query modal hide fade" id="query">
			<form class="queryForm form-horizontal" name="queryForm" action="candSsbAjustMentCheck_query.action" id="queryForm"">
			<!--开始 页面控制 -->
			<!--结束 页面控制 -->
			<jsp:include page="query.jsp"></jsp:include>
			</form>
		</div>
	
		<!-- 定义页面查询结果及分页类 -->
		<div class="list" id="ssbAjustMentcheckListId">
			<jsp:include page="list.jsp"></jsp:include>
		</div>
		<!-- 编辑页面 -->
		<div class="edit"></div>
		<!-- detail页面 -->
		<div class="detail" id="ssbAjustMentcheckDetailId"></div>
	</div>
	
	<script type="text/javascript">
	$( "#page-passStatus" ).click(function(){
		var ids = "";
		if($("#ssbAjustMentcheckListId").is(":visible")){
			ids = $("#chkboxChoosedId").val();
			if(ids==null || ids==""){
				alert("请选择要操作的数据!");
				return false;
			}
		}else if($("#ssbAjustMentcheckDetailId").is(":visible")){
			ids = $("#candAjustMentId").val();
		}
		$.ajax({
			url : 'candSsbAjustMentCheck_changeStatusAll.action?ids='+ids+'&status=2',
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(json) {
				//如果出错或者后台校验不通过
				if(!json.result)
				{
					failNotify(json,$("#debug"));
				}else{
					successNotify(json);
					page.data.chkboxChoosed = [];
					$("#chkboxChoosedId").val("");
					$("a.prepaginggo").trigger("click");
				}
			}
		});
	});
	
	$( "#page-turnStatus" ).click(function(){
		var ids = "";
		if($("#ssbAjustMentcheckListId").is(":visible")){
			ids = $("#chkboxChoosedId").val();
			if(ids==null || ids==""){
				alert("请选择要操作的数据!");
				return false;
			}
		}else if($("#ssbAjustMentcheckDetailId").is(":visible")){
			ids = $("#candAjustMentId").val();
			if(ids==null || ids=="")
			{
				alert("请选择有效的数据!");
				return false;	
			}
		}
		$.ajax({
			url : 'candSsbAjustMentCheck_changeStatusAll.action?ids='+ids+'&status=3',
			type : "POST",
			cache : false,
			dataType : "json",
			success : function(json) {
				//如果出错或者后台校验不通过
				if(!json.result)
				{	
					failNotify(json,$("#debug"));
				}else{
					successNotify(json);
					 page.data.chkboxChoosed = [];
					 $("a.prepaginggo").trigger("click");
				}
			}
		});
	});
	</script>
