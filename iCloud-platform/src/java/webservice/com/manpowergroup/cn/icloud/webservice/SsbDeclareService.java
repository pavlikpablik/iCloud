package com.manpowergroup.cn.icloud.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;

@WebService
//(targetNamespace = "http://10.86.36.22")
//@SOAPBinding(style = Style.RPC)
public interface SsbDeclareService {
    
    /** 申报导出
     * <p> 描述	:  方法的主要功能和使用场合</p>
     * <p> 备注	:  其他对方法的说明信息</p>
     * @param monthFee
     * @param vendorId
     * @param cityId
     * @param directory
     * @return
     */
    public String exportSsbDeclare(@WebParam(name = "monthFee")
    String monthFee,@WebParam(name = "vendorId" ) String vendorId,@WebParam(name = "cityId")
    String cityId,@WebParam(name = "directory")
    String directory);
    
    /** 申报核对
     * <p> 描述	:  方法的主要功能和使用场合</p>
     * <p> 备注	:  其他对方法的说明信息</p>
     * @param fileName
     * @param monthFee
     * @param vendorId
     * @return
     * @throws Exception
     */
    public String cancelDeclare(@WebParam(name = "fileName")
    String fileName,@WebParam(name = "monthFee" ) String monthFee,@WebParam(name = "vendorId")
    String vendorId,@WebParam(name = "errorPath" ) String errorPath) throws Exception;
    
    /** 保存社保
     * <p> 描述   :  方法的主要功能和使用场合</p>
     * <p> 备注   :  其他对方法的说明信息</p>
     * @param fileName
     * @param monthFee
     * @param vendorId
     * @return
     * @throws Exception
     */
    public void saveSsbAdjuest(@WebParam(name = "headerList") List<SsbAjust> headerList) throws Exception;
}
