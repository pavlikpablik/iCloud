<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8">
<title>Bootstrap, from Twitter</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link
	href="resource/twitter-bootstrap-2.2.2/docs/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="resource/twitter-bootstrap-2.2.2/docs/assets/css/bootstrap-responsive.css"
	rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script  src="resource/javascript/jquery.js" type="text/javascript"></script> 
<script src="resource/twitter-bootstrap-2.2.2/docs/assets/js/bootstrap.js" type="text/javascript"></script>
<script src="resource/jquery-validation-1.9.0/lib/jquery.metadata.js" type="text/javascript"></script>
<script src="resource/jquery-validation-1.9.0/jquery.validate.js" type="text/javascript"></script>
<script src="resource/jquery-validation-1.9.0/localization/messages_cn.js" type="text/javascript"></script>
		<script type="text/javascript">	
	$().ready(function() { 
		$("#registerForm").validate(); 
		}); 
	</script>
<style type="text/css">


.login-header {
	padding-top: 30px;
	height: 120px;
}

div.center,p.center,img.center {
	margin-left: auto !important;
	margin-right: auto !important;
	float: none !important;
	display: block;

}


</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="row-fluid" align="center">
				<div class="span12 center login-header">
					<h2>平台用户注册</h2>
				</div>
			</div>

			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info " align="center">请输入用户信息，进行注册</div>
<form class="form-horizontal" action="logon_logon.action" id="registerForm" method="post">
  <div class="control-group">
    <label class="control-label" for="loginName">用户名</label>
    <div class="controls">
      <input type="text" id="loginName"  name="loginName"   class="{required:true,messages:{required:'必填项信息不能为空'}}"  placeholder="用户名">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="password">密码</label>
    <div class="controls">
      <input type="password" id="password" name="password" class="{required:true,minlength:6,messages:{required:'必填项信息不能为空'}}"  placeholder="密码">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="surePassword">确认密码</label>
    <div class="controls">
      <input type="password" id="surePassword" name="surePassword" class="{required:true,messages:{required:'必填项信息不能为空'},minlength:6,equalTo:'#password'}"  placeholder="确认密码">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="trueName">真实姓名</label>
    <div class="controls">
      <input type="text" id="trueName"  name="trueName" class="{required:true,messages:{required:'必填项信息不能为空'}}"  placeholder="真实姓名">
    </div>
  </div>
      <div class="control-group">
    <label class="control-label" for="company">公司</label>
    <div class="controls">
      <input type="text" id="company" name="company" placeholder="公司">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="sex">性别</label>
    <div class="controls">
    <select >
    <option value ="0" checke name="sex" id="sex">请选择</option>
    <option value ="1">男</option>
    <option value ="2">女</option>
    </select>
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="mobile">电话</label>
    <div class="controls">
      <input type="text" id="mobile" mobile="mobile" class="{required:true,messages:{required:'必填项信息不能为空'}}" placeholder="电话">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="phone">手机</label>
    <div class="controls">
      <input type="text" id="phone" name="phone" class="{required:true,messages:{required:'必填项信息不能为空'}}" placeholder="手机">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="email">邮箱</label>
    <div class="controls">
      <input type="text" id="email" name="email" class="{required:true,email:true,messages:{required:'必填项信息不能为空'}}" placeholder="邮箱">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="birthday">生日</label>
    <div class="controls">
      <input type="text" id="birthday" name="birthday" class="{required:true,messages:{required:'必填项信息不能为空'}} datepickTime" placeholder="生日">
    </div>
  </div>
  
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">注册</button>
    </div>
  </div>
</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
		$(function(){
			
			$(".datepickTime").datepicker({
				dateFormat: 'yy-mm-dd',
				showAnim: '',
				numberOfMonths: 3,
				changeMonth: true,
				changeYear: true
			});		
			
			
			
			
		});
		
		
	
	</script>




