<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

	<table class="table table-condensed table-striped table-hover">
      	<thead>
      		<tr>
				<th>资源名称</th>
				<th>资源别名</th>
				<th>超级用户</th>
				<th>类型</th>
				<th>层级</th>
				<th>状态</th>
				<th>描述</th>
				<th>操作</th>
				
      		</tr>
      	</thead>
      	<tbody >
      		<c:forEach var="resource" items="${page.result}" varStatus="st">

		      <tr>
				<td>
      				<c:forEach begin="2" end="${resource.sourceLevel}" var="levelStep">
      					&nbsp;&nbsp;&nbsp;&nbsp;
      				</c:forEach>${resource.fullName}
      			</td>
      			<td>
      			   ${resource.name}
      			</td>
      			<td>
      			<c:if test="${resource.issys == 0 }">
      			       否
      			</c:if>
      			  <c:if test="${resource.issys == 1}">
      			      是     
      			</c:if>
      			</td>
      			<td>
      			<c:if test="${resource.type == 0 }">
      			       菜单
      			</c:if>
      			  <c:if test="${resource.type == 1}">
      			      按钮     
      			</c:if>
      			</td>
      			<td>
      			   ${resource.sourceLevel}
      			</td>
				<td>
      			<c:if test="${resource.enabled == 0 }">
      			       停用
      			</c:if>
      			  <c:if test="${resource.enabled == 1}">
      			      启用     
      			</c:if>
      			</td>
      			<td>
      				${resource.remark}
      			</td>
      			<td>
      				<a class="btn btn-mini page_edit" href="resource_edit.action?resource.id=${resource.id}">编辑</a>		 
					<c:if test="${resource.enabled == 0}">
						<a class="btn btn-mini" href="resource_changeStatus.action?resource.id=${resource.id}">启用</a>&nbsp;
					</c:if>
					<c:if test="${resource.enabled == 1}">
						<a class="btn btn-mini " href="resource_changeStatus.action?resource.id=${resource.id}">停用</a>&nbsp;
					</c:if>
					<a class="btn btn-mini page_delete" href="resource_delete.action?resource.id=${resource.id}">删除</a>
      			</td>
			</tr>
		      
      		</c:forEach>
      	</tbody>
     </table>

     
     <!-- 分页 -->
<c:set var="url" value="resource_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>