<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
	
</style>

<div class="row-fluid">
	<div class="span6">
		<p><label>社保类型名称：</label><s:property value="socialBenefit.name"/></p>
				<p><label>省份-城市：</label><s:property value="socialBenefit.cityName"/></p>
				<p><label>户籍：</label>
				<!-- <div class="socialBenefit-residency"> -->
				<s:property value="socialBenefit.residency"/>
				<!-- </div> -->
				</p>
				<p><label>开始规则：</label><s:property value="socialBenefit.effectiveStart"/></p>
				<p><label>结束规则：</label><s:property value="socialBenefit.effectiveEnd"/> </p>
				<p><label>社保操作月：</label><s:property value="socialBenefit.meString"/> </p>
				<p><label>社保所属月：</label><s:property value="socialBenefit.amString"/></p>	
				<p><label>&nbsp;</label></p>
				<%-- <p><label><a class="detailUploadFiles btn" 
					href="socialBenefitBase_detailUploadFile.action?socialBenefit.ssbFile=${socialBenefit.ssbFile }">
					查看附件&nbsp;<span class="badge badge-info">${uploadFileNum }</span></a>
				</label> --%>
	</div>
	<div class="span6">
				<p><label>加保截止日期：</label><s:property value="socialBenefit.addLimitTime"/> </p>
				<p><label>退保截止日期：</label><s:property value="socialBenefit.ebbLimitTime"/> </p>
				<p><label>支付月：</label><s:property value="socialBenefit.termMonth"/></p>
				<p><label>退保是否产生费用：</label><s:property value="socialBenefit.quitSsbStatusStr"/></p>
				<p><label>基数每年调整月份：</label><s:property value="socialBenefit.ajustTime"/>月</p>
				<p><label>描述：</label>
					<label>
						<s:textarea name="socialBenefit.description" readonly="true" cssStyle="width:250px;height:80px;"></s:textarea>
					</label>
				</p>
				<p><label>备注（理赔流程及政策相关）：</label>
					<label>
						<s:textarea name="socialBenefit.remark" readonly="true" cssStyle="width:250px;height:80px;"></s:textarea>
					</label>
				</p>
				
	
	</div>

</div>
<div class="btn-toolbar ">
		<div class="btn-group">
		<a class="open btn btn-mini" href="javascript:show(1)"><i class=" icon-folder-open  icon-white"></i>显示</a> 
		<a class="collapsed btn btn-mini" href="javascript:show(0)"><i class="icon-folder-close  icon-white"></i>收起</a> 
		</div>
					
