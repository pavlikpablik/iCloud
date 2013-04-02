<%@page import="com.manpowergroup.cn.icloud.base.entity.City"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<s:form action="city_save.action" theme="simple" id="editForm" name="editForm">
		<s:hidden name="city.id"></s:hidden>
		<s:hidden name="city.createBy"></s:hidden>
		<s:hidden name="city.createDate"></s:hidden>
		<s:hidden name="city.status"></s:hidden>
		<s:hidden name="city.seq"></s:hidden>
		<table  class="table table-condensed">
			<tr>
				<td >省份中文名*：</td>
				<td ><s:textfield  placeholder="省份中文名" cssClass="{validate:{required:true,messages:{required:'省份中文名不能为空'}}} " id="cityname" name="city.name" />
				</td>
				<td >省份英文名*：</td>
				<td ><s:textfield placeholder="省份英文名" cssClass="{validate:{required:true,messages:{required:'省份中英文名不能为空'}}} " id="citynameEn" name="city.nameEn" />
			</tr>			
			<tr>
				<td >备注：</td>
				<td colspan="3"><s:textarea placeholder="备注" name="city.remark" id="city.remark"  cssStyle="height:80px;width: 550px"></s:textarea>
				</td>
			</tr>
		</table>
		<table  class="table table-condensed">
			<thead>
				<tr>
					<th>城市中文名*</th>
					<th>城市英文名*</th>
					<th>备注</th>
					<th>管理</th>
				</tr>
			</thead>
			<tbody id="cityImport">
				<c:forEach items="${city.citys}" var="cityItem" varStatus="status">
				<tr >
						<td >
							<input type="hidden" name="city.citys[${status.index }].id" value="${cityItem.id}"> 
							<input id="CN${cityItem.id}" class="{validate:{required:true,messages:{required:'城市中文名不能为空'}}} " type="text" name="city.citys[${status.index }].name" value="${cityItem.name}">
						</td>
						<td >
							<input class="{validate:{required:true,messages:{required:'城市英文名不能为空'}}} " id="EN${cityItem.id}" type="text" name="city.citys[${status.index }].nameEn" value="${cityItem.nameEn}">
						</td>
						<td >
							<input type="text" name="city.citys[${status.index }].remark" value="${cityItem.remark}">
						</td>
						<td >
							<c:if test="${cityItem.id != null}">
									<input type="hidden" name="city.citys[${status.index }].status" value="${cityItem.status}">
									<a href="#" class="closeCityItem btn btn-mini"  ><c:if test="${cityItem.status== 1}">停用</c:if><c:if test="${cityItem.status== 0}">启用</c:if></a>
							</c:if> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div><a class="btn btn-primary" href="javascript:addCityItem()">添加城市</a></div>
	</s:form>
	<script type="text/javascript">

			$(function(){
				 
				/* //选择普通年月
				$('#cityname').datepicker();
				//只选择月份
				$('#citynameEn').datepicker({
					startView:1,//1：先出月份再出年，2：先出年再出月份
					format: 'yyyy/mm',
					month_mode:true//月份模式，只选择到月份就ok
					}
				);  */
				
			});
	
			//启用和禁用
			$("a.closeCityItem").click(function(){
				var _this = $(this);
				var prev = _this.prev("input:hidden");
				if(prev.val() == '<%=City.STATUS_START%>'){
					_this.html('启用');
					prev.val(<%=City.STATUS_STOP%>);
				}else{
					_this.html('停用');
					prev.val(<%=City.STATUS_START%>);
				}
				return false;
			});
			
			//添加城市
			function addCityItem() {
				var data = $("#editForm").serialize();
				$.ajax({
					url : "city_addCityItem.action",
					data : data,
					async : false,
					dataType : "html",
					success : function(html) {
						$("#cityImport").append(html);
					}
				});
			}
		
		//删除新增的城市,删除一并移除验证提示
		function subcity(target) {
			$(target).parent().parent().remove();
		}
	</script>
