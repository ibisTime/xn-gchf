package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;
import java.util.Date;

/**
 * 摄像机
 *
 * @author: jiafr
 * @since: 2019-06-21 00:40:51
 * @history:
 */
public class ProjectCamera extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 摄像头名称
    private String cameraName;

    // 摄像机账号
    private String cameraUsername;

    // 摄像机密码
    private String cameraPassword;

    // 摄像头ip
    private String cameraIp;

    // 摄像机ip端口
    private String cameraIpPort;

    // 摄像头频道
    private String cameraChannel;

    // 摄像头码流
    private String cameraBitStream;

    // cvr主机账号
    private String cvrHostUsername;

    // cvr主机密码
    private String cvrHostPassword;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraUsername(String cameraUsername) {
        this.cameraUsername = cameraUsername;
    }

    public String getCameraUsername() {
        return cameraUsername;
    }

    public void setCameraPassword(String cameraPassword) {
        this.cameraPassword = cameraPassword;
    }

    public String getCameraPassword() {
        return cameraPassword;
    }

    public void setCameraIp(String cameraIp) {
        this.cameraIp = cameraIp;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public void setCameraIpPort(String cameraIpPort) {
        this.cameraIpPort = cameraIpPort;
    }

    public String getCameraIpPort() {
        return cameraIpPort;
    }

    public void setCameraChannel(String cameraChannel) {
        this.cameraChannel = cameraChannel;
    }

    public String getCameraChannel() {
        return cameraChannel;
    }

    public void setCameraBitStream(String cameraBitStream) {
        this.cameraBitStream = cameraBitStream;
    }

    public String getCameraBitStream() {
        return cameraBitStream;
    }

    public void setCvrHostUsername(String cvrHostUsername) {
        this.cvrHostUsername = cvrHostUsername;
    }

    public String getCvrHostUsername() {
        return cvrHostUsername;
    }

    public void setCvrHostPassword(String cvrHostPassword) {
        this.cvrHostPassword = cvrHostPassword;
    }

    public String getCvrHostPassword() {
        return cvrHostPassword;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}