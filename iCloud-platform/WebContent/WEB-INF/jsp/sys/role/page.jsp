<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<link rel="stylesheet" href="resource/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="resource/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<div class="pageContainer">
	<jsp:include page="header.jsp"></jsp:include>
	<div class="query modal hide fade" id="query">
		<form class="queryForm form-horizontal" name="queryForm" id="queryForm"
			action="role_query.action">
			<jsp:include page="query.jsp"></jsp:include>
		</form>
	</div>
	<!-- 定义页面查询结果及分页类 -->
	<div class="list">
		<jsp:include page="list.jsp"></jsp:include>
	</div>
	<!-- 编辑页面 -->
	<div class="edit"></div>
	
	<!-- 详细页面 -->
	<div class="detail"></div>
	
	<!-- 分配权限 -->
	<div class="modal hide fade" id="authrAssign"></div>
	
	<!-- 用于保存 -->
	<input type="hidden" class="queryCondition" />
	<!-- 保存页面分页页面checkbox条件类 -->
	<input type="hidden" class="chkboxChoosed" />
</div>
<script type="text/javascript">

	
	$(function(){
		$("a.page_authz_assign").live("click",function(){
			var _url = $(this).attr("href") || '';
			var div_authrAssign = $( "#authrAssign");
			$.ajax({
				url : _url,
				cache : false,
				dataType : "html",
				async : false,
				success : function(html) { 
					div_authrAssign.empty();
					div_authrAssign.html(html);
					$('#authrAssign').modal('show');
				}
			}); 
			return false;
		});
		$("#page_authz_save").live("click",function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		        var nodes = treeObj.getCheckedNodes(true);  
		        var ids = "";  
		        for (var i = 0; i < nodes.length; i++) {  
		        	if(i == nodes.length-1){
		        		ids += nodes[i].id;
		        	}else{
		        		ids += nodes[i].id + ",";  
		        	}
		        }  
		        $.ajax({
		        	url : 'role_saveRoleResource.action?roleId='+$("#roleId").val()+"&rsourceIds="+ids,
					cache : false,
					async : false,
					dataType : "json",
					success : function(json) {
						if(json.result)
						{	
							successNotify(json);
						}else{
							failNotify(json,$("#debug"));
							return;
						}
					}
				});  
		});






		$("a.page_user").live("click",function(){
			var _url = $(this).attr("href") || '';
			var div_authrAssign = $( "#authrAssign");
			$.ajax({
				url : _url,
				cache : false,
				dataType : "html",
				async : false,
				success : function(html) { 
					div_authrAssign.empty();
					div_authrAssign.html(html);
					$('#authrAssign').modal('show');
				}
			}); 
			return false;
		});

		$("#page_user_save").live("click",function(){
			var treeObj1 = $.fn.zTree.getZTreeObj("treeUser1");
		        var nodes1 = treeObj1.getCheckedNodes(true);  
		        var ids1 = "";  
		        for (var i = 0; i < nodes1.length; i++) {  
		        	if(i == nodes1.length-1){
		        		ids1 += nodes1[i].id;
		        	}else{
		        		ids1 += nodes1[i].id + ",";  
		        	}
		        }  

		        var treeObj2 = $.fn.zTree.getZTreeObj("treeUser2");
		        var nodes2 = treeObj2.getCheckedNodes(true);  
		        var ids2 = "";  
		        for (var j = 0; j < nodes2.length; j++) {  
		        	if(j == nodes2.length-1){
		        		ids2 += nodes2[j].id;
		        	}else{
		        		ids2 += nodes2[j].id + ",";  
		        	}
		        }  


		        var treeObj3 = $.fn.zTree.getZTreeObj("treeUser3");
		        var nodes3 = treeObj3.getCheckedNodes(true);  
		        var ids3 = "";  
		        for (var k = 0; k < nodes3.length; k++) {  
		        	if(k == nodes3.length-1){
		        		ids3 += nodes3[k].id;
		        	}else{
		        		ids3 += nodes3[k].id + ",";  
		        	}
		        }  


				var ids="";
				if(ids1.length>0){
					ids=ids+ids1;
					}
				if(ids2.length>0&& ids1.length>0){
					ids=ids+","+ids2;
					}else{
						ids=ids+ids2;
						}
				if(ids3.length>0&&ids1.length>0){
					ids=ids+","+ids3;
					}else if(ids3.length>0&&ids2.length>0){
						ids=ids+","+ids3;
						}else if(!(ids1.length>0&&ids2.length>0)){
							ids=ids+ids3;
							}

		        
		        $.ajax({
		        	url : 'role_saveUserRole.action?roleId='+$("#roleId").val()+"&userIds="+ids,
					cache : false,
					async : false,
					dataType : "json",
					success : function(json) {
						if(json.result)
						{	
							successNotify(json);
						}else{
							failNotify(json,$("#debug"));
							return;
						}
					}
				});  
		});
		
		
	}); 
	
</script>