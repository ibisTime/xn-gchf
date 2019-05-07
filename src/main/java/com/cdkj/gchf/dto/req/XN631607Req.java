package com.cdkj.gchf.dto.req;

public class XN631607Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 5295096972967707904L;

    private String projectCode;

    private String corpCode;

    // 用户id
    private String userId;

    // 上传状态
    private String uploadStatus;

    // 设备序列号
    private String deviceKey;

    // 工人姓名
    private String workerName;

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

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

}
