/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
*/


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
function uploadfileQueued(file) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("正在上传...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}

}

function uploadfileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			//alert(message);
			//将message === 0 修改为==
			alert("上传队列中已经存在太多文件.\n" + (message == 0 ? "目前已经达到上限,如果想上传新文件,请删除已上传文件." : "您可以选择 " + (message > 1 ? "上传 " + message + "个文件." : "1 个文件.")));
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("上传文件大小不能超过"+$("#uploadfileconfig").attr("file_size_limit"));
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("Cannot upload Zero Byte files.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("Invalid File Type.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadfileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			//document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		
		/* I want auto start the upload and I can do that here */
		this.startUpload();
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadfileStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("上传中...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	
	return true;
}

function uploadfileProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus("上传中...");
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadfileSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		if(serverData != null){
			var fileUpload = $.parseJSON(serverData);
			appendJsonToShowUploadMessage(fileUpload);
			appendUploadFileValues(fileUpload);
			progress.setStatus("已完成.");
			//alert(uploadFileSwf.getStats().successful_uploads);
		}else{
			progress.setStatus("上传失败.");
		}
		progress.toggleCancel(false);

	} catch (ex) {
		this.debug(ex);
	}
}



/**
 * 追加新增的信息
* <p>Date : 2011-12-8</p>
* @author :lei.wang
 */
function appendUploadFileValues(fileUpload){
	$("#uploadfileconfig").attr("upload_file_values" , $("#uploadfileconfig").attr("upload_file_values" ) + fileUpload.fileId +",");
}


/**
 * 删除操作
* <p>Date : 2011-12-8</p>
* @author :lei.wang
 */
function uploadfiledelete(uploadfile){
	if (confirm("确定要删除?")) {
	  $.ajax({
		   type: "POST",
		   url: $("#uploadfileconfig").attr("upload_delete_url"),
		   cache:false,
		   data: "uploadfileId="+$(uploadfile).attr("uploadfileId"),
		   dataType:"json",
		   success: function(fileUpload){
			   if(fileUpload != null){
				   if(fileUpload.deleteFlag == -1){
					   changeUploadFileValues(fileUpload.fileId);
					   $("#up"+fileUpload.fileId).remove();
					   var objStats = uploadFileSwf.getStats();
					   objStats.successful_uploads = objStats.successful_uploads - 1;
					   uploadFileSwf.setStats(objStats);
				   }
			   }
		   }
		});
  }
}	

/**
 * 显示上传文件信息
* <p>Date : 2011-12-8</p>
* @author :lei.wang
 */
function appendJsonToShowUploadMessage(fileUpload){
	var showUploadMessage = $("#uploadfileconfig").attr("showUploadMessage");
	var download_url = $("#uploadfileconfig").attr("download_url");
	var appendTr= "<tr id='up"+fileUpload.fileId+"'><td style='border-top: #aaaaaa 1px solid;'>";
	appendTr = appendTr + fileUpload.fileName;
	appendTr = appendTr + "</td><td align='center' style='border-top: #aaaaaa 1px solid;'><a class='downexceltemp pagetableoperate ui-button ui-widget ui-state-default' href='"+download_url+"?uploadfileId="+fileUpload.fileId+"'><span class='ui-icon ui-icon-arrowthickstop-1-s'></span>下载</a>&nbsp;<a class='uploadfiledelete pagetableoperate ui-button ui-widget ui-state-default' uploadfileId='"+fileUpload.fileId+"' onclick='uploadfiledelete(this)'><span class='ui-icon ui-icon-trash'></span>删除</a>";
	appendTr = appendTr + "</td></tr>";
	//alert(appendTr);
	$("#"+showUploadMessage).append(appendTr);
}

/**
 * 删除修改upload_file_values
* <p>Date : 2011-12-8</p>
* @author :lei.wang
 */
function changeUploadFileValues(fileId){
	   var fileIds = $("#uploadfileconfig").attr("upload_file_values").split(",");
	   var ufvs = ",";
	   $.each(fileIds, function(i, fid){
		     if(fid != null && fid != "" && fid != fileId){
		    	 ufvs = ufvs + fid + ",";
		     }
		 });
	   $("#uploadfileconfig").attr("upload_file_values" , ufvs);
}

/**
 * 初始化upload_file_values
* <p>Date : 2011-12-8</p>
* @author :lei.wang
 */
function initUploadFileValues(fileUploads){
	   var ufvs = ",";
	   var upload_sum = 0;
	   if(fileUploads != null){
		   $.each(fileUploads, function(i, fileUpload){
			   appendJsonToShowUploadMessage(fileUpload);
			   ufvs = ufvs + fileUpload.fileId + ",";
			   upload_sum = upload_sum + 1;
			 });
	   }
	   var objStats = uploadFileSwf.getStats();
	   objStats.successful_uploads = upload_sum;
	   uploadFileSwf.setStats(objStats);
	   //alert(uploadFileSwf.getStats().successful_uploads);
	   $("#uploadfileconfig").attr("upload_file_values" , ufvs);
}

function uploadfileError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				//document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadfileComplete(file) {
	if (this.getStats().files_queued === 0) {
		//document.getElementById(this.customSettings.cancelButtonId).disabled = true;
	}
}

// This event comes from the Queue Plugin
function uploadfilequeueComplete(numFilesUploaded) {
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " 文件" + (numFilesUploaded === 1 ? "" : "s") + " 已上传.";
}
