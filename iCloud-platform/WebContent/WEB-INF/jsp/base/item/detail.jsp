<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="table table-condensed">
	<tr>
		<td>项目元素中文名：</td>
		<td>${baseItem.name}</td>
		<td>项目元素英文名：</td>
		<td>${baseItem.nameEn}</td>
	</tr>
	<tr>
		<td>项目元素类型：</td>
		<td>
			<div>
				<c:forEach items="${typeList}" var="type">
					<div style="float: left;">
						${type.name} 
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</c:forEach>
			</div>
		</td>		
		
		
		<td>项目类别：</td>
		<td>
			<div>
				<div style="float: left;">
				<c:if test="${baseItem.type==1}">社保</c:if>
				<c:if test="${baseItem.type==2}">工资</c:if>
				<c:if test="${baseItem.type==3}">服务费</c:if>
				<c:if test="${baseItem.type==4}">商保</c:if>
				<c:if test="${baseItem.type==5}">其他社保(社保调整)</c:if>
				</div>
			</div>
		</td>
		
			
	</tr>

	<tr>
		<td>描述：</td>
		<td>
			<textarea rows="5" disabled="disabled">${baseItem.describe}</textarea>
		</td>
		<td>备注：</td>
		<td>
			<textarea  rows="5" class="ui-widget-content" disabled="disabled">${baseItem.remark}</textarea>
		</td>
	</tr>
</table>
