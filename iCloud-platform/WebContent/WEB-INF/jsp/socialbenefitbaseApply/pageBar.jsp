<%-- <%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="AT" uri="/WEB-INF/tld/AT.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!-- id命名不要更改 -->
<c:if test="${method == 'manage' }">
	<button class="pageBarBtn editPageBarBtn" id="page-save"
		style="display: none;">保存并提交</button>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_importfile.action">
		<button class="pageBarBtn listPageBarBtn pageImport"
			dialogTitle="导入社保类型" dialogWidth="650" dialogHeight="600"
			url="socialBenefitBaseApply_importpre.action?method=manage">导入</button>
	</AT:AuthorityTag>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_export.action">
		<button class="pageBarBtn listPageBarBtn pageExport"
			url="socialBenefitBaseApply_export.action">导出</button>
	</AT:AuthorityTag>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_edit.action">
		<button class="pageBarBtn listPageBarBtn detailPageBarBtn"
			id="page-create" url="socialBenefitBaseApply_edit.action" dialogWidth="1024"
			dialogHeight="600" dialogTitle="新建社保类型">新建</button>
	</AT:AuthorityTag>
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if>

<c:if test="${method == 'view' }">
	<AT:AuthorityTag actionName="socialBenefitBaseApply_export.action">
		<button class="pageBarBtn listPageBarBtn pageExport"
			url="socialBenefitBaseApply_export.action">导出</button>
	</AT:AuthorityTag>
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if>

<c:if test="${method == 'create' }">
	<button class="pageBarBtn editPageBarBtn" id="page-save"
		style="display: none;">保存并提交</button>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_importfile.action">
		<button class="pageBarBtn listPageBarBtn pageImport"
			dialogTitle="导入社保类型" dialogWidth="650" dialogHeight="600"
			url="socialBenefitBaseApply_importpre.action?method=create">导入</button>
	</AT:AuthorityTag>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_edit.action">
		<button class="pageBarBtn listPageBarBtn detailPageBarBtn"
			id="page-create" url="socialBenefitBaseApply_edit.action" dialogWidth="1024"
			dialogHeight="600" dialogTitle="新建社保类型">新建</button>
	</AT:AuthorityTag>
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if>

<c:if test="${method == 'modify' }">
	<button class="pageBarBtn editPageBarBtn" id="page-save"
		style="display: none;">保存并提交</button>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_importfile.action">
		<button class="pageBarBtn listPageBarBtn pageImport"
			dialogTitle="导入社保类型" dialogWidth="650" dialogHeight="600"
			url="socialBenefitBaseApply_importpre.action?method=modify">导入</button>
	</AT:AuthorityTag>
	<AT:AuthorityTag actionName="socialBenefitBaseApply_export.action">
		<button class="pageBarBtn listPageBarBtn pageExport"
			url="socialBenefitBaseApply_export.action">导出</button>
	</AT:AuthorityTag>
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if>

<c:if test="${method == 'status' }">
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if>

<c:if test="${method == 'audit' }">
	<button class="pageBarBtn listPageBarBtn" id="page-batchAudit"
		url="socialBenefitBaseApply_batchAudit.action" dialogWidth="600"
		dialogHeight="250" dialogTitle="批量审批">批量审批</button>
	<button id="page-query" showStyle="dialog" dialogWidth="600"
		dialogHeight="400" dialogTitle="查询社保类型">查询</button>
	<button id="page-list">列表</button>
</c:if> --%>