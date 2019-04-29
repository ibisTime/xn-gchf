package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 
 * @ClassName:  WorkerPicture   
 * @Description:人员照片相关
 * @author: Old3
 * @date:   2019年4月29日 下午5:20:14     
 */
public class WorkerPicture {
    // 云端录入人员照片Url
    private String picAddUrl = AppConfig.getBaseUrl()
            + "/Api/Photo/StaffPhotoRegistrationBase";

    // 云端人员删除照片Url
    private String picDelUrl = AppConfig.getBaseUrl()
            + "Api/Photo/PhotoDeletion";

    // 云端照片查询UrL
    private String picQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Photo/PersonnelPhotoQuery";

    /**
     * @Description: 注册人员照片到云端 base64
     * @param: @param guid 人员guid
     * @param: @param img  图片base64字符串
     * @param: @param type 图片类型  1.：普通 RGB 照片2：红外照片， 特定设备型号使用； 
     * @param: @param useUFaceCloud
     * @param: @param validLevel
     * @param: @return      
     * @return: String      
     * @throws
     */
    private String picRegisterToCloud(String guid, String img, String type,
            String useUFaceCloud, String validLevel) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        req.put("img", img.substring(img.indexOf("base64") + 1, img.length()));
        System.out.println(
            "::" + img.substring(img.indexOf("base64") + 1, img.length()));
        req.put("type", type);
        if (StringUtils.isNotBlank(useUFaceCloud)) {
            req.put("useUFaceCloud", useUFaceCloud);
        }
        if (StringUtils.isNotBlank(validLevel)) {
            req.put("validLevel", validLevel);
        }
        String doRequest = HttpRequest.doRequest(picAddUrl, "POST", req);
        return doRequest;
    }

    @Test
    public void test1() {
        // String t = "abcdef";
        //
        // System.out.println(t.indexOf("b"));
        // System.out.println(t.substring(t.indexOf("b") + 1, t.length()));

    }
}
