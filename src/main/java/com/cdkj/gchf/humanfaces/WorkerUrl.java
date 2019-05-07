package com.cdkj.gchf.humanfaces;

public interface WorkerUrl {
    // 云端录入人员照片base64 Url
    String picAddBase64Url = AppConfig.getBaseUrl()
            + "/Api/Photo/StaffPhotoRegistrationBase";

    // 云端录入人员照片Url
    String picAddUrl = AppConfig.getBaseUrl()
            + "/Api/Photo/StaffPhotoRegistrationUrl";

    // 云端人员删除照片Url
    String picDelUrl = AppConfig.getBaseUrl() + "/Api/Photo/PhotoDeletion";

    // 云端照片查询UrL
    String picQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Photo/PersonnelPhotoQuery";
}
