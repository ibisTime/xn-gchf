package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.humanfaces.res.DeviceQuery;
import com.cdkj.gchf.humanfaces.res.DeviceRes;
import com.cdkj.gchf.humanfaces.res.ResultMsg;
import com.google.gson.Gson;

/**
 * 
 * @ClassName:  Device   
 * @Description:人脸设备相关
 * @author: Old3
 * @date:   2019年4月29日 下午12:56:00     
 * @Copyright:
 */
@Component
public class Device {
    // 添加设备Url
    private String createUrl = AppConfig.getBaseUrl()
            + "/Api/Device/DeviceCreation";

    // 更新设备信息Url
    private String updateUrl = AppConfig.getBaseUrl()
            + "/Api/Device/EquipmentUpdate";

    // 查询设备信息Url
    private String queryUrl = AppConfig.getBaseUrl()
            + "/Api/Device/DeviceQuery";

    // 同步设备信息Url
    private String updateCloudUrl = AppConfig.getBaseUrl()
            + "/Api/Device/Equipment";

    // 删除设备信息
    private String delDeviceUrl = AppConfig.getBaseUrl()
            + "/Api/Device/DeviceDeletion";

    // 设备销权人员接口
    private String trancateUrl = AppConfig.getBaseUrl()
            + "/Api/Device/EquipmentEP";

    /**
     * @Description: 设备创建、添加设备信息到云端
     * @param: @return      
     * @return: DeviceRes      
     */
    public DeviceRes deviceCreation(String deviceKey, String name, String tag) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        req.put("name", deviceKey);
        if (StringUtils.isNotBlank(tag)) {
            req.put("tag", tag);
        }

        String doRequest = HttpRequest.doRequest(createUrl, "POST", req);
        DeviceRes fromJson = new Gson().fromJson(doRequest, DeviceRes.class);
        return fromJson;
    }

    /**
     * 
     * @Description: 更新设备
     * @param: @param deviceKey 设备序列号
     * @param: @param name 设备名
     * @param: @param tag 设备标签
     * @param: @return      
     * @return: DeviceRes      
     * @throws
     */
    public DeviceRes EquipmentUpdate(String deviceKey, String name,
            String tag) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("name", name);
        if (StringUtils.isNotBlank(tag)) {
            req.put("tag", tag);
        }
        req.put("deviceKey", deviceKey);
        String doRequest = HttpRequest.doRequest(updateUrl, "POST", req);
        DeviceRes fromJson = new Gson().fromJson(doRequest, DeviceRes.class);
        return fromJson;
    }

    /**
     * 
     * @Description: 查询设备信息
     * @param: @param deviceKey 设备序列号
     * @param: @return      
     * @return: DeviceRes      
     * @throws
     */
    public DeviceQuery deviceQuery(String deviceKey) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        String doRequest = HttpRequest.doRequest(queryUrl, "GET", req);
        DeviceQuery fromJson = AppConfig.gson.fromJson(doRequest,
            DeviceQuery.class);
        return fromJson;

    }

    /**
     * 
     * @Description: 同步设备到云端：保持设备端数据与云端数据的一致。
     * @param: @param deviceKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public ResultMsg updateCloudDevice(String deviceKey) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        String doRequest = HttpRequest.doRequest(updateCloudUrl, "POST", req);
        ResultMsg fromJson = AppConfig.gson.fromJson(doRequest,
            ResultMsg.class);
        return fromJson;
    }

    /**
     * 
     * @Description: 从云端删除设备
     * @param: @param deviceKey
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String delCloudDevice(String deviceKey) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        String doRequest = HttpRequest.doRequest(delDeviceUrl, "POST", req);
        return doRequest;
    }

    /**
     * 
     * @Description: 清空某台机器授权人员 若人员guid为空 则清空所有授权人员
     * @param: @param deviceKey 设备序列号
     * @param: @param personGuid 人员guid
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String truncateDevice(String deviceKey, String personGuid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        if (StringUtils.isNotEmpty(personGuid)) {
            req.put("deviceKey", deviceKey);
        }
        String doRequest = HttpRequest.doRequest(trancateUrl, "POST", req);
        return doRequest;
    };

    @Test
    public void test1() {
        // 添加设备
        // DeviceRes deviceCreation = deviceCreation("84E0F420576700B0", "test",
        // null);
        // System.out.println(deviceCreation);

        // 修改设备
        DeviceRes equipmentUpdate = EquipmentUpdate("84E0F420576700B0",
            "!@#$%%^&**()", null);
        System.out.println(equipmentUpdate.toString());

        // 查询设备
        DeviceQuery deviceQuery = deviceQuery("84E0F420576700B0");
        System.out.println(deviceQuery.toString());

        // String updateCloudDevice = updateCloudDevice("84E0F420576700B0");
        // System.out.println(updateCloudDevice);

        // String updateCloudDevice = updateCloudDevice("84E0F420576700B0");
        // System.out.println(updateCloudDevice);
        // GovUtil.queryAsyncHandleResult("projectworker-add-2019050611-4-0001",
        // "23108420190520001", "8e2f8935685a0d58564df09ea5e4f102");
    }

}
