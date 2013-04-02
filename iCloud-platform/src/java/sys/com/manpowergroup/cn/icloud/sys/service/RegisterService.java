package com.manpowergroup.cn.icloud.sys.service;

import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.icloud.sys.entity.User;


public interface RegisterService {

	boolean checkLoginNameValidity(String staffName);

    Result register(User user,String checkCode,String checkCodeOrg)throws Exception;

    boolean checkVendorIdValidity(Long vendorId);
}
