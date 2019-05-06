package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.humanfaces.res.DeviceWorkerRes;
import com.cdkj.gchf.humanfaces.res.ResultMsg;

/**
 * 
 * @Description:设备相关人员
 * @author: Old3
 * @date:   2019年4月29日 下午4:20:32     
 */
@Component
public class DeviceWorker {
    // 人员录入设备Url
    private String workerAddUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelEntry";

    // 人员删除Url
    private String workerDelUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelDeletion";

    // 人员查询Url
    private String workerQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffInquiry";

    // 人员更新Url
    private String workerUpdateUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelUpdate";

    // 人员授权
    private String authorizationUrl = AppConfig.getBaseUrl()
            + "/Api/Device/EquipmentAP";

    // 人员搜索
    private String workerSearchUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffSearch";

    /**
     * 
     * @Description: 人员录入到云端
     * @param: @param name 员工名称
     * @param: @param icNo ic卡 /身份证 (身份证处若使用其他编号标记，限制 32 个字符 )
     * @param: @param phone 手机号
     * @param: @param tag 人员标签 (可自定义)
     * @param: @param type 人员类型 可自定义 0无意义
     * @throws
     */
    public DeviceWorkerRes cloudWorkerAdd(String deviceKey, String name,
            String icNo, String phone, String tag, String type) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("name", name);
        req.put("idNo", icNo);
        req.put("phone", phone);
        req.put("tag", tag);
        req.put("type", type);
        String request = HttpRequest.doRequest(workerAddUrl, "POST", req);
        DeviceWorkerRes fromJson = AppConfig.gson.fromJson(request,
            DeviceWorkerRes.class);

        return fromJson;
    }

    /**
     * 
     * @Description: 删除云端人员
     * @param: @param guid 人员guid
     */
    public String cloudWorkerDel(String guid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        String request = HttpRequest.doRequest(workerDelUrl, "POST", req);
        return request;
    }

    /**
     * @Description: 修改云端人员
        * @param: @param name 员工名称
     * @param: @param icNo ic卡 /身份证 (身份证处若使用其他编号标记，限制 32 个字符 )
     * @param: @param phone 手机号
     * @param: @param tag 人员标签 (可自定义)
     * @param: @param type 人员类型 可自定义 0无意义
     */
    public String cloudWorkerUpdate(String guid, String name, String idNo,
            String phone, String tag, String type) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        req.put("name", name);
        req.put("idNo", idNo);
        req.put("phone", phone);
        req.put("tag", tag);
        req.put("type", type);
        String request = HttpRequest.doRequest(workerUpdateUrl, "POST", req);
        return request;
    }

    /**
     * 
     * @Description: 查询云端已保存人员
     * @param: @param guid 人员查询guid
     */
    public String cloudWorkerQuery(String guid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        String request = HttpRequest.doRequest(workerQueryUrl, "GET", req);
        return request;

    }

    /**
     * @Description: 人员设备授权接口  
     */
    public ResultMsg cloudWorkerAuthorizationEuipment(String deviceKey,
            List<String> personGuids, String startTime, String endTime) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        String person = "";
        for (String string : personGuids) {
            person += string + ",";
        }
        req.put("personGuids", person.substring(0, person.length() - 1));
        if (StringUtils.isNotBlank(startTime)
                && StringUtils.isNotBlank(endTime)) {
            req.put("passTimes", startTime + "," + endTime);
        }
        String response = HttpRequest.doRequest(authorizationUrl, "POST", req);
        ResultMsg resultMsg = AppConfig.gson.fromJson(response,
            ResultMsg.class);
        return resultMsg;
    }

    /**
     * @Description: 搜索云端人员 id为空默认查询、全部
     */
    public String workerSearch(String name, String tag, String idNo,
            String phone, String startTime, String endTime, String index,
            String length, String type, String guid, String userGuid,
            String orderFieldKey, String orderTypeKey) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        String doRequest = HttpRequest.doRequest(workerSearchUrl, "POST", req);
        System.out.println(doRequest);
        return doRequest;
    }
    @Test
    public void test22() {
        // 人员录入
        // String device = cloudWorkerAdd("84E0F420576700B0", "老三", "002532123",
        // "18967632284", "", "");
        // System.out.println(device);

        // 人员修改
        // String workerUpdate = cloudWorkerUpdate(
        // "E73D7C4277A540018D6E90AA6595A182", "yikaluosi", "", "18967632284",
        // "2", "2");
        // System.out.println(workerUpdate);

        // E73D7C4277A540018D6E90AA6595A182

        // 人员查询
        // String cloudWorkerQuery = cloudWorkerQuery(
        // "70C34989189A47C3BCA4B68F412B7360");
        // System.out.println(cloudWorkerQuery);

        // 人员删除
        // 70C34989189A47C3BCA4B68F412B7360 guid
        // String cloudWorkerDel = cloudWorkerDel(
        // "70C34989189A47C3BCA4B68F412B7360");
        // System.out.println(cloudWorkerDel);

    }
}
