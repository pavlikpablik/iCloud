<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table  class="table_nowrap table table-condensed">
			<thead>
				<tr>
					<th  >调整项目</th>
					<th  >个人调整金额</th>
					<th  >公司调整金额</th>
					<th  >调整社保所属月(YYYY/MM)</th>
					<th  >操作</th>
				</tr>
			</thead>
			<tbody id="ajustDetailImport">
				<s:iterator value="candAjustMent.candAjustmentssbDetails" id='details' status='st'>
				
<%-- 				<c:choose>
				<c:when test="${st.index % 2 == 0}">
					<tr  class="trdouble" listTableRowIndex="${st.index }">
				</c:when>
				<c:otherwise>
					<tr  class="trsingle" listTableRowIndex="${st.index }">
				</c:otherwise>
				</c:choose> --%>
					<tr>
					<td >
						<s:hidden name="candAjustMent.candAjustmentssbDetails[%{#st.index}].id"></s:hidden>
						<s:select cssClass="{validate:{required:true,messages:{required:'险种第%{#st.index +1 }条险种内容不能为空'}}} text-select ui-widget-content"
							list="ssbItemList"  
							name="candAjustMent.candAjustmentssbDetails[%{#st.index}].itemId" 
							listKey="id" listValue="name" theme="simple" headerKey="" headerValue="请选择"></s:select>
					</td>
					<td>
						<s:textfield cssStyle="text-align: right;" onBlur="validatePer(this.value)" cssClass="span2 " name="candAjustMent.candAjustmentssbDetails[%{#st.index}].personAmount" theme="simple"></s:textfield>
					</td>
					<td>
						<s:textfield  cssStyle="text-align: right;" onBlur="validateCom(this.value)" cssClass="span2 " name="candAjustMent.candAjustmentssbDetails[%{#st.index}].companyAmount" theme="simple"></s:textfield>
					</td>
					<td>
						<s:textfield cssClass="monthAttribute {validate:{dateYM:true,messages:{dateYM:'险种第%{#st.index +1 }起缴年月不合法，不是YYYY/MM格式'}}}" name="candAjustMent.candAjustmentssbDetails[%{#st.index}].monthAttribute" theme="simple"></s:textfield>
					</td>
					
					<td align="center">
							<a href="#" class="btn  btn-small  "  onclick="delAjustmentItem(this,'<s:property value="#st.index"/>')" removeIndex="<s:property value="#st.index"/>" >删除</a>
					</td> 
					</tr>
				</s:iterator>
			</tbody>
		</table>
<script type="text/javascript">


		$(function(){
			/* 
			//选择普通年月
			$('#dpYears').datepicker();
			
			*/
			//只选择月份
			$('.monthAttribute').datepicker({
				startView:1,//1：先出月份再出年，2：先出年再出月份
				format: 'yyyy/mm',
				month_mode:true//月份模式，只选择到月份就ok
				}
			); 
			
			
			
		});

	function validatePer( Amount){
		if(!isNaN(Amount)){
			
			}else{
				$.pnotify({
			    	title: '验证信息',
			    	text: "个人调整金额必须为数值",
			    	type: 'error',
			    	styling: 'bootstrap'
				});
			}
	}

	function validateCom( Amount){
		if(!isNaN(Amount)){
			
			}else{
				$.pnotify({
			    	title: '验证信息',
			    	text: "公司调整金额必须为数值",
			    	type: 'error',
			    	styling: 'bootstrap'
				});
			}
	}
</script>