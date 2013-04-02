<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="itemtype_save.action" theme="simple" id="editForm" name="editForm">
	<s:hidden name="itemType.id"></s:hidden>
	<s:hidden name="itemType.createBy"></s:hidden>
	<s:hidden name="itemType.createDate"></s:hidden>
	<s:hidden name="itemType.status"></s:hidden>
	<table  class="table table-condensed">
		<tr>
			<td>项目元素类型中文名：</td>
			<td><s:textfield id="itemtype.name" name="itemType.name" cssClass="{validate:{required:true,messages:{required:'项目元素类型中文名不能为空'}}}"/>
			</td>
			<td>项目元素类型英文名：</td>
			<td><s:textfield id="itemtype.nameEn" name="itemType.nameEn"/>
		</tr>
	</table>		
</s:form>
