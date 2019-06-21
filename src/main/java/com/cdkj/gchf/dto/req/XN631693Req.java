package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631693Req {
    @NotBlank
    private String updater;

    @NotEmpty
    private List<XN631693ReqData> workerList;

    // 项目编号
    @NotBlank
    private String projectCode;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<XN631693ReqData> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<XN631693ReqData> workerList) {
        this.workerList = workerList;
    }

}
