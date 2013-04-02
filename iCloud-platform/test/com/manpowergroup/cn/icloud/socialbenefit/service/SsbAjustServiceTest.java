package com.manpowergroup.cn.icloud.socialbenefit.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;
import com.manpowergroup.cn.icloud.socialbenefit.service.impl.SsbAjustServiceImpl;


public class SsbAjustServiceTest {
    @Autowired
    SsbAjustServiceImpl ssbAjustServiceImpl;
    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ssbAjustServiceImpl = (SsbAjustServiceImpl) ac.getBean("ssbAjustServiceImpl");
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
//          ssbAjustServiceImpl.saveSsbAdjust(headerList);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
}