</div>
<table class="pagelisttable table table-condensed" >
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
					<th >状态</th>
					<th >操作</th>
				</tr>
			</thead>
			<tbody id="socialBenefitImport">
			
				<s:iterator value="socialBenefit.socialBenefitDetails" id='details' status='st'>
				<tr >
					<td  >
						<s:property value="#details.itemName"/>
					</td>
					<td >
						<s:property value="#details.companyPerency"/>%
					</td>
					<td >
						<s:property value="#details.personPerency"/>%
					</td>
					<td style="text-align: right;">
						<s:property value="#details.companyCap"/>
					</td>
					<td style="text-align: right;">
						<s:property value="#details.companyFloor"/>
					</td>
					<td style="text-align: right;">
						<s:property value="#details.personCap"/>
					</td>
					<td style="text-align: right;">
						<s:property value="#details.personFloor"/>
					</td>
					<td style="text-align: right;">
						<s:property value="#details.companyFixAmount"/>
					</td>
					<td style="text-align: right;">
						<s:property value="#details.personFixAmount"/>	
					</td>
					<td style="text-align: right;">
						<s:property value="#details.companyPayOffCapAmount"/>	
					</td>
					<td style="text-align: right;">
						<s:property value="#details.companyPayOffFloorAmount"/>	
					</td>
					<td style="text-align: right;">
						<s:property value="#details.personPayOffCapAmount"/>	
					</td>
					<td style="text-align: right;">
						<s:property value="#details.personPayOffFloorAmount"/>	
					</td>
					<td >
							 	<nobr><s:if test="#details.status ==1">启用</s:if>
								<s:if test="#details.status ==0">停用</s:if></nobr>
					</td>
					<td >
						<a class="btn btn-mini" onclick="showDetail(${st.index});">显示详细</a>
					</td>
				</tr>
				<tr style="display: none;" id="detail_tr_${st.index}" class="showtr">
						<td  >详细信息</td>
						<td colspan="14" >
							<table style="width: 100%">
								<tr>
									<td  style="border-top:0px;" >支付月：</td>
									<td style="border-top:0px" colspan="5">
										<s:property value="#details.termMonth"/> 
									</td>
								</tr>
								<tr>
									<td >开始规则：</td>
									<td >
										<s:property value="#details.effectiveStart"/> 
									</td>
									
									<td >社保操作月：</td>
									<td >
										<s:property value="#details.meString"/>	
									</td>
							
									<td   >加保截止日期：</td>
									<td style="" width="18%">
										<s:property value="#details.addLimitTime" />
									</td>
									
								</tr>
								<tr>
									<td >结束规则：</td>
									<td >
										<s:property value="#details.effectiveEnd"/>
									</td>
									
									<td >社保所属月：</td>
									<td>
										<s:property value="#details.amString"/>	
									</td>
									
									<td >退保截止日期：</td>
									<td >
										<s:property value="#details.ebbLimitTime"/> 
									</td>
								</tr>
								<tr>
									<td>每年调整时间：</td>
									<td>
										<s:property value="#details.ajustTime" />月
									</td>
									<td>退保是否产生费用:</td>
									<td colspan="3">
										<s:property value="#details.quitSsbStatusStr"/>
									</td>
								</tr>
								<tr>
									<td >描述：</td>
									<td  colspan="2">
										<s:textarea theme="simple" rows="5" name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].description'}" readonly="true" ></s:textarea>
									</td>
									<td >备注：</td>
									<td  colspan="2">
										<s:textarea theme="simple" rows="5" name="%{'socialBenefit.socialBenefitDetails['+#st.index+'].remark'}" readonly="true" ></s:textarea>
									</td>
								</tr>
								
							</table>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>



		

<script type="text/javascript">
$(function(){
/* //引入增加样式
$( "div.itemBar a.import" ).button({
	icons: {
		primary: "ui-icon-arrowthickstop-1-s"
	}
});
$( "div.itemBar a.addItem" ).button({
	icons: {
		primary: "ui-icon-plusthick"
	}
});
$( "div.itemBar a.collapsed" ).button({
	icons: {
		primary: "ui-icon-folder-collapsed"
	}
});
$( "div.itemBar a.open" ).button({
	icons: {
		primary: "ui-icon-folder-open"
	}
});

$( "#edit-Div-3" ).button({
	icons: {
		primary: "ui-icon-carat-2-n-s"
	}
}).toggle(function(){
	$("#edit-Div-1").hide();
	$("#edit-Div-2").hide();
	$("#edit-Div").height(20);
},function(){
	$("#edit-Div-1").show();
	$("#edit-Div-2").show();
	$("#edit-Div").height(250);
});  */

//查看附件信息
$( "a.detailUploadFiles" ).click(function(){
	var _this =  $(this);
	var _url = _this.attr("href");
	
	var div_uploadfile = $( "div.detailUploadFiles");
	$.ajax({
		url : _url,
		async : false,
		cache : false,
		dataType : "html",
		success : function(html) { 
			div_uploadfile.html(html);
			div_uploadfile.modal('show');
		}
	}); 
	return false;
});

});
function showDetail(index){
	var trdd= $("#detail_tr_"+index);
	if(trdd.css("display")== 'none'){
		$(trdd).show();
	}else{
		$(trdd).hide();
	}
}

function show(val){
	if(val== 1){
		$(".showtr").show();
	}else{
		$(".showtr").hide();
	}
}

</script>

