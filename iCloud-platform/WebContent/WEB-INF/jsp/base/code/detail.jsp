<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="table table-condensed">
	<tr>
		<td>数据键：</td>
		<td>${Code.bdCode}</td>
		<td>数据值</td>
		<td>${Code.bdName}</td>
	</tr>

	<tr>
		<td>所属类别</td>
		<td>${Code.bdType}</td>
		<td >备注</td>
		<td >
			<textarea rows="5">${Code.bdRemark}</textarea>
		</td>
	</tr>
</table>
