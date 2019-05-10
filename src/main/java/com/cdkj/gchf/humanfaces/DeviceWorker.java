package com.cdkj.gchf.humanfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public DeviceWorkerRes cloudWorkerAdd(String name, String icNo,
            String phone, String tag, String type) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("name", name);
        req.put("idNo", icNo);
        req.put("phone", phone);
        if (StringUtils.isNotBlank(tag)) {
            req.put("tag", tag);
        }
        if (StringUtils.isNotBlank(type)) {
            req.put("type", type);
        }
        String request = HttpRequest.doRequest(DeviceWorkUrl.workerAddUrl,
            "POST", req);
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
        String request = HttpRequest.doRequest(DeviceWorkUrl.workerDelUrl,
            "POST", req);
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
        String request = HttpRequest.doRequest(DeviceWorkUrl.workerUpdateUrl,
            "POST", req);
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
        String request = HttpRequest.doRequest(DeviceWorkUrl.workerQueryUrl,
            "GET", req);
        return request;

    }

    /**
     * @Description: 人员设备授权接口  
     */
    public ResultMsg cloudWorkerAuthorizationEuipment(String deviceKey,
            List<String> personGuids, String startTime, String endTime) {
        ResultMsg resultMsg = null;
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        String person = "";
        for (String string : personGuids) {
            person += string + ",";
        }
        if (CollectionUtils.isEmpty(personGuids)) {
            return null;
        } else {
            req.put("personGuids", person.substring(0, person.length() - 1));
        }

        if (StringUtils.isNotBlank(startTime)
                && StringUtils.isNotBlank(endTime)) {
            req.put("passTimes", startTime + "," + endTime);
        }
        String response = HttpRequest.doRequest(DeviceWorkUrl.authorizationUrl,
            "POST", req);
        resultMsg = AppConfig.gson.fromJson(response, ResultMsg.class);
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
        // req.put("length", "77");
        String doRequest = HttpRequest.doRequest(DeviceWorkUrl.workerSearchUrl,
            "POST", req);
        System.out.println(doRequest);
        return doRequest;
    }

    /**
     * 
     * @Description: 人员授权查询
     * @param: guid 人员guid
     * @return      
     * @return: String      
     */
    public String workerAuthorizationQuery(String guid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        String doRequest = HttpRequest
            .doRequest(DeviceWorkUrl.authorizationQueryUrl, "GET", req);
        System.out.println(doRequest);
        return doRequest;
    }

    /**
     * 
     * @Description: 清空设备人员
     * @param: @param deviceKey
     * @param: @param personGuid
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String workerClear(String deviceKey, String personGuid) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("deviceKey", deviceKey);
        if (StringUtils.isNotBlank(personGuid)) {
            req.put("personGuid", personGuid);
        }
        String doRequest = HttpRequest.doRequest(DeviceWorkUrl.ClearWorkersUrl,
            "POST", req);
        return doRequest;
    }

    /**
     * 
     * @Description: 取消人员授权  取消人员授权  全部设备或某台设备
     * @param: @return      
     * @return: String      
     * @throws
     */
    public String workerBatchElimination(String guid, String deviceKey) {
        String token = AppConfig.getToken();
        Map<String, String> req = new HashMap<>();
        req.put("appid", AppConfig.getAppid());
        req.put("token", token);
        req.put("guid", guid);
        if (StringUtils.isNotBlank(deviceKey)) {
            req.put("deviceKeys", deviceKey);
        }
        String doRequest = HttpRequest.doRequest(
            DeviceWorkUrl.BatchEliminationOfPersonnelEquipmentUrl, "POST", req);
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
        // "A7FE8C63E3094E1281E6738C34DD1C65");
        // JSONObject parse = JSONObject.parseObject(cloudWorkerQuery);
        // System.out.println(parse.getString("code")
        // .equals(EEquipmentWorkerResponse.CHAXUNCHENGGONG.getCode())
        // && parse.get("data") == null);

        // 人员删除
        // String cloudWorkerDel = cloudWorkerDel(
        // "2E81A9B755804030BF0744CD8359B083");
        // String cloudWorkerDel2 = cloudWorkerDel(
        // 人员搜索 删除已上传人员
        // workerBatchElimination("8A436291DC414307ADFD5B213063D706",
        // "84E0F420576700B0");
        String workerSearch = workerSearch(null, null, null, null, null, null,
            null, null, null, null, null, null, null);
        System.out.println(workerSearch);
        // 删除云端所有人员
        JSONObject jsonObject = JSONObject.parseObject(workerSearch);
        JSONObject js = (JSONObject) jsonObject.get("data");
        JSONArray ja = (JSONArray) js.get("content");
        System.out.println(ja.size());
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jp = (JSONObject) ja.get(i);
            String guid = jp.getString("guid");
            System.out.println("guid" + guid);
            String cloudWorkerDel = cloudWorkerDel(guid);
            System.out.println("res" + cloudWorkerDel);
        }

        // cloudWorkerDel("7ACE02006D934D0296325B3E3DFEF02D");
        // 清空设备人员
        //
        // workerClear("84E0F420576700B0", null);
        // 人员授权查询
        // String workerAuthorizationQuery = workerAuthorizationQuery(
        // "C83BFC0DB364433B97FB0BA7254C20EA");
        // workerAuthorizationQuery("422585696F4E405A854D92EAE17174A9");
        // 人员授权销毁

    }
}
