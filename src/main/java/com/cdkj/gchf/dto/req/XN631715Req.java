package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631715Req {
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 进出方向
    @NotBlank
    private String direction;

    @NotBlank(message = "请选择班组")
    private String teamSysNo;

    // 开始时间
    @NotBlank
    private String startDatetime;

    // 结束时间
    @NotBlank
    private String endDatetime;

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

}
