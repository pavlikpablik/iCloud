<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.error {
	padding-bottom: 14px;
	padding-top: 14px;
	background-color: #FCF8E3;
	border: 1px solid #FBEED5;
	border-radius: 4px 4px 4px 4px;
	color: red;
	margin-bottom: 18px;
	padding: 8px 35px 8px 14px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	float: left;
	height: 23px;
	margin-bottom: 0px;
	padding: 5px 5px 0px 5px;
}
</style>
<form class="form-horizontal" action="user_savePassword.action"
	id="editForm" name="editForm" method="post">
	<input id="userId" type="hidden" name="user.id" value="${user.id}"></input>
	<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>修改密码</h4>
	</div>
	<div class="modal-body">
		<div class="control-group">
			<label class="control-label" for="oldPassword">原密码*</label>
			<div class="controls">
				<input type="password" id="oldPassword" name="oldPassword" 
					placeholder="密码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">新密码*</label>
			<div class="controls">
				<input type="password" id="password" name="password"
					placeholder="密码">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="surePassword">确认新密码*</label>
			<div class="controls">
				<input type="password" id="surePassword" name="surePassword"
					placeholder="确认密码">
			</div>
		</div>
	</div>
	<div class="modal-footer">
	<a href="#" class="btn btn-primary page_updatePassword" >保存</a> 
	<a href="#" class="btn btn-inverse" data-dismiss="modal">取消</a>
	</div>
</form>
<script type="text/javascript">
	$(function() {

		//帐号重复检查
		jQuery.validator.addMethod("checkLoginPassword", function(value, element) {
			var url = "user_checkLoginPassword.action?userId="+$("#userId").val();
			var returnv = false;
			$.ajax({
				'url' : url,
				'data' : {
					"oldPassword" : value
				},
				'cache' : false,
				'type' : 'POST',
				'dataType' : 'json',
				async : false,
				'success' : function(result) {
					returnv = result.result;
				}
			});
			return returnv;
		}, "密码不对请重新输入。");

		$("#editForm").validate(
		{
			rules : {
				oldPassword : {
					required : true,
					maxlength : 20,
					checkLoginPassword : '#oldPassword'

				},
				password : { //密码2的描述多于1项使用对象类型
					required : true, //必填，这里可以是一个匿名方法
					minlength : 6,
					maxlength : 20
				},

				surePassword : { //密码2的描述多于1项使用对象类型
					required : true, //必填，这里可以是一个匿名方法
					equalTo : '#password'

				},
			},
			messages : {
				oldPassword : {
					required : " 必填项信息不能为空。"

				},
				password : {
					required : " 必填项信息不能为空。",
					minlength : " 密码必须由6-20个字符组成。"
				},
				surePassword : {
					required : " 必填项信息不能为空。",
					equalTo : " 两次输入的密码不相同。"

				},
			},
			debug : true

		});
		$("a.page_updatePassword").live("click",function(){
			var _editForm = $("#editForm");
			if(!_editForm.valid()){
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
						return false;
					}else{
						successNotify(json);
						$("#updatePassword").modal('hide');
						return false;
					}
				}
			});
		});

	});
</script>




