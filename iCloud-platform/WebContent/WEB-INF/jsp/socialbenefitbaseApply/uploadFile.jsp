<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="resource/javascript/sys/uploadfile/upload.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="resource/javascript/sys/uploadfile/uploadfilehandlers.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/uploadfile.js"></script>
	
<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>附件</h4>
</div>
<div class="modal-body">
	<div id="uploadfileconfig" 
		     upload_url="socialBenefitBaseApply_uploadFile.action;jsessionid=${cookie.JSESSIONID.value}?uploadOperate=upload" 
		     upload_init_url="socialBenefitBaseApply_uploadFile.action?uploadOperate=init"
		     upload_delete_url="socialBenefitBaseApply_uploadFile.action?uploadOperate=delete" 
		     download_url="socialBenefitBaseApply_downloadUploadFile.action" 
		     file_types="*.txt;*.rar;*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.zip;*.7z;*.jpg;*.gif;*.png" 
		     file_post_name="uploadSsbFile" 
		     file_types_description="上传附件,允许上传txt,rar格式的文件" 
		     file_size_limit="30 MB" 
		     file_upload_limit="3" 
		     upload_progressTarget="uploadfsUploadProgress" 
		     upload_button_placeholder="uploadspanButtonPlaceHolder" 
		     upload_file_values="${param.uploadFileValues }"
		     showUploadMessage="showUploadMessage" 
	></div>
	<div class="fieldset flash" id="uploadfsUploadProgress">
		<span class="legend">上传</span>
	</div>
	<div>
			<span id="uploadspanButtonPlaceHolder"></span>
	</div>
	<br/>
	<div>
	     <table class="table" >
	            <thead>
			    <tr>
				<th>文件名称</th>
				<th >操作</th>
			    </tr>
		      </thead>
		      <tbody id="showUploadMessage">
		        
		      </tbody>
	     </table>
	</div>
		
</div>
<div class="modal-footer">
	<a  class="btn" data-dismiss="modal">关闭</a>
</div>




