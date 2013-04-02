/**
 * leo.wang
 * 文件上传
 * upload_url: $("div.import").attr("upload_url") 有上传的div进行提供
 */

var swfu;
$(function(){
	
	/**
	 * 上传文件的配置信息
	 */
	
	//上传路径action
	var upload_url = $("#importconfig").attr("upload_url"); 
	//上传文件类型 *.*所有文件  指定多个文件类型*.txt;*.jpg;*.jpeg 多个文件类型之间用;分割
	var file_types = $("#importconfig").attr("file_types"); 
	//上传到action中接收参数
	var file_post_name = $("#importconfig").attr("file_post_name"); 
	//上传文件描述
	var file_types_description = $("#importconfig").attr("file_types_description"); 
	//上传文件的progress ID
	var upload_progressTarget =  $("#importconfig").attr("upload_progressTarget");
	//上传按钮ID
	var upload_button_placeholder = $("#importconfig").attr("upload_button_placeholder");
	
	var upload_flash_url = "resource/javascript/sys/uploadfile/swfupload.swf";
	
	var upload_button_image_url = "resource/javascript/sys/uploadfile/UploadImageNoText_65x32.png";
	
	var upload_default_importModel = $("#importconfig").attr("upload_default_importModel") || "insert";
	
	var settings = {
			flash_url : upload_flash_url,
			upload_url: upload_url,
			use_query_string:true,
			post_params: {"importmodel" : upload_default_importModel},
			file_post_name: file_post_name,  
			file_size_limit : "30 MB",
			file_types : file_types,
			file_types_description : file_types_description,
			file_upload_limit : 0,
			file_queue_limit : 1,
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
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete
			//queue_complete_handler : queueComplete	// Queue plugin event

		};
		swfu = new SWFUpload(settings);

		$(".importModelCheck").click(
		  function(){
			  var _this = $(this);
			  swfu.addPostParam("importmodel",_this.val());
			 
		  }		
		);
});
