<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	
	<s:form action="candssbAjustMent_save.action" theme="simple" id="editForm" name="editForm" >
		<s:hidden name="candAjustMent.id"></s:hidden>
		<s:hidden name="candAjustMent.ajustmentStatus" id="candAjustMentStatus" value="0"></s:hidden>
	    <div class="row-fluid">
    <div class="span4"><div calss="span4">
		
	<div class="input-append">
	<p>
		<label>员工编号：</label>
	</p>
    <input type="text"  class="span7  {validate:{required:true,messages:{required:'员工编号不能为空'}}} input-small" id="ajustmentCandId" name="candAjustMent.candidateId" value="${candAjustMent.candidateId}">
		<a class="chooseCand btn  btn-info"  href="ssbajustment_editCand.action" ><i class="icon-ok"></i>确认</a>
    </div>
		</div>
		</div>
    <div class="span8">
    <br><br>
    <table>
    <tr>
    <td><label >员工中文姓名：</label></td>
    <td><input type="text" disabled="disabled" name="candAjustMent.candidateName" id="cityName" value="${candAjustMent.candidateName}"></td>
    </tr>
     <tr>
    <td><label >身份证件号码：</label></td>
    <td><input type="text" disabled="disabled" name="candAjustMent.candidateNo" id="cityName" value="${candAjustMent.candidateNo}"></td>
    </tr>
     <tr>
    <td><label >收费规则：</label></td>
    <td>
    				<s:if test="candAjustMent.ssbChargeMap!=null && candAjustMent.ssbChargeMap.size()>0">
					<s:select id="ruleId"
					cssClass="ui-widget-content ssbChange {validate:{required:true,messages:{required:'收费规则不能为空'}}}"
					list="candAjustMent.ssbChargeMap" cssStyle="width:150px" 
					name="candAjustMent.ruleId"
					headerKey=""
					theme="simple"/>
				</s:if>
				<s:elseif test="candAjustMent.ssbChargeMap==null || candAjustMent.ssbChargeMap.size()==0">
					<s:label>无收费规则</s:label>
				</s:elseif>
    </td>
    </tr>
     <tr>
    <td><label >社保类型：</label></td>
    <td>
    <s:if test="candAjustMent.candSsbList!=null && candAjustMent.candSsbList.size()>0">
					<s:select id="ssbId"
					cssClass="ui-widget-content ssbChange {validate:{required:true,messages:{required:'社保类型不能为空'}}}"
					list="candAjustMent.candSsbList" cssStyle="width:150px" 
					name="candAjustMent.ssbId"
					listKey="id" listValue="name"
					headerKey=""
					theme="simple"/>
				</s:if>
				<s:elseif test="candAjustMent.candSsbList==null || candAjustMent.candSsbList.size()==0">
					<s:label>无社保类型</s:label>
				</s:elseif>
    </td>
    </tr>
     <tr>
    <td><label >社保缴纳地： </label></td>
    <td><input type="text" disabled="disabled" name="candSsb.cityName" id="cityName" value=""></td>
    </tr>
     <tr>
    <td><label >社保费用月：</label></td>
    <td><input type="text" disabled="disabled" name="${candAjustMent.monthFee}" id="cityName" value="${candAjustMent.monthFee}"></td>
    </tr>
    </table>
    
    </div>
    </div>
    
    <div >
		<div ><caption><h4>员工社保金额信息调整</h4></caption></div>
			<p>
				<a class="btn btn-primary btn-small" href="javascript:addSsbItemNew()"><i class="icon-plus  icon-white"></i>添加项目</a>
				<font color="red">(项目中残疾人保障金和社保供应商管理费的个人调整金额无须填写默认为0)</font>
			</p>
			<div id="editlist">
				<jsp:include page="edit-itemlist.jsp"></jsp:include>
			</div>
		</div>
	</s:form>
	
<script type="text/javascript">
	$(function(){
		
		$('#ruleId').combobox({size: 18});
		$('#ssbId').combobox({size: 18});
	});
	
	//删除新增的ITEM,删除一并移除验证提示
	function delAjustmentItem(target,index) {
		
		$(target).parent().parent().remove();
		$("#tr_"+index).remove();
	}
	
	//添加item(刷整个table)
	function addSsbItemNew() {
		var data = $("#editForm").serialize();
		$.ajax({
			url : "candssbAjustMent_addItemNew.action",
			async : false,
			data : data,
			type : "POST",
			dataType : "html",
			success : function(html) {
				$("#editlist").empty();
				$("#editlist").append(html);
			}
		});
	}
	//社保变更时，动态改变信息
	$("#ruleId").change(function(){
		var ruleId = $("#ruleId").val();
		var ssbId = $("#ssbId").val();
		var candId = $("#ajustmentCandId").val();
		$.ajax({
			url : "candssbAjustMent_changeRule.action?candAjustMent.ruleId="+ruleId+"&candAjustMent.candidateId="+candId,
			async : false,
			type : "POST",
			dataType : "json",
			success : function(data) {
				
			$("#ssbId").empty();
			for(var i = 0 ; i< data.ssbHeadList.length ; i++){
				if(data.ssbId == data.ssbHeadList[i][0]){
					$("#ssbId").append("<option selected='selected' value='"+data.ssbHeadList[i][0]+"'>"+data.ssbHeadList[i][1]+"</option>");
				}else{
					$("#ssbId").append("<option value='"+data.ssbHeadList[i][0]+"'>"+data.ssbHeadList[i][1]+"</option>");
				}
			}
			
			$("#cityName").val(data.cityName);
			$("#serviceName").val(data.serviceContent);
			$("#startMonth").val(data.jqny);
			$("#vendorName").val(data.vendorName);
			$("#stopMonth").val(data.tjny); 
			}
		});
	});
	
	//社保变更时，动态改变信息
	$("#ssbId").change(function(){
		var ssbId = $("#ssbId").val();
		var candId = $("#ajustmentCandId").val();
		$.ajax({
			url : "candssbAjustMent_changeSsb.action?candAjustMent.ssbId="+ssbId+"&candAjustMent.candidateId="+candId,
			async : false,
			type : "POST",
			dataType : "json",
			success : function(data) {
				$("#cityName").val(data.cityName);
				$("#serviceName").val(data.serviceName);
				$("#startMonth").val(data.startMonth);
				$("#vendorName").val(data.vendorName);
				$("#stopMonth").val(data.stopMonth);
			}
		});
	});


	$("a.chooseCand").click(function(){
		var _this =  $(this);
		var _url = _this.attr("href");
		var div_edit = $( "div.edit");	
		var data = $("#ajustmentCandId").val();
		if(data == "" ||data == null){
			alert("请输入员工编号！");
			return false;
		}
		$.ajax({
			url : _url,
			data: "&candAjustMent.candidateId="+data,
			cache : data,
			dataType : "html",
			success : function(html) {
				if(!page.isJson(html)) {
					try{
						div_edit.html(html);
					}catch(err){
						
						div_edit.html(htmlAddDivHight(html));
						//初始化验证
						initvalidate();
					}
				}else{
					div_edit.html(htmlAddDivHight(html));
					//初始化验证
					initvalidate();
				}
			}
		}); 
		return false;
	});
	</script>