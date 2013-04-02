<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
var item =$('input:radio[name=importModelRadio]:checked').val();
$("#importconfig").attr("upload_default_importModel",item);
</script>
<link href="resource/javascript/sys/uploadfile/upload.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resource/javascript/sys/uploadfile/swfupload.js"></script>
<script type="text/javascript"
	src="resource/javascript/sys/uploadfile/swfupload.queue.js"></script>
<script type="text/javascript"
	src="resource/javascript/sys/uploadfile/fileprogress.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/handlers.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/upload.js"></script>
<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>导入社保申报确认信息</h4>
</div>
<div class="modal-body">
<%-- 	<div class="alert fade in">
	    <a class="close" data-dismiss="alert" href="#">×</a>
	    <strong>温馨提示：</strong> 选择<strong>新增</strong>Excel中有编号的数据，不会被新增；选择<strong>修改</strong>Excel中没有编号的数据，不会被更新。
	</div> --%>
	<div id="importconfig" 
	     upload_url="ssbdeclarationconfirmation_importSsbDeclarationConfirmation.action;jsessionid=${cookie.JSESSIONID.value}"
	     file_types="*.xlsx" 
	     file_post_name="uploadFile" 
	     file_types_description="Excel 2007"
	     upload_progressTarget="fsUploadProgress" 
	     upload_button_placeholder="spanButtonPlaceHolder" 
	     >
	</div>
	<div  id="fsUploadProgress">
		<span >
		
					</span>
	</div>
	<div>
			<span id="spanButtonPlaceHolder"></span>
	        <span style="height: 32px; vertical-align: bottom;">&nbsp;<a class="downexceltemp" href="ssbdeclarationconfirmation_downloadTemplate.action">模板下载</a></span>
	</div>
	<div id="excelmessage">
	     
	</div>
</div>

