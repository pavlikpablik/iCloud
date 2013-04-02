<%@page import="com.manpowergroup.cn.icloud.socialbenefitBase.entity.SocialBenefitDetail"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<s:form action="socialBenefitBase_save.action" theme="simple" id="editForm"  name="editForm" >
		<s:hidden name="socialBenefit.id"></s:hidden>
		<s:hidden name="socialBenefit.createBy"></s:hidden>
		<s:hidden name="socialBenefit.createDate"></s:hidden>
		<s:hidden name="socialBenefit.status"></s:hidden>
	
	 <div class="accordion-group ">
    <div class="accordion-heading alter alert-info">
      <a class="accordion-toggle" data-toggle="collapse"  href="#collapseOne">
       <i id="icon-resize" class="icon-folder-close"></i>	<strong>社保类型<c:if test="${socialBenefit.id==null}">新增</c:if><c:if test="${socialBenefit.id!=null}">修改</c:if></strong>
      </a>
    </div>
    <div id="collapseOne" class="accordion-body collapse in">
      <div class="accordion-inner">
       <div class="row-fluid">
	 	<div class="span6">
	 		<p><label>社保类型名称*：</label><s:textfield cssClass="{validate:{required:true,messages:{required:'社保类型名称不能为空'}}} " id="socialBenefit.name" name="socialBenefit.name" /></p>
				<p><label>省份*：</label><s:select list="provinceList" cssClass="{validate:{required:true,messages:{required:'省份不能为空'}}}  " name="socialBenefit.provinceId" id="province" headerKey="" headerValue="请选择" listKey="id" listValue="name" ></s:select> </p>
				<p><label>城市*:</label><s:select list="cityList" cssClass="{validate:{required:true,messages:{required:'城市不能为空'}}}  " name="socialBenefit.cityId" id="socialBenefitcityId" 
										 listKey="id" listValue="name" ></s:select></p>
				<p><label>户籍：</label>
						  <c:forEach items="${residencyList}" var="entry" varStatus="residencyStatus">
							 	 <label class="checkbox inline" style="font-weight:normal;color:black;">
									<input type="checkbox" name="socialBenefit.residency"  id="residency_${residencyStatus.index}" 
										<c:forEach items="${socialBenefit.residencys}" var="checkeds">
											<c:if test="${checkeds == entry.id}">checked="checked"</c:if>
										</c:forEach>
									value="${entry.id}" />${entry.bdcode}
								 </label>
						  </c:forEach>
				</p>
				
				<p><label>社保开始月缴纳规则：</label>
						<s:select list="effecitveList" onchange="sbeStartTypeChange(this.value,'')"   name="socialBenefit.sbe.startType" id="startType" listKey="id" listValue="bdcode"></s:select> 
				
						<br/>
						<span id="startTypeSpan1">
						<c:if test="${socialBenefit.sbe.startType==33}">
							早于
						</c:if>
						<c:if test="${socialBenefit.sbe.startType==32}">
							工作天数少于
						</c:if>
						</span>
						<s:textfield cssClass="input-mini {validate:{digits:true,range:[1,31],messages:{digits:'开始规则为1~31的整数',range:'开始规则为1~31的整数'}}}"    id="startDay" name="socialBenefit.sbe.startDay" readonly="true"/>
						<span id="startTypeSpan2">
							<c:if test="${socialBenefit.sbe.startType==33}">
								日入职，入职当月需缴纳社保
							</c:if>
							<c:if test="${socialBenefit.sbe.startType==32}">
								天，入职当月需缴纳社保
							</c:if>
						</span>
				</p>
				
				
				
				<p><label>社保结束月缴纳规则：</label>
					<s:select list="effecitveList" onchange="sbeEndTypeChange(this.value,'')"   name="socialBenefit.sbe.endType" id="endType" listKey="id" listValue="bdcode"></s:select> 
					<br/>
					<span id="endTypeSpan1">
						<c:if test="${socialBenefit.sbe.endType==33}">
							晚于
						</c:if>
						<c:if test="${socialBenefit.sbe.endType==32}">
							工作天数大于
						</c:if>
						</span>
						<s:textfield cssClass="input-mini {validate:{digits:true,range:[1,31],messages:{digits:'社保结束月缴纳规则为1~31的整数',range:'社保结束月缴纳规则为1~31的整数'}}}"  id="endDay" name="socialBenefit.sbe.endDay" readonly="true"/>
						<span id="endTypeSpan2">
							<c:if test="${socialBenefit.sbe.startType==33}">
								日离职，离职当月需缴纳社保
							</c:if>
							<c:if test="${socialBenefit.sbe.startType==32}">
								天，离职当月需缴纳社保
							</c:if>
						</span>
				<p><label>社保操作月：</label><s:select name="socialBenefit.monthEffctive" id="monthEffctive" list="monthEffctiveList"  listKey="id" listValue="bdcode"></s:select></p>
				<p><label>社保所属月：</label><s:select name="socialBenefit.attributeMonth" id="attributeMonth" list="attributeMonthList"   headerKey="" headerValue="请选择" listKey="id" listValue="bdcode"></s:select></p>
				
				 <p><label>&nbsp;</label>
				    <input type="hidden" name="socialBenefit.ssbFile" id="ssbFile" value="${socialBenefit.ssbFile}"/>
					<%-- <a class="uploadfiles btn"   url="socialBenefitBase_toUploadFile.action" uploadfileHiddenValue="ssbFile" ><i class="icon-download-alt"></i>附件&nbsp;<span class="badge badge-info">${uploadFileNum }</span></a> --%>
					
				 </p>
	 	</div>
		<div class="span6">
			
				<p><label>加保截止日期：</label><s:textfield name="socialBenefit.addLimitTime" id="addLimitTime" cssClass="{validate:{digits:true,range:[1,31],messages:{digits:'加保截止日期为1~31的整数',range:'加保截止日期为1~31的整数'}}}"  theme="simple"></s:textfield></p>
				<p><label>退保截止日期：</label><s:textfield name="socialBenefit.ebbLimitTime" id="ebbLimitTime" cssClass="{validate:{digits:true,range:[1,31],messages:{digits:'退保截止日期为1~31的整数',range:'退保截止日期为1~31的整数'}}}"  theme="simple"></s:textfield></p>
				<p><label>支付月：</label>
					 <label class="checkbox inline" >
						<input id="isFullChecksocialBenefittermMonth"  type="checkbox" name="socialBenefit.everyMonth" value="true"  onclick="showTermMonth(this,'')" <c:if test="${socialBenefit.everyMonth=='true'}">checked="checked"</c:if> />每月
					 </label>
				<%-- <div id="" <c:if test="${socialBenefit.everyMonth=='true'}">style="display:none"</c:if>>
					 <c:forEach items="${mp}" var="entry4" >
					 	<div>
							<input type="checkbox" name="socialBenefit.termMonth" onclick="isCheckAllTermMonth('')" id="socialBenefit-termMonth-${entry4.key}"
								<c:forEach items="${socialBenefit.termMonths}" var="checkeds">
									<c:if test="${checkeds == entry4.key}">checked="checked"</c:if> 
								</c:forEach>
								value="${entry4.key}" class=" socialBenefittermMonthclass"/><label for="socialBenefit-termMonth-${entry4.key}">${entry4.value}</label>
						</div>
					</c:forEach>
					</div> --%>
				</p>
					<div id="socialBenefittermMonthspan" <c:if test="${socialBenefit.everyMonth=='true'}">style="display:none"</c:if> class="control-group">
			            <div class="controls">
			               <c:forEach items="${mp}" var="entry4" varStatus="ss1">
			               	<c:if test="${ss1.index<6}">
							 	 <label class="checkbox inline">
									<input type="checkbox" name="socialBenefit.termMonth" onclick="isCheckAllTermMonth('')" id="socialBenefit-termMonth-${entry4.key}"
										<c:forEach items="${socialBenefit.termMonths}" var="checkeds">
											<c:if test="${checkeds == entry4.key}">checked="checked"</c:if> 
										</c:forEach>
										value="${entry4.key}" class="socialBenefittermMonthclass"/>${entry4.value}
								 </label>
							</c:if>
						  </c:forEach>
			            	
			            </div>
			            <div class="controls">
			               <c:forEach items="${mp}" var="entry5" varStatus="ss2">
			               	<c:if test="${ss2.index>5}">
							 	 <label class="checkbox inline">
									<input type="checkbox" name="socialBenefit.termMonth" onclick="isCheckAllTermMonth('')" id="socialBenefit-termMonth-${entry5.key}"
										<c:forEach items="${socialBenefit.termMonths}" var="checkeds">
											<c:if test="${checkeds == entry5.key}">checked="checked"</c:if> 
										</c:forEach>
										value="${entry5.key}" class="socialBenefittermMonthclass"/>${entry5.value}
								 </label>
							</c:if>
						  </c:forEach>
			            	
			            </div>
		           </div>
				
				<p><label>退保是否产生费用:</label>
					<label class="radio inline" style="font-weight:normal;color:black;">
					<input type="radio" name="socialBenefit.quitSsbStatus" <c:if test="${socialBenefit.quitSsbStatus == 1}">checked="checked"</c:if>
						value="1"/>是
						</label>
						<label class="radio inline" style="font-weight:normal;color:black;">
					<input type="radio" name="socialBenefit.quitSsbStatus" <c:if test="${socialBenefit.quitSsbStatus == 0 or socialBenefit.quitSsbStatus == null}">checked="checked"</c:if>
					value="0"/>否
					</label>
					
				</p>
				<p><label>基数每年调整月份：</label><s:select name="socialBenefit.ajustTime" cssClass="" id="ajustTime"  
					list="#request.mp"  
						headerKey="" headerValue="请选择" listKey="key" listValue="value" ></s:select></p>
				
				<p><label>描述：</label><s:textarea name="socialBenefit.description" rows="5" id="socialBenefit.description" ></s:textarea></p>
				<p><label> 备注（理赔流程及政策相关）：</label><s:textarea name="socialBenefit.remark" rows="5" id="socialBenefit.remark" ></s:textarea></p>
			 
			  
		</div>
	</div>
      </div>
    </div>
  </div>
 <!--  <div class="accordion-group">
    <div class="accordion-heading">
      <a class="accordion-toggle" data-toggle="collapse"  href="#collapseTwo">
        Collapsible Group Item #2
      </a>
    </div>
    <div id="collapseTwo" class="accordion-body collapse">
      <div class="accordion-inner">
        Anim pariatur cliche...
      </div>
    </div>
  </div> -->
		
	
		<div class="btn-toolbar ">
					<div class="btn-group">
					<a class="open btn btn-mini" href="javascript:show(1)"><i class=" icon-folder-open  icon-white"></i>显示</a> 
					<a class="collapsed btn btn-mini" href="javascript:show(0)"><i class="icon-folder-close  icon-white"></i>收起</a> 
					</div>
					<div class="btn-group">
					<a class="import btn btn-mini" href="javascript:merge()"><i class="icon-download-alt  icon-white"></i>全部引入</a> 
					</div>
					<div class="btn-group">
					<a class="addItem btn btn-mini" href="javascript:addSsbItemNew()"><i class="icon-plus  icon-white"></i>添加险种</a>
					</div>
		</div>
		<div id="editlist" >
			<jsp:include page="edit-itemlist.jsp"></jsp:include>
		</div>
	</s:form>
	
