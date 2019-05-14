package com.cdkj.gchf.humanfaces;

public interface DeviceUrl {
    /**
     *添加设备Url
     */
    String CREATE_URL = AppConfig.getBaseUrl() + "/Api/Device/DeviceCreation";

    /**
     *更新设备信息Url
     */
    String UPDATE_URL = AppConfig.getBaseUrl() + "/Api/Device/EquipmentUpdate";
    /**
     *查询设备信息Url
     */
    String QUERY_URL = AppConfig.getBaseUrl() + "/Api/Device/DeviceQuery";

    /**
     *同步设备信息Url
     */
    String UPDATE_CLOUD_URL = AppConfig.getBaseUrl() + "/Api/Device/Equipment";

    /**
     * 删除设备信息
     */
    String DEL_DEVICE_URL = AppConfig.getBaseUrl() + "/Api/Device/DeviceDeletion";

    /**
     *设备销权人员Url
     */
    String TRANCATE_URL = AppConfig.getBaseUrl() + "/Api/Device/EquipmentEP";

    /**
     *设备禁用Url
     */
    String BAN_DEVICE_URL = AppConfig.getBaseUrl() + "/Api/Device/DeviceDisable";

    /**
     *设备启动Url
     */
    String ENABLE_DEVICE_URL = AppConfig.getBaseUrl()
            + "/Api/Device/DeviceEnabled";
}
