package com.manpowergroup.cn.icloud.webservice;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.manpowergroup.cn.icloud.base.model.VendorModel;

@WebService
public interface VendorService {
    
    public List<VendorModel> getVendorAllList() ;
}
