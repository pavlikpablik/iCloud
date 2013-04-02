package com.manpowergroup.cn.icloud.sys.util;
import java.util.List;

/**
 * 在Excel 单元信息 T为基本类型的信息
 * 目前泛型T支持：Integer,BigDecimal,String,Date,Boolean
* <p>Date : 2011-11-7</p>
* <p>Module : Excel 导入导出</p>
* @author :lei.wang
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
public class ExcelCell<T> implements java.io.Serializable{
    
    private static final long serialVersionUID = -2386556039988182555L;
    
    private Integer colIndex; // execl列号 从0开始
    private String cellHeaderText; // excel 头信息
    private Class<T>  excelCellType; //excel单元格类型
    private T excelCellValue = null; //excel单元格数据
    private boolean isNull = true; // 是否可以为空 true可以为空，false不可以为空
    private boolean isRepeat = true;//一列中是否可以存在重复数据 true可以重复，false不可以重复
    private boolean isExist = true; //当前列是否可以存在或不存在 true必须存在，false可以不存在
    private boolean existColumn = true; 

    private ExcelImg excelImg = new ExcelImg(ExcelImg.OK ); //储存验证信息  验证通过OK 失败为Faild 并在result填入错误信息
    
    /**
     * 构造方法
     * @param cellHeaderText excel 头信息
     * @param excelCellType  excel单元格类型
     */
    public ExcelCell(String cellHeaderText , Class<T>  excelCellType) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
    }

    /**
     * 构造方法
     * @param cellHeaderText excel 头信息
     * @param excelCellType  excel单元格类型
     * @param isExist        当前列是否可以存在或不存在 true必须存在，false可以不存在
     */
    public ExcelCell(String cellHeaderText, Class<T> excelCellType,
            boolean isExist) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.isExist = isExist;
    }
    
    /**
     * 构造方法
     * @param cellHeaderText  excel 头信息
     * @param excelCellType   excel单元格类型
     * @param isNull          是否可以为空 true可以为空，false不可以为空
     * @param isRepeat        一列中是否可以存在重复数据 true可以重复，false不可以重复
     * @param isExist         当前列是否可以存在或不存在 true必须存在，false可以不存在
     */
    public ExcelCell(String cellHeaderText, Class<T> excelCellType,
            boolean isNull, boolean isRepeat, boolean isExist) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.isNull = isNull;
        this.isRepeat = isRepeat;
        this.isExist = isExist;
    }

    /**
     * 构造方法
     * @param cellHeaderText  excel 头信息
     * @param excelCellType   excel单元格类型
     * @param isNull          是否可以为空 true可以为空，false不可以为空
     * @param isRepeat        一列中是否可以存在重复数据 true可以重复，false不可以重复
     */
    public ExcelCell(String cellHeaderText, Class<T> excelCellType,
            boolean isNull, boolean isRepeat) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.isNull = isNull;
        this.isRepeat = isRepeat;
    }
    
    /**
     * 构造方法
     * @param cellHeaderText  excel 头信息
     * @param excelCellType   excel单元格类型
     * @param excelCellValue  excel单元格数据
     */
    public ExcelCell(String cellHeaderText, Class<T> excelCellType,
            T excelCellValue) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.excelCellValue = excelCellValue;
    }
    
    /**
     * 构造方法
     * @param cellHeaderText  excel 头信息
     * @param excelCellType   excel单元格类型
     * @param isNull          是否可以为空 true可以为空，false不可以为空
     * @param isRepeat        一列中是否可以存在重复数据 true可以重复，false不可以重复
     * @param isExist         当前列是否可以存在或不存在 true必须存在，false可以不存在
     * @param excelImg        储存验证信息  验证通过OK 失败为Faild 并在result填入错误信息
     */
    public ExcelCell(String cellHeaderText, Class<T> excelCellType,
            boolean isNull, boolean isRepeat, boolean isExist, ExcelImg excelImg) {
        super();
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.isNull = isNull;
        this.isRepeat = isRepeat;
        this.isExist = isExist;
        this.excelImg = excelImg;
    }



    /**
     * 构造方法
     * @param colIndex        execl列号 从0开始
     * @param cellHeaderText  excel 头信息
     * @param excelCellType   excel单元格类型
     * @param excelCellValue  excel单元格数据
     * @param isNull          是否可以为空 true可以为空，false不可以为空
     * @param isRepeat        一列中是否可以存在重复数据 true可以重复，false不可以重复
     * @param isExist         当前列是否可以存在或不存在 true必须存在，false可以不存在
     * @param excelImg        储存验证信息  验证通过OK 失败为Faild 并在result填入错误信息
     */
    public ExcelCell(Integer colIndex, String cellHeaderText,
            Class<T> excelCellType, T excelCellValue, boolean isNull,
            boolean isRepeat, boolean isExist, ExcelImg excelImg) {
        super();
        this.colIndex = colIndex;
        this.cellHeaderText = cellHeaderText;
        this.excelCellType = excelCellType;
        this.excelCellValue = excelCellValue;
        this.isNull = isNull;
        this.isRepeat = isRepeat;
        this.isExist = isExist;
        this.excelImg = excelImg;
    }



    public ExcelCell(){
        
    }

    public String getCellHeaderText() {
        return cellHeaderText;
    }

    public void setCellHeaderText(String cellHeaderText) {
        this.cellHeaderText = cellHeaderText;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean isNull) {
        this.isNull = isNull;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }
    
    public ExcelImg getExcelImg() {
        return excelImg;
    }

    public void setExcelImg(String resultSeparator) {
        excelImg.setResultSeparator(resultSeparator);
    }
    
    public void setExcelImg(List<String> results) {
        if(results != null && !results.isEmpty()){
            for(String result : results){
                setExcelImg(ExcelImg.FAILD, result);
            }
        }
    }
    
    public void setExcelImg(String isOK,String result,String resultSeparator) {
        excelImg.setIsOK(isOK);
        excelImg.setResultSeparator(resultSeparator);
        excelImg.getResults().add(result);
    }
    
    public void setExcelImg(String isOK,String result) {
        excelImg.setIsOK(isOK);
        excelImg.getResults().add(result);
    }

    public T getExcelCellValue() {
        return excelCellValue;
    }

    public void setExcelCellValue(T excelCellValue) {
        this.excelCellValue = excelCellValue;
    }
    
    /**
     * 获取当前列的对应的数据类型
    * @author lei.wang
    * @bugId
    * @summary:
    * @url:
     */
    public Class<T> getExcelCellType(){
           return this.excelCellType;
    }
    
    public void setExcelCellType(Class<T> excelCellType) {
        this.excelCellType = excelCellType;
    }

    /**
     * 获取对应字母列
    * @author lei.wang
    * @bugId
    * @summary:
    * @url:
     */
    public String getColLetter()
     {
      String ch = "";
         if (colIndex  < 26)
             ch = "" + (char)((colIndex) + 65);
         else
            ch = "" + (char)((colIndex) / 26 + 65 - 1) + (char)((colIndex) % 26 + 65);
         return ch;
     }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean isExist) {
        this.isExist = isExist;
    }

    public boolean isExistColumn() {
        return existColumn;
    }

    public void setExistColumn(boolean existColumn) {
        this.existColumn = existColumn;
    }
}
