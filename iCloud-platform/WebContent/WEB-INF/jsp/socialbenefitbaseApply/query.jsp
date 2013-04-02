<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>社保类型查询</h4>
</div>
<div class="modal-body">
	<p><label class="control-label">名称：</label> <input name="name" type="text" /></p>
	<p>
		<label class="control-label">省份：</label> <s:select list="provinceList" theme="simple"
							name="provinceName" 
							id="provinceId" headerKey="" headerValue="请选择" listKey="id"
							listValue="name" ></s:select>
	</p>
	<p><label class="control-label">城市：</label><s:select list="#{}"  theme="simple"
							 name="cityName" id="cityId"
							headerKey="" headerValue="请选择" listKey="id" listValue="name" 
							></s:select></p>
	<p><label class="control-label">户籍：</label><s:select list="residencyList"  theme="simple"
							name="residency" 
							id="residency" headerKey="" headerValue="请选择" 
							listKey="id" listValue="bdcode"></s:select></p>
	
	<p><label class="control-label">状态：</label>
	<c:if test="${method == 'audit'}">
	<s:select list="#{1:'已审批',2:'待审批',0:'停用'}" label="状态" theme="simple"
							name="status"   value="2"
							headerKey="" headerValue="请选择" listKey="key" listValue="value"></s:select>
	</c:if>
	<c:if test="${method != 'audit'}">
		<s:select list="#{1:'已审批',2:'待审批',0:'停用'}" label="状态" theme="simple"
							name="status"  
							headerKey="" headerValue="请选择" listKey="key" listValue="value"></s:select>
	</c:if>
	</p>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_query">查询</a> 
	<a href="javascript:$('#queryForm')[0].reset()" class="btn" >重置</a>
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
</div>

<script type="text/javascript">
	$(function(){
		$("#provinceId").change(
				function() {
					var checkValue = $("#provinceId").val();
					$.ajax({
						url : "socialBenefitBaseApply_queryCityByProvince.action?city.id="
								+ checkValue,
						async : false,
						dataType : "JSON",
						type : "POST",
						cache : false,
						success : function(data) {
							$("#cityId").empty();
							$("#cityId").append(
									"<option value=''>" + '请选择' + "</option>");
							$.each(eval(data), function(i, item) {
								$("#cityId").append(
										"<option value='" + item.id + "'>"
												+ item.name + "</option>");
							});
						}
					});
				});
	});
</script>