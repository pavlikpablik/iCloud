package com.manpowergroup.cn.icloud.base.model;

import java.util.Date;
/**
 * Vendor webservice接口的模型
* <p>Date : 2012-2-7</p>
* <p>Module : VendorModel</p>
* <p>Remark : </p>
* @author :steven
* @version :
* @bugId :
* @summary:
* @url:
* <p>------------------------------------------------------------</p>
* <p> 修改历史</p>
* <p> 序号 日期 修改人 修改原因</p>
* <p> 1 </p>
 */
public class VendorModel implements java.io.Serializable {

	/**
     * Create date:2013-3-7
     * Describe   :TODO
     */
    private static final long serialVersionUID = -3924559233357108044L;
    //供应商ID
	private Integer id; 
	//供应商名称
	private String name; 
	//所属人
	private Integer owner;
//	//所有人名性
//	private String staffNames;
//	//部门编号
//	private String branchCode;
	//联系人
	private String contactName1; 
	//联系人地址
	private String contactAddress1; 
	//联系人邮编
	private String contactPostCode1;  
	//联系人电话
	private String contactPhone1;    
	//联系人电子邮件
	private String contactEmail1; 
//	//第二联系人
//	private String contactName2;
//	//第二联系人地址
//	private String contactAddress2;  
//	//第二联系人邮编
//	private String contactPostCode2;  
//	//第二联系人电话
//	private String contactPhone2; 
//	//第二联系人电子邮件
//	private String contactEmail2;
	//所在城市
    private String vendorCity;   
    //合同开始时间
    private Date contractBegin;   
    //合同结束时间
    private Date contractEnd;   
//    //合作方
//    private String collaborator; 
//    //供应商类型
//    private String typeName;  
//    //营业执照编号
//    private String charterNumber;
//    //开户行名
//    private String accountBank; 
//    //账户名称
//    private String accountName;  
//    //账号
//    private String accountNumber;
//    //描述
//    private String description; 
//    //备注
//    private String remark;   
//    //状态
//    private Integer status;   
//    //服务内容
//    private String serviceItems;  
//    //服务城市ID
//    private Integer vendorCityId; 
//    //服务城市
//    private String vendorCityName;
//    //该城市社保类型ID
//    private Integer ssbId;   
//    //该城市社保类型
//    private String ssbName;  
//    //该城市服务内容ID
//    private Integer vendorCityServiceId;    
//    //该城市服务内容
//    private Integer vendorCityServiceBaseId;   
//    //管理费
//    private BigDecimal serviceFee;      
//    //该城市状态
//    private Integer vendorCityStatus;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOwner() {
        return owner;
    }
    public void setOwner(Integer owner) {
        this.owner = owner;
    }
    public String getContactName1() {
        return contactName1;
    }
    public void setContactName1(String contactName1) {
        this.contactName1 = contactName1;
    }
    public String getContactAddress1() {
        return contactAddress1;
    }
    public void setContactAddress1(String contactAddress1) {
        this.contactAddress1 = contactAddress1;
    }
    public String getContactPostCode1() {
        return contactPostCode1;
    }
    public void setContactPostCode1(String contactPostCode1) {
        this.contactPostCode1 = contactPostCode1;
    }
    public String getContactPhone1() {
        return contactPhone1;
    }
    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }
    public String getContactEmail1() {
        return contactEmail1;
    }
    public void setContactEmail1(String contactEmail1) {
        this.contactEmail1 = contactEmail1;
    }
    public String getVendorCity() {
        return vendorCity;
    }
    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }
    public Date getContractBegin() {
        return contractBegin;
    }
    public void setContractBegin(Date contractBegin) {
        this.contractBegin = contractBegin;
    }
    public Date getContractEnd() {
        return contractEnd;
    }
    public void setContractEnd(Date contractEnd) {
        this.contractEnd = contractEnd;
    }
	
   
}
