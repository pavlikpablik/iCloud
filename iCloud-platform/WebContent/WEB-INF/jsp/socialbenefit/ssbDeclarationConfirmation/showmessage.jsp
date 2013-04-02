<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table class="table table-condensed table-striped table-hover">
	<thead>
		<tr>
			<th>验证条目</th>
			<th>状态</th>
			<th>错误信息</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${headerText.excelErrorTitle }</td>
			<td>${headerText.excelError.isOK }</td>
			<td><c:if test="${ headerText.excelError.results != null }">
					<c:forEach var="res" items="${headerText.excelError.results }">
          				 ${res }
      				</c:forEach>
				</c:if></td>
		</tr>
		<c:set var="stindex" value="0"></c:set>
		<c:if test="${headerText.excelCells != null }">
			<c:forEach var="excelCell" items="${headerText.excelCells}">
				<c:if test="${excelCell.existColumn}">
					<tr>
						<td>${excelCell.cellHeaderText}</td>
						<td>${excelCell.excelImg.isOK}</td>
						<td><c:forEach var="res" items="${excelCell.excelImg.results}">
						       ${res}<br />
							</c:forEach>
						</td>
					</tr>
					<c:set var="stindex" value="${stindex + 1}"></c:set>
				</c:if>
			</c:forEach>
		</c:if>
	</tbody>
</table>
