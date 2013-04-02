package com.manpowergroup.cn.icloud.base.entity;

import java.util.Date;
import java.util.EnumSet;

import org.apache.commons.lang.StringUtils;

import com.manpowergroup.cn.icloud.base.model.BaseItemTypeEnum;


public class Item implements java.io.Serializable{


    /**
     * Create date:2013-1-21
     * Describe   :TODO
     */
    private static final long serialVersionUID = -2795837537825672248L;

    public static final Integer STATUS_ABILITY1 = 1; //有效的或已审批状态
    
    private Long id;

    private String name;

    private String code;

    private String remark;

    private Long createBy;

    private Date createDate;

    private Integer seq;

    private String type;

    private Integer status;
    
    private String statusName;
    
    private String typeName;
    
    //add by bob 2013-03-27
    private String nameEn;
    
    private String describe;
    
    private Long modifyBy;
    
    private Date modifyDate;
    
    private Integer personTax;
    
    private Integer companyTax;
    
    private Integer isNegative;
    
    private Integer isCompanyNegative;
    
    private String itemGroup;
    
    private String inOrOut;
    
    private String taxAble;
    
    private String serviceType;
    
    private String itemType;
    
    public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	private Integer isPersonAmount;
    
    private Integer isCompanyAmount;
    
    private Integer isPersonPay;
    
    private Integer isCompanyPay;
    
    private Integer taxProvideBefore;
    
    private Integer taxDeductAfter;
    
    
    public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getPersonTax() {
		return personTax;
	}

	public void setPersonTax(Integer personTax) {
		this.personTax = personTax;
	}

	public Integer getCompanyTax() {
		return companyTax;
	}

	public void setCompanyTax(Integer companyTax) {
		this.companyTax = companyTax;
	}

	public Integer getIsNegative() {
		return isNegative;
	}

	public void setIsNegative(Integer isNegative) {
		this.isNegative = isNegative;
	}

	public Integer getIsCompanyNegative() {
		return isCompanyNegative;
	}

	public void setIsCompanyNegative(Integer isCompanyNegative) {
		this.isCompanyNegative = isCompanyNegative;
	}

	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getTaxAble() {
		return taxAble;
	}

	public void setTaxAble(String taxAble) {
		this.taxAble = taxAble;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}



	public Integer getIsPersonAmount() {
		return isPersonAmount;
	}

	public void setIsPersonAmount(Integer isPersonAmount) {
		this.isPersonAmount = isPersonAmount;
	}

	public Integer getIsCompanyAmount() {
		return isCompanyAmount;
	}

	public void setIsCompanyAmount(Integer isCompanyAmount) {
		this.isCompanyAmount = isCompanyAmount;
	}

	public Integer getIsPersonPay() {
		return isPersonPay;
	}

	public void setIsPersonPay(Integer isPersonPay) {
		this.isPersonPay = isPersonPay;
	}

	public Integer getIsCompanyPay() {
		return isCompanyPay;
	}

	public void setIsCompanyPay(Integer isCompanyPay) {
		this.isCompanyPay = isCompanyPay;
	}

	public Integer getTaxProvideBefore() {
		return taxProvideBefore;
	}

	public void setTaxProvideBefore(Integer taxProvideBefore) {
		this.taxProvideBefore = taxProvideBefore;
	}

	public Integer getTaxDeductAfter() {
		return taxDeductAfter;
	}

	public void setTaxDeductAfter(Integer taxDeductAfter) {
		this.taxDeductAfter = taxDeductAfter;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	
    
    
    

    public String getTypeName() {
        if(StringUtils.isNotBlank(this.type)&& BaseItemTypeEnum.getEnumByValue(Integer.parseInt(this.type))!=null)
            return BaseItemTypeEnum.getEnumByValue(Integer.parseInt(this.type)).getName();
        return null;
    }
    
    public String getStatusName() {
        return BaseItemStatus.getEnumByValue(this.status)== null ? null : BaseItemStatus.getEnumByValue(this.status).getName();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        //this.createBy = createBy == null ? null : createBy.trim();
    	this.createBy=createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDt(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    /**brief description
     * <p>Date       : 2013-1-21</p>
     * <p>Module     : 模块名称</p>
     * <p>Description: 描述:内部枚举类</p>
     * <p>Remark     : 备注</p>
     * 版权声明 :
     * @author  whq 
     * @version 1.0
     * <p>------------------------------------------------------------</p>
     * <p> 修改历史</p>
     * <p>  序号		日期		修改人			修改原因</p>
     * <p>   1                                     </p>
     */
    public   static  enum BaseItemStatus {
        STATUS_START(1,"启用"), STATUS_STOP(0,"停用");
        
        private final int value;
        private final String name;
        public int getValue() {
            return value;
        }
        
        public String getName() {
            return name;
        }
        
        public static BaseItemStatus getEnumByValue(int value){
            EnumSet<BaseItemStatus> stateSet = EnumSet.allOf(BaseItemStatus.class);  
            for (BaseItemStatus s : stateSet) {  
                if(s.getValue() == value)
                    return s;
            } 
//            Iterator<BaseItemStatus> it = iterator(BaseItemStatus.class);
//            while(it.hasNext()){
//                BaseItemStatus state = it.next();
//                if(state.getValue() == value)
//                    return state;
//            }
            return null ;
        }

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BaseItemStatus(int value,String name) {
            this.value = value;
            this.name = name;
        }
    }
}