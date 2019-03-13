package com.cdkj.gchf.gov;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.common.PropertiesUtil;
import com.cdkj.gchf.exception.BizException;

public class GovConnecter {

    public static final String GOV_URL = PropertiesUtil.Config.GOV_TEST_URL;

    public static String DEFAULT_VERSION = "1.0";

    public static String APPID = "33112220190310002";

    public static String APPSECRET = "24484b262dd63dd584902a266bdbdca0";

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

            System.out.println("****res****" + res);

            JSONObject resJson = JSONObject.parseObject(res);
            if (0 != Integer.parseInt(resJson.getString("code"))) {
                throw new BizException("XN000000",
                    resJson.getString("message"));
            }

        } catch (Exception ex) {
            String errorMsg = "";
            if (ex.getMessage().contains("ClientProtocolException")) {
                errorMsg = "ClientProtocolException";
            } else if (ex.getMessage().contains("UnknownHostException")
                    || ex.getMessage().contains("unreachable")) {
                errorMsg = "UnknownHostException";
            } else if (ex instanceof BizException) {
                throw ex;
            }
            System.out.println(errorMsg);
        }
        return null;
    }

    private static String acceptmultiLineChars2(Scanner sc) {
        ArrayList<String> ns = new ArrayList<>();
        do {
            String str = sc.nextLine();
            if (str.equals("")) {
                break;
            }
            ns.add(str);
        } while (true);
        StringBuilder sb = new StringBuilder();
        if (ns.size() > 0) {
            for (String nString : ns) {
                sb.append(nString + "\r\n");
            }
        }
        return sb.toString();
    }
}
