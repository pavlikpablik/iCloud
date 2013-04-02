<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="item_save.action" theme="simple" id="editForm" name="editForm">
	<s:hidden name="baseItem.id"></s:hidden>
	<s:hidden name="baseItem.createBy"></s:hidden>
	<s:hidden name="baseItem.createDate"></s:hidden>
	<s:hidden name="baseItem.status"></s:hidden>
	<table  class="table table-condensed">
		<tr>
			<td>项目元素中文名*：</td>
			<td><s:textfield id="baseItem.name" name="baseItem.name" cssClass="{validate:{required:true,messages:{required:'项目元素中文名不能为空'}}}"/>
			</td>
			<td>项目元素英文名*：</td>
			<td><s:textfield id="baseItem.nameEn" name="baseItem.nameEn" cssClass="{validate:{required:true,messages:{required:'项目元素英文名不能为空'}}}"/>
		</tr>
		<tr>
			<td>项目元素类型：</td>
			<td>
				<div>
					<c:forEach items="${typeList}" var="type">
						<div>
							<input name="typeIds" type="checkbox" <c:if test="${type.isUse == 'isUse'}">checked="checked"</c:if> value="${type.id}"/>
							${type.name} 
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</c:forEach>
				</div>
			</td>
			<td> 项目类别*：  </td>
			<td>
			
			<s:select  label=""  cssClass="{validate:{required:true,messages:{required:'项目类别不能为空'}}} "   
				list="#{'1':'社保','2':'工资','3':'服务费','4':'商保','5':'其他社保(社保调整)'}" name="baseItem.type" 
				listKey="key" listValue="value" headerKey="" headerValue="请选择"  />
			
			</td>
		</tr>
		<tr>
			<td>序号：</td>
			<td><s:textfield name="baseItem.seq" id="baseItem.seq" cssClass="{validate:{digits:true,messages:{digits:'序号必须为数值'}}}"></s:textfield>
			</td>
			<td>GROUP：</td>
			<td>
			<s:textfield name="baseItem.itemGroup" id="baseItem.itemGroup" ></s:textfield>
<!--			<select id="" name="item.itemGroup" >-->
<!--							<option value="">请选择</option>-->
<!--							<c:forEach items="${ssbDesList}" var="ssbDes">-->
<!--								<option value="${ssbDes}" title="${ssbDes}" -->
<!--							<c:if test="${item.itemGroup == ssbDes}"> selected="selected"</c:if>>${ssbDes}-->
<!--							</option>-->
<!--							</c:forEach>-->
<!--					</select>-->
			</td>
		</tr>
		<tr>
			<td>个人税前：</td>
			<td><input type="checkbox" name="baseItem.personTax" id="itempersonTax"  value="1" <c:if test="${baseItem.personTax == 1}">checked="checked"</c:if> />
			</td>
			<td>公司税前：</td>
			<td><input type="checkbox" name="baseItem.companyTax" id="itemcompanyTax" value="1" <c:if test="${baseItem.companyTax == 1}">checked="checked"</c:if>/>
			</td>
		</tr>
		<tr>
			<td>个人扣除：</td>
			<td>
				<input type="checkbox" name="baseItem.isNegative" id="isNegative" value="1" <c:if test="${baseItem.isNegative == 1}">checked="checked"</c:if>/>
			</td>
			<td>公司扣除：</td>
			<td><input type="checkbox" name="baseItem.isCompanyNegative" value="1" <c:if test="${baseItem.isCompanyNegative == 1}">checked="checked"</c:if>/>
			</td>
		</tr>
		
		<tr>
			<td>TAXABLE：</td>
			<td>
				<s:select  label=""  cssClass="{validate:{required:true,messages:{required:'TAXABLE不能为空'}}} "   
					list="#{'Y':'是','N':'否'}" name="baseItem.taxAble" 
					listKey="key" listValue="value" headerKey="" headerValue="请选择"  />
			</td>
			<td>IN/OUT：</td>
			<td>
			<s:select  label=""  cssClass="{validate:{required:true,messages:{required:'IN/OUT不能为空'}}} "   
					list="#{'Y':'是','N':'否'}" name="baseItem.inOrOut" 
					listKey="key" listValue="value" headerKey="" headerValue="请选择"  />
			</td>
		</tr>
		<tr>
			<td>ITEMTPYE：</td>
			<td>
			
			<select id="" name="baseItem.itemType"  class="{validate:{required:true,messages:{required:'ITEMTEYP不能为空'}}} "   >
							<option value="">请选择</option>
							<c:forEach items="${itemsTypeList}" var="ssbsType">
								<option value="${ssbsType}" title="${ssbsType}" 
							<c:if test="${baseItem.itemType == ssbsType}"> selected="selected"</c:if>>${ssbsType}
							</option>
							</c:forEach>
					</select>

			</td>
			<td>服务类别：</td>
			<td>
					<s:select  label=""  cssClass="{validate:{required:true,messages:{required:'服务类别不能为空'}}} "   
					list="#{'Salary':'Salary','SB':'SB','SF':'SF'}" name="baseItem.serviceType" 
					listKey="key" listValue="value" headerKey="" headerValue="请选择"  />
			</td>
		</tr>
		
		
		
		<tr>
			<td>描述：</td>
			<td>
				<s:textarea name="baseItem.describe" id="baseItem.describe" ></s:textarea>
			</td>
			<td>备注：</td>
			<td>
				<s:textarea name="baseItem.remark" id="baseItem.remark"></s:textarea>
			</td>
		</tr>

		<tr>
			<td>是否个人发放：</td>
			<td><input id="item.isPersonAmount" type="checkbox" value="1"
				name="baseItem.isPersonAmount"
				<c:if test="${baseItem.isPersonAmount == 1}">checked="checked"</c:if> />
			</td>
			<td>是否公司费用：</td>
			<td><input id="item.isCompanyAmount" type="checkbox" value="1"
				name="baseItem.isCompanyAmount"
				<c:if test="${baseItem.isCompanyAmount == 1}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td>是否个人扣除：</td>
			<td><input id="item.isPersonPay" type="checkbox" value="1"
				name="baseItem.isPersonPay"
				<c:if test="${baseItem.isPersonPay == 1}">checked="checked"</c:if> />
			</td>
			<td>是否公司成本：</td>
			<td><input id="item.isCompanyPay" type="checkbox" value="1"
				name="baseItem.isCompanyPay"
				<c:if test="${baseItem.isCompanyPay == 1}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td>是否税前发放：</td>
			<td><input id="item.taxProvideBefore" type="checkbox" value="1"
				name="baseItem.taxProvideBefore"
				<c:if test="${baseItem.taxProvideBefore == 1}">checked="checked"</c:if> />
			</td>
			<td>是否税后扣除：</td>
			<td><input id="item.taxDeductAfter" type="checkbox" value="1"
				name="baseItem.taxDeductAfter"
				<c:if test="${baseItem.taxDeductAfter == 1}">checked="checked"</c:if> />
			</td>
		</tr>

	</table>		
</s:form>
	<!--TODO: 暂时将js写入到jsp中 后期将抽取独立存放-->
	<script type="text/javascript">
			$("#submitEntry").click(function(){
				$.ajax({
					url : "item_save.action",
					type : "POST",
					data : $("#editForm").serialize(),
					async : false,
					dataType : "html",
					success : function(html) {
						alert("保存成功");
					}
				});
				return false;
			});
	</script>