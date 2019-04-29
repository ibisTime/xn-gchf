package com.cdkj.gchf.humanfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.MD5Util;

public class HttpRequest {
    private static InputStream ins = null;

    private static OutputStream ous = null;

    private static String res = "";

    private static InputStreamReader inputStreamReader = null;

    private static BufferedReader bufferedReader = null;

    private static HttpURLConnection httpURLConnection = null;

    public static String doRequest(String url, String method,
            Map<String, String> req) {
        URL localURL = null;
        try {
            if (method.equals("GET")) {
                url += "?";
                for (Entry<String, String> entry : req.entrySet()) {
                    url += entry.getKey() + "=" + entry.getValue() + "&";
                }
                url.substring(0, url.length() - 1);
                System.out.println(url);
            }
            localURL = new URL(url);
            URLConnection connection = (URLConnection) localURL
                .openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            StringBuffer sb = new StringBuffer();
            if (method.equals("POST")) {
                httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);

                Set<Entry<String, String>> entrySet = req.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                httpURLConnection.connect();
                ous = httpURLConnection.getOutputStream();
                ous.write(
                    sb.substring(0, sb.length() - 1).toString().getBytes());
                System.out.println(
                    "入参:" + sb.substring(0, sb.length() - 1).toString());
                ous.flush();
            }
            ins = httpURLConnection.getInputStream();
            String resString = null;
            inputStreamReader = new InputStreamReader(ins, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((resString = bufferedReader.readLine()) != null) {
                res += resString;
            }
            // JSONObject resJson = JSONObject.parseObject(res);
            // if (resJson.containsKey("code")) {
            // String code = (String) resJson.get("code");
            // System.out.println(code);
            // }
            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (ins != null) {
                    ins.close();
                }
                if (ous != null) {
                    ous.close();
                }
                res = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void test1() {

        String url = AppConfig.getBaseUrl() + "/Api/Device/Authentication";
        Map<String, String> reqParameter = new HashMap<>();
        reqParameter.put("appid", AppConfig.getAppid());
        reqParameter.put("appKey", AppConfig.getAppKey());
        Date time = DateUtil.strToDate(AppConfig.getTimestamp(),
            DateUtil.DATA_TIME_PATTERN_1);
        reqParameter.put("timestamp", String.valueOf(time.getTime()));
        String md5 = MD5Util.md5(AppConfig.getAppKey()
                + String.valueOf(time.getTime()) + AppConfig.getAppSecret());
        reqParameter.put("sign", md5.toLowerCase());
        String doRequest = doRequest(url, "POST", reqParameter);
        System.out.println(doRequest);

    }

}
