<%@page import="com.manpowergroup.cn.icloud.base.entity.City"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>省份城市查询</h4>
</div>
<div class="modal-body">
	<p>
		<label class="control-label">省份中文名：</label> <input name="pname" type="text" />
	</p>
	<p>
		<label class="control-label">省份英文名：</label> <input name="pname_en" type="text" />
	</p>
	<p>
		<label class="control-label">城市中文名：</label> <input name="cname" type="text" />
	</p>
	<p>
		<label class="control-label">城市英文名：</label> <input name="cname_en" type="text" />
	</p>
	<p>
		<label class="control-label">状态：</label>
		<s:select list="#{1:'启用',0:'停用'}" theme="simple"
			cssStyle="width:220px" name="status"
			 headerKey="" headerValue="请选择"
			listKey="key" listValue="value"></s:select>
	</p>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_query">查询</a> 
	<a href="javascript:$('#queryForm')[0].reset()" class="btn" >重置</a>
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
</div>


