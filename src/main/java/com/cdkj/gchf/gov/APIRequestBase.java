package com.cdkj.gchf.gov;

import java.io.Serializable;

/**
 * Created by jim.z.hu on 2018/7/11.
 */
public class APIRequestBase implements Serializable {

    private static final long serialVersionUID = -3301651208526425250L;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

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
