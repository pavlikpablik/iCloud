<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="code_save.action" theme="simple" id="editForm" name="editForm">
		<s:hidden name="code.id"></s:hidden>
		<s:hidden name="code.createBy"></s:hidden>
		<s:hidden name="code.createDate"></s:hidden>
		<s:hidden name="code.bdStatus"></s:hidden>
		<table  class="table table-condensed">
			<tr>
				<td>数据键<font color="red">*</font>：</td>
				<td><s:textfield placeholder="数据键" cssClass="{validate:{required:true,messages:{required:'数据键不能为空'}}}" id="code.bdCode" name="code.bdCode" />
				</td>
				<td>数据值<font color="red">*</font>：</td>
				<td><s:textfield placeholder="数据值" cssClass="{validate:{required:true,messages:{required:'数据值不能为空'}}}" id="code.bdName" name="code.bdName" />
			</tr>
			<tr>
				<td>所属类别<font color="red">*</font></font>：</td>
				<td><s:textfield placeholder="所属类别" cssClass="{validate:{required:true,messages:{required:'数据类别不能为空'}}}" id="code.bdType" name="code.bdType" /></td>
				<td>序号：</td>
				<td><s:textfield placeholder="序号" id="code.seq" name="code.seq" /></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><s:textarea placeholder="备注" name="code.bdRemark" id="code.bdRemark" rows="5"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<script type="text/javascript">	
		$("#submitEntry").click(function(){
			$.ajax({
				url : "baseCode_save.action",
				type : "POST",
				data : $("#baseCodeForm").serialize(),
				async : false,
				dataType : "html",
				success : function(html) {
					alert("保存成功");
				}
			});
			return false;
		});
	</script>
