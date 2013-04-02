package com.manpowergroup.cn.core.utils;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.manpowergroup.cn.core.Result;
import com.manpowergroup.cn.core.orm.Page;
import com.manpowergroup.cn.icloud.base.entity.City;
import com.manpowergroup.cn.icloud.base.entity.Code;
import com.manpowergroup.cn.icloud.base.entity.Item;
import com.manpowergroup.cn.icloud.base.model.VendorModel;
import com.manpowergroup.cn.icloud.base.service.ItemService;
import com.manpowergroup.cn.icloud.sys.entity.Resource;
import com.manpowergroup.cn.icloud.sys.service.LoginService;
import com.manpowergroup.cn.icloud.webservice.VendorService;

/**
 * 只存放系统级别的常量和公用的方法 ;模块的常量，应放到相应模块下面
 * @author jiangpeng.sun
 */
public class ICloudDataUtil {
	
	
   public static final Integer LOGIN_FALSE_TIME = 10;//一天内允许登陆失败的次数

	// static {
	// System.setProperty("java.rmi.server.hostname","10.86.1.179");
	// }
	public static String showMemoryUsage() {
		long memory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		return String.format("%.1f MB", (memory / (1024.0 * 1024.0)));
	}

	@Autowired
	private ItemService itemService;
	/*@Autowired
	private CityService cityService;*/
	@Autowired
	private LoginService loginService; 
	
	//手动注入
	private VendorService vendorService;

	public static List<City> cityList;
	public static List<Item> itemList;
	public static List<Code> codeList;
	public static List<Resource> resourceList;
	public static List<String> sysResourceList;
	public static List<String> sysActionList;
	public static List<VendorModel> vendorList;
	

	/**
	 * 初始化基础数据
	 */
	public synchronized void init() {
		this.initCity();
		this.initResource();
		this.initItem();
		//this.initVendor();
	}

	private void initResource() {
		resourceList =loginService.findAllResource();
		sysResourceList = Lists.newArrayList();
		sysActionList = Lists.newArrayList();
		if (resourceList!=null) {
			for (Resource resource : resourceList) {
				if (Integer.valueOf(1).equals(resource.getIssys())) {
					sysResourceList.add(resource.getName());
					sysActionList.add(resource.getUrl());
				}
			}
		}
	}
	
	/**
	 * 初始化vendor数据
	 */
	public void initVendor(){
		
		/*//手动注入
		ApplicationContext ctx = new ClassPathXmlApplicationContext(  
        "applicationContext-webservice.xml");  
		vendorService = (VendorService) ctx.getBean("vendorService"); 
		//设置超时时间
		org.apache.cxf.endpoint.Client client1 = ClientProxy.getClient(vendorService);
        HTTPConduit http = (HTTPConduit) client1.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(ICloudConst.WEBSERVICE_CONNECTION_TIMEOUT);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(ICloudConst.WEBSERVICE_RECEIVE_TIMEOUT);

        http.setClient(httpClientPolicy);
		vendorList=vendorService.getVendorAllList();*/
	}

	/**
	 * 初始化city数据
	 */
	public void initCity() {
//		cityList = cityService.findAll();
	}

	/**
	 * 初始化Item数据
	 */
	public void initItem() {
		itemList = itemService.findAll();
	}
	
	
	/**
	 * 根据ID获取城市
	 */
	public static City getCityById(Long id) {
		if (id == null || cityList == null)
			return null;
		for (int i = 0; i < cityList.size(); i++) {
			if (id.equals(cityList.get(i).getId())) {
				return cityList.get(i);
			}
		}
		return null;
	}
	
	
//	/**
//	 * 根据cityId获取名称，例如：广东-广州
//	 */
//	public static String getProvinceAndCityName(Long cityId) {
//		 City city = getCityById(cityId);
//		 if (city!=null && city.getParentId()==null) {
//			return city.getName();
//		}else {
//			City pcity = getCityById(city.getParentId());
//			if (pcity!=null) {
//				return pcity.getName()+"-"+city.getName();
//			}
//		}
//		return null;
//	}
	
	
	/**
	 * 根据ID取ITEM
	 */
	public static Item getItemById(Integer id) {
		if (id == null || itemList == null)
			return null;
		for (int i = 0; i < itemList.size(); i++) {
			if (id.equals(itemList.get(i).getId())) {
				return itemList.get(i);
			}
		}
		return null;
	}
	
	
	
	public static RowBounds getRowBounds(@SuppressWarnings("rawtypes") Page page) {
		return new RowBounds(page.getFirst()-1, page.getPageSize());
	}
	
	public static Result getResult(String messange,Boolean isSuccess) {
		Result result = new Result();
		if (isSuccess==null) {
			result.setResult(true);
		}else {
			result.setResult(isSuccess);
		}
		result.setResultText(StringUtils.isNotBlank(messange)?messange:"保存成功");
		return result;
	}
	public static Result getdefaultResult() {
		Result result = new Result();
		result.setResult(true);
		result.setResultText("保存成功");
		return result;
	}
	
	
	

	/**
	 * 判断登录人是否有权限
	 */
	public static boolean hasRoles(Set<String> roles, String actionName,
			String namespace) {
		boolean reslut = false;
		
		if ("layout_layout".equals(actionName) || actionName.contains("register")){
			return true;
		}
		if (StringUtils.isBlank(actionName) || StringUtils.isBlank(namespace)) {
			return false;
		}
		
		if (actionName.contains("logon")) {
			return true;
		}
			
		if (resourceList != null && resourceList.contains(actionName)) {
			if (roles!=null && roles.contains(actionName) && namespace.equals("/")) {
				reslut = true;
			}else {
				reslut = false;
			}
		} else {
			reslut = true;
		}
		return reslut;
	}
	
	
}
