package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author old3
 * @title: XN631696Req
 * @description: TODO
 * @date 2019-05-20 10:51
 */
public class XN631696Req {

    /**
     * workerCode : 实名制code
     * teamSysNo : 班组编号
     * workType : 工人类型
     * workRole : 工种
     * hasBuyInsurance : 是否购买保险
     * userId : 用户id
     */
    @NotBlank
    private String workerCode;
    @NotBlank
    private String teamSysNo;
    @NotBlank
    private String workType;
    private String isTeamLeader;
    @NotBlank
    private String workRole;
    private String hasBuyInsurance;
    @NotBlank
    private String userId;

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(String isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkRole() {
        return workRole;
    }

    public void setWorkRole(String workRole) {
        this.workRole = workRole;
    }

    public String getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(String hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
