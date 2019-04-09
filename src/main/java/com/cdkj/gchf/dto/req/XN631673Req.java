package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631673Req {
    @NotBlank
    private String updater;

    @NotBlank
    private String projectCode;

    @NotEmpty
    private List<XN631673ReqData> dateList;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<XN631673ReqData> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631673ReqData> dateList) {
        this.dateList = dateList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
