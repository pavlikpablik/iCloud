<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table class="table table-condensed table-striped table-bordered " > 
<thead>
		<tr style="background-color: #FFE696">
			<th  align="center"><nobr><b>&nbsp;&nbsp;验证条目&nbsp;&nbsp;</b></nobr></th>
		    <th  align="center"><nobr><b>&nbsp;&nbsp;状态&nbsp;&nbsp;</b></nobr></th>
		    <th  align="center"><nobr><b>&nbsp;&nbsp;错误信息&nbsp;&nbsp;</b></nobr></th>
		</tr>
</thead>
<tbody>
  <tr >
       <td align="center"><nobr>${ce.headerText.excelErrorTitle }</nobr></td>
    <td align="center"><nobr>
     ${ce.headerText.excelError.isOK }
    </nobr></td>
    <td align="left"><nobr>
    <c:if test="${ce != null && ce.headerText.excelError.results != null }">
        <c:forEach var="res" items="${ce.headerText.excelError.results }">
           ${res }
        </c:forEach> 
    </c:if>
    </nobr></td>
   </tr>
   <c:set var="stindex" value="0"></c:set>
   <c:if test="${ce.headerText.excelCells != null }">
		  <c:forEach var="excelCell" items="${ce.headerText.excelCells}" >
		  	<c:if test="${excelCell.existColumn}">
		  
				 <tr >
					    <td align="center"><nobr>${excelCell.cellHeaderText}</nobr></td>
						<td align="center"><nobr>
						 ${excelCell.excelImg.isOK}
						</nobr></td>
						<td align="left"><nobr>
						    <c:forEach var="res" items="${excelCell.excelImg.results }">
						       ${res}<br/>
						    </c:forEach>
						</nobr></td>
						
				 </tr>
			
			  <c:set var="stindex" value="${stindex + 1 }"></c:set>
			 </c:if>
		 </c:forEach>
		 </c:if>
</tbody>
 </table>
