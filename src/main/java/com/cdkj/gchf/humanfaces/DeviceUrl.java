package com.cdkj.gchf.humanfaces;

public interface DeviceUrl {
    // 添加设备Url
    String createUrl = AppConfig.getBaseUrl() + "/Api/Device/DeviceCreation";

    // 更新设备信息Url
    String updateUrl = AppConfig.getBaseUrl() + "/Api/Device/EquipmentUpdate";

    // 查询设备信息Url
    String queryUrl = AppConfig.getBaseUrl() + "/Api/Device/DeviceQuery";

    // 同步设备信息Url
    String updateCloudUrl = AppConfig.getBaseUrl() + "/Api/Device/Equipment";

    // 删除设备信息
    String delDeviceUrl = AppConfig.getBaseUrl() + "/Api/Device/DeviceDeletion";

    // 设备销权人员Url
    String trancateUrl = AppConfig.getBaseUrl() + "/Api/Device/EquipmentEP";

    // 设备禁用Url
    String banDeviceUrl = AppConfig.getBaseUrl() + "/Api/Device/DeviceDisable";

    // 设备启动Url
    String enableDeviceUrl = AppConfig.getBaseUrl()
            + "/Api/Device/DeviceEnabled";
}
