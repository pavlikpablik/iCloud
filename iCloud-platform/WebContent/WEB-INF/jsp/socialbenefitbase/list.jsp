<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 
	table 的 标准定义：
	1.必须是<table><thead></thead><tbody></tbody></table>结构
	2.thead的内部标签必须是tr th, 不允许是 tr td
	3.整个table标签内不要定义仍和与样式相关的属性，样式全部通过css统一设置
 -->
 <!--  <div class="accordion-group">
    <div class="accordion-heading">
      <a class="accordion-toggle" data-toggle="collapse"  href="#collapseOne">
        Collapsible Group Item #1
      </a>
    </div>
    <div id="collapseOne" class="accordion-body collapse in">
      <div class="accordion-inner">
        Anim pariatur cliche...
      </div>
    </div>
  </div>
  <div class="accordion-group">
    <div class="accordion-heading">
      <a class="accordion-toggle" data-toggle="collapse"  href="#collapseTwo">
        Collapsible Group Item #2
      </a>
    </div>
    <div id="collapseTwo" class="accordion-body collapse in" >
      <div class="accordion-inner">
        Anim pariatur cliche...
      </div>
    </div>
  </div> -->
<table class="table table-condensed table-striped table-hover">
	<thead>
		<tr>
			<th width="8px"><input class="chkboxall" name="checkall" type="checkbox"/></th>
			<th>编号</th>
			<th>类型名</th>
			<th>省份-城市</th>
			<!-- <th>描述</th> -->
			<th >状态</th>
			<th>个人/月</th>
			<th>公司/月</th>
			<!-- <th>个人/其他</th>
			<th>公司/其他</th> -->
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
		   	<tr>
				<td width="8px"><input class="chkboxitem" name="ids" id="ssbids" type="checkbox"  value="${entry.id}"/></td>
				<td>${entry.id}</td>
				<td>${entry.name}</td>
				<td>${entry.cityName}</td>
				<%-- <td>${entry.description}</td> --%>
				<td><c:if test="${entry.status==0}">停用</c:if>
								   <c:if test="${entry.status==1}">启用</c:if>
								   <c:if test="${entry.status==2}">待审批</c:if></td>
				
				<td>
					
				<fmt:formatNumber pattern="0.00" value="${entry.personMonthPercent}"/>%
					+ <fmt:formatNumber  pattern="￥0.00"  value="${entry.personMonthAmount}"/></td>
				<td align="center"><fmt:formatNumber   pattern="0.00" value="${entry.companyMonthPercent}"/>%
				    + <fmt:formatNumber  pattern="￥0.00" value="${entry.companyMonthAmount}"/> </td>
				<%-- <td align="center"><fmt:formatNumber    pattern="0.00" value="${entry.personOtherPercent}"/>%
				    + <fmt:formatNumber  pattern="￥0.00" value="${entry.personOtherAmount}"/></td>
				<td align="center"><fmt:formatNumber    pattern="0.00" value="${entry.companyOtherPercent}"/>%
				    + <fmt:formatNumber  pattern="￥0.00" value="${entry.companyOtherAmount}"/> </td> --%>
				<td align="center">
				       <c:if test="${method == 'manage' }">
						<a  class="btn btn-mini page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						<AT:AuthorityTag actionName="socialBenefitBase_edit.action">
						<a  class="btn btn-mini page_edit" href="socialBenefitBase_edit.action?socialBenefit.id=${entry.id}">修改</a>
						</AT:AuthorityTag>
						<AT:AuthorityTag actionName="socialBenefitBase_changeStatus.action">
							<c:if test="${entry.status == 2}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=0">停用</a>
								<c:if test="${entry.createDate == entry.modifyDate}">
									<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=1">新增审批</a>
								</c:if>
								<c:if test="${entry.createDate != entry.modifyDate}">
									<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=1">修改审批</a>
								</c:if>								
							</c:if>
							<c:if test="${entry.status == 0}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=2">启用</a>
							</c:if>
							<c:if test="${entry.status == 1}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=0">停用</a>
							</c:if>
						</AT:AuthorityTag>	
						</c:if>
						
						
						<c:if test="${method == 'view' }">
							<a  class="btn btn-mini page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						</c:if>
						
						<c:if test="${method == 'create' }">
						<a  class="btn btn-mini page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						<AT:AuthorityTag actionName="socialBenefitBase_edit.action">
						<a   class="btn btn-mini page_edit" href="socialBenefitBase_edit.action?socialBenefit.id=${entry.id}">修改</a>
						</AT:AuthorityTag>
						</c:if>
						
						<c:if test="${method == 'modify' }">
						<a  class="btn btn-mini page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						<AT:AuthorityTag actionName="socialBenefitBase_edit.action">
						<a   class="btn btn-mini page_edit" href="socialBenefitBase_edit.action?socialBenefit.id=${entry.id}">修改</a>
						</AT:AuthorityTag>
						</c:if>
						
						
						<c:if test="${method == 'status' }">
						<a  class="btn btn-mini page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						<AT:AuthorityTag actionName="socialBenefitBase_changeStatus.action">
							<c:if test="${entry.status == 2}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=0">停用</a>
							</c:if>
							<c:if test="${entry.status == 0}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=2">启用</a>
							</c:if>
							<c:if test="${entry.status == 1}">
								<a class="btn btn-mini page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=0">停用</a>
							</c:if>
						</AT:AuthorityTag>	
						</c:if>
						
						
						<c:if test="${method == 'audit' }">
						<a  class="btn btn-mini btn-info page_detail" href="socialBenefitBase_detail.action?socialBenefit.id=${entry.id}">详细</a>
						<AT:AuthorityTag actionName="socialBenefitBase_changeStatus.action">
							<c:if test="${entry.status == 2}">
								<c:if test="${entry.createDate == entry.modifyDate}">
									<a class="btn btn-mini btn-inverse page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=1">新增审批</a>
								</c:if>
								<c:if test="${entry.createDate != entry.modifyDate}">
									<a class="btn btn-mini btn-inverse page_status" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=1">修改审批</a>
								</c:if>	
								<%-- <a class="status pagetableoperate ui-button ui-widget ui-state-default" href="socialBenefitBase_changeStatus.action?socialBenefit.id=${entry.id}&socialBenefit.status=1"><span class="ui-icon ui-icon-unlocked"></span>审批通过</a>&nbsp;--%>
							</c:if>
						</AT:AuthorityTag>	
						</c:if>
				</td>
			  </tr>
		</c:forEach>
	</tbody>
</table>
<c:set var="url" value="socialBenefitBase_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>


