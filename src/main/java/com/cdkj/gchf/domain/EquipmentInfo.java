package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 考勤设备信息
* @author: jiafr 
* @since: 2019-05-02 11:32:06
* @history:
*/
public class EquipmentInfo extends ABaseDO {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -2269786678705277139L;

    // 编号
    private String code;

    // 人脸识别设备序列号
    private String deviceKey;

    // 设备名称
    private String name;

    // 设备标签
    private String tag;

    // 加密tag标签
    private String tagEncreapt;

    // 设备场景guid
    private String sceneGuid;

    // 设备状态
    private String state;

    // 设备网络状态
    private String status;

    // 设备客户端id
    private String clientId;

    // 设备客户端 id（个推）
    private String cId;

    // 设备应用版本号
    private String versionNo;

    // 设备系统版本号
    private String systemVersionNo;

    // 设备总识别次数
    private Integer regNum;

    // 是否需要升级app
    private Integer needUpgradeApp;

    // 是否需要升级app和系统
    private Integer needUpgrade;

    // 设备添加时间
    private Date createTime;

    // 人脸识别设备是否禁用
    private Integer expired;

    // 人脸识别设备状态
    private String deviceStatus;

    // 项目编码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 考勤方向
    private String direction;

    // 用户id
    private String userId;

    /****DB Properties****/
    private String deviceName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagEncreapt() {
        return tagEncreapt;
    }

    public void setTagEncreapt(String tagEncreapt) {
        this.tagEncreapt = tagEncreapt;
    }

    public String getSceneGuid() {
        return sceneGuid;
    }

    public void setSceneGuid(String sceneGuid) {
        this.sceneGuid = sceneGuid;
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

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
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

    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    public Integer getNeedUpgradeApp() {
        return needUpgradeApp;
    }

    public void setNeedUpgradeApp(Integer needUpgradeApp) {
        this.needUpgradeApp = needUpgradeApp;
    }

    public Integer getNeedUpgrade() {
        return needUpgrade;
    }

    public void setNeedUpgrade(Integer needUpgrade) {
        this.needUpgrade = needUpgrade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
