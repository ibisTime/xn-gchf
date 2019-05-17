package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631835Req extends APageReq {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -2425673826710669835L;

    // 设备人员状态
    private String state;

    // 用户id
    @NotBlank
    private String userId;

    // 工人姓名
    private String workerName;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

}
