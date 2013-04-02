<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="resource/javascript/sys/uploadfile/handlers.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/upload.js"></script>
	
<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>社保类型导入</h4>
</div>
<div class="modal-body">

	<div class="alert fade in">
	    <a class="close" data-dismiss="alert" >×</a>
	    <strong>温馨提示：</strong> 选择<strong>新增</strong>Excel中有编号的数据，不会被新增；选择<strong>修改</strong>Excel中没有编号的数据，不会被更新。
	</div>
	
	<div id="importconfig" 
	     upload_url="socialBenefitBaseApply_importfile.action;jsessionid=${cookie.JSESSIONID.value}"
	     file_types="*.xlsx" 
	     file_post_name="uploadFile" 
	     file_types_description="Excel 2007"
	     upload_progressTarget="fsUploadProgress" 
	     upload_button_placeholder="spanButtonPlaceHolder" 
	     >
	</div>
		<div class="fieldset flash" id="fsUploadProgress">
			<span class="legend">
			<c:if test="${method == 'manage' }">
			导入数据方式：<input checked="checked" type="radio" class="importModelCheck" name="importModelRadio" value="insert">新增&nbsp;&nbsp;<input type="radio" class="importModelCheck" name="importModelRadio" value="update">修改
			</c:if>
			<c:if test="${method == 'modify' }">
			导入修改数据
			</c:if>
			<c:if test="${method == 'create' }">
			导入新增数据
			</c:if>
			</span>
		</div>
		<div>
			<span id="spanButtonPlaceHolder"></span>
	         <a class="btn downexceltemp" href="socialBenefitBaseApply_downloadTemplate.action">模板下载</a></span>
	</div>
	<div id="excelmessage">
	     
	</div>
</div>
<div class="modal-footer">
	<a  class="btn" data-dismiss="modal">关闭</a>
</div>




