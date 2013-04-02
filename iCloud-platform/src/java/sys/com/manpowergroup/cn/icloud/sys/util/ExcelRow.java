package com.manpowergroup.cn.icloud.sys.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 在Excel Row信息
* <p>Date : 2011-11-7</p>
* <p>Module : Excel 导入导出</p>
* @author :lei.wang
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
@SuppressWarnings("rawtypes")
public class ExcelRow implements Comparator<ExcelCell> , java.io.Serializable {

	private static final long serialVersionUID = 2047149760326559266L;

	protected Integer rowIndex; // row
	
	protected List<ExcelCell> excelCells; //所有单元格的集合
	
	protected ExcelImg excelError = new ExcelImg(ExcelImg.OK); //Excel级别的错误
	
	protected String excelErrorTitle = "上传结果";
	
	public void setExcelError(String isOK,String result,String resultSeparator) {
		excelError.setIsOK(isOK);
		excelError.setResultSeparator(resultSeparator);
		excelError.getResults().add(result);
	}
	
	public void setExcelError(String isOK,String result) {
		excelError.setIsOK(isOK);
		excelError.getResults().add(result);
	}
	
	public ExcelImg getExcelError() {
		return excelError;
	}

	public void setExcelError(ExcelImg excelError) {
		this.excelError = excelError;
	}
	
	public void setExcelError(String isOK) {
		excelError.setIsOK(isOK);
	}
	
	public ExcelCell getExcelCell(String cellHeaderText){
		   if(null == excelCells || excelCells.isEmpty()){
			   return null;
		   }
		   
		   for(ExcelCell excelCell : excelCells){
			   if(excelCell.getCellHeaderText().equalsIgnoreCase(cellHeaderText)){
				   return excelCell;
			   }
		   }
		   
		   return null;
		   
	}

	/**
	 * 对于excelCells进行排序
	* @author lei.wang
	* @bugId
	* @summary:
	* @url:
	 */
	public void sort(){
		   boolean flag = true;
		   if(excelCells == null || excelCells.isEmpty()){
			   flag = false;
		   }else{
			   for(ExcelCell excelCell : excelCells){
				   if(excelCell.getColIndex() == null){
					   flag = false;
				   }
			   }
		   }
			   
		   if(flag){
		   Collections.sort(excelCells, this);
		   }
	}
	
	/**
	 * 判断非空的第一列是否有值
	* @author lei.wang
	* @bugId
	* @summary:
	* @url:
	 */
	public boolean checkFirstNotNullColValue(){
		   if(excelCells != null && !excelCells.isEmpty()){
			   for(ExcelCell excelCell : excelCells){
				   if(excelCell != null && !excelCell.isNull()){
					   return true;
				   }
			   }
		   }
		   return false;
	}
	
	/**
	 * 获取非空的第一列
	* @author lei.wang
	* @bugId
	* @summary:
	* @url:
	 */
	public ExcelCell getFirstNotNullColValue(){
			   for(ExcelCell excelCell : excelCells){
				   if(!excelCell.isNull() && excelCell.isExistColumn()){
					   return excelCell;
				   }
			   }
			   return null;
	}

	
	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public List<ExcelCell> getExcelCells() {
		return excelCells;
	}
	
	public void setExcelCells(List<ExcelCell> excelCells) {
		this.excelCells = excelCells;
	}

	public void setExcelCells(ExcelCell... ec) {
		if(null != ec)
	    this.excelCells = Arrays.asList(ec);
	}

	
	public int compare(ExcelCell o1, ExcelCell o2) {
		return o1.getColIndex().compareTo(o2.getColIndex());
	}

	public String getExcelErrorTitle() {
		return excelErrorTitle;
	}

	public void setExcelErrorTitle(String excelErrorTitle) {
		this.excelErrorTitle = excelErrorTitle;
	}
	
}
