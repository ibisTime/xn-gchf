package com.cdkj.gchf.humanfaces;

/**
 * @author old3
 */
public interface WorkerUrl {
    /**云端录入人员照片base64 Url
     */
    String PIC_ADD_BASE64_URL = AppConfig.getBaseUrl()
            + "/Api/Photo/StaffPhotoRegistrationBase";

    /**云端录入人员照片Url
     */
    String PIC_ADD_URL = AppConfig.getBaseUrl()
            + "/Api/Photo/StaffPhotoRegistrationUrl";

    /**云端人员删除照片Url
     */
    String PIC_DEL_URL = AppConfig.getBaseUrl() + "/Api/Photo/PhotoDeletion";

    /**云端照片查询UrL
     */
    String PIC_QUERY_URL = AppConfig.getBaseUrl()
            + "/Api/Photo/PersonnelPhotoQuery";
}
