package com.cdkj.gchf.gov;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.PropertiesUtil;
import com.cdkj.gchf.enums.EGovErrorMessage;
import com.cdkj.gchf.exception.BizException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GovConnecter {

    public static final String GOV_URL = PropertiesUtil.Config.GOV_URL;

    public static String DEFAULT_VERSION = "1.0";

    public static String APPID = "33112220190310002";

    public static String APPSECRET = "24484b262dd63dd584902a266bdbdca0";

    // public static String TESTCORPSECRET = "3e43e64832ea4d298c277e52a96e407e";

    static final Logger logger = LoggerFactory.getLogger(GovConnecter.class);

    public static String getGovData(String method, String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String sortString = "appid=" + APPID;
        String guid = UUID.randomUUID().toString().replace("-", "");
        String timestamp = sdf.format(new Date());

        sortString += "&data=" + data;
        sortString += "&format=json";
        sortString += "&method=" + method;
        sortString += "&nonce=" + guid;
        sortString += "&timestamp=" + timestamp;
        sortString += "&version=" + DEFAULT_VERSION;
        sortString += "&appsecret=" + APPSECRET;

        String sign = EncriptionHelper
            .getSHA256StrJava(sortString.toLowerCase());

        BaseRequest request = new BaseRequest() {
        };
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("appid", APPID);
        dataMap.put("data", data);

        dataMap.put("format", "json");
        dataMap.put("method", method);
        dataMap.put("nonce", guid);
        dataMap.put("timestamp", timestamp);
        dataMap.put("version", DEFAULT_VERSION);
        dataMap.put("sign", sign);
        try {
            String res = request.postData(GOV_URL, null, dataMap);
            dataMap.put("appsecret", APPSECRET);

//            logger.info("调用国家平台结果为：{}", res);

            JSONObject resJson = JSONObject.parseObject(res);
            if (0 != Integer.parseInt(resJson.getString("code"))) {
                throw new BizException("XN000000",
                    resJson.getString("message"));
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public static String getGovData(String method, String data, String appId,
            String appSecert) {

        String res = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String sortString = "appid=" + appId;
        String guid = UUID.randomUUID().toString().replace("-", "");
        String timestamp = sdf.format(new Date());

        sortString += "&data=" + data;
        sortString += "&format=json";
        sortString += "&method=" + method;
        sortString += "&nonce=" + guid;
        sortString += "&timestamp=" + timestamp;
        sortString += "&version=" + DEFAULT_VERSION;

        sortString += "&appsecret=" + appSecert;

        String sign = EncriptionHelper
            .getSHA256StrJava(sortString.toLowerCase());

        BaseRequest request = new BaseRequest() {
        };
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("appid", appId);
        // sortString += "&corpsign="
        // + AesUtils.encrypt(TESTCORPSECRET + timestamp, appSecert);
        dataMap.put("data", data);

        dataMap.put("format", "json");
        dataMap.put("method", method);
        dataMap.put("nonce", guid);
        dataMap.put("timestamp", timestamp);
        dataMap.put("version", DEFAULT_VERSION);
        dataMap.put("sign", sign);
        try {

            res = request.postData(GOV_URL, null, dataMap);
            dataMap.put("appsecret", appSecert);

//            logger.info("调用国家平台结果为：{}", res);

            JSONObject resJson = JSONObject.parseObject(res);
            if (0 != Integer.parseInt(resJson.getString("code"))) {
                String rawMessage = resJson.getString("message");
                String localMessageValue = EGovErrorMessage
                    .getLocalMessageValue(rawMessage);
                if (localMessageValue != null) {
                    throw new BizException("XN000000", localMessageValue);
                }
                throw new BizException("XN000000",
                    resJson.getString("message"));
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return res;
    }

}
