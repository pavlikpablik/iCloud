/**
 * leo.wang
 * 文件上传
 * upload_url: $("div.import").attr("upload_url") 有上传的div进行提供
 */
var uploadFileSwf;
$(function(){
	/**
	 * 上传文件的配置信息
	 */
	
	//上传路径action
	var upload_url = $("#uploadfileconfig").attr("upload_url"); 
	//初始化
	var upload_init_url = $("#uploadfileconfig").attr("upload_init_url");
	//删除操作
	var upload_delete_url = $("#uploadfileconfig").attr("upload_delete_url");
	//上传文件类型 *.*所有文件  指定多个文件类型*.txt;*.jpg;*.jpeg 多个文件类型之间用;分割
	var file_types = $("#uploadfileconfig").attr("file_types"); 
	//上传到action中接收参数
	var file_post_name = $("#uploadfileconfig").attr("file_post_name"); 
	//上传文件描述
	var file_types_description = $("#uploadfileconfig").attr("file_types_description"); 
	//允许文件上传的文件大小 默认为100    单位为MB
	var file_size_limit = $("#uploadfileconfig").attr("file_size_limit") || "100 MB"; 
	//允许上传文件的个数 0 为无限多
	var file_upload_limit = $("#uploadfileconfig").attr("file_upload_limit") || 0; 
	//上传文件的progress ID
	var upload_progressTarget =  $("#uploadfileconfig").attr("upload_progressTarget");
	//上传按钮ID
	var upload_button_placeholder = $("#uploadfileconfig").attr("upload_button_placeholder");
	//已上传文件ID
	var upload_file_values = $("#uploadfileconfig").attr("upload_file_values");
	
	var upload_flash_url = "resource/javascript/sys/uploadfile/swfupload.swf";
	
	var upload_button_image_url = "resource/javascript/sys/uploadfile/UploadImageNoText_65x32.png";
	
	var uploadFileSettings = {
			flash_url : upload_flash_url,
			upload_url: upload_url,
			use_query_string:true,
			post_params: {"uploadFileValues" : upload_file_values || ""},
			file_post_name: file_post_name,  
			file_size_limit : file_size_limit,
			file_types : file_types,
			file_types_description : file_types_description,
			file_upload_limit : file_upload_limit,
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : upload_progressTarget
			},
			debug: false,

			// Button settings
			button_image_url: upload_button_image_url,
			button_width: "65",
			button_height: "32",
			button_placeholder_id: upload_button_placeholder,
			button_text: '',
			button_text_style: "",
			button_text_left_padding: 12,
			button_text_top_padding: 3,
			
			// The event handler functions are defined in handlers.js
			file_queued_handler : uploadfileQueued,
			file_queue_error_handler : uploadfileQueueError,
			file_dialog_complete_handler : uploadfileDialogComplete,
			upload_start_handler : uploadfileStart,
			upload_progress_handler : uploadfileProgress,
			upload_error_handler : uploadfileError,
			upload_success_handler : uploadfileSuccess,
			upload_complete_handler : uploadfileComplete
			//queue_complete_handler : uploadfilequeueComplete	// Queue plugin event

		};
	uploadFileSwf = new SWFUpload(uploadFileSettings);
	
	//init 数据
	$.ajax({
		   type: "POST",
		   url: upload_init_url,
		   cache:false,
		   data: "uploadFileValues="+upload_file_values,
		   dataType:"json",
		   success: function(fileUploads){
				  initUploadFileValues(fileUploads);
		   }
		});
});

