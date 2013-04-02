<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!-- 
	table 的 标准定义：
	1.必须是<table><thead></thead><tbody></tbody></table>结构
	2.thead的内部标签必须是tr th, 不允许是 tr td
	3.整个table标签内不要定义仍和与样式相关的属性，样式全部通过css统一设置
 -->
<table class="table table-condensed table-striped table-hover">
	<thead>
		<tr>
			<th><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>项目元素中文名</th>
			<th>类型</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
			<tr>
				<td><input class="chkboxitem" name="ids" type="checkbox"  value="${entry.id}"/></td>
				<td align="center">${entry.id}</td>
				<td>${entry.name}</td>
				<td>${entry.typeName }</td>
				<td>${entry.statusName }</td>
				<td align="center">
						<a class="btn btn-mini page_detail" href="item_detail.action?baseItem.id=${entry.id}" >详细</a>
						<a  class="btn btn-mini page_edit" href="item_edit.action?baseItem.id=${entry.id}" >修改</a>
<%-- 						<c:if test="${entry.status == 0}"> --%>
<%-- 							<a href="baseItem_changeStatus.action?item.id=${entry.id}">启用</a>&nbsp; --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${entry.status == 1}"> --%>
<%-- 							<a href="baseItem_changeStatus.action?item.id=${entry.id}">停用</a>&nbsp; --%>
<%-- 						</c:if> --%>
						<!--<a class="delete pagetableoperate ui-button ui-widget ui-state-default" href="item_delete.action?item.id=${entry.id}"><span class="ui-icon ui-icon-trash"></span>删除</a>-->
					<AT:AuthorityTag actionName="type_changeStatus.action">
					<c:if test="${entry.status == 0}">
						<a class="btn btn-mini  page_status" href="item_changeStatus.action?baseItem.id=${entry.id}&baseItem.status=1">启用</a>&nbsp;
					</c:if>
					<c:if test="${entry.status == 1}">
						<a class="btn btn-mini  page_status" href="item_changeStatus.action?baseItem.id=${entry.id}&baseItem.status=0">停用</a>&nbsp;
					</c:if>
					</AT:AuthorityTag>	
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<c:set var="url" value="item_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>


