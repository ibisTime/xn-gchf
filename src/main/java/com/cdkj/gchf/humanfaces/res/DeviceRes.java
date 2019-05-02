package com.cdkj.gchf.humanfaces.res;

/**
 * 
 * @ClassName:  DeviceRes   
 * @Description:
 * @author: Old3
 * @date:   2019年4月29日 下午1:55:26     
 * @Copyright:
 */
public class DeviceRes {
    // 应用 Id
    private String appId;

    private String code;

    private DeviceResData data;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DeviceResData getData() {
        return data;
    }

    public void setData(DeviceResData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeviceRes [appId=" + appId + ", code=" + code + ", data=" + data
                + "]";
    }

}
