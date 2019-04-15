package com.cdkj.gchf.humanfaces;

public class AppConfig {

    private static String appId = "09387D3AA2CE4F9C8F170852B70ADFD7";

    private static String AppKey = "033A3C067BCF418A9C3CAFBE88744A0C";

    private static String AppSecret = "2434323F6E794A8D8C5C633ECB39D8F1";

    private static String baseUrl = "https://smz.lesun-idea.com/Api";

    public static AppConfig getProjectConfig() {
        AppConfig appConfig = new AppConfig();
        return appConfig;
    }

    public static String getConfigBaseUrl() {
        return baseUrl;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        AppConfig.appId = appId;
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

}
