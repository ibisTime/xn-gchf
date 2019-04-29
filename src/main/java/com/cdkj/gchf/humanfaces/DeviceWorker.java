package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * @Description:设备相关人员
 * @author: Old3
 * @date:   2019年4月29日 下午4:20:32     
 */
public class DeviceWorker {
    // 人员录入设备Url
    public String workerAddUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelEntry";

    // 人员删除Url
    public String workerDelUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelDeletion";

    // 人员查询Url
    public String workerQueryUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/StaffInquiry";

    // 人员更新Url
    public String workerUpdateUrl = AppConfig.getBaseUrl()
            + "/Api/Personnel/PersonnelUpdate";

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
    public String cloudWorkerAdd(String deviceKey, String name, String icNo,
            String phone, String tag, String type) {
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
        return request;
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
        String request = HttpRequest.doRequest(workerDelUrl, "POST", req);
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
     * @Description: 人员设备授权接口  没写完
     */
    public String cloudWorkerAuthorizationEuipMent() {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);

        return null;
    }

    @Test
    public void test22() {
        // 人员录入
        // String device = cloudWorkerAdd("84E0F420576700B0", "李四", "002532123",
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
