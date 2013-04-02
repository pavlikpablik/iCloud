<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %> 

<table  class="table_nowrap table table-condensed">
			<thead>
				<tr>
					<th>调整项目</th>
					<th>个人调整金额</th>
					<th>公司调整金额</th>
					<th>调整社保所属月</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="ajustDetailImport">
				<s:iterator value="candAjustMent.candAjustmentssbDetails" id='details' status='st'>
					<tr>
					<td>
						${details.itemName}
					</td>
					<td>
						<fmt:formatNumber value="${details.personAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${details.companyAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>
					</td>
					<td>
						${details.monthAttribute}
					</td>
					
					<td align="center">
							
					</td> 
					</tr>
				</s:iterator>
			</tbody>
		</table>
