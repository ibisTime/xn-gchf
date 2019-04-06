package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631812Req {
    @NotBlank
    private String userId;

    @NotBlank
    private String projectCode;

    @NotEmpty
    private List<XN631812ReqData> dateList;

    private String payMonth;

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

    public List<XN631812ReqData> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631812ReqData> dateList) {
        this.dateList = dateList;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

}
