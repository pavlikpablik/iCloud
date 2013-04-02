<%@page import="com.manpowergroup.cn.icloud.base.entity.City"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table class="table table-condensed table-striped table-hover">
 	<%-- <caption><h4>省份列表</h4></caption> --%>
	<thead>
		<tr>
			<th width="15px;"><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>省份中文名</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
			<tr>
				<td width="20px;"><input class="chkboxitem" name="ids" type="checkbox"  value="${entry.id}"/></td>
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
						<a href="city_edit.action?city.id=${entry.id}" class="btn btn-mini page_edit">修改</a> 
						<a href="city_detail.action?city.id=${entry.id}" class="btn btn-mini page_detail">详细</a> 
				</td>
			  </tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<c:set var="url" value="city_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>

