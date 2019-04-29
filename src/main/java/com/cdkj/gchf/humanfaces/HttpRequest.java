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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.cdkj.gchf.common.MD5Util;

public class HttpRequest {
    private static InputStream ins = null;

    private static OutputStream ous = null;

    private String res = "";

    private InputStreamReader inputStreamReader = null;

    private BufferedReader bufferedReader = null;

    private HttpURLConnection httpURLConnection = null;

    public String doRequest(String url, String method,
            Map<String, String> req) {
        URL localURL = null;
        try {
            localURL = new URL(url);
            URLConnection connection = (URLConnection) localURL
                .openConnection();
            httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod(method);
            // 添加参数
            if (null != req) {
                Set<Entry<String, String>> entrySet = req.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    httpURLConnection.addRequestProperty(entry.getKey(),
                        entry.getValue());
                }
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            ins = httpURLConnection.getInputStream();
            String resString = null;
            inputStreamReader = new InputStreamReader(ins, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((resString = bufferedReader.readLine()) != null) {
                res += resString;
            }
            System.out.println("res:" + res);

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void test1() {

        String url = AppConfig.getBaseUrl() + "/Api/Device/Authentication";
        System.out.println(url);
        Map<String, String> reqParameter = new HashMap<>();
        reqParameter.put("appId", AppConfig.getAppId());
        reqParameter.put("appKey", AppConfig.getAppKey());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        reqParameter.put("timestamp", String.valueOf(timestamp.getTime()));
        String md5 = MD5Util
            .md5(AppConfig.getAppKey() + String.valueOf(timestamp.getTime())
                    + AppConfig.getAppSecret());
        reqParameter.put("sign", md5.toLowerCase());
        System.out.println("md5:" + md5.toLowerCase());
        String doRequest = doRequest(url, "POST", reqParameter);
        System.out.println(doRequest);

    }

}
