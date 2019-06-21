package com.cdkj.gchf.dto.res;

import java.util.List;
import java.util.Map;

/**
 * @author old3
 * @title: XN631618Res
 * @description: Led java程序查询res
 * @date 2019-05-21 16:43
 */
public class XN631618Res {

    /**
     * 班组信息
     */
    private List<Map> data;
    /**
     * 项目进出记录人数
     */
    private long totalCount;

    public List<Map> getData() {
        return data;
    }

    public void setData(List<Map> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
