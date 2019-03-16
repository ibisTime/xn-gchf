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
}
