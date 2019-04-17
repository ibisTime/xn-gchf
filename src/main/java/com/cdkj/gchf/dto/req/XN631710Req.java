package com.cdkj.gchf.dto.req;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631710Req {

    // userId
    @NotBlank
    private String userId;

    // 平台为项目分配的接入编号
    @NotBlank
    private String projectCode;

    // 本地人员考勤
    @NotBlank
    private String teamSysNo;

    // 刷卡时间
    private String date;

    // 刷卡进出方向
    @NotBlank
    private String direction;

    // 通道的名称
    private String channel;

    // 通行方式
    private String attendType;

    // WGS84 经度
    private BigDecimal lng;

    // WGS84 纬度
    private BigDecimal lat;

    // 员工编号
    private String workerCode;

    // 出工状态
    private String status;

    // 生成时间
    private Date createDatetime;

    // 上班时间
    private Date startDatetime;

    // 下班时间
    private Date endDatetime;

    // 结算时间
    private Date settleDatetime;

    // 人脸识别准确率
    private BigDecimal sim;

    // 考勤机编号
    private String terminalCode;

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

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getSettleDatetime() {
        return settleDatetime;
    }

    public void setSettleDatetime(Date settleDatetime) {
        this.settleDatetime = settleDatetime;
    }

    public BigDecimal getSim() {
        return sim;
    }

    public void setSim(BigDecimal sim) {
        this.sim = sim;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

}
