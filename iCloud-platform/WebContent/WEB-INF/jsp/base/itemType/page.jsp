<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>



<div class="pageContainer">


	<jsp:include page="header.jsp"></jsp:include>


	<div class="query modal hide fade" id="query">
		<form class="queryForm form-horizontal" name="queryForm" id="queryForm"
			action="itemtype_query.action">
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
	
	<!-- 导入 -->
	<div class="modal fade import" id="excel_import">
		
	</div>
	

	<!-- 用于保存 -->
	<input type="hidden" class="queryCondition" />
	<!-- 保存页面分页页面checkbox条件类 -->
	<input type="hidden" class="chkboxChoosed" />
	

	<script type="text/javascript">


			$(function(){
				/* 
				//选择普通年月
				$('#dpYears').datepicker();
				
				
				//只选择月份
				$('#dpMonths').datepicker({
					startView:2,//1：先出月份再出年，2：先出年再出月份
					format: 'yyyy/mm',
					month_mode:true//月份模式，只选择到月份就ok
					}
				); */
				
				
				
			});
		
	</script>

</div>
