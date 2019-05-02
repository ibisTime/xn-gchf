package com.cdkj.gchf.humanfaces.res;

//人员响应
public class DeviceWorkerRes {
    // 响应code
    private String code;

    // 返回消息
    private String msg;

    // 结果
    private String result;

    // 人员信息
    private DeviceWorkerInfo data;

    public String getCode() {
        return code;
    }

    public class DeviceWorkerInfo {
        // 人员Guid
        private String guid;

        // 人员所有者（客户）guid
        private String userGuid;

        // 创建时间
        private String createTime;

        // 应用 Id
        private String appId;

        // 手机号
        private String phone;

        /// 卡号（IC 卡／身份证）
        private String idNo;

        // 人员标记（加密）
        private String tag;

        // 人员类型（由客户自行定义，支持正整数，0 没有意义
        private String type;

        // 人员姓名
        private String name;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

    public DeviceWorkerInfo getData() {
        return data;
    }

    public void setData(DeviceWorkerInfo data) {
        this.data = data;
    }

}
