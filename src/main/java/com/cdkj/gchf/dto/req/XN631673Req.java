package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631673Req {
    @NotBlank
    private String userId;

    @NotBlank
    private String projectCode;

    @NotEmpty
    private List<XN631673ReqData> workContractList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<XN631673ReqData> getWorkContractList() {
        return workContractList;
    }

    public void setWorkContractList(List<XN631673ReqData> workContractList) {
        this.workContractList = workContractList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
