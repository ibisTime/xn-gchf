package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631733Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<XN631733ReqData> dateList;

    @NotBlank
    private String projectCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<XN631733ReqData> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631733ReqData> dateList) {
        this.dateList = dateList;
    }

}
