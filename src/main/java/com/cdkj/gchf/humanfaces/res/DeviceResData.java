package com.cdkj.gchf.humanfaces.res;

public class DeviceResData {
    // 应用 Id
    private String appId;

    // 设备序列号
    private String deviceKey;

    // 设备名
    private String name;

    // 设备场景 guid
    private String sceneGuid;
    // 设备 tag（加密）

    private String tag;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getSceneGuid() {
        return sceneGuid;
    }

    public void setSceneGuid(String sceneGuid) {
        this.sceneGuid = sceneGuid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
