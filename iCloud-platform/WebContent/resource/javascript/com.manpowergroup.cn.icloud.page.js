var page = page || {};
page.data = page.data || {};
page.data.chkboxChoosed = [];
page.downloadFfile = function(url){
	if (typeof (this.iframe) == "undefined") {
		var iframe = document.createElement("iframe");
		this.iframe = iframe;
		document.body.appendChild(this.iframe);
	}
	// alert(download_file.iframe);
	this.iframe.style.display = "none";
	this.iframe.src = url;
};
page.isJson =  function(obj){
	var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
	return isjson;
};

Array.prototype.remove = function(dx)
  {
    for(var i=0;i<this.length;i++){
      if(this[i]==dx){
        this.splice(i,1);
        break;
      }
    }
  };
function successNotify(json){
	//保存成功
	$.pnotify({
    	title: '成功',
    	text: json.resultText,
    	type: 'success',
    	styling: 'bootstrap'
	});
}
function failNotify(json,debug){
	//保存失败提示信息
	$.pnotify({
    	title: '失败',
    	text: json.resultText,
    	type: 'error',
    	styling: 'bootstrap'
	});
	
	if(json.t && json.t.stackTrace){
		debug.empty();
		debug.append("<p>"+json.exceptionText+"</p>");
 		$.each(json.t.stackTrace, function(n, st){
 			debug.append("<p>at "+st.className+"."+st.methodName+"("+st.fileName+"."+st.lineNumber+")"+"</p>");
 		}); 
 	}
}
function initvalidate(){
	//重写默认设置
	$.validator.setDefaults({
		onclick:false,
		//onfocusout:false,
		//onkeyup:false,
		ignore:false,
		errorClass:"ui-state-error",
		errorPlacement: function(error, element) {
			//alert(error.html());
		},
		showErrors: function(errorMap, errorList) {   

			var errorMessage = "";
			for ( var error in errorList ) {
				if(errorList[error].message===undefined){
				}else{
					errorMessage = errorMessage + errorList[error].message + "<br/>";
				}
			}
			if(errorMessage != ""){
				$.pnotify({
			    	title: '验证信息',
			    	text: errorMessage,
			    	type: 'error',
			    	styling: 'bootstrap'
				});
			}

	        this.defaultShowErrors();   

	    }   
	});
	//增加验证模式
	$("#editForm").validate({meta:"validate"});
}  


