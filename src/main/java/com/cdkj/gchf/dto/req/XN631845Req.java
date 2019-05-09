package com.cdkj.gchf.dto.req;

public class XN631845Req extends APageReq {

    private static final long serialVersionUID = -2425673826710669835L;

    // 设备序列号
    private String deviceKey;

    // 员工名称
    private String workerName;

    // 进出方向
    private String direction;

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
