<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<link href="resource/javascript/sys/uploadfile/upload.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.js"></script>
    <script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.queue.js"></script>
    <script type="text/javascript" src="resource/javascript/sys/uploadfile/fileprogress.js"></script>
<div class="pageContainer">
		<div class="row-fluid">
			<div class="page-header span12">
				<h4>
					社保申报核对确认<small>&nbsp;您可以按照条件导入需要申报确认的社保数据</small>
				</h4>
			</div>
		</div>
	
		<!-- 定义页面查询结果及分页类 -->
		<div class="list">
			<form class="queryForm form-horizontal" id="queryFormCheck" action="candSsbCheckAffirm_export.action" method="post">
				<jsp:include page="export.jsp"></jsp:include>
			</form>
		</div>
		<!-- 编辑页面 -->
		<div class="edit"></div>
		<!-- detail页面 -->
		<div class="detail"></div>
		
		<!-- 导入 -->
		<div class="modal fade import" id="excel_import">
			
		</div>
	</div>
<script type="text/javascript">
//如果提交表单时，查询条件为空时，询问
$(document).ready(function() {
	$("#exportSubmit").click(function(){
		var candId=$("#candId").val();
		var candName=$("#candName").val();
		var candNo=$("#candNo").val();
		var clientName=$("#clientName").val();
		var provinceId=$("#provinceId").val();
		var cityId=$("#cityId").val();
		var socialBenefitHeaderId=$("#candSsbsocialBenefitHeaderid").val();
		var vendorName=$("#vendorName").val();
		var candids=$("#candids").val();
		if( candId=="" && candName=="" && candNo=="" && clientName=="" 
				&& provinceId=="" && cityId=="" && socialBenefitHeaderId=="" && vendorName=="" && candids==""){
			/* if(!confirm("查询条件为空，是否要继续？")){
			return false;
			} */
			alert("请至少输入一个查询条件！");
			return false;
		}
	});
});
</script>
<!-- <script type="text/javascript">
$("#exportSubmit").click(function() {
 	var candids = $("#candids").val();
 	var _form = $("#queryFormCheck");
	candids = ReplaceSeperatorCheck(candids);
	$.ajax({
		url : "candSsbCheckAffirm_export.action?candids="+candids,
		data : _form.serialize(),
		type : "POST",
		async : false,
		cache : false,
		dataType : "html",
		success : function(html) {
		}
	});
  
});

//用分号替换中英文分号、中文逗号或者回车
function ReplaceSeperatorCheck(mobiles) {
    var i;
    var result = "";
    var c;
    for (i = 0; i < mobiles.length; i++) {
        c = mobiles.substr(i, 1);
        if (c == ";" || c == "，" || c == "\n" || c == "；" )
            result = result + ",";
        else if (c != "\r")
            result = result + c;
    }
    return result;
}
</script> -->
