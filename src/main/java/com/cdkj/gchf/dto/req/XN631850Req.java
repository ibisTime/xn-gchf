package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : old3
 * @since : 2019-06-21 0:54
 */
public class XN631850Req {

    /**
     * cameraName : 摄像头名称 cameraUsername : 摄像头账号 camerPassword : 摄像头密码 cameraIp : 摄像头ip cameraIpPort
     * : 摄像头ip端口 cameraChannel : 摄像头频道 cameraBitStream : 摄像头码流 cvrHostUsername : cvr主机地址
     * cvrHostPassword : cvr主机密码 userId : 用户id
     */
    @NotBlank(message = "摄像头名称不能为空")
    private String cameraName;
    @NotBlank(message = "摄像头账号不能为空")
    private String cameraUsername;
    @NotBlank(message = "摄像头密码不能为空")
    private String cameraPassword;
    @NotBlank(message = "摄像头ip必填")
    private String cameraIp;

    private String cameraIpPort;
    @NotBlank(message = "摄像头频道必填")
    private String cameraChannel;
    private String cameraBitStream;
    private String cvrHostUsername;
    private String cvrHostPassword;
    @NotBlank(message = "用户id不能为空")
    private String userId;

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
}

    
    