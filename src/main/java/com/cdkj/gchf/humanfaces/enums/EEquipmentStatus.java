package com.cdkj.gchf.humanfaces.enums;

/**
 * @ClassName:  EEquipmentStatus   
 * @Description:设备状态
 * @author: Old3
 * @date:   2019年5月7日 上午10:08:15     
 */
public enum EEquipmentStatus {
    //设备状态
    UNBINDING("1", "设备未绑定"),

    BINDING("2", "绑定中"),

    UNTYING("3", "解绑中"),

    UNUPDATING("4", "未同步"),

    UPDATING("5", "同步中"),

    UPDATED("6", "已同步"),

    BANED("7", "已禁用"),

    BANDING("8", "禁用中"),

    STARTING("9", "启用中");

    EEquipmentStatus(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