<script type="text/javascript">
		$(function(){
			//启用和停用.click(function(){
			$("a.closesocialBenefitItem").die('click').live('click', function() { 
				var _this = $(this);
				var prev = _this.prev("input:hidden");
				if(prev.val() == '<%=SocialBenefitDetail.STATUS_START%>'){
					_this.html('启用');
					prev.val(<%=SocialBenefitDetail.STATUS_STOP%>);
				}else{
					_this.html('停用');
					prev.val(<%=SocialBenefitDetail.STATUS_START%>);
				}
				return false;
			});
			
			
			
		});
			
			//添加item(刷整个table)
			function addSsbItemNew() {
				var data = $("#editForm").serialize();
				$.ajax({
					url : "socialBenefitBase_addItemNew.action",
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
		
			//合并item(刷整个table)
			function merge() {
				if(confirm("确定全部引入吗？")){
				var data = $("#editForm").serialize();
				$.ajax({
					url : "socialBenefitBase_merge.action",
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
				
			}
			//导入一条item(刷整个table)
			function mergeOne(index) {
				 /* var _div = $("#edit-Div");
					if( !_div.valid()){
						return;
					} */
				if(confirm("确定引入吗？")){
					var data = $("#editForm").serialize();
					$.ajax({
						url : "socialBenefitBase_merge.action",
						async : false,
						data : data+'&mergeIndex='+index,
						type : "POST",
						dataType : "html",
						success : function(html) {
							$("#editlist").empty();
							$("#editlist").append(html);
						}
						});
				}
			}
		
		
		//删除新增的ITEM,删除一并移除验证提示
		function subSocialBenefit(target,index) {
			$(target).parent().parent().remove();
			$("#tr_"+index).remove();
		}
		
		
		//省市级连
		$("#province").change(function(){
			 var checkValue = $("#province").val();
			 $.ajax({
					url : "socialBenefitBase_queryCityByProvince.action?city.id="+checkValue,
					async : false,
					dataType : "JSON",
					type : "POST",
					cache: false,
					success : function(data) {
						 $("#socialBenefitcityId").empty();
						 $.each(eval(data), function(i, item) {
		                        $("#socialBenefitcityId").append("<option value='" + item.id + "'>" + item.name + "</option>");
		                 });
					}
			});
		}); 
		 
		function sbeStartTypeChange(value,index){
			if(value==32){
				$("#startTypeSpan1"+index).html("工作天数少于");
				$("#startTypeSpan2"+index).html("天，入职当月需缴纳社保");
			}
			if(value==33){
				$("#startTypeSpan1"+index).html("早于");
				$("#startTypeSpan2"+index).html("日入职，入职当月需缴纳社保");
			}
		}
		function sbeEndTypeChange(value,index){
			if(value==32){
				$("#endTypeSpan1"+index).html("工作天数大于");
				$("#endTypeSpan2"+index).html("天，离职当月需缴纳社保");
			}
			if(value==33){
				
				$("#endTypeSpan1"+index).html("晚于");
				$("#endTypeSpan2"+index).html("日离职，离职当月需缴纳社保");
			}
		}
		
		function showDetail(index){
			var trdd= $("#tr_"+index);
			if(trdd.css("display")== 'none'){
				$("#tr_"+index).show();
			}else{
				$("#tr_"+index).hide();
			}
		}
	
		function show(val){
			if(val== 1){
				$(".showtr").show();
			}else{
				$(".showtr").hide();
			}
		}
	
		function isCheckAllTermMonth(index){
			var i=0; //定义一个常量
			$(".socialBenefittermMonthclass"+index).each(function(){
				if($(this).is(':checked')){
					i+=1; //如果被选中，i就加1
				}
			});
			if(i==12){
				$("#isFullChecksocialBenefittermMonth"+index).attr('checked',true);
				$("#socialBenefittermMonthspan"+index).hide();
			}
		}
		
		function showTermMonth(target,index){
			if($(target).is(':checked')){
				$(".socialBenefittermMonthclass"+index).each(function(){
					$(this).attr('checked',true);
				});
				$("#socialBenefittermMonthspan"+index).hide();
			}else{
				$("#socialBenefittermMonthspan"+index).show();
			}
		}
		
		$('#collapseOne').on('hidden', function () {
			  $("#icon-resize").removeClass("icon-folder-close").addClass("icon-folder-open");
			});
		$('#collapseOne').on('show', function () {
			  $("#icon-resize").removeClass("icon-folder-open").addClass("icon-folder-close");
			});
</script>