package com.cdkj.gchf.humanfaces.res;

public class DeviceWorkerPicRes {
    // 响应code
    private String code;

    // 返回消息
    private String msg;

    // 结果
    private String result;

    // 人员信息
    private DeviceWorkerPicResData data;

    // 是否成功
    private String success;

    public class DeviceWorkerPicResData {
        private String guid;

        private String userGuid;

        private String appId;

        private String personGuid;

        private Integer type;

        private Long createTime;

        private String faceUrl;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getUserGuid() {
            return userGuid;
        }

        public void setUserGuid(String userGuid) {
            this.userGuid = userGuid;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPersonGuid() {
            return personGuid;
        }

        public void setPersonGuid(String personGuid) {
            this.personGuid = personGuid;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DeviceWorkerPicResData getData() {
        return data;
    }

    public void setData(DeviceWorkerPicResData data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
