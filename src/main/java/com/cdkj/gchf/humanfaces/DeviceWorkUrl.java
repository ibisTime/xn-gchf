package com.cdkj.gchf.humanfaces;

public interface DeviceWorkUrl {
    // 人员录入设备Url
    String workerAddUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelEntry";

    // 人员删除Url
    String workerDelUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelDeletion";

    // 人员查询Url
    String workerQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffInquiry";

    // 人员更新Url
    String workerUpdateUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelUpdate";

    // 人员授权
    String authorizationUrl = AppConfig.getBaseUrl()
            + "/Api/Device/EquipmentAP";

    // 人员搜索Url
    String workerSearchUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffSearch";

    // 人员授权查询Url
    String authorizationQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelAuthorizedDeviceQuery";

    // 人员授权取消Url
    String BatchEliminationOfPersonnelEquipmentUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/BatchEliminationOfPersonnelEquipment";

    // 人员清空url （清空人员和照片）
    String ClearWorkersUrl = AppConfig.getBaseUrl() + "/Api/Device/EquipmentEP";

}
