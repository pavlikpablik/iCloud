package com.manpowergroup.cn.icloud.socialbenefit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.DateUtil;
import com.manpowergroup.cn.core.utils.PropertiesLoader;
import com.manpowergroup.cn.core.utils.Struts2Utils;
import com.manpowergroup.cn.core.web.BaseAction;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.base.service.CityService;
import com.manpowergroup.cn.icloud.webservice.SsbDeclareService;
import com.manpowergroup.cn.icloud.webservice.VendorService;

/**
 * 
 * @author Sundy.Sun
 * 社保管理
 */
public class SocialBenefitAction extends BaseAction{

	private static final long serialVersionUID = -2419492697895141410L;
	
	private SsbDeclareService ssbDeclareService;
	
	private City city;
	
	private List<City> cityList;

	private List<City> provinceList =  new ArrayList<City>();
	
	public List<City> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<City> provinceList) {
		this.provinceList = provinceList;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}




	@Autowired
	private CityService cityService;

	/**
	 * 跳转到page查询页面
	 * @return
	 * @throws Exception
	 */
	public String page() throws Exception 
	{
		provinceList = cityService.provinceList();
		return "page";
	}
	
	/**
	 * 通过省获取下面的所有city
	 * @return
	 * @throws Exception
	 */
	public String queryCityByProvince() throws Exception 
	{
		if (city != null && city.getId() != null) 
		{
			cityList = cityService.queryCityById(city.getId()).getCitys();
		} else {
			cityList = new ArrayList<City>();
		}
		String treejosn = jsonMapper.toJson(cityList);
		Struts2Utils.renderText(treejosn, headers);
		return null;
	}
	
	/**
	 * 预览导出到Excel
	 * @return
	 * @throws Exception
	 */
	public String export()throws Exception
	{
		/*Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cityId", request.getParameter("cityId"));
		parameters.put("userId", this.getCurrOperatorId());
		parameters.put("rulemonth", DateUtil.getDateFormat(new Date(),"yyyy/MM"));
		*/
		PropertiesLoader pl = new PropertiesLoader(ICloudConst.EXCEL_EXPORT);
		
		String dir = pl.getProperty("excel.temp");
		String monthFee = DateUtil.getDateFormat(new Date(),"yyyy/MM");
		String vendorId = String.valueOf(this.getCurrVendorId()==null?"":this.getCurrVendorId());
		//String vendorId = "1";
		String cityId = request.getParameter("cityId");
		
		//TODO:接口调用
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-excel");
		try {
			InputStream in = null;
			OutputStream out = null;
			File file = null;
	         try {
	             //设置超时时间
	             ApplicationContext ctx = new ClassPathXmlApplicationContext(  
	                     "applicationContext-webservice.xml");  
	             ssbDeclareService = (SsbDeclareService) ctx.getBean("ssbDeclareService");  
	             org.apache.cxf.endpoint.Client client1 = ClientProxy.getClient(ssbDeclareService);
	             HTTPConduit http = (HTTPConduit) client1.getConduit();
	             HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	             httpClientPolicy.setConnectionTimeout(ICloudConst.WEBSERVICE_CONNECTION_TIMEOUT);
	             httpClientPolicy.setAllowChunking(false);
	             httpClientPolicy.setReceiveTimeout(ICloudConst.WEBSERVICE_RECEIVE_TIMEOUT);
	             http.setClient(httpClientPolicy);
	            String filePath = ssbDeclareService.exportSsbDeclare(monthFee, vendorId, cityId,dir);
//	            file = new File("//10.86.1.100/app/Reach/iCloud-platform/ExcelTemplate/test.xlsx");
	            file = new File(dir+filePath);
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ new String("社保申报预览.xlsx".getBytes("GBK"),
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
	            if (file!=null && file.exists()) {
					try {
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	         }
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
	}
}
