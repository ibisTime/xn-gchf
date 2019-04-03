package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631653Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<XN631653ReqData> dateList;

    @NotBlank
    private String projectCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<XN631653ReqData> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631653ReqData> dateList) {
        this.dateList = dateList;
    }

    public void setDataList(List<XN631653ReqData> dataList) {
        this.dateList = dataList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
