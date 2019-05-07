package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 考勤设备信息
* @author: jiafr 
* @since: 2019-05-02 11:35:43
* @history:
*/
public class EquipmentWorker extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 人脸识别设备编号
    private String deviceCode;

    // 人脸识别设备序列号
    private String deviceKey;

    // 人脸识别设备名称
    private String deviceName;

    // 项目人员编号
    private String workerCode;

    // 项目人员姓名
    private String workerName;

    // 班组编号
    private String teamCode;

    // 班组名称
    private String teamName;

    // 员工身份证件号
    private String idCardNumber;

    // 状态
    private String status;

    // 每日允许进入
    private String passTimes;

    // 添加时间
    private String createTime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPassTimes(String passTimes) {
        this.passTimes = passTimes;
    }

    public String getPassTimes() {
        return passTimes;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

}
