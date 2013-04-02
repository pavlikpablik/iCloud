package com.manpowergroup.cn.icloud.socialbenefit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjust;
import com.manpowergroup.cn.icloud.socialbenefit.entity.SsbAjustDetail;

import com.manpowergroup.cn.icloud.base.entity.City;

@Repository
public class SsbAdjustDaoImpl implements SsbAdjustDao {
	
        @Autowired
        @Qualifier(value="sqlserver")
        private SqlSessionTemplate sqlSession;  
    
	   /**
	    * 保存调整数据
	    * @param saveSsbAdjustment
	    * @throws SQLException 
	    */
       @Override
	   public void saveSsbAdjustment(List <SsbAjust> ssbAdjustList) throws SQLException{
	       Connection conn = sqlSession.getConnection();
	       PreparedStatement stmtHeader = null;
	       PreparedStatement stmtDetail = null;
	       ResultSet rs = null;
	       
	       int i = 0;int id = 0;
	       try {
	           conn.setAutoCommit(false);
	           stmtHeader = conn.prepareStatement(
	                   "  INSERT  INTO dbo.T_CHARGE_AJUST"+
	                   "  ( CAND_ID ,TYPE,RULE_ID ,MONTH_ATTRIBUTE ,MONTH_FEE ,"+
	                    "   CREATE_DATE  ,STATUS ,REMARK "+  
	                     
	                       ") VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
	           
	           stmtDetail = conn.prepareStatement(
                       " INSERT  INTO dbo.T_CHARGE_AJUST_DETAIL"+
                       "  ( AJUST_ID , "+
                       " ITEM_ID , "+
                       " CREATE_DATE , "+
                       " REMARK , "+
                       " COMPANY_PAY , "+
                       " COMPANY_AMOUNT , "+
                       " PERSON_PAY , "+
                       " PERSON_AMOUNT , "+
                       " TYPE "+
                       " )  VALUES(?,?,?,?,?,?,?,?,?)");
	           for(SsbAjust header:ssbAdjustList){
	               if(header==null) continue;
    	           stmtHeader.setInt(1, header.getCandidateId());
    	           stmtHeader.setInt(2, header.getType());
    	           stmtHeader.setObject(3, header.getRuleId());  
    	           stmtHeader.setString(4, header.getMonthAttribute());
    	           stmtHeader.setString(5, header.getMonthFee());
    //	           stmtHeader.setInt(6, header.getCreateBy());
    	           stmtHeader.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));  
    	           stmtHeader.setInt(7, header.getStatus());
    	           stmtHeader.setObject(8, header.getRemark());
    	           stmtHeader.executeUpdate();
	           //id = stmtHeader.RETURN_GENERATED_KEYS; 
    	            rs = stmtHeader.getGeneratedKeys(); // 这里返回ResultSet ，rs里就是我们要的主键
    	           if (rs.next()) {
    	               id = rs.getInt(1);
    	           }
	           if (header.getSsbDetailList()!=null) {
	               for (SsbAjustDetail pp :header.getSsbDetailList()) {
	                   stmtDetail.setInt(1, id);
	                   stmtDetail.setInt(2, pp.getItemId());
	                   stmtDetail.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));  
	                   stmtDetail.setObject(4, pp.getRemark());
                       stmtDetail.setBigDecimal(5, pp.getCompanyPay());
                       stmtDetail.setBigDecimal(6,pp.getCompanyAmount());
                       stmtDetail.setBigDecimal(7,pp.getPersonPay());
                       stmtDetail.setBigDecimal(8,pp.getPersonAmount());
                       stmtDetail.setInt(9,pp.getType());
	                   stmtDetail.addBatch();
	               }
	               stmtDetail.executeBatch();
	           }
	           }
	           conn.commit();
	           stmtHeader.close();
	           stmtDetail.close();
	           conn.setAutoCommit(true);
	           conn.close();
	       } catch (SQLException e) {
	            conn.rollback();
	           e.printStackTrace();
	       } finally {
	           if (rs != null) try {rs.close();} catch (Exception ignore) {}
	           if (stmtHeader != null) try {stmtHeader.close();} catch (Exception ignore) {}
	           if (stmtDetail != null)   try {stmtDetail.close();} catch (Exception ignore) {}
	           if (conn != null) try {conn.close();} catch (Exception ignore) {}
	       }
	   }
}
