<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<tr  listTableRowIndex="${index }">
	<td >
		<input type="hidden" name="city.citys[${index }].id" value="">
		<input class="{validate:{required:true,messages:{required:'城市中文名不能为空'}}} " type="text" id="city_citys_${index }_name" name="city.citys[${index }].name" value="">
	</td>
	<td >
		<input class="{validate:{required:true,messages:{required:'城市中文名不能为空'}}} " type="text" id="city_citys_${index }_nameEn" name="city.citys[${index }].nameEn" value="">
	</td>
	<td >
		<input  type="text" id="city_citys_${index }_remark" name="city.citys[${index }].remark" value="">
	</td>
	<td>
		<input type="hidden" id="city_citys_${index }_status" name="city.citys[${index }].status" value="1">
		<a href="#" class="btn btn-mini"  onclick="subcity(this)" removeIndex="${index}">删除</a>
	</td>
</tr>