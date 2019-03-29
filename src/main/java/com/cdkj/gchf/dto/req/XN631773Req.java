package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631773Req {
    @NotBlank
    private String userId;

    @NotBlank
    private String projectCode;

    @NotEmpty
    private List<XN631770Req> dateList;

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

    public List<XN631770Req> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631770Req> dateList) {
        this.dateList = dateList;
    }

}
