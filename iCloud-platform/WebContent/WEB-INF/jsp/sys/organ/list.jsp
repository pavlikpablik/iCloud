<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="az" uri="/WEB-INF/tld/authz.tld"%>

	<table class="table table-condensed table-striped table-hover">
      	<thead>
      		<tr>
				<th >名称</th>
				<th></th>
				<th></th>
				<th></th>
				<th>描述</th>
				<th>
				    <%--<AT:AuthorityTag actionName="menu_edit.action">
					<a id="createMenu" url="menu_edit.action" dialogWidth="800" dialogHeight="500" dialogTitle="新建菜单信息">新建</a>
					</AT:AuthorityTag> --%>
				</th>
				
      		</tr>
      	</thead>
      	<tbody >
      		<c:forEach var="organ" items="${page.result}" varStatus="st">

		      <tr>
				<td>
      				
      				${organ.name}
      			</td>
      			<td>
      			
      			</td>
      			<td>
      			 
      			</td>
				<td>
      			
      			</td>
      			<td>
      				&nbsp;${organ.remark}
      			</td>
      			<td>
      				<a class="btn btn-mini page_edit" href="organ_edit.action?organ.id=${organ.id}">修改</a>		
      				<a href="organ_detail.action?organ.id=${organ.id}" class="btn btn-mini  page_detail" >详细</a> 
      				<a href="organ_searchUser.action?organ.id=${organ.id}" class="btn btn-mini  page_searchUser" >用户</a> 
      			</td>
			</tr>
		      
      		</c:forEach>
      	</tbody>
     </table>

     
     <!-- 分页 -->
<c:set var="url" value="organ_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>