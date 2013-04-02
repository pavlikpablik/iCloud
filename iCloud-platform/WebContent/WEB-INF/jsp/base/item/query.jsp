<%@page import="com.manpowergroup.cn.icloud.base.entity.City"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="modal-header">
	<a class="close" data-dismiss="modal">×</a>
	<h4>项目元素查询</h4>
</div>

<div class="modal-body" >
<p>
	<label class="control-label">项目元素中文名：</label>
	<input name="name" type="text"/>
</p>
<p>
	<label class="control-label">项目元素英文名：</label>
	<input name="nameEn" type="text"/>
</p>	
 <p>
	<label class="control-label">项目元素类型：</label>
	<select name="itemType" >
		<option value="">请选择</option>
		<c:forEach items="${typeList}" var="types">
		<option value="${types.id}">${types.name}</option>
		</c:forEach>
	</select>
</p>	

<p>
	<label class="control-label">项目类别：</label>
	<select name="type" >
		<option value="">请选择</option>
		<option value="1">社保</option>
		<option value="2">工资</option>
		<option value="3">服务费</option>
		<option value="4">商保</option>
		<option value="5">其他社保(社保调整)</option>
	</select>
</p>

<p>
	<label class="control-label">GROUP：</label>
	<s:textfield name="itemGroup" id="item.itemGroup" ></s:textfield>
<!--			<select id="" name="item.itemGroup" >-->
<!--							<option value="">请选择</option>-->
<!--							<c:forEach items="${ssbDesList}" var="ssbDes">-->
<!--								<option value="${ssbDes}" title="${ssbDes}">${ssbDes}-->
<!--							</option>-->
<!--							</c:forEach>-->
<!--					</select>-->
	
	
</p>


<p>
	<label class="control-label">ITEMTPYE：</label>
			<select id="" name="itemsType" >
							<option value="">请选择</option>
							<c:forEach items="${itemsTypeList}" var="ssbstype">
								<option value="${ssbstype}" title="${ssbstype}">${ssbstype}
							</option>
							</c:forEach>
					</select>
</p>

</div>

<div class="modal-footer">
	<a class="btn btn-primary" data-dismiss="modal" id="page_query">查询</a> 
	<a href="javascript:$('#queryForm')[0].reset()" class="btn" >重置</a>
	<a class="btn" data-dismiss="modal">关闭</a>
</div>

