package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631830Req {
    // 考勤设备编号
    private String equipmentCode;

    // 项目人员编号列表
    private List<String> workerList;

    // 用户id
    private String userId;

    // 开始时间
    private String startTime;

    // 结束时间
    private String endTime;

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public List<String> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<String> workerList) {
        this.workerList = workerList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
