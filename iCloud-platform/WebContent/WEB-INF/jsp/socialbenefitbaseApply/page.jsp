<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<link href="resource/javascript/sys/uploadfile/upload.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/swfupload.queue.js"></script>
<script type="text/javascript" src="resource/javascript/sys/uploadfile/fileprogress.js"></script>
	
<div class="pageContainer">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 用于保存 -->
		<input type="hidden" class="queryCondition"/>
		<!-- 保存页面分页页面checkbox条件类 -->
		<input type="hidden" class="chkboxChoosed"/>
		<!-- 定义页面内部查询条件类 -->
		
		<div class="query modal fade hide " id="query">
			<form class="queryForm form-horizontal" name="queryForm" id="queryForm" action="socialBenefitBaseApply_query.action">
			<!--开始 页面控制 -->
			<input type="hidden" name="method" value="${method }">
			<!--结束 页面控制 -->
			<jsp:include page="query.jsp"></jsp:include>
			</form>
		</div>
	
		<!-- 定义页面查询结果及分页类 -->
		<div class="list">
			<jsp:include page="list.jsp"></jsp:include>
		</div>
		<!-- 编辑页面 -->
		<div class="edit"></div>
		<!-- detail页面 -->
		<div class="detail"></div>
		
		<!-- 导入 -->
		<div class="import modal fade hide " id="excel_import"></div> 
		<div class="openuploadfile modal fade  hide " id="openuploadfile" ></div>
		<div class="detailUploadFiles modal fade  hide " id="detailUploadFiles" ></div>
	</div>
	
	<c:if test="${fromMenuLink}">
	<script type="text/javascript">
	$(function(){
		<c:choose>
		 <c:when test="${method == 'create' }">
		    $("#page-create").trigger("click");
		 </c:when>
		 <c:otherwise>
		   $("#page-query").trigger("click");
		   //$('#query').modal('show');
		 </c:otherwise> 
		</c:choose>
		
		
		$("#page-batchAudit").click(function(){
			if($(".chkboxChoosed").val()==null || $(".chkboxChoosed").val()==""){
				alert("请选择社保类型。");		
				return false;
			}
			
			var _this =  $(this);
		
			var url = _this.attr("url");
			url = url+"?ssbIds="+$(".chkboxChoosed").val();
		
			$.ajax({
				url : url,
				type : 'POST',
				datatype : 'json',
				async : false,
				success : function(json) {
					if(json.success == "success")
					{
						$.pnotify({
					    	title: '成功',
					    	text: '操作成功',
					    	type: 'success',
					    	styling: 'bootstrap'
						});
					}
					
					$("#ssbids").trigger("click");
					$(".chkboxChoosed").val("");
					if(page && page.data && page.data.chkboxChoosed)
					{
						page.data.chkboxChoosed = [];
					}
					$("a.prepaginggo").trigger("click");
				}
			});
			
		});
		
	});	


	$(document).keydown(function(event) {
		  if (event.keyCode == 13) {
		    $('form').each(function() {
		      event.preventDefault();
		    });
		  }
		}); 
		
	</script>
	</c:if>
