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
<table class="table_nowrap table table-condensed table-striped">
	<thead>
		<tr>
			<th width="8px"><input class="chkboxall" name="checkall" type="checkbox"/></th> 
			<th>员工编号</th>
			<th>中文姓名</th>
			<th>身份证件号码</th>
			<th>社保类型</th>
			<th>社保费用月</th>
			<th>社保调整状态</th>
			<th>个人调整部分</th>
			<th>公司调整部分</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.result}" var="entry" varStatus="st">
<%-- 		    <c:choose>
				<c:when test="${st.index % 2 == 0}">
					<tr class="trdouble" listTableRowIndex="${st.index }">
				</c:when>
				<c:otherwise>
					<tr class="trsingle" listTableRowIndex="${st.index }">
				</c:otherwise>
			</c:choose> --%>
			<tr>
				<td width="8px"><input class="chkboxitem" name="ids" type="checkbox"  value="${entry.id}"/></td>
				<td align="center">${entry.candidateId}</td>
				<td align="center">${entry.candidateName}</td>
				<td align="center">${entry.candidateNo}</td>
				<td align="center">${entry.ssbName}</td>
				<td align="center">${entry.monthFee}</td>
				<td align="center">
					<c:if test="${entry.ajustmentStatus == 0}">
						未提交
					</c:if>
					<c:if test="${entry.ajustmentStatus == 1}">
						已提交待审核
					</c:if>
					<c:if test="${entry.ajustmentStatus == 2}">
						审批通过
					</c:if>
					<c:if test="${entry.ajustmentStatus == 3}">
						审批未通过
					</c:if>
					<c:if test="${entry.ajustmentStatus == 4}">
						已核对
					</c:if>
				</td>
				<td align="center"><fmt:formatNumber value="${entry.personAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></td>
				<td align="center"><fmt:formatNumber value="${entry.companyAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>
				</td>
				<td align="center">
					<a class="btn btn-mini  page_detail" href="candSsbAjustMentCheck_detail.action?candAjustMent.id=${entry.id}" >详细</a>
				</td>
			  </tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页类  -->
<!-- 分页 -->
<c:set var="url" value="candSsbAjustMentCheck_prepaging.action" scope="request"/>
<jsp:include page="/WEB-INF/jsp/layout/paging.jsp" flush="true"/>

