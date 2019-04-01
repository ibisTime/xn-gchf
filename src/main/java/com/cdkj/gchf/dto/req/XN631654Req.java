package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631654Req {
    // userId
    private String userId;

    // 上传编号
    private List<String> dataList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

}
