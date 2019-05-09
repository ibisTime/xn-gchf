package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 人员进出记录
* @author: jiafr 
* @since: 2019-05-09 14:53:25
* @history:
*/
public class WorkerEntryExitRecord extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 设备编号
    private String deviceCode;

    // 设备序列号
    private String deviceKey;

    // 设备名称
    private String deviceName;

    // 班组编号
    private String teamCode;

    // 班组名称
    private String teamName;

    // 员工编号
    private String workerCode;

    // 员工名称
    private String workerName;

    // 证件号码
    private String idcardNumber;

    // 考勤时间
    private Date date;

    // 进出方向
    private String direction;

    // 刷卡近照
    private String image;

    // 识别模式(1:刷脸，2:刷卡，3:双重认证， 4:人证比对)
    private String recMode;

    // 识别出的人员类型(0:时间段内，1:时间段外，2:陌生人)
    private String attendType;

    // 删除状态
    private String deleteStatus;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setRecMode(String recMode) {
        this.recMode = recMode;
    }

    public String getRecMode() {
        return recMode;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

}
