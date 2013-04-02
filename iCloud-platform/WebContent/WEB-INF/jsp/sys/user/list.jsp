<%@page import="com.manpowergroup.cn.icloud.sys.entity.User"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="az" uri="/WEB-INF/tld/authz.tld"%>
<table class="table table-condensed table-striped table-hover">
 	<%-- <caption><h4>省份列表</h4></caption> --%>
	<thead>
		<tr>
			<th width="15px;"><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>身份证号码</th>
			<th>状态</th>
			<th>组织名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
			<tr>
				<td width="20px;"><input class="chkboxitem" name="ids" type="checkbox"  value="${entry.id}"/></td>
				<td>${entry.id}</td>
				<td>${entry.loginName}</td>
				<td>${entry.trueName}</td>
				<td>${entry.idcard}</td>
				<td>
					<c:if test="${entry.enabled == 0}">
						停用
					</c:if>
					<c:if test="${entry.enabled == 1}">
						启用
					</c:if>
				</td>
				<td>${entry.orgName}</td>
				<td>
						<c:if test="${entry.enabled == 1}">
						<a href="user_updateStatus.action?user.id=${entry.id}&user.enabled=0" class="btn btn-mini page_status">停用</a>
						</c:if>
						<c:if test="${entry.enabled == 0}">
						<a href="user_updateStatus.action?user.id=${entry.id}&user.enabled=1" class="btn btn-mini page_status">启用</a>
						</c:if>
						<az:authorize ifAny="R_USER_AUTH">
						<a href="user_authzAssign.action?user.id=${entry.id}" class="btn btn-mini page_authz_assign">授权</a> 
						</az:authorize>
						<a href="user_edit.action?user.id=${entry.id}" class="btn btn-mini page_edit">修改</a> 
						<a href="user_detail.action?user.id=${entry.id}" class="btn btn-mini page_detail">详细</a> 
						
				</td>
			  </tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<c:set var="url" value="user_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>

