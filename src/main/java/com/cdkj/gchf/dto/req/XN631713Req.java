package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631713Req {
    @NotBlank(message = "用户id")
    private String updater;

    @NotEmpty
    private List<XN631713ReqData> dateList;

    // 项目编号
    @NotBlank
    private String projectCode;

    @NotBlank
    private String teamSysNo;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<XN631713ReqData> getDateList() {
        return dateList;
    }

    public void setDateList(List<XN631713ReqData> dateList) {
        this.dateList = dateList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

}
