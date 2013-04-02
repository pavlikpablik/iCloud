<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="modal-header">
	
	<a class="close" data-dismiss="modal">×</a>
	<h4>用户查看</h4>
</div>

<div class="modal-body">
<table class="table table-condensed table-striped table-hover">
      	<thead>
      		<tr>
				<th>用户名</th>
				<th></th>
				<th>真实姓名</th>
				<th></th>
				<th></th>
				<th></th>
				<th>电话</th>
				<th></th>
				<th></th>
				<th>手机</th>
				<th></th>
				<th></th>
				<th></th>
				<th>Email</th>
      		</tr>
      	</thead>
      	<tbody >
      		<c:forEach var="user" items="${userList}" varStatus="st">

		      

		      <tr>
				<td>
      			${user.loginName}
      			</td>
      			<td>
      			</td>
      			<td>
      			${user.trueName}
      			</td>
      			<td>
      			</td>
      			<td>
      			</td>
      			<td>
      			</td>
				<td>
      			${user.mobile}
      			</td>
      			<td>
      			</td>
      			<td>
      			</td>
      			<td>
      			${user.phone}	
      			</td>
      			<td>
      			</td>
      			<td>
      			</td>
      			<td>
      			</td>
      			<td>
      			${user.email}	
      			</td>
			</tr>
		      
      		</c:forEach>
      	</tbody>
     </table>

</div>


<div class="modal-footer">
	<a href="#" class="btn " data-dismiss="modal">取消</a>
</div>