$(function(){
	
	
	
	
		
	/*
	 * ajax请求遮罩功能
	 */
	 $("div.ui-overlay").ajaxStart(function(){
		 $(this).show();
	 }).ajaxComplete(function(event,XMLHttpRequest, settings){
		 $(this).hide();
	  });

	$("#debug").ajaxError(function (event, XMLHttpRequest, ajaxOptions, thrownError) {
		$(this).hide();
		if(XMLHttpRequest.status == 403){
			
			/*alert('登录已失效或没有登录，请登录！');*/
			/*window.top.location.href=  "logon_toLogon.action";*/
			window.top.location.href= "index.jsp";
			/* if(window.opener){  
			        //若是弹出的打开窗口，刷新父窗口，就关闭本窗口  
			        window.open.reload();  
			        window.close();  
			    }  
			    else{  
			        var topwin = window.parent;  
			        //找到顶层窗口  
			        while(topwin!= topwin.parent){  
			            topwin = topwin.parent;  
			        }  
			        if(window.parent!=window)  
			            topwin.location.href=  "logon_toLogon.action";
			    }  */
		}
		var _this = $(this);
	    var json = $.parseJSON(XMLHttpRequest.responseText);
	    failNotify(json,_this);
	});
	
	/******************************   V5 START **************************************/
	
	$( "#page_query" ).click(function(){
		
		var _form = $("form.queryForm");
		var url = _form.attr("action");
		var data = _form.serialize();
		$("input.queryCondition").val(data);
		renderList(url,data);
		
		$('#query').modal('hide');
		
		$("div.list_bottoms").show();
		$("div.pageContainer>div.list").show();
		$("div.pageContainer>div.edit").hide();
		$("div.pageContainer>div.detail").hide();
	
	});
	
	$("a.page_edit").live("click",function(){
		var _this =  $(this);
		var _url = _this.attr("href");
		var div_edit = $( "div.edit");		
		$.ajax({
			url : _url,
			cache : false,
			async : false,
			dataType : "html",
			success : function(html) {
				 if(!page.isJson(html)) {
					try{
						var json = jQuery.parseJSON(html);
						failNotify(json,$("#debug"));
						div_edit.html("");
					}catch(err){
						div_edit.html(html);
						//初始化验证
						initvalidate();
					}
				}else{
					div_edit.html(html);
					//初始化验证
					initvalidate();
				}
			}
		}); 
		$("div.pageContainer>div.edit").show();
		$("div.list_bottoms").hide();
		$("div.edit_bottoms").show();
		$("div.detail_bottoms").hide();
		$("div.pageContainer>div.list").hide();
		
		return false;
	});
	
	$("a.page_detail").live("click",function(){
		var _this =  $(this);
		var _url = _this.attr("href");
		var div_detail = $( "div.detail");		
		$.ajax({
			url : _url,
			cache : false,
			async : false,
			dataType : "html",
			success : function(html) {
				 if(!page.isJson(html)) {
					try{
						var json = jQuery.parseJSON(html);
						failNotify(json,$("#debug"));
						div_detail.html("");
					}catch(err){
						div_detail.html(html);
					}
				}else{
					div_detail.html(html);
				}
			}
		}); 
		$("div.pageContainer>div.edit").hide();
		$("div.pageContainer>div.list").hide();
		$("div.pageContainer>div.detail").show();
		$("div.list_bottoms").hide();
		$("div.edit_bottoms").hide();
		$("div.detail_bottoms").show();
		return false;
	});
	
	$("a.page_save").live("click",function(){
		var _editForm  = $("#editForm");
		if( !_editForm.valid()){
			return false;
		}
		
		var _action = _editForm.attr("action");
		var _data = _editForm.serialize();
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
	
	$("a.page_status").live("click",function(){
		var _this = $(this);
		var _url = _this.attr("href");
		jConfirm('确定要执行此操作?', '提示', function(result) {
			if(result){
				$.ajax({
					url : _url,
					async : false,
					cache : false,
					dataType : "json",
					success : function(json) {
						if(!json.result)
						{	failNotify(json,$("#debug"));
							return;
						}else{
							successNotify(json);
							$("a.prepaginggo").trigger("click");
						}
					}
					});
			}
		});
		
		return false;
	});
	
	$("a.page_update_password").live("click",function(){
		var _url = $(this).attr("href") || '';
		var div_updatePassword = $( "#updatePassword");
		$.ajax({
			url : _url,
			cache : false,
			dataType : "html",
			async : false,
			success : function(html) { 
				div_updatePassword.empty();
				div_updatePassword.html(html);
				$('#updatePassword').modal('show');
			}
		}); 
		return false; 
	});
	
	
	
	/*批量操作page_batch*/
	$("a.page_batch").live("click",function(){
		var _this = $(this);
		var _url = _this.attr("url");
		jConfirm('确定要执行此操作?', '提示', function(result) {
			if(result){
				$.ajax({
					url : _url,
					async : false,
					cache : false,
					dataType : "json",
					success : function(json) {
						if(!json.result)
						{	failNotify(json,$("#debug"));
							return;
						}else{
							successNotify(json);
							$("a.prepaginggo").trigger("click");
						}
					}
				});
			}
		});
		
		return false;
	});
	
	$("a.page_back,a.page_back_detail").live("click",function(){
				
		$("div.list_bottoms").show();
		$("div.edit_bottoms").hide();
		$("div.detail_bottoms").hide();
		$("div.pageContainer>div.list").show();
		$("div.pageContainer>div.edit").hide();
		$("div.pageContainer>div.detail").hide();
				
	});
	
	$( "a.page_export").click(function(){
		var _this = $(this);
		var _url = _this.attr("url");
		var _chkboxChoosed = $("input.chkboxChoosed");
		_url = _url+"?chkboxChoosed="+_chkboxChoosed.val();
		var _data = $(".queryCondition").val();
		_data = decodeURIComponent(_data,true); 
		_url = _url+"&"+_data;
		var downloadFfile = page.downloadFfile;
		downloadFfile(encodeURI(encodeURI(_url)));
	});
	
	$( "a.page_import" ).click(function(){
		var _this =  $(this);
		var _url = _this.attr("url") || '';
		var div_import = $( "div.import");
		$.ajax({
			url : _url,
			cache : false,
			dataType : "html",
			async : false,
			success : function(html) { 
				div_import.empty();
				div_import.html(html);
				$('#excel_import').modal('show');
			}
		}); 
	});
	
	$('#excel_import').on('hidden', function () {
		if(null != swfu){
			swfu.destroy();
		}
		$("#excelmessage").empty();
		$("a.prepaginggo").trigger("click");
	});
	
	/******************************   V5 END **************************************/
	
	
	
	/**
	 * Checkbox 交互
	 */
	$("div.list input.chkboxall").live('click',function(){
	
		var _chkboxitems = $("div.list input.chkboxitem");
		if(this.checked){
			_chkboxitems.attr("checked",true);
		}else{
			_chkboxitems.attr("checked",false);
		}
		processChkboxs(_chkboxitems);
	});
	
	$("div.list input.chkboxitem").live('click',function(){
		var _this = $(this);
	
		youcheckedIchecked();
		var _chkboxitems = $("div.list input.chkboxitem");
		processChkboxs(_chkboxitems);
	});
	
	/**
	 * 彩蛋，提示详细的错误信息
	 */
	$("#menu-div-first").click(function(){
		if($("#debug").html().length > 0){$("#debug").dialog({title:'供IT参考',width:1000,height:500});}		
	});
	
	
	function processChkboxs(chkboxs){
		for(var i = 0; i < chkboxs.size(); i++){
			var curr = chkboxs.get(i);
			if(curr.checked){
				addCheckedIds($(curr).val());
			}else{
				removeCheckedIds($(curr).val());
			}
		}
	}
	
	function addCheckedIds(id){
		var chkboxChoosed = page.data.chkboxChoosed;
		chkboxChoosed[chkboxChoosed.length]=id;
		$.unique(chkboxChoosed);
		$("input.chkboxChoosed").val(chkboxChoosed.join());
	}
	
	function removeCheckedIds(id){
		var chkboxChoosed = page.data.chkboxChoosed;
		$.unique(chkboxChoosed);
		
		chkboxChoosed.remove(id);
		$("input.chkboxChoosed").val(chkboxChoosed.join());
	}
	
	
	/**
	 * 翻页后原来选中的仍然选中
	 */
	function checkedBeforeChoosed(){
		var choosed = page.data.chkboxChoosed;
		if(choosed && choosed.length>0){
			var _chkboxitems = $("div.list input.chkboxitem");
			for(var i = 0; i < choosed.length; i++){
				for(var j = 0; j < _chkboxitems.size(); j++){
					var curr = $(_chkboxitems.get(j));
					var currval = choosed[i];
					if(curr.val() == currval){
						curr.attr("checked",true);
					}
				}
			}
			
		}
		youcheckedIchecked();
	}
	
	/**
	 * 如果tbody中的checkbox都被选择，thead的checkbox也需要被选中
	 */
	function youcheckedIchecked(){
		var _chkboxall = $("div.list input.chkboxall");
		var _chkboxitems = $("div.list input.chkboxitem");
		var _chkboxitemsed = $("div.list tbody input:checked");
		if(_chkboxitems.size() != _chkboxitemsed.size()){
			_chkboxall.attr("checked",false);
		}else{
			_chkboxall.attr("checked",true);
		}
	}
	
	/**
	 * 从服务器获取数据render到list中
	 */
	function renderList(url,data){
		$.ajax({'url':url,'data':data,'cache': false,'type':'POST','dataType':'html',
			'success':function(data, textStatus){
				$("div.list input,div.list a").unbind();
				$("div.list").empty();
				$("div.list").html(data);
				checkedBeforeChoosed();
			}
		});
	}
	
	/**
		后台分页Js	
	*/
	function prepageingRenderList(url){
		var data =$("input.queryCondition").val() || "";
		renderList(url,data);
	}
	$("a.prepaging").live('click',function(){
		var totalPages = $("input.totalPages").val();
		var url = $(this).attr("href")+"&page.totalCount="+totalPages;
		
		prepageingRenderList(url);
		return false;
	});
	$("a.prepaginggo").live('click',function(){
		var totalPages = $("input.totalPages").val();
		var prepaginggo = $("input.prepaginggo").val();
		if(totalPages && prepaginggo){
			if(prepaginggo*1 > totalPages*1){
				 prepaginggo = totalPages;
			}
			var url = $(this).attr("href")+"?page.pageNo="+prepaginggo;
			prepageingRenderList(url);
		}
		return false;
	});
	
	$("a.page_delete").live("click",function(){
		var _this = $(this);
		var _url = _this.attr("href");
		jConfirm('确定要'+_this.text()+'?', '警告', function(result) {
			if(result){
				$.ajax({
					url : _url,
					async : false,
					cache : false,
					dataType : "json",
					success : function(json) {
						if(!json.result)
						{	failNotify(json,$("#debug"));
							return;
						}else{
							successNotify(json);
							$("a.prepaginggo").trigger("click");
						}
					}
				});
			}
		});
		return false;
	});
	
	$("a.downexceltemp").live("click",function(){
		var _this = $(this);
		var _url = _this.attr("href");
		window.open (_url);
		return false;
	});
	
	$("a.exportExcel").live("click",function(){
		var _this = $(this);
		var _url = _this.attr("href");
		window.location.href=_url;
		return false;
	});
	
	
	
	//文件上传操作
	$( ".uploadfiles" ).live("click",function(){
		var _this =  $(this);
		var _url = _this.attr("url");
		var div_uploadfile = $( "div.openuploadfile");
		var uploadfileHiddenValue = _this.attr("uploadfileHiddenValue");
		$.ajax({
			url : _url,
			async : false,
			cache : false,
			data:{"uploadFileValues":$("#"+uploadfileHiddenValue).val() || ""},
			dataType : "html",
			success : function(html) { 
				div_uploadfile.html(html);
				$('#openuploadfile').modal('show');
			}
		}); 
		return false;
	});
	
	//文件上传关闭窗口
	$('#openuploadfile').on('hidden', function () {
		if(null != uploadFileSwf){
			uploadFileSwf.destroy();
		}
		var uploadfileHiddenValue = $( ".uploadfiles" ).attr("uploadfileHiddenValue");
		$("#"+uploadfileHiddenValue).val($("#uploadfileconfig").attr("upload_file_values"));
	});
	
	
	//判断打开的是否是编辑页面
	function page_data_entry_edit_flag(){
		var divEdit = $("div.pageContainer div.edit");
		if(divEdit.is(":visible") && divEdit.has("form").length > 0){
			return true;
		}else{
			return false;
		}
	}
});		

