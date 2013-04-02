<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<script type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true,
				/* chkStyle: "radio" */
				 chkboxType: { "Y" : "ps", "N" : "s" } 
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		/* setting.check.radioType = "all"; */
		var zNodes =[
			 /* { id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:0, pId:-1, name:"最高级", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"禁止勾选 2", open:true,checked:true, doCheck:false},
			{ id:21, pId:2, name:"禁止勾选 2-1", doCheck:false},
			{ id:22, pId:2, name:"禁止勾选 2-2", checked:true, open:true, doCheck:false},
			{ id:221, pId:22, name:"禁止勾选 2-2-1", doCheck:false},
			{ id:222, pId:22, name:"禁止勾选 2-2-2", checked:true, doCheck:false},
			{ id:23, pId:2, name:"禁止勾选 2-3", doCheck:false},
			{ id:666, pId:1, name:"11111"},  */
		];

		
		$(function(){
			
			 	$.ajax({
					url : 'resource_queryRoleResourceTree.action?roleId='+"${role.id}",
					cache : false,
					async : false,
					dataType : "json",
					success : function(josn) {
						zNodes = josn;
					}
				});  
				
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				$.fn.zTree.getZTreeObj("treeDemo").expandAll(true);
				
				
				
		});
	</script>


<div class="modal-header">
	<input type="hidden" name="roleId" id="roleId" value="${role.id}">
	<a class="close" data-dismiss="modal">×</a>
	<h4>权限设置</h4>
</div>
<div class="modal-body">
		<ul id="treeDemo" class="ztree"></ul>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_authz_save">保存</a> 
	<a href="#" class="btn btn-inverse" data-dismiss="modal">取消</a>
</div>
