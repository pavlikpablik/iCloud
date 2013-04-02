<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="pageContainer">
	<!-- 定义页面查询结果及分页类 -->
	<div class="row-fluid">
		<div class="page-header span12">
			<h4>
				社保申报导出<small>&nbsp;您可以按照条件导出需要申报的社保数据</small>
			</h4>
		</div>
	</div>
	<div class="edit">
		<form class="form-horizontal" name="editForm"
			action="socialbenefit_export.action" method="post" id="editForm">
			<div>
				<div class="control-group">
					<label class="control-label" for="ssbProvice">社保缴纳省</label>
					<div class="controls">
						<s:select list="provinceList" name="provinceId" id="provinceId"
							headerKey="" headerValue="请选择" listKey="id" listValue="name"
							theme="simple"></s:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="ssbCity">社保缴纳市*</label>
					<div class="controls">
						<s:select list="#{}"
							cssClass="{validate:{required:true,messages:{required:'社保缴纳市不能为空'}}} "
							name="cityId" id="cityId" headerKey="" headerValue="请选择"
							listKey="id" listValue="name" theme="simple"></s:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="ssbProvice">社保缴纳月*</label>
					<div class="controls">
						<label class="radio inline"
							style="color: black;width: 30px;"> <input
							checked="checked" type="radio" class="importModelCheck"
							name="importModelRadio" value="preMonth">上月 </label> <label
							class="radio inline" style="color: black;width: 30px;">
							<input type="radio" class="importModelCheck"
							name="importModelRadio" checked="checked" value="currMonth">当月
						</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="submit"></label>
					<div class="controls">
						<a id="exportSubmit111" class="btn"><i class="icon-download"></i>申报导出</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("div.pageContainer>div.edit").show();
		$("div.list_bottoms").hide();
		$("div.edit_bottoms").show();
		$("div.detail_bottoms").hide();
		$("div.pageContainer>div.list").hide();

	});
	//省市联动的下拉框
	$("#provinceId").change(
			function() {
				var checkValue = $("#provinceId").val();
				$.ajax({
					url : "socialbenefit_queryCityByProvince.action?city.id="
							+ checkValue,
					async : true,
					dataType : "JSON",
					type : "POST",
					cache : false,
					success : function(data) {
						$("#cityId").empty();
						$("#cityId").append("<option value=''>请选择</option>");
						$.each(eval(data), function(i, item) {
							$("#cityId").append(
									"<option value='" + item.id + "'>"
											+ item.name + "</option>");
						});
					}
				});
			});

	$("#exportSubmit111").live("click", function() {
		var _queryForm = $("#editForm");
		if (!_queryForm.valid()) {
			return false;
		}
		_queryForm.submit();
	});

	initvalidate();
</script>