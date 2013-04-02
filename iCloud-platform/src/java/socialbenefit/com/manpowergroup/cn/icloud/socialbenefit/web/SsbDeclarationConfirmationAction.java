package com.manpowergroup.cn.icloud.socialbenefit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.FileUploadUtil;
import com.manpowergroup.cn.core.utils.PropertiesLoader;
import com.manpowergroup.cn.core.utils.StringUtils;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.sys.util.ExcelRow;
import com.manpowergroup.cn.icloud.webservice.SsbDeclareService;


/**
 * 
 * @author Bob.Chen
 * 社保申报确认
 */
public class SsbDeclarationConfirmationAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4585428193927867352L;
	
	private SsbDeclareService ssbDeclareService;
	
	private File uploadFile;
	
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String page(){
		return "page";
	}
	
	/**
	 * 跳转到员工社保导入页面
	 * @return
	 * @throws Exception
	 */
	public String importPre()throws Exception{
		return "importPre";
	}
	
	/**
	 * 导入社保核对确认
	 * @return
	 * @throws Exception
	 */
	public String importSsbDeclarationConfirmation() throws Exception{
String attrMonth = request.getParameter("importmodel");
        
        if(this.uploadFile != null&&this.getCurrVendorId()!=null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
            Calendar calendar = Calendar.getInstance();
            if("preMonth".equals(attrMonth)){
                calendar.add(Calendar.MONTH, -1);    //得到前一天
            }
            Date date = calendar.getTime();
            String monthFee = sdf.format(date);
    		//手动注册webservice
    		ApplicationContext ctx = new ClassPathXmlApplicationContext(  
            "applicationContext-webservice.xml");  
    		ssbDeclareService = (SsbDeclareService) ctx.getBean("ssbDeclareService");  
    		PropertiesLoader p = new PropertiesLoader(ICloudConst.EXCEL_EXPORT);
    		File destFile = FileUploadUtil.getUUIDFileName(p.getProperty("excel.temp"), "temp.xlsx");
    		System.out.println("----------------上传路径："+destFile.getPath());
    		FileUploadUtil.copyFile(this.uploadFile, destFile);
		    org.apache.cxf.endpoint.Client client1 = ClientProxy.getClient(ssbDeclareService);
	        HTTPConduit http = (HTTPConduit) client1.getConduit();
	        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	        httpClientPolicy.setConnectionTimeout(ICloudConst.WEBSERVICE_CONNECTION_TIMEOUT);
	        httpClientPolicy.setAllowChunking(false);
	        httpClientPolicy.setReceiveTimeout(ICloudConst.WEBSERVICE_RECEIVE_TIMEOUT);
	        http.setClient(httpClientPolicy);
	       // String  xmlString = ssbDeclareService.cancelDeclare("\\\\10.86.1.100\\app\\Reach\\iCloudTest\\社保日常管理-员工社保核对确认模板.xlsx","2012/03","1239","\\\\10.86.1.100\\app\\Reach\\iCloudTest\\");
   	    	String  xmlString  = ssbDeclareService.cancelDeclare(destFile.getPath(),monthFee,this.getCurrVendorId().toString(),p.getProperty("excel.temp"));
	        ExcelRow headerText =  (ExcelRow) XMLUtil.fromXML(xmlString) ;
	        Struts2Utils.getRequest().setAttribute("headerText", headerText);
	}
	        return "importinfo";
	}
	
	/**
	 * 下载导入导出模板
	 * @return
	 * @throws Exception
	 */
	public String downloadTemplate() throws Exception{
		
		PropertiesLoader p = new PropertiesLoader(ICloudConst.EXCEL_EXPORT);
		String dirs = p.getProperty("icloud.platform.fileDir");
		String fileName = p.getProperty("template.excel.SsbExcelTemplate");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String type = request.getParameter("type");	String filePath = dirs+fileName;
	
		/*if(StringUtils.isNotBlank(type) && type.equals("new")){
			filePath = dirs+fileName;
		}else if(StringUtils.isNotBlank(type) && type.equals("modify")) {
			filePath = dirs+fileName;
		}else{
			filePath = dirs+fileName;
		}*/
		if (StringUtils.isBlank(filePath)) {
			throw new RuntimeException("模板找不到。"); 
		}
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				File file = new File(filePath);
				response.setContentType(new MimetypesFileTypeMap().getContentType(file));
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String(file.getName().getBytes("GBK"),
										"iso8859-1"));
				in = new FileInputStream(file);
				out = response.getOutputStream();
				IOUtils.copy(in, out);
			} finally {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException("读取模板发生错误,请联系系统管理员。"); 
		}
		return null;
	}
}
