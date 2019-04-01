package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631713Req {
    @NotBlank(message = "用户id")
    private String userId;

    @NotEmpty
    private List<XN631713ReqData> workerAttendanceList;

    // 项目编号
    @NotBlank
    private String projectCode;

    @NotBlank
    private String teamSysNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<XN631713ReqData> getWorkerAttendanceList() {
        return workerAttendanceList;
    }

    public void setWorkerAttendanceList(
            List<XN631713ReqData> workerAttendanceList) {
        this.workerAttendanceList = workerAttendanceList;
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
