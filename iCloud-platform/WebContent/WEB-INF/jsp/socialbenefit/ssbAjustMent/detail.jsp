<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	
	<form class="form-horizontal" action="ssbajustment_save.action" theme="simple" id="editForm" name="editForm" >
		<s:hidden name="candAjustMent.id"></s:hidden>
		<s:hidden name="candAjustMent.ajustmentStatus" id="candAjustMentStatus" value="0"></s:hidden>
		<s:hidden name="candAjustMent.ruleId" id="candRuleId" value=""></s:hidden>
		
	   <input type="hidden" name="candAjustMent.candidateId" id="candidateId1" value="${candAjustMent.candidateId}" />
	    
	 
	 <div class="control-group  input-append" >
     <label class="control-label" >员工编号或身份证号：</label>
     <div class="controls">
     <input type="text"   readonly="readonly" class=" {validate:{required:true,messages:{required:'员工编号不能为空'}}} " id="ajustmentCandId" name="noOrId" 
       value="${candAjustMent.candidateId}">
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
	  <div class="control-group"  >
     <label class="control-label" >社保类型：</label>
     <div class="controls">
    <input type="text" disabled="disabled" name="candAjustMent.ssbName" id="ssbName" value="${candAjustMent.ssbName}">
				
				
     </div>
     </div>
      <div class="control-group"  >
     <label class="control-label" >社保费用月：</label>
     <div class="controls">
    <input type="text"  name="candAjustMent.monthFee" readonly="readonly" id="monthFee" value="${candAjustMent.monthFee}">
     </div>
     </div>
		
		<div >
		<div ><caption><h4>员工社保金额信息调整</h4></caption></div>
			
			<div id="editlist">
				<jsp:include page="detail-itemlist.jsp"></jsp:include>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	var checkIds = $("#candAjustMentId").val();
	$(function(){
	//查看附件信息
/* 	$( "a.detailUploadFiles" ).click(function(){
		var _this =  $(this);
		var _url = _this.attr("href");
		var _dialogTitle = "查看附件信息";
		var _dialogWidth = 600;
		var _dialogHeight = 450;
		var div_uploadfile = $( "div.openuploadfile");
		$.ajax({
			url : _url,
			async : false,
			cache : false,
			dataType : "html",
			success : function(html) { 
				div_uploadfile.html(html);
				div_uploadfile.dialog({
					width:_dialogWidth,
					height: _dialogHeight,
					title:_dialogTitle,
					modal: true,
					close:function(event, ui) {
						//$( this ).dialog( "close" );
					},
					buttons: {
						'关闭': function() {
							$( this ).dialog( "close" );
						}
					}
				}); 
			}
		}); 
		return false;
	}); */
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
	
	</script>
