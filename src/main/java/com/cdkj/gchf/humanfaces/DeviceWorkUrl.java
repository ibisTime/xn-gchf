package com.cdkj.gchf.humanfaces;

public interface DeviceWorkUrl {
    /**
     *人员录入设备Url
     */
    String WORKER_ADD_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelEntry";

    /**
     *人员删除Url
     */
    String WORKER_DEL_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelDeletion";

    /**
     *人员查询Url
     */
    String WORKER_QUERY_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffInquiry";

    /**
     *人员更新Url
     */
    String WORKER_UPDATE_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelUpdate";

    /**
     *人员授权
     */
    String AUTHORIZATION_URL = AppConfig.getBaseUrl()
            + "/Api/Device/EquipmentAP";

    /**
     *人员搜索Url
     */
    String WORKER_SEARCH_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffSearch";

    /**
     *人员授权查询Url
     */
    String AUTHORIZATION_QUERY_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelAuthorizedDeviceQuery";

    /**
     *人员授权取消Url
     */
    String BATCH_ELIMINATION_OF_PERSONNEL_EQUIPMENT_URL = AppConfig.getBaseUrl()
            + "/Api/Personnel/BatchEliminationOfPersonnelEquipment";

    /**
     *人员清空url （清空人员和照片）
     */
    String CLEAR_WORKERS_URL = AppConfig.getBaseUrl() + "/Api/Device/EquipmentEP";

}
