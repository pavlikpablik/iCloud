<%@page import="com.manpowergroup.cn.icloud.base.entity.City"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>键值对信息查询</h4>
</div>
<div class="modal-body">
	<p>
		<label class="control-label">所属类别：</label> <input name="bdtype" type="text" />
	</p>
	<p>
		<label class="control-label">数据值：</label> <input name="bdname" type="text" />
	</p>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_query">查询</a> 
	<a href="javascript:$('#queryForm')[0].reset()" class="btn" >重置</a>
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
</div>


