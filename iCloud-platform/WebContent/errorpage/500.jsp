<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>500 - 系统内部错误.<a href="<c:url value="/"/>">返回首页</a></div>
<div style="display: none;">
<table>
<tr><td>错误码：</td><td><%=request.getAttribute("javax.servlet.error.status_code")%></td></tr>
<tr><td>信息：</td><td><%=request.getAttribute("javax.servlet.error.message")%></td></tr>
<tr><td>异常：</td><td> <%=request.getAttribute("javax.servlet.error.exception")%></td></tr>
</table> 
</div>
