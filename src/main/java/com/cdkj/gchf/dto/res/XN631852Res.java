package com.cdkj.gchf.dto.res;

/**
 * @author : old3
 * @since : 2019-06-21 2:07
 */
public class XN631852Res {

    private String cameraName;

    private String rpstpStreamAddress;

    private String uuid;

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getRpstpStreamAddress() {
        return rpstpStreamAddress;
    }

    public void setRpstpStreamAddress(String rpstpStreamAddress) {
        this.rpstpStreamAddress = rpstpStreamAddress;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public XN631852Res(String cameraName, String rpstpStreamAddress, String uuid) {
        this.cameraName = cameraName;
        this.rpstpStreamAddress = rpstpStreamAddress;
        this.uuid = uuid;
    }
}
