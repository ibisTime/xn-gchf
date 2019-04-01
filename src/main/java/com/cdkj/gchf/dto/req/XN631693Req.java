package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.api.impl.XN631693ReqData;

public class XN631693Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<XN631693ReqData> workerList;

    // 项目编号
    @NotBlank
    private String projectcode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public List<XN631693ReqData> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<XN631693ReqData> workerList) {
        this.workerList = workerList;
    }

}
