<%@page import="com.manpowergroup.cn.icloud.base.entity.Code"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table class="pagelisttable table table-condensed table-striped">
	<thead>
		<tr>
			<th width="10px;"><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>项目元素类型名称</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
			<tr>
				<td><input class="chkboxtype" name="ids" type="checkbox"  value="${entry.id}"/></td>
				<td>${entry.id}</td>
				<td>${entry.name}</td>
				<td>
					<c:if test="${entry.status == 0}">
						停用
					</c:if>
					<c:if test="${entry.status == 1}">
						启用
					</c:if>
				</td>
				<td>
					<a href="itemtype_detail.action?itemType.id=${entry.id}" class="btn btn-mini  page_detail">详细</a>
					<AT:AuthorityTag actionName="itemtype_edit.action">
					<a href="itemtype_edit.action?itemType.id=${entry.id}" class="btn btn-mini  page_edit">修改</a>
					</AT:AuthorityTag>
					<AT:AuthorityTag actionName="type_changeStatus.action">
					<c:if test="${entry.status == 0}">
						<a class="btn btn-mini  page_status" href="itemtype_changeStatus.action?itemType.id=${entry.id}&itemType.status=1">启用</a>&nbsp;
					</c:if>
					<c:if test="${entry.status == 1}">
						<a class="btn btn-mini  page_status" href="itemtype_changeStatus.action?itemType.id=${entry.id}&itemType.status=0">停用</a>&nbsp;
					</c:if>
					</AT:AuthorityTag>
					<!--<a class="delete pagetableoperate ui-button ui-widget ui-state-default" href="type_delete.action?type.id=${entry.id}"><span class="ui-icon ui-icon-trash"></span>删除</a>-->
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<c:set var="url" value="itemtype_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>

