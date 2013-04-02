package com.manpowergroup.cn.icloud.sys.entity;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;


public class User implements java.io.Serializable {
   
	private static final long serialVersionUID = 6870748083902003365L;

	private Long id;
	
	private Branch branch;

    private String loginName;

    private String password;
    
    private String surePassword;

    private String trueName;

    private Integer sex;

    private String mobile;

    private String phone;

    private String idcard;

    private String email;

    private String birthday;

    private Long organId;

    private Long branchId;

    private String officePhone;

    private Integer isOrganManage;

    private Integer issys;

    private Integer enabled;

    private Date createDate;

    private Long createBy;

    private Date modifyDate;

    private Long modifyBy;

    private Integer status;

    private String remark;
    
    private List<Role> roleList;
    
    
    //自定义列
    private String orgName;
	
    private String branchName; 
    
    private String oldPassword;
    
    private String orgId;
    
    private String roleName;
    
    private List<Integer> ids;//权限组id
    
    private String roleNames;//权限组名称
    
    private String roleIds;//权限组编号
    
    private Long vendorId;

   
    
    public Long getVendorId() {
		return vendorId;
	}


	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public User() {
    }

    
	public String getSurePassword() {
        return surePassword;
    }

    public void setSurePassword(String surePassword) {
        this.surePassword = surePassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public Integer getIsOrganManage() {
        return isOrganManage;
    }

    public void setIsOrganManage(Integer isOrganManage) {
        this.isOrganManage = isOrganManage;
    }

    public Integer getIssys() {
        return issys;
    }

    public void setIssys(Integer issys) {
        this.issys = issys;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
    public   static  enum IS_SYS {
        IS_SYS(1,"是"), IS_NOT_SYS(0,"否");
        
        private final int value;
        private final String name;
        public int getValue() {
            return value;
        }
        
        public String getName() {
            return name;
        }
        
        public static IS_SYS getEnumByValue(int value){
            EnumSet<IS_SYS> stateSet = EnumSet.allOf(IS_SYS.class);  
            for (IS_SYS s : stateSet) {  
                if(s.getValue() == value)
                    return s;
            } 
            return null ;
        }

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        IS_SYS(int value,String name) {
            this.value = value;
            this.name = name;
        }
    }
    
    public   static  enum ENABLED {
        ENABLED(1,"正常"), UN_ENABLED(0,"禁用");
        
        private final int value;
        private final String name;
        public int getValue() {
            return value;
        }
        
        public String getName() {
            return name;
        }
        
        public static ENABLED getEnumByValue(int value){
            EnumSet<ENABLED> stateSet = EnumSet.allOf(ENABLED.class);  
            for (ENABLED s : stateSet) {  
                if(s.getValue() == value)
                    return s;
            } 
            return null ;
        }

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ENABLED(int value,String name) {
            this.value = value;
            this.name = name;
        }
    }

	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<Integer> getIds() {
		return ids;
	}


	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}


	public String getRoleNames() {
		roleNames = "";
		if(this.roleList == null )return "";
		else{
			for(int i = 0; roleList != null && i < roleList.size(); i++)
			{
				roleNames+=roleList.get(i).getName()+",";
			}
		}
		return roleNames;
	}


	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}


	public String getRoleIds() {
		roleIds = "";
		if(this.roleList == null )return "";
		else{
			for(int i = 0; roleList != null && i < roleList.size(); i++)
			{
				roleIds+=roleList.get(i).getId()+",";
			}
		}
		return roleIds;
	}


	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}


	public List<Role> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	

	
	
    
}