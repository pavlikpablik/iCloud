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
					url : 'role_queryUserAllTree.action',
					cache : false,
					async : false,
					dataType : "json",
					success : function(josn) {
						zNodes = josn;
					}
				});  
				var batch=3;
				var flag=zNodes.length/batch;
				var tree1=flag;
				var tree2=flag;
				var tree3=flag;
				var flag1=zNodes.length%batch;

				if(flag1==1){
					tree1=flag+1;
					}else if(flag1==2){
					tree3=flag+2;	
						}else{
					tree3=flag;		
							}

				var treeNodes1 = zNodes.slice(0,tree1); 
				tree2=tree1+tree2;
				var treeNodes2 = zNodes.slice(tree1,tree2);
				tree3=tree2+tree3; 
				var treeNodes3 = zNodes.slice(tree2,tree3); 
				//alert(step);
				//alert(treeNodes.length);
				
				$.fn.zTree.init($("#treeUser1"), setting, treeNodes1);
				$.fn.zTree.getZTreeObj("treeUser1").expandAll(true);
				$.fn.zTree.init($("#treeUser2"), setting, treeNodes2);
				$.fn.zTree.getZTreeObj("treeUser2").expandAll(true);
				$.fn.zTree.init($("#treeUser3"), setting, treeNodes3);
				$.fn.zTree.getZTreeObj("treeUser3").expandAll(true);
		});
	</script>

<div class="modal-header">
	<input type="hidden" name="roleId" id="roleId" value="${role.id}">
	<a class="close" data-dismiss="modal">×</a>
	<h4>用户组设置</h4>
</div>
<div class="modal-body">
    <div class="container-fluid">
  <div class="row-fluid" id="userTree">
    <div class='span3'>
    <ul id='treeUser1' class='ztree'></ul>
    </div>
    <div class='span3'>
    <ul id='treeUser2' class='ztree'></ul>
    </div>
    <div class='span3'>
    <ul id='treeUser3' class='ztree'></ul>
    </div>
  </div>
</div>
</div>



<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_user_save">保存</a> 
	<a href="#" class="btn btn-inverse" data-dismiss="modal">取消</a>
</div>
