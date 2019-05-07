package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.humanfaces.res.DeviceWorkerPicRes;
import com.cdkj.gchf.humanfaces.res.ResultMsg;

/**
 * 
 * @ClassName:  WorkerPicture   
 * @Description:人员照片相关
 * @author: Old3
 * @date:   2019年4月29日 下午5:20:14     
 */
@Component
public class WorkerPicture {

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
    public DeviceWorkerPicRes picRegisterToCloud(String guid, String img,
            String type, String useUFaceCloud, String validLevel) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        req.put("img", img.substring(img.indexOf(",") + 1, img.length()));

        if (StringUtils.isNotBlank(type)) {
            req.put("type", type);
        }

        if (StringUtils.isNotBlank(useUFaceCloud)) {
            req.put("useUFaceCloud", useUFaceCloud);
        }
        if (StringUtils.isNotBlank(validLevel)) {
            req.put("validLevel", validLevel);
        }
        String doRequest = HttpRequest.doRequest(WorkerUrl.picAddBase64Url,
            "POST", req);
        System.out.println(doRequest);
        DeviceWorkerPicRes fromJson = AppConfig.gson.fromJson(doRequest,
            DeviceWorkerPicRes.class);
        return fromJson;
    }

    /**
     * @Description: 注册人员照片到云端 Url
     * @param: @param guid 人员guid
     * @param: @param img  图片base64字符串
     * @param: @param type 图片类型  1.：普通 RGB 照片2：红外照片， 特定设备型号使用； 
     * @param: @param useUFaceCloud
     * @param: @param validLevel
     * @param: @return      
     * @return: String      
     * @throws
     */
    public DeviceWorkerPicRes picRegisterToCloudUrl(String guid, String img,
            String type, String useUFaceCloud, String validLevel) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        req.put("imageUrl", img);
        req.put("type", type);
        if (StringUtils.isNotBlank(useUFaceCloud)) {
            req.put("useUFaceCloud", useUFaceCloud);
        }
        if (StringUtils.isNotBlank(validLevel)) {
            req.put("validLevel", validLevel);
        }
        String doRequest = HttpRequest.doRequest(WorkerUrl.picAddUrl, "POST",
            req);
        DeviceWorkerPicRes fromJson = AppConfig.gson.fromJson(doRequest,
            DeviceWorkerPicRes.class);
        return fromJson;
    }

    /**
     * @Description: 查询人员照片
     * @param: @param guid 人员guid
     * @param: @param img  图片base64字符串
     * @param: @param type 图片类型  1.：普通 RGB 照片2：红外照片， 特定设备型号使用； 
     * @param: @param useUFaceCloud
     * @param: @param validLevel
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String picQueryCloud(String guid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        String doRequest = HttpRequest.doRequest(WorkerUrl.picQueryUrl, "GET",
            req);
        return doRequest;
    }

    /**
     * @Description: 删除云端照片信息
     * @param: @param picGuid 图片guid
     * @param: @param workerGuid 人员guid
     */
    public ResultMsg picDelCloud(String picGuid, String workerGuid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", picGuid);
        req.put("personGuid", workerGuid);
        String doRequest = HttpRequest.doRequest(WorkerUrl.picDelUrl, "POST",
            req);
        ResultMsg fromJson = AppConfig.gson.fromJson(doRequest,
            ResultMsg.class);
        return fromJson;
    }

    @Test
    public void test1() {
        // String t = "abcdef";
        // 老三：5D2A1DBBAB6D4330AA7EA8336FF913E9
        // System.out.println(t.indexOf("b"));
        // System.out.println(t.substring(t.indexOf("b") + 1, t.length()));
        // String picRegisterToCloud = picRegisterToCloud(
        // "5D2A1DBBAB6D4330AA7EA8336FF913E9","" ,"", "",
        // "");07CAADCFED8E4916ADDEA048EEF71A99
        // System.out.println(picRegisterToCloud);5EE70015C96848FA8E9C4023B30F0464
        // ResultMsg picDelCloud =
        // picDelCloud("07CAADCFED8E4916ADDEA048EEF71A99",
        // "5EE70015C96848FA8E9C4023B30F0464");
        // System.out.println(picDelCloud.toString());
    }
}
