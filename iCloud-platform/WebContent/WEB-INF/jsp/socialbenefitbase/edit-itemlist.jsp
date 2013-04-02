<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<table  class="table_nowrap table table-condensed">
			<thead>
				<tr>
					<th >险种</th>
					<th >公司比例</th>
					<th >个人比例</th>
					<th >公司基数上限</th>
					<th >公司基数下限</th>
					<th >个人基数上限</th>
					<th >个人基数下限</th>
					<th >公司固定金</th>
					<th >个人固定金</th>
					<th >公司缴存额上限</th>
					<th >公司缴存额下限</th>
					<th >个人缴存额上限</th>
					<th >个人缴存额下限</th>
					<%--
					<th  width="11%">支付月</th>
					<th  width="5%">每年调整时间</th>
					<th  width="7%">社保所属月</th>
					<th  width="5%">截止日期</th>
					<th  width="10%">开始结束规则</th>
					<th  width="10%">描述</th>
					<th  width="10%">备注</th>
					 --%>
					<th >操作</th>
				</tr>
			</thead>
			<tbody id="socialBenefitImport">
			
				<s:iterator value="socialBenefit.socialBenefitDetails" id='details' status='st'>
				
				<tr >
					<td >
						<s:hidden name="socialBenefit.socialBenefitDetails[%{#st.index}].id"></s:hidden>
						<s:select cssClass="span2 {validate:{required:true,messages:{required:'险种第%{#st.index +1 }条险种内容不能为空'}}}"
							 list="itemList"  name="socialBenefit.socialBenefitDetails[%{#st.index}].itemId" 
							listKey="id" listValue="name" theme="simple" headerKey="" headerValue="请选择"></s:select>
					</td>
					<td >
						<nobr><s:textfield cssClass="input-mini money  {validate:{required:true,number:true,messages:{required:'险种第%{#st.index +1 }条公司比例不能为空',number:'险种第%{#st.index +1 }条公司比例必须为数值'}}} "   name="socialBenefit.socialBenefitDetails[%{#st.index}].companyPerency"   theme="simple"></s:textfield>%</nobr>
					</td>
					<td >
						<nobr><s:textfield cssClass="input-mini money {validate:{required:true,number:true,messages:{required:'险种第%{#st.index +1 }条个人比例不能为空',number:'险种第%{#st.index +1 }条个人比例必须为数值'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].personPerency"  theme="simple"></s:textfield>%</nobr>
					</td>
					<td >
						<s:textfield id="companyuplimit%{#st.index}" cssClass="input-mini money {validate:{required:true,number:true,ge:'#companydownlimit%{#st.index}',messages:{required:'险种第%{#st.index +1 }条公司上限不能为空',number:'险种第%{#st.index +1 }条公司上限必须为数值',ge:'险种第%{#st.index +1 }条公司上限不能小于下限'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].companyCap"  theme="simple"></s:textfield>
					</td>
					<td >
						<s:textfield id="companydownlimit%{#st.index}" cssClass="input-mini money {validate:{required:true,number:true,le:'#companyuplimit%{#st.index}',messages:{required:'险种第%{#st.index +1 }条公司下限不能为空',number:'险种第%{#st.index +1 }条公司下限必须为数值',le:'险种第%{#st.index +1 }条公司下限不能大于上限'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].companyFloor"   theme="simple"></s:textfield>
					</td>
					<td >
						<s:textfield id="personuplimit%{#st.index}" cssClass="input-mini money {validate:{required:true,number:true,ge:'#persondownlimit%{#st.index}',messages:{required:'险种第%{#st.index +1 }条个人上限不能为空',number:'险种第%{#st.index +1 }条个人上限必须为数值',ge:'险种第%{#st.index +1 }条个人上限不能小于下限'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].personCap"  theme="simple"></s:textfield>
					</td>
					<td >
						<s:textfield id="persondownlimit%{#st.index}" cssClass="input-mini money {validate:{required:true,number:true,le:'#personuplimit%{#st.index}',messages:{required:'险种第%{#st.index +1 }条个人下限不能为空',number:'险种第%{#st.index +1 }条个人下限必须为数值',le:'险种第%{#st.index +1 }条个人下限不能大于上限'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].personFloor"   theme="simple"></s:textfield>
					</td>
					<td >
						<s:textfield cssClass="input-mini money {validate:{required:true,number:true,messages:{required:'险种第%{#st.index +1 }条公司固定金不能为空',number:'险种第%{#st.index +1 }条公司固定金必须为数值'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].companyFixAmount" theme="simple"></s:textfield>
					</td>
					<td >
						<s:textfield cssClass="input-mini money {validate:{required:true,number:true,messages:{required:'险种第%{#st.index +1 }条个人固定金不能为空',number:'险种第%{#st.index +1 }条个人固定金必须为数值'}}} " name="socialBenefit.socialBenefitDetails[%{#st.index}].personFixAmount"  theme="simple"></s:textfield>
					</td>
					
					<td style="text-align: right;">
						<s:property value="#details.companyPayOffCapAmount"/>
					</td>
					<td style="text-align: right;" >
						<s:property value="#details.companyPayOffFloorAmount"/>
					</td>
					<td style="text-align: right;" >
						<s:property value="#details.personPayOffCapAmount"/>
					</td>
					<td style="text-align: right;" >
						<s:property value="#details.personPayOffFloorAmount"/>
					</td>
					<td >
						<a  class="btn btn-mini" onclick="mergeOne('<s:property value="#st.index"/>')" >引入</a>
						<s:if test="#details.id!=null"> 
								<input type="hidden" name="socialBenefit.socialBenefitDetails[${st.index}].status" value="${details.status}">
								<a  class="closesocialBenefitItem btn btn-mini"><s:if test="#details.status ==1">停用</s:if><s:if test="#details.status ==0">启用</s:if></a>
						</s:if>
						<s:else>
							<a  class="btn btn-mini"  onclick="subSocialBenefit(this,'<s:property value="#st.index"/>')" removeIndex="<s:property value="#st.index"/>" >删除</a>
						</s:else>
						<a class="btn btn-mini"  onclick="showDetail('${st.index}')">显示详细</a>
					</td> 
					</tr>
					<tr style="display: none;" id="tr_${st.index}" listTableRowIndex="${st.index }" class=" showtr" >
						<td >详细信息</td>
						<td colspan="13" >
							<table class="quborder" style="width: 90%">
								<tr>
									<td align="right" style="border-top:0px;" width="15%">支付月*：</td>
									<td style="border-top:0px;" colspan="4">
									<label class="checkbox inline" >
									<input id="isFullChecksocialBenefittermMonth${st.index}" type="checkbox" name="socialBenefit.socialBenefitDetails[${st.index}].everyMonth"  value="true" class=""
									 <c:if test="${details.everyMonth=='true'}">checked="checked"</c:if>
									 onclick="showTermMonth(this,'${st.index}')">每月
									</label>&nbsp;&nbsp;
									<span id="socialBenefittermMonthspan${st.index}" style="display: <c:if test="${details.everyMonth=='true'}">none</c:if>">
									<c:forEach items="${mp}" var="entry3" varStatus="st3">
									<label class="checkbox inline" style="font-weight:normal;color:black;">
									<input type="checkbox" name="socialBenefit.socialBenefitDetails[${st.index}].termMonth" 
										id="termMonth_${st.index}_${st3.index}" onclick="isCheckAllTermMonth('${st.index}')"
									<c:forEach items="${details.termMonths}" var="checkeds">
										<c:if test="${checkeds == entry3.key}">checked="checked"</c:if> 
									</c:forEach>
									value="${entry3.key}" class=" socialBenefittermMonthclass${st.index}"/>${entry3.value}
									</label>
									</c:forEach>
									</span>
									</td>
								</tr>
								<tr>
									<td align="right" style="border-right:0px" width="15%">社保开始月缴纳规则*：</td>
									<td style="border-right:0px"  colspan="3">
										<s:select  onchange="sbeStartTypeChange(this.value,'%{#st.index}')" cssClass="{validate:{required:true,messages:{required:'险种第%{#st.index +1 }条社保开始月缴纳规则不能为空'}}}   select_width"  theme="simple" cssStyle="width:80px"
										list="effecitveList" name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].sbe.startType'}" listKey="id" listValue="bdcode"></s:select>
										
										<span id="startTypeSpan1${st.index}">
										<c:if test="${details.sbe.startType==33}">
											早于
										</c:if>
										<c:if test="${details.sbe.startType==32}">
											工作天数少于
										</c:if>
										</span>
										<s:textfield  cssStyle="width:20px" theme="simple" cssClass="{validate:{digits:true,range:[1,31],messages:{digits:'险种第%{#st.index +1 }条社保开始月缴纳规则为1~31的整数',range:'险种第%{#st.index +1 }条社保开始月缴纳规则为1~31的整数'}}}  " name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].sbe.startDay'}" readonly="true" />
										<span id="startTypeSpan2${st.index}">
										<c:if test="${details.sbe.startType==33}">
											日入职，入职当月需缴纳社保
										</c:if>
										<c:if test="${details.sbe.startType==32}">
											天，入职当月需缴纳社保
										</c:if>
									</span>
									</td>
									
									<td >社保操作月*：</td>
									<td style="border-right:0px" width="20%">
									<select name="socialBenefit.socialBenefitDetails[${st.index}].monthEffctive" class="{validate:{required:true,messages:{required:'险种第${st.index + 1}条社保起缴月不能为空'}}} " style="width:80px">
										<c:forEach items="${monthEffctiveList}" var="abm">
										<option value="${abm.id}" 
										<c:if test="${details.monthEffctive == abm.id}">selected</c:if> >${abm.bdcode}
										</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td >社保结束月缴纳规则*：</td>
									<td colspan="3">
										<s:select  onchange="sbeEndTypeChange(this.value,'%{#st.index}')" cssClass="{validate:{required:true,messages:{required:'险种第%{#st.index +1 }条结束规则不能为空'}}}   select_width" theme="simple" cssStyle="width:80px"
											list="effecitveList" name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].sbe.endType'}" listKey="id" listValue="bdcode"></s:select>
										<span id="endTypeSpan1${st.index}">
										<c:if test="${details.sbe.endType==33}">
											晚于
										</c:if>
										<c:if test="${details.sbe.endType==32}">
											工作天数大于
										</c:if>
										</span>
										<s:textfield  cssStyle="width:20px" cssClass="{validate:{digits:true,range:[1,31],messages:{digits:'险种第%{#st.index +1 }条社保结束月缴纳规则为1~31的整数',range:'险种第%{#st.index +1 }条社保结束月缴纳规则为1~31的整数'}}}  " theme="simple"
										 name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].sbe.endDay'}" readonly="true"/>
										<span id="endTypeSpan2${st.index}">
										<c:if test="${details.sbe.endType==33}">
											日离职，离职当月需缴纳社保
										</c:if>
										<c:if test="${details.sbe.endType==32}">
											天，离职当月需缴纳社保
										</c:if>
										</span>
									</td>
									
									<td align="right" style="border-right:0px">社保所属月*：</td>
									<td style="border-right:0px">
										<select name="socialBenefit.socialBenefitDetails[${st.index}].attributeMonth" class="{validate:{required:true,messages:{required:'险种第${st.index + 1}条社保所属月不能为空'}}} " style="width:80px">
										<c:forEach items="${attributeMonthList}" var="abm">
										<option value="${abm.id}" 
										<c:if test="${details.attributeMonth == abm.id}">selected</c:if> >${abm.bdcode}
										</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right" style="border-right:0px" width="15%">基数每年调整月份：</td>
									<td style="border-right:0px"  width="20%" colspan="3">
										<s:select cssClass=""
										cssStyle="width:80px" theme="simple"  name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].ajustTime'}" 
										list="#request.mp"  
										headerKey="" headerValue="请选择" listKey="key" listValue="value"></s:select>
									</td>
									<td align="right" style="border-right:0px" width="15%">退保是否产生费用:</td>
									<td style="border-right:0px" width="15%">
										<label class="radio inline" style="font-weight:normal;color:black;">
											<input type="radio" name="socialBenefit.socialBenefitDetails[${st.index}].quitSsbStatus" <c:if test="${socialBenefit.socialBenefitDetails[st.index].quitSsbStatus == 1}"> checked="checked" </c:if>
												value="1"/>是
										</label>
										<label class="radio inline" style="font-weight:normal;color:black;">
											<input type="radio" name="socialBenefit.socialBenefitDetails[${st.index}].quitSsbStatus" <c:if test="${socialBenefit.socialBenefitDetails[st.index].quitSsbStatus == 0 or socialBenefit.socialBenefitDetails[st.index].quitSsbStatus == null}"> checked="checked" </c:if>
												value="0"/>否
										</label>
									</td>
								
								</tr>
								<tr>
									<td align="right" style="border-right:0px" width="15%">加保截止日期*：</td>
									<td style="border-right:0px" width="20%" colspan="3">
										<s:textfield cssClass="{validate:{required:true,digits:true,range:[1,31],messages:{required:'险种第%{#st.index +1 }条加保截止日期不能为空',digits:'险种第%{#st.index +1 }条加保截止日期为1~31的整数',range:'险种第%{#st.index +1 }条加保截止日期为1~31的整数'}}}  "
										cssStyle="width:80px"
										name="socialBenefit.socialBenefitDetails[%{#st.index}].addLimitTime" theme="simple"></s:textfield>	
									</td>
									<td align="right" style="border-right:0px" width="15%">退保截止日期*：</td>
									<td style="border-right:0px" width="15%">
										<s:textfield cssClass="{validate:{required:true,digits:true,range:[1,31],messages:{required:'险种第%{#st.index +1 }条退保截止日期不能为空',digits:'险种第%{#st.index +1 }条退保截止日期为1~31的整数',range:'险种第%{#st.index +1 }条退保截止日期为1~31的整数'}}}  "
										cssStyle="width:80px"
										name="socialBenefit.socialBenefitDetails[%{#st.index}].ebbLimitTime" theme="simple"></s:textfield>
									</td>
								</tr>
								<tr>
								<td align="right" style="border-right:0px">描述：</td>
								<td style="border-right:0px" colspan="5">
							 		<s:textarea cssStyle="width:600px;height:50px" cssClass="" theme="simple"  name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].description'}"></s:textarea>
								</td>
								
								</tr>
								<tr>
								<td align="right" style="border-right:0px">备注：</td>
								<td style="border-right:0px" colspan="5">
							 		<s:textarea cssStyle="width:600px;height:50px" cssClass="" theme="simple"  name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].remark'}"></s:textarea>
								</td>
								</tr>
							</table>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
