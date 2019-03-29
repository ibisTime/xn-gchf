package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631653Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<XN631653ReqData> dataList;

    @NotBlank
    private String projectCode;

    @NotBlank
    private String corpCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<XN631653ReqData> getDataList() {
        return dataList;
    }

    public void setDataList(List<XN631653ReqData> dataList) {
        this.dataList = dataList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

}
