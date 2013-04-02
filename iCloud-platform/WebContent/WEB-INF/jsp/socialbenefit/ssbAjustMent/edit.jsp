<%@page import="com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	
	<form class="form-horizontal" action="ssbajustment_save.action" theme="simple" id="editForm" name="editForm" >
		<s:hidden name="candAjustMent.id"></s:hidden>
		<s:hidden name="candAjustMent.ajustmentStatus" id="candAjustMentStatus" value="3"></s:hidden>
		<s:hidden name="candAjustMent.ruleId" id="candRuleId" value=""></s:hidden>
		
	   <input type="hidden" name="candAjustMent.candidateId" id="candidateId1" value="${candAjustMent.candidateId}" />
	    
	 
	 <div class="control-group  input-append" >
     <label class="control-label" >员工编号或身份证号：</label>
     <div class="controls">
     <input type="text"    class=" {validate:{required:true,messages:{required:'员工编号不能为空'}}} " id="ajustmentCandId" name="noOrId" 
       value="${candAjustMent.candidateId}"><a class="chooseCand btn  "  href="ssbajustment_editCand.action" ><i class="icon-ok"></i>确认</a>
     </div>
     </div>
	 <div class="control-group"  >
     <label class="control-label" >员工中文姓名：</label>
     <div class="controls">
    <input type="text"   name="candAjustMent.candidateName" readonly="readonly" id="candidateName" value="${candAjustMent.candidateName}">
     </div>
     </div>
	  <div class="control-group"  >
     <label class="control-label" >身份证件号码：</label>
     <div class="controls">
    <input type="text"  name="candAjustMent.candidateNo" readonly="readonly" id="candidateNo" value="${candAjustMent.candidateNo}">
     </div>
     </div>
	 <div class="control-group input-append"  >
    	 <label class="control-label" >社保类型：</label>
	     <div class="controls ">
	   		 <s:if test="candAjustMent.candSsbList!=null && candAjustMent.candSsbList.size()>0">
						<s:select id="ssbId"
						
						list="candAjustMent.candSsbList" 
						name="candAjustMent.ssbId"
						listKey="id" listValue="name"
						headerKey="" theme="simple"
						/>
						
					</s:if>
					
				
					<s:elseif test="candAjustMent.candSsbList==null || candAjustMent.candSsbList.size()==0">
						<input type="text"   readonly="readonly"  >
					</s:elseif>
					
	     </div>
     </div>
     
     
    <!--   <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
			<s:select list="#{1:'启用',0:'停用'}" name="baseItem.status" listKey="key" listValue="value" /> 
			</div>
		</div>-->
     
      <div class="control-group"  >
     <label class="control-label" >社保费用月：</label>
     <div class="controls">
    <input type="text"  name="candAjustMent.monthFee" readonly="readonly" id="monthFee" value="${candAjustMent.monthFee}">
     </div>
     </div>
	 
    <div >
			<p>
				<a class="btn btn-primary btn-small" href="javascript:addSsbItemNew()"><i class="icon-plus  icon-white"></i>添加项目</a>
			</p>
			<div id="editlist">
				<jsp:include page="edit-itemlist.jsp"></jsp:include>
			</div>
		</div>
		
		
		
	</form>
	
<script type="text/javascript">
	$(function(){
/* 		$(".datepick").datepicker();
		$( "a.addItem" ).button({
			icons: {
				primary: "ui-icon-plusthick"
			}
		});
		
		$( ".uploadfiles" ).button({
			icons: {
				primary: "ui-icon-arrowthickstop-1-n"
			}
		}); */
		
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
			url : "ssbajustment_addItemNew.action",
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
		var div_item = $( "div.editlist");	
		var ssbId = $("#ssbId").val();
		var candId = $("#ajustmentCandId").val();
		var data = $("#editForm").serialize();
		$.ajax({
			url : "ssbajustment_changeSsb.action",
			async : false,
			data : data,
			type : "POST",
			dataType : "html",
			success : function(html) {
				if(!page.isJson(html)) {
					try{
						//var json = jQuery.parseJSON(html);
						//failNotify(json,$("#debug"));
						//alert(html);
						$("#editlist").empty();
						$("#editlist").html(html);
					}catch(err){
						
						div_item.html(htmlAddDivHight(html));
						//初始化验证
						initvalidate();
					}
				}else{
					div_item.html(htmlAddDivHight(html));
					//初始化验证
					initvalidate();
				}


				
				var div_item = $( "div.editlist");	
				var ssbId = $("#ssbId").val();
				var candId = $("#ajustmentCandId").val();
				var datas = $("#editForm").serialize();
				//var _data = _editForm.serialize()+"candAjustMent.ssbId="+ssbId+"&candAjustMent.candidateId="+candId;
				  $.ajax({
					  url : "ssbajustment_changeRuleId.action",
                      async: false ,
          			  data : datas,
        			  type : "POST",
        			  dataType : "json",
                      success: function(data){
                                    //对返回值的处理
                          $("#candRuleId").val(data);
                                   }
                       });




				
			}
		});
		return false;
	});


	$("a.chooseCand").click(function(){
		var _this =  $(this);
		var _url = _this.attr("href");
		var div_edit = $( "div.edit");	
		var data = $("#ajustmentCandId").val();
		if(data == "" ||data == null){

			$.pnotify({
		    	title: '验证信息',
		    	text: "请输入员工编号或身份证编号！",
		    	type: 'error',
		    	styling: 'bootstrap'
			});

			return false;
		}
		$.ajax({
			url : _url,
			data: "&noOrId="+data,
			cache : data,
			dataType : "html",
			success : function(html) {
				if(!page.isJson(html)) {
					try{
						//var json = jQuery.parseJSON(html);
						//failNotify(json,$("#debug"));
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

				var datas = $("#ajustmentCandId").val();
				  $.ajax({
					  url : "ssbajustment_initCandidateId.action",
                      async: false ,
                      data: "&noOrId="+data,
        			  type : "POST",
        			  dataType : "json",
                      success: function(data){
                                    //对返回值的处理
                                    // alert(data);
                          //$("#candidateId").val(data);
                          $("#candidateId1").attr("value",data);
                                   }
                       });


				
			}
		}); 
		return false;
	});
	</script>