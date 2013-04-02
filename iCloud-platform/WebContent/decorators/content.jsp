<%@ include file="/resource/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>
	<decorator:title default="iCloud云结算系统"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 30px;
}
table {
	white-space: nowrap;
}
label{/* font-weight:bold; */ color:#2F96B4;} 

.footer{
	margin: 0 auto;
/* 	padding: 15px 0 25px 0; */
	text-align: center;
}
/* combobox */
/* .ui-button-icon-only .ui-button-text { padding: 0.35em; }
.ui-autocomplete-input {margin: 0; padding: 0.5em 0 0.51em 0.4em; *padding:0.72em 0 0.46em 0.4em;}
button.comboboxButton { margin-left: -3px;margin-top:-5px; width: 2.35em; height: 2.3em;line-height:1.35; }
ul.ui-autocomplete {max-height: 200px; overflow-x: hidden; overflow-y: auto; padding:2px;} */

</style>

<jsp:include page="/WEB-INF/jsp/layout/resources.jsp"></jsp:include>

<decorator:head />
</head>
<body data-spy="scroll" data-target=".subnav" data-offset="50" <decorator:getProperty property="body.id" writeEntireProperty="true"/>
		<decorator:getProperty property="body.class" writeEntireProperty="true"/>
		<decorator:getProperty property="body.style" writeEntireProperty="true"/>
		<decorator:getProperty property="body.scroll" writeEntireProperty="true"/>
		<decorator:getProperty property="body.onunload" writeEntireProperty="true"/>
		<decorator:getProperty property="body.onload" writeEntireProperty="true"/>>
	
	<!--menu-->
	<%-- <s:action name="sysMenu_listMenus" executeResult="true">
	  <s:param name="menu_id">${param.menu_id}</s:param>
	</s:action> --%>
	<jsp:include page="/WEB-INF/jsp/layout/menu.jsp"></jsp:include> 
	
	 

	
	
 	<div class="container-fluid" > 
	<decorator:body />
	 <jsp:include page="/WEB-INF/jsp/layout/footer.jsp"></jsp:include> 
	</div>
	

	<div id="debug" style="display: none;"></div>
	<div class="ui-overlay" style="display: none;">
         <div class="ui-widget-overlay" style="text-align: center;"></div> 
        <div style="width: 200px; height: 200px; position: absolute; left: 46%; top: 30%;z-index: 2000"><img src="resource/images/loading.gif" border="0" /></div>
    </div> 
	
	
	
	
    
  
  
<script type="text/javascript">
	$(function(){
		if($.browser.msie && $.browser.version <= 7.0 ){
			$.pnotify({
		    	title: '请更新您的浏览器版本！',
		    	text: "您当前使用的浏览器的内核版本为&nbsp;IE"+$.browser.version+"&nbsp;,为了获得更好的浏览体验,推荐您使用&nbsp;<b>Chrome浏览器</b>&nbsp;或者&nbsp;<b>IE8.0+</b>,&nbsp;请更新您的浏览器。"
			});
		}
		
		$("#remeberme").click(function() {
			var _this = $(this);
			var _url = 'logon_remember.action';
			$.ajax({
				url : _url,
				cache : false,
				dataType : "text",
				success : function(text) {
					if (text == "success") {
						$.pnotify({
							title : '免登录设置成功',
							text : "浏览器已经记住了您的登录信息，30天内无需重复登录.",
							type : 'notice'
						});
						_this.remove();
					} else {
						$.pnotify({
							title : '免登录设置失败',
							text : "请确认您的浏览器允许设置Cookie.",
							type : 'error'
						});
					}
				}
			});

			return false;
		});

		
		
		
	});
</script>

</body>

</html>

