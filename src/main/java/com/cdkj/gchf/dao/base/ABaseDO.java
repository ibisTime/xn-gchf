/**
 * @Title ABaseDO.java 
 * @Package com.ibis.pz.base 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:32:54 
 * @version V1.0   
 */
package com.cdkj.gchf.dao.base;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:32:54 
 * @history:
 */
public abstract class ABaseDO implements Serializable {

    private static final long serialVersionUID = -1529928645166513824L;

    private String order = null;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    public void setOrder(String fieldName, boolean ascending) {
        Order orderInfo = ascending ? Order.asc(fieldName)
                : Order.desc(fieldName);
        this.order = orderInfo.toSqlString();
    }

    public void setOrder(String fieldName, String direction) {
        Order orderInfo = null;
        if (StringUtils.isBlank(direction)
                || "desc".equalsIgnoreCase(direction)) {
            orderInfo = Order.desc(fieldName);
        } else {
            orderInfo = Order.asc(fieldName);
        }
        this.order = orderInfo.toSqlString();
    }

    public String getOrder() {
        return this.order;
    }

    public String generateId() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-".intern(),
            StringUtils.EMPTY);
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result
                + ((pageIndex == null) ? 0 : pageIndex.hashCode());
        result = prime * result
                + ((pageSize == null) ? 0 : pageSize.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ABaseDO other = (ABaseDO) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (pageIndex == null) {
            if (other.pageIndex != null)
                return false;
        } else if (!pageIndex.equals(other.pageIndex))
            return false;
        if (pageSize == null) {
            if (other.pageSize != null)
                return false;
        } else if (!pageSize.equals(other.pageSize))
            return false;
        return true;
    }

}
