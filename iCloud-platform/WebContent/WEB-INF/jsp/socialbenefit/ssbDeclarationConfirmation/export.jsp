<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<div class="control-group">
		<label class="control-label" for="ssbProvice">社保缴纳月*</label>
		<div class="controls">
		  <label class="radio inline" style="color:black;width: 30px;">
					<input checked="checked" 
				type="radio" class="importModelCheck" name="importModelRadio"
				value="preMonth">上月
			</label>
				  <label class="radio inline" style="color:black;width: 30px;">
					<input type="radio"
				class="importModelCheck" name="importModelRadio" checked="checked"  value="currMonth">当月
						</label>	
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="submit"></label>
		<div class="controls">
			<a class="btn page_import" url="ssbdeclarationconfirmation_importPre.action" href="#"><i class="icon-download"></i>导入</a>
		</div>
	</div>
</div>