<%@page import="com.manpowergroup.cn.icloud.base.entity.Code"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table class="table table-condensed table-striped table-hover">
 	<%-- <caption><h4>省份列表</h4></caption> --%>
	<thead>
		<tr>
			<th width="15px;"><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>数据键</th>
			<th>数据值</th>
			<th>所属类别</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.result}" var="entry" varStatus="st">
			<tr>
				<td><input class="chkboxitem" name="ids" type="checkbox"  value="${entry.id}"/></td>
				<td>${entry.id}</td>
				<td>${entry.bdCode}</td>
				<td>${entry.bdName}</td>
				<td>${entry.bdType}</td>
				<td>${entry.bdRemark}</td>
				<td align="center">
					<a href="code_detail.action?code.id=${entry.id}" class="btn btn-mini  page_detail" >详细</a>&nbsp;
					
					<AT:AuthorityTag actionName="code_edit.action">
					<a href="code_edit.action?code.id=${entry.id}" class="btn btn-mini  page_edit">修改</a>&nbsp;
					</AT:AuthorityTag>
					<!--  <AT:AuthorityTag actionName="code_delete.action">
					<a id="codeListDelete" href="code_delete.action?code.id=${entry.id}" class="btn btn-mini  delete"></span>删除</a>
			        </AT:AuthorityTag>-->
				</td>
			  </tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<c:set var="url" value="code_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>

