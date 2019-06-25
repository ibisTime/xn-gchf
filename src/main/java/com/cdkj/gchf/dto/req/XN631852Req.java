package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-06-24 17:49
 */
public class XN631852Req {

    /**
     * cameraName : cameraUsername : cameraPassword : cameraIp : cameraIpPort : cameraChannel :
     * cameraBitStream : cvrHostUsername : cvrHostPassword : userId :
     */

    @NotBlank
    private String cameraName;
    @NotBlank
    private String cameraUsername;
    @NotBlank
    private String cameraPassword;
    @NotBlank
    private String cameraIp;
    private String cameraIpPort;
    @NotBlank
    private String cameraChannel;
    @NotBlank
    private String cameraBitStream;
    @NotBlank
    private String cvrHostUsername;
    @NotBlank
    private String cvrHostPassword;
    @NotBlank
    private String userId;
    @NotBlank
    private String code;

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraUsername() {
        return cameraUsername;
    }

    public void setCameraUsername(String cameraUsername) {
        this.cameraUsername = cameraUsername;
    }

    public String getCameraPassword() {
        return cameraPassword;
    }

    public void setCameraPassword(String cameraPassword) {
        this.cameraPassword = cameraPassword;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }

    public String getCameraIpPort() {
        return cameraIpPort;
    }

    public void setCameraIpPort(String cameraIpPort) {
        this.cameraIpPort = cameraIpPort;
    }

    public String getCameraChannel() {
        return cameraChannel;
    }

    public void setCameraChannel(String cameraChannel) {
        this.cameraChannel = cameraChannel;
    }

    public String getCameraBitStream() {
        return cameraBitStream;
    }

    public void setCameraBitStream(String cameraBitStream) {
        this.cameraBitStream = cameraBitStream;
    }

    public String getCvrHostUsername() {
        return cvrHostUsername;
    }

    public void setCvrHostUsername(String cvrHostUsername) {
        this.cvrHostUsername = cvrHostUsername;
    }

    public String getCvrHostPassword() {
        return cvrHostPassword;
    }

    public void setCvrHostPassword(String cvrHostPassword) {
        this.cvrHostPassword = cvrHostPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

    
    