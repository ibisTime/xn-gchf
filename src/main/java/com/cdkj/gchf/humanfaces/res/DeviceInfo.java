package com.cdkj.gchf.humanfaces.res;

public class DeviceInfo {
    /**
     * 设备id
     */
    private String id;

    /**
     * 设备所有者 id
     */
    private String userGuid;

    /**
     * 设备序列号id
     */
    private String deviceKey;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备状态
     */
    private String state;

    /**
     * 设备网络状态
     */
    private String status;

    /**
     * 设备客户端id
     */
    private String clientId;

    /**
     * 设备客户端 id（个推），通常用于消息推送
     */
    private String cid;

    /**
     * 设备应用版本号
     */
    private String versionNo;

    /**
     * 设备系统版本号
     */
    private String systemVersionNo;

    /**
     * 设备场景 gu id
     */
    private String sceneGuid;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 设备总识别次数
     */
    private Integer regNum;

    /**
     * 设备tag(加密)
     */
    private String tag;

    /**
     * 是否需要app升级
     */
    private Boolean needUpgradeApp;

    /**
     * 是否需要系统升级
     */
    private Boolean needUpgradeSystem;

    /**
     * 是否需要升级 app和系统
     */
    private Boolean needUpgrade;

    /**
     * 创建时间 时间戳
     */
    private long createTime;

    /**
     * 设备是否禁用
     */
    private Boolean expired;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getSystemVersionNo() {
        return systemVersionNo;
    }

    public void setSystemVersionNo(String systemVersionNo) {
        this.systemVersionNo = systemVersionNo;
    }

    public String getSceneGuid() {
        return sceneGuid;
    }

    public void setSceneGuid(String sceneGuid) {
        this.sceneGuid = sceneGuid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getNeedUpgradeApp() {
        return needUpgradeApp;
    }

    public void setNeedUpgradeApp(Boolean needUpgradeApp) {
        this.needUpgradeApp = needUpgradeApp;
    }

    public Boolean getNeedUpgradeSystem() {
        return needUpgradeSystem;
    }

    public void setNeedUpgradeSystem(Boolean needUpgradeSystem) {
        this.needUpgradeSystem = needUpgradeSystem;
    }

    public Boolean getNeedUpgrade() {
        return needUpgrade;
    }

    public void setNeedUpgrade(Boolean needUpgrade) {
        this.needUpgrade = needUpgrade;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((cid == null) ? 0 : cid.hashCode());
        result = prime * result
                + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result + (int) (createTime ^ (createTime >>> 32));
        result = prime * result
                + ((deviceKey == null) ? 0 : deviceKey.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((needUpgrade == null) ? 0 : needUpgrade.hashCode());
        result = prime * result
                + ((needUpgradeApp == null) ? 0 : needUpgradeApp.hashCode());
        result = prime * result + ((needUpgradeSystem == null) ? 0
                : needUpgradeSystem.hashCode());
        result = prime * result + ((regNum == null) ? 0 : regNum.hashCode());
        result = prime * result
                + ((sceneGuid == null) ? 0 : sceneGuid.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result
                + ((systemVersionNo == null) ? 0 : systemVersionNo.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result
                + ((userGuid == null) ? 0 : userGuid.hashCode());
        result = prime * result
                + ((versionNo == null) ? 0 : versionNo.hashCode());
        return result;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeviceInfo other = (DeviceInfo) obj;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (cid == null) {
            if (other.cid != null)
                return false;
        } else if (!cid.equals(other.cid))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (createTime != other.createTime)
            return false;
        if (deviceKey == null) {
            if (other.deviceKey != null)
                return false;
        } else if (!deviceKey.equals(other.deviceKey))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (needUpgrade == null) {
            if (other.needUpgrade != null)
                return false;
        } else if (!needUpgrade.equals(other.needUpgrade))
            return false;
        if (needUpgradeApp == null) {
            if (other.needUpgradeApp != null)
                return false;
        } else if (!needUpgradeApp.equals(other.needUpgradeApp))
            return false;
        if (needUpgradeSystem == null) {
            if (other.needUpgradeSystem != null)
                return false;
        } else if (!needUpgradeSystem.equals(other.needUpgradeSystem))
            return false;
        if (regNum == null) {
            if (other.regNum != null)
                return false;
        } else if (!regNum.equals(other.regNum))
            return false;
        if (sceneGuid == null) {
            if (other.sceneGuid != null)
                return false;
        } else if (!sceneGuid.equals(other.sceneGuid))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (systemVersionNo == null) {
            if (other.systemVersionNo != null)
                return false;
        } else if (!systemVersionNo.equals(other.systemVersionNo))
            return false;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        if (userGuid == null) {
            if (other.userGuid != null)
                return false;
        } else if (!userGuid.equals(other.userGuid))
            return false;
        if (versionNo == null) {
            if (other.versionNo != null)
                return false;
        } else if (!versionNo.equals(other.versionNo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceInfo [id=" + id + ", userGuid=" + userGuid
                + ", deviceKey=" + deviceKey + ", name=" + name + ", state="
                + state + ", status=" + status + ", clientId=" + clientId
                + ", cid=" + cid + ", versionNo=" + versionNo
                + ", systemVersionNo=" + systemVersionNo + ", sceneGuid="
                + sceneGuid + ", appId=" + appId + ", regNum=" + regNum
                + ", tag=" + tag + ", needUpgradeApp=" + needUpgradeApp
                + ", needUpgradeSystem=" + needUpgradeSystem + ", needUpgrade="
                + needUpgrade + ", createTime=" + createTime + "]";
    }

}
