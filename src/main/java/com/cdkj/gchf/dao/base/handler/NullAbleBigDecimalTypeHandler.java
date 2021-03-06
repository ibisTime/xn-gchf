/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-7
 * <修改描述:>
 */
package com.cdkj.gchf.dao.base.handler;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * 修改默认的BigDecimal映射处理器 替换系统中的TypeHandler <功能详细描述>
 * 
 * @author brady
 * @version [版本号, 2012-12-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@MappedTypes(value = { BigDecimal.class })
public class NullAbleBigDecimalTypeHandler extends BaseTypeHandler<BigDecimal> {

    /**
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, BigDecimal parameter,
            JdbcType jdbcType) throws SQLException {
        if (parameter == null
                && (jdbcType == null || JdbcType.OTHER == jdbcType)) {
            ps.setNull(i, JdbcUtils.getSqlTypeByJavaType(BigDecimal.class));
        } else {
            super.setParameter(ps, i, parameter, jdbcType);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            BigDecimal parameter, JdbcType jdbcType) throws SQLException {
        ps.setBigDecimal(i, parameter);
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return rs.getBigDecimal(columnName);
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return rs.getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return cs.getBigDecimal(columnIndex);
    }

}
