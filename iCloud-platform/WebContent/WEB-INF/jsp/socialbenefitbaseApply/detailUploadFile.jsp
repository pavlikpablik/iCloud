<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>查看附件</h4>
</div>
<div class="modal-body">
	 <table class="table">
            <thead>
		    <tr>
			<th>文件名称</th>
			<th >操作</th>
		    </tr>
	      </thead>
	      <tbody>
	        <c:forEach var="file" items="${fileList}">
	        <tr>
	        <td >${file.fileName }</td>
	        <td >
	        	<a class=' btn btn-mini' href="socialBenefitBaseApply_downloadUploadFile.action?uploadfileId=${file.fileId}">下载</a>
	        </td>
	        </tr>
	        </c:forEach>
	      </tbody>
     </table>
</div>
<div class="modal-footer">
	<a  class="btn" data-dismiss="modal">关闭</a>
</div>
