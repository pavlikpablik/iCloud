<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>员工社保调整信息查询</h4>
</div>
<div class="modal-body" >
	<p>
		<label class="control-label">员工编号:</label><s:textfield  theme="simple"  name="candidateId" label="员工编号" ></s:textfield>
	</p>
	<p>
		<label class="control-label">员工姓名:</label><s:textfield   theme="simple" name="candidateName"  label="员工姓名" ></s:textfield>
	</p>
	<p>
		<label class="control-label">身份证件号码:</label><s:textfield theme="simple" name="candidateNo"  label="身份证件号码"  ></s:textfield>
	</p>
	<p>
		<label class="control-label">社保费用月(YYYY/MM):</label><s:textfield    name="monthFee"  label="社保费用月(YYYY/MM)"  theme="simple"></s:textfield>
	</p>
	<p>
		<label class="control-label">调整状态：</label>
		<s:select list="#{0:'未提交',1:'已提交待审核',2:'审批通过',3:'审批未通过',4:'已核对'}" theme="simple"
			cssStyle="width:220px" name="ajuststatus"
			 headerKey="" headerValue="请选择"
			listKey="key" listValue="value"></s:select>
	</p>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-primary" data-dismiss="modal" id="page_query">查询</a> 
	<a href="javascript:$('#queryForm')[0].reset()" class="btn" >重置</a>
	<a href="#" class="btn" data-dismiss="modal">关闭</a>
</div>

