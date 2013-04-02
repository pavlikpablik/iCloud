package com.manpowergroup.cn.icloud.webservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manpowergroup.cn.icloud.sys.util.ExcelRow;

import com.manpowergroup.cn.core.ICloudConst;
import com.manpowergroup.cn.core.utils.XMLUtil;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;

public class SsbDeclareServiceTest {

    SsbDeclareService ssbDeclareService;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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
	}

	@After
	public void tearDown() throws Exception {
		
	}

//	@Test
	public void exportSsbDeclare() {
	    
      String result = ssbDeclareService.exportSsbDeclare("2012/03","104","1239","\\work\\iCloud\\excelTemp\\");

	}
//	@Test
	 public void cancelDeclare() throws Exception {
	        
	    String xmlString = ssbDeclareService.cancelDeclare("\\\\10.86.1.100\\app\\Reach\\iCloudTest\\社保日常管理-员工社保核对确认模板.xlsx","2012/03","1239","\\\\10.86.1.100\\app\\Reach\\iCloudTest\\");
	    //ExcelRow headerText =  (ExcelRow) XMLUtil.fromXML(xmlString) ;   
//	    assertNotNull(headerText);
	     }
	@Test
    public void saveSsbAdjuest() {
        
	  SsbAjust ssbAjust = new SsbAjust();
	  ssbAjust.setCandidateId(1);
	  ssbAjust.setRuleId(2);
	  ssbAjust.setMonthFee("2012/01");
	  ssbAjust.setCreateBy(3);
	  ssbAjust.setRemark("平台调整");
	  List<SsbAjust> headerList=new ArrayList<SsbAjust>();
	  List<SsbAjustDetail> detailList=new ArrayList<SsbAjustDetail>();
	  SsbAjustDetail ssbAjustDetail1 = new SsbAjustDetail();
	  ssbAjustDetail1.setItemId(1);
	  ssbAjustDetail1.setCreateBy(2);
	  ssbAjustDetail1.setRemark("detail");
	  ssbAjustDetail1.setCompanyAmount(BigDecimal.TEN);
	  ssbAjustDetail1.setPersonAmount(new BigDecimal(20));
	  ssbAjustDetail1.setMonthAttribute("2012/02");
	  detailList.add(ssbAjustDetail1);
	  
	   SsbAjustDetail ssbAjustDetail2 = new SsbAjustDetail();
	   ssbAjustDetail2.setItemId(1);
	   ssbAjustDetail2.setCreateBy(2);
	   ssbAjustDetail2.setRemark("detail");
	   ssbAjustDetail2.setCompanyAmount(BigDecimal.TEN);
	   ssbAjustDetail2.setPersonAmount(new BigDecimal(30));
	   ssbAjustDetail2.setMonthAttribute("2012/01");
	   detailList.add(ssbAjustDetail2);
	  ssbAjust.setSsbDetailList(detailList);
	  headerList.add(ssbAjust);
      try {
        ssbDeclareService.saveSsbAdjuest(headerList);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }

}
