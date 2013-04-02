<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="table table-condensed">
	<tr>
		<td>省份中文名：</td>
		<td>${city.name}</td>
		<td>省份英文名：</td>
		<td>${city.nameEn}</td>
	</tr>

	
	<tr>
		<td>备注：</td>
		<td colspan="3"><textarea style="height: 80px; width: 600px"
				disabled="disabled">${city.remark}</textarea></td>

	</tr>
</table>
<table class="table table-condensed">
	<thead>
		<tr>
			<th>城市中文名</th>
			<th>城市英文名</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody id="cityImport">
		<c:forEach items="${city.citys}" var="c" varStatus="st">
			<tr>
				<td>${c.name}</td>
				<td>${c.nameEn}</td>
				<td>${c.remark}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
	$(function() {
		$(".page_submit_save").hide();
	});
	</script>