package com.cdkj.gchf.humanfaces;

import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.humanfaces.res.ResultMsg;
import com.google.gson.Gson;
import jnr.ffi.Struct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author old3
 */
public class AppConfig {

    private static String appid = "09387D3AA2CE4F9C8F170852B70ADFD7";

    private static String AppKey = "033A3C067BCF418A9C3CAFBE88744A0C";

    private static String AppSecret = "2434323F6E794A8D8C5C633ECB39D8F1";

    private static String timestamp = "2019-04-13 09:34:04";

    private static String baseUrl = "https://smz.lesun-idea.com";

    protected static Gson gson = new Gson();

    public static AppConfig getProjectConfig() {
        return new AppConfig();
    }

    public static String getConfigBaseUrl() {
        return baseUrl;
    }

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        AppConfig.appid = appid;
    }

    public static String getAppKey() {
        return AppKey;
    }

    public static void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public static String getAppSecret() {
        return AppSecret;
    }

    public static void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        AppConfig.baseUrl = baseUrl;
    }

    public static String getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(String timestamp) {
        AppConfig.timestamp = timestamp;
    }

    public static String getToken() {
        String url = AppConfig.getBaseUrl() + "/Api/Device/Authentication";
        Map<String, String> reqParameter = new HashMap<>();
        reqParameter.put("appid", AppConfig.getAppid());
        reqParameter.put("appKey", AppConfig.getAppKey());
        Date time = DateUtil.strToDate(AppConfig.getTimestamp(),
                DateUtil.DATA_TIME_PATTERN_1);
        reqParameter.put("timestamp", String.valueOf(time.getTime()));
        String md5 = MD5Util.md5(AppConfig.getAppKey()
                + time.getTime() + AppConfig.getAppSecret());
        reqParameter.put("sign", md5.toLowerCase());
        String json = HttpRequest.doRequest(url, "POST", reqParameter);
        ResultMsg fromJson = new Gson().fromJson(json, ResultMsg.class);
        return fromJson.getData();
    }

    public void test2() {

        java.util.Properties properties = new java.util.Properties();

        try {
            FileInputStream is = new FileInputStream(
                    File.separator + "token.properties");
            properties.load(is);
            is.close();
            System.out.println(properties.getProperty("token"));
        } catch (IOException e) {
            System.out.println("路径错误");
            e.printStackTrace();
        }
    }

    //        @Test
    public void test() {
    }


}
