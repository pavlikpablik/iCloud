//package com.manpowergroup.cn.icloud.socialbenefitBase.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.Set;
//
//import com.manpower.es.entity.role.EsRoleInfo;
//import com.manpower.es.entity.staff.Staff;
//import com.manpowergroup.cn.icloud.sys.util.ICloudDataUtil;
//
//public class SocialBenefitInfoByCandModel {
//
//	private Integer cand_ssb_id; // 员工社保编号
//	private Integer cand_ssb_item_id; // 员工社保险种编号
//	private String gzszd;// 员工工作所在地
//	private Integer sbjncs;// 社保缴纳城市
//	private Integer sblxbh;// 社保类型编号
//	private String sblxmc;// 社保类型名称
//	private Integer gysbh;// 供应商编号
//	private String gysmc;// 供应商名称
//	private Integer ygbh;// 员工编号
//	private String xm;// 姓名
//	private String sfzjhm;// 身份证件号码
//	private Integer xb;// 性别
//	private String khbh;// 客户编号
//	private String khmc;// 客户名称
//	private String sbkzhbh;// 社保卡账号/编号
//	private String ybkzhbh;// 医保卡账号/编号
//	private String gjjzhbh;// 公积金账号/编号
//	private String hjxz;// 户籍性质
//	private Integer hjcsbh;// 户籍城市编号
//	private String hjcs;// 户籍城市
//	private String hjdz;// 户籍地址
//	private Integer zgxl;// 最高学历
//	private Integer hyzk;// 婚姻状况
//	private Integer ygzt;// 员工状态
//	private Integer ygsbjnzt;// 员工社保缴纳状态
//	private String jqny;// 起缴年月
//	private String tjny;// 退缴年月
//	private Date rzrq;// 入职日期
//	private Date lzrq;// 离职日期
//	private BigDecimal gssx;// 公司上限
//	private BigDecimal gsxx;// 公司下限
//	private BigDecimal gsbfb;// 公司百分比
//	private BigDecimal gsgdj;// 公司固定金
//	private BigDecimal grsx;// 个人上限
//	private BigDecimal grxx;// 个人下限
//	private BigDecimal grbfb;// 个人百分比
//	private BigDecimal grgdj;// 个人固定金
//	private Integer xzssxmysbh;// 险种所属项目元素编号
//	private String xzzfy;// 险种支付月
//	private Integer xzfysxy;// 险种生效月 社保操作月
//	private Integer xzfyssy;// 险种所属月
//	private Integer xzjbjzrq;// 险种加保截止日期
//	private Integer xztbjzrq;// -险种退保截止日期
//	private BigDecimal companyBase;// 公司部分缴费基数
//	private BigDecimal personBase;// 个人部分缴费基数
//	private BigDecimal glf;// /管理费
//	private Integer gysfwxmbh;// 供应商服务项目服务编号
//	private Integer gzk; // 工资卡
//	private Integer sbk;// 社保卡
//	private Integer ybk;// 医保卡
//	private Integer sbssy;// 社保所属月
//	private Integer sbczy;// 社保操作月
//	private Integer csadId; // 客服专员
//	private String csadStr;// 客服专员名称
//	private Integer css; // 客服主管
//	private String cssStr;// 客服主管名称
//
//	// add by sundy
//	private Integer quitSsbStatus;// 退保是否缴纳费用
//
//	// 下面为计算数据
//	private BigDecimal gsjnfy; // 公司缴纳费用
//	private BigDecimal grjnfy; // 个人缴纳费用
//	private BigDecimal gsjs;// 计算后的公司基数
//	private BigDecimal grjs;// 计算后的个人基数
//	
//	private String attributeMonth;//险种所属月
//	
//	//add by sundy 报表中添加收费规则名称及编号
//	private String chargeName;//收费规则名称
//	
//	
//	private Integer ruleId;//收费规则编号
//
//	public Integer getRuleId() {
//		return ruleId;
//	}
//
//	public void setRuleId(Integer ruleId) {
//		this.ruleId = ruleId;
//	}
//
//	public Integer getHjcsbh() {
//		return hjcsbh;
//	}
//
//	public void setHjcsbh(Integer hjcsbh) {
//		this.hjcsbh = hjcsbh;
//	}
//
//	public Integer getGysbh() {
//		return gysbh;
//	}
//
//	public void setGysbh(Integer gysbh) {
//		this.gysbh = gysbh;
//	}
//
//	public Integer getCand_ssb_id() {
//		return cand_ssb_id;
//	}
//
//	public void setCand_ssb_id(Integer cand_ssb_id) {
//		this.cand_ssb_id = cand_ssb_id;
//	}
//
//	public Integer getCand_ssb_item_id() {
//		return cand_ssb_item_id;
//	}
//
//	public void setCand_ssb_item_id(Integer cand_ssb_item_id) {
//		this.cand_ssb_item_id = cand_ssb_item_id;
//	}
//
//	public String getGzszd() {
//		return gzszd;
//	}
//
//	public void setGzszd(String gzszd) {
//		this.gzszd = gzszd;
//	}
//
//	public Integer getSbjncs() {
//		return sbjncs;
//	}
//
//	public void setSbjncs(Integer sbjncs) {
//		this.sbjncs = sbjncs;
//	}
//
//	public Integer getSblxbh() {
//		return sblxbh;
//	}
//
//	public void setSblxbh(Integer sblxbh) {
//		this.sblxbh = sblxbh;
//	}
//
//	public String getSblxmc() {
//		return sblxmc;
//	}
//
//	public void setSblxmc(String sblxmc) {
//		this.sblxmc = sblxmc;
//	}
//
//	public String getGysmc() {
//		return gysmc;
//	}
//
//	public void setGysmc(String gysmc) {
//		this.gysmc = gysmc;
//	}
//
//	public Integer getYgbh() {
//		return ygbh;
//	}
//
//	public void setYgbh(Integer ygbh) {
//		this.ygbh = ygbh;
//	}
//
//	public String getXm() {
//		return xm;
//	}
//
//	public void setXm(String xm) {
//		this.xm = xm;
//	}
//
//	public String getSfzjhm() {
//		return sfzjhm;
//	}
//
//	public void setSfzjhm(String sfzjhm) {
//		this.sfzjhm = sfzjhm;
//	}
//
//	public Integer getXb() {
//		return xb;
//	}
//
//	public void setXb(Integer xb) {
//		this.xb = xb;
//	}
//
//	public String getKhbh() {
//		return khbh;
//	}
//
//	public void setKhbh(String khbh) {
//		this.khbh = khbh;
//	}
//
//	public String getKhmc() {
//		return khmc;
//	}
//
//	public void setKhmc(String khmc) {
//		this.khmc = khmc;
//	}
//
//	public String getSbkzhbh() {
//		return sbkzhbh;
//	}
//
//	public void setSbkzhbh(String sbkzhbh) {
//		this.sbkzhbh = sbkzhbh;
//	}
//
//	public String getYbkzhbh() {
//		return ybkzhbh;
//	}
//
//	public void setYbkzhbh(String ybkzhbh) {
//		this.ybkzhbh = ybkzhbh;
//	}
//
//	public String getGjjzhbh() {
//		return gjjzhbh;
//	}
//
//	public void setGjjzhbh(String gjjzhbh) {
//		this.gjjzhbh = gjjzhbh;
//	}
//
//	public String getHjxz() {
//		return hjxz;
//	}
//
//	public void setHjxz(String hjxz) {
//		this.hjxz = hjxz;
//	}
//
//	public String getHjcs() {
//		return hjcs;
//	}
//
//	public void setHjcs(String hjcs) {
//		this.hjcs = hjcs;
//	}
//
//	public String getHjdz() {
//		return hjdz;
//	}
//
//	public void setHjdz(String hjdz) {
//		this.hjdz = hjdz;
//	}
//
//	public Integer getZgxl() {
//		return zgxl;
//	}
//
//	public void setZgxl(Integer zgxl) {
//		this.zgxl = zgxl;
//	}
//
//	public Integer getHyzk() {
//		return hyzk;
//	}
//
//	public void setHyzk(Integer hyzk) {
//		this.hyzk = hyzk;
//	}
//
//	public Integer getYgzt() {
//		return ygzt;
//	}
//
//	public void setYgzt(Integer ygzt) {
//		this.ygzt = ygzt;
//	}
//
//	public Integer getYgsbjnzt() {
//		return ygsbjnzt;
//	}
//
//	public void setYgsbjnzt(Integer ygsbjnzt) {
//		this.ygsbjnzt = ygsbjnzt;
//	}
//
//	public String getJqny() {
//		return jqny;
//	}
//
//	public void setJqny(String jqny) {
//		this.jqny = jqny;
//	}
//
//	public String getTjny() {
//		return tjny;
//	}
//
//	public void setTjny(String tjny) {
//		this.tjny = tjny;
//	}
//
//	public Date getRzrq() {
//		return rzrq;
//	}
//
//	public void setRzrq(Date rzrq) {
//		this.rzrq = rzrq;
//	}
//
//	public Date getLzrq() {
//		return lzrq;
//	}
//
//	public void setLzrq(Date lzrq) {
//		this.lzrq = lzrq;
//	}
//
//	public BigDecimal getGssx() {
//		return gssx;
//	}
//
//	public void setGssx(BigDecimal gssx) {
//		this.gssx = gssx;
//	}
//
//	public BigDecimal getGsxx() {
//		return gsxx;
//	}
//
//	public void setGsxx(BigDecimal gsxx) {
//		this.gsxx = gsxx;
//	}
//
//	public BigDecimal getGsbfb() {
//		return gsbfb;
//	}
//
//	public void setGsbfb(BigDecimal gsbfb) {
//		this.gsbfb = gsbfb;
//	}
//
//	public BigDecimal getGsgdj() {
//		return gsgdj;
//	}
//
//	public void setGsgdj(BigDecimal gsgdj) {
//		this.gsgdj = gsgdj;
//	}
//
//	public BigDecimal getGrsx() {
//		return grsx;
//	}
//
//	public void setGrsx(BigDecimal grsx) {
//		this.grsx = grsx;
//	}
//
//	public BigDecimal getGrxx() {
//		return grxx;
//	}
//
//	public void setGrxx(BigDecimal grxx) {
//		this.grxx = grxx;
//	}
//
//	public BigDecimal getGrbfb() {
//		return grbfb;
//	}
//
//	public void setGrbfb(BigDecimal grbfb) {
//		this.grbfb = grbfb;
//	}
//
//	public BigDecimal getGrgdj() {
//		return grgdj;
//	}
//
//	public void setGrgdj(BigDecimal grgdj) {
//		this.grgdj = grgdj;
//	}
//
//	public BigDecimal getCompanyBase() {
//		return companyBase;
//	}
//
//	public void setCompanyBase(BigDecimal companyBase) {
//		this.companyBase = companyBase;
//	}
//
//	public BigDecimal getPersonBase() {
//		return personBase;
//	}
//
//	public void setPersonBase(BigDecimal personBase) {
//		this.personBase = personBase;
//	}
//
//	public Integer getXzssxmysbh() {
//		return xzssxmysbh;
//	}
//
//	public void setXzssxmysbh(Integer xzssxmysbh) {
//		this.xzssxmysbh = xzssxmysbh;
//	}
//
//	public String getXzzfy() {
//		return xzzfy;
//	}
//
//	public void setXzzfy(String xzzfy) {
//		this.xzzfy = xzzfy;
//	}
//
//	public Integer getXzfysxy() {
//		return xzfysxy;
//	}
//
//	public void setXzfysxy(Integer xzfysxy) {
//		this.xzfysxy = xzfysxy;
//	}
//
//	public Integer getXzfyssy() {
//		return xzfyssy;
//	}
//
//	public void setXzfyssy(Integer xzfyssy) {
//		this.xzfyssy = xzfyssy;
//	}
//
//	public BigDecimal getGlf() {
//		return glf;
//	}
//
//	public void setGlf(BigDecimal glf) {
//		this.glf = glf;
//	}
//
//	public BigDecimal getGsjnfy() {
//		return gsjnfy;
//	}
//
//	public void setGsjnfy(BigDecimal gsjnfy) {
//		this.gsjnfy = gsjnfy;
//	}
//
//	public BigDecimal getGrjnfy() {
//		return grjnfy;
//	}
//
//	public void setGrjnfy(BigDecimal grjnfy) {
//		this.grjnfy = grjnfy;
//	}
//
//	public BigDecimal getGsjs() {
//		return gsjs;
//	}
//
//	public void setGsjs(BigDecimal gsjs) {
//		this.gsjs = gsjs;
//	}
//
//	public BigDecimal getGrjs() {
//		return grjs;
//	}
//
//	public void setGrjs(BigDecimal grjs) {
//		this.grjs = grjs;
//	}
//
//	public Integer getXzjbjzrq() {
//		return xzjbjzrq;
//	}
//
//	public void setXzjbjzrq(Integer xzjbjzrq) {
//		this.xzjbjzrq = xzjbjzrq;
//	}
//
//	public Integer getXztbjzrq() {
//		return xztbjzrq;
//	}
//
//	public void setXztbjzrq(Integer xztbjzrq) {
//		this.xztbjzrq = xztbjzrq;
//	}
//
//	public Integer getGysfwxmbh() {
//		return gysfwxmbh;
//	}
//
//	public void setGysfwxmbh(Integer gysfwxmbh) {
//		this.gysfwxmbh = gysfwxmbh;
//	}
//
//	public Integer getGzk() {
//		return gzk;
//	}
//
//	public void setGzk(Integer gzk) {
//		this.gzk = gzk;
//	}
//
//	public Integer getSbk() {
//		return sbk;
//	}
//
//	public void setSbk(Integer sbk) {
//		this.sbk = sbk;
//	}
//
//	public Integer getYbk() {
//		return ybk;
//	}
//
//	public void setYbk(Integer ybk) {
//		this.ybk = ybk;
//	}
//
//	public Integer getSbssy() {
//		return sbssy;
//	}
//
//	public void setSbssy(Integer sbssy) {
//		this.sbssy = sbssy;
//	}
//
//	public Integer getSbczy() {
//		return sbczy;
//	}
//
//	public void setSbczy(Integer sbczy) {
//		this.sbczy = sbczy;
//	}
//
//	public Integer getQuitSsbStatus() {
//		return quitSsbStatus;
//	}
//
//	public void setQuitSsbStatus(Integer quitSsbStatus) {
//		this.quitSsbStatus = quitSsbStatus;
//	}
//
//	public Integer getCsadId() {
//		return csadId;
//	}
//
//	public void setCsadId(Integer csadId) {
//		this.csadId = csadId;
//	}
//
//
//	public void setCsadStr(String csadStr) {
//		this.csadStr = csadStr;
//	}
//
//	public Integer getCss() {
//		return css;
//	}
//
//	public void setCss(Integer css) {
//		this.css = css;
//	}
//
//	public void setCssStr(String cssName) {
//		this.cssStr = cssName;
//	}
//
//	public String getAttributeMonth() {
//		return attributeMonth;
//	}
//
//	public void setAttributeMonth(String attributeMonth) {
//		this.attributeMonth = attributeMonth;
//	}
//	
//	public String getCsadStr() {
//        /*Staff staff = ICloudDataUtil.getStaffById(csad);
//        return staff==null?"":staff.getStaffChineseName();*/
//        EsRoleInfo esRoleInfo = ICloudDataUtil.getEsRoleInfoById(csadId);
//        if ( esRoleInfo==null) {
//            return "";
//        }
//        Set<Staff> staffs =  esRoleInfo.getOwnStaffs();
//        if (staffs!=null && staffs.size()>0) {
//            for (Staff staff : staffs) {
//                if (staff==null ) {
//                    continue;
//                }
//                return staff.getStaffChineseName();
//            }
//        }
//        return "";
//    }
//
//    
//    public String getCssStr() {
//        /*Staff staff = ICloudDataUtil.getStaffById(csad);
//        return staff==null?"":staff.getStaffChineseName();*/
//        EsRoleInfo esRoleInfo = ICloudDataUtil.getEsRoleInfoById(css);
//        if ( esRoleInfo==null) {
//            return "";
//        }
//        Set<Staff> staffs =  esRoleInfo.getOwnStaffs();
//        if (staffs!=null && staffs.size()>0) {
//            for (Staff staff : staffs) {
//                if (staff==null ) {
//                    continue;
//                }
//                return staff.getStaffChineseName();
//            }
//        }
//        return "";
//    }
//
//	public String getChargeName() {
//		return chargeName;
//	}
//
//	public void setChargeName(String chargeName) {
//		this.chargeName = chargeName;
//	}
//	
//}
