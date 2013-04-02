package com.manpowergroup.cn.core.orm.myBatis;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * BooleanTypeHandler类, 处理完成char(1)与Boolean类型的转换
 */
public class BooleanTypeHandler implements TypeHandler<Boolean> 
{
    private static final String YES = "Y";  
    private static final String NO = "N";  
    
	private Boolean valueOf(String value) throws SQLException {
		if (YES.equalsIgnoreCase(value))
			return Boolean.TRUE;
		else if (NO.equalsIgnoreCase(value)) {
			return Boolean.FALSE;
		}
		return null;
	}

    @Override
    public Boolean getResult(ResultSet resultSet, String name) throws SQLException
    {
        return valueOf(resultSet.getString(name));
    }

    @Override
    public Boolean getResult(CallableStatement statement, int position) throws SQLException
    {
        return valueOf(statement.getString(position));  
    }
 
    @Override
    public void setParameter(PreparedStatement statement, int position, Boolean value, JdbcType jdbcType) throws SQLException
    {
        statement.setString(position, value.booleanValue() ? YES : NO);  
    }

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		 return valueOf(rs.getString(columnIndex));
	}

}